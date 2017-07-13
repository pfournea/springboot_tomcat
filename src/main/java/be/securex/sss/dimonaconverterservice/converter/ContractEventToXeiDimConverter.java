package be.securex.sss.dimonaconverterservice.converter;

import be.securex.sss.dimonaconverterservice.enums.*;
import be.securex.sss.dimonaconverterservice.xsd.dimonarequest.*;
import be.securex.sss.dimonaconverterservice.xsd.dimonarequest.AddressType;
import be.securex.sss.dimonaconverterservice.xsd.dimonarequest.ContractType;
import be.securex.sss.dimonaconverterservice.xsd.dimonarequest.DimonaDeclarationType;
import be.securex.sss.dimonaconverterservice.xsd.v1.contractevent.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 8565 on 22/10/2015.
 */

@Component
public class ContractEventToXeiDimConverter {

    public XeiDimList convert(ContractEvent contractEvent) throws JAXBException {
        XeiDimList xeiDimList = new XeiDimList();

        List<PersonType> personTypes = new ArrayList<>();
        if (contractEvent.getNewContract() != null) {
            personTypes.add(convertNewContract(contractEvent));
        } else if (contractEvent.getUpdateContract() != null) {
            UpdateConversionContext context = UpdateConversionContext.create(contractEvent.getUpdateContract());
            personTypes.addAll(convertUpdateContract(contractEvent, context));
        } else if (contractEvent.getCancelContract() != null) {
            personTypes.add(convertCancelContract(contractEvent));
        }

        for (PersonType personType : personTypes) {
            XeiDim xeiDim = new XeiDim();
            xeiDim.setPerson(personType);
            xeiDimList.add(xeiDim);
        }

        return xeiDimList;
    }

    private PersonType convertNewContract(ContractEvent contractEvent) {
        PersonType person = getPersonType(contractEvent.getNewContract().getEmployeeInfo());
        ContractType contract = getContractType(contractEvent.getNewContract().getContractInfo());
        contract.setContractuuid(contractEvent.getNewContract().getContractUuid());
        contract.getDimonaDeclaration().setDeclarationType(Integer.parseInt(DeclarationType.INDIENST.getCode()));
        person.setContract(contract);
        return person;
    }

    private List<PersonType> convertUpdateContract(ContractEvent contractEvent, UpdateConversionContext context) {
        List<PersonType> personTypes = new ArrayList<>();
        for (DeclarationType declarationType : context.getDeclarationTypes()) {
            PersonType person = getPersonType(contractEvent.getUpdateContract().getEmployeeInfo());
            ContractType contract = getContractType(contractEvent.getUpdateContract().getContractInfo());
            contract.setAcNr(contractEvent.getUpdateContract().getContractIdentifier().getContractId());
            contract.setContractuuid(contractEvent.getUpdateContract().getContractIdentifier().getContractUuid());
            contract.getDimonaDeclaration().setDeclarationType(Integer.parseInt(declarationType.getCode()));
            ContractType originalDummyContract = new ContractType();
            copyContractFields(contractEvent.getUpdateContract().getOriginalContractInfo(), originalDummyContract);
            contract.getDimonaDeclaration().setDateEffStartOrig(originalDummyContract.getDateEffStart());
            contract.getDimonaDeclaration().setContractNatureOrig(originalDummyContract.getContractNature());
            contract.getDimonaDeclaration().setDateBreachOrig(originalDummyContract.getDateBreach());
            contract.getDimonaDeclaration().setDateForeseenEndOrig(originalDummyContract.getDateForeseenEnd());
            person.setContract(contract);
            personTypes.add(person);
        }
        return personTypes;
    }

    private PersonType convertCancelContract(ContractEvent contractEvent) {
        PersonType personType = getPersonType(contractEvent.getCancelContract().getEmployeeInfo());
        ContractType contract = getContractType(contractEvent.getCancelContract().getContractInfo());
        contract.setAcNr(contractEvent.getCancelContract().getContractIdentifier().getContractId());
        contract.setContractuuid(contractEvent.getCancelContract().getContractIdentifier().getContractUuid());
        contract.getDimonaDeclaration().setDeclarationType(Integer.parseInt(DeclarationType.ANNULATIE.getCode()));
        ContractType originalDummyContract = new ContractType();
        copyContractFields(contractEvent.getCancelContract().getOriginalContractInfo(), originalDummyContract);
        contract.getDimonaDeclaration().setDateEffStartOrig(originalDummyContract.getDateEffStart());
        contract.getDimonaDeclaration().setContractNatureOrig(originalDummyContract.getContractNature());
        personType.setContract(contract);
        return personType;
    }

    private PersonType getPersonType(EmployeeInfoType employeeInfo) {
        PersonType person = new PersonType();
        person.setFirstName(employeeInfo.getFirstName());
        person.setLastName(employeeInfo.getLastName());
        String fullName = StringUtils.defaultString(employeeInfo.getLastName())
                + " "
                + StringUtils.defaultString(employeeInfo.getFirstName());
        person.setFullName(fullName.trim());
        if (employeeInfo.getGender() instanceof String) {
            person.setGender(employeeInfo.getGender().equals("MALE") ? "1" : "2");
        }
        person.setNationality(employeeInfo.getNationality());
        person.setBirthDate(ConverterUtils.toDdmmyyyyString(employeeInfo.getBirthDate()));
        person.setBirthCountry(employeeInfo.getBirthCountry());
        person.setBirthPlace(employeeInfo.getBirthPlace());
        person.setNumNatReg(employeeInfo.getRegisterNumber());
        if (employeeInfo instanceof CreationEmployeeInfoType) {
            person.setAddress(getAddressType(((CreationEmployeeInfoType) employeeInfo).getAddress()));
        } else {
            person.setAddress(getAddressType(((ModificationEmployeeInfoType) employeeInfo).getAddress()));
        }
        return person;
    }

    private ContractType getContractType(ContractInfoType contractInfo) {
        ContractType contract = new ContractType();
        contract.setPayGroup(getPayGroup(contractInfo));
        contract.setDimonaDeclaration(new DimonaDeclarationType());
        contract.setContractDimona(null); //TODO niet nodig bij creatie??
        contract.setParCom(contractInfo.getParitairComite());
        contract.setEmployeeStatute(convertEmployeeStatute(contractInfo.getEmployeeStatute()));
        copyContractFields(contractInfo, contract);
        return contract;
    }

    private AddressType getAddressType(be.securex.sss.dimonaconverterservice.xsd.v1.contractevent.CreationAddressType creationAddressType) {
        AddressType xeiDimAddress = null;
        if (creationAddressType != null) {
            xeiDimAddress = new AddressType();
            xeiDimAddress.setStreetFull(creationAddressType.getStreet() + " " + creationAddressType.getHouseNumber());
            xeiDimAddress.setBoxNr(creationAddressType.getBoxNumber());
            xeiDimAddress.setZip(creationAddressType.getZip());
            xeiDimAddress.setComplement(creationAddressType.getComplement());
            xeiDimAddress.setLocality(creationAddressType.getCity());
            xeiDimAddress.setCountry(creationAddressType.getCountryCode());
        }
        return xeiDimAddress;
    }

    private AddressType getAddressType(be.securex.sss.dimonaconverterservice.xsd.v1.contractevent.ModificationAddressType modificationAddressType) {
        AddressType xeiDimAddress = null;
        if (modificationAddressType != null) {
            xeiDimAddress = new AddressType();
            xeiDimAddress.setStreetFull(modificationAddressType.getStreet() + " " + modificationAddressType.getHouseNumber());
            xeiDimAddress.setBoxNr(modificationAddressType.getBoxNumber());
            if (StringUtils.isNotBlank(modificationAddressType.getZip())) {
                xeiDimAddress.setZip(modificationAddressType.getZip());
            } else {
                //In XeiDim is postcode verplicht indien ook adres is ingevuld.  Bij buitenlanders is het vaak zo
                //dat postcode niet is ingevuld => spatie invullen om door de xeidim validatie te geraken.
                xeiDimAddress.setZip(" ");
            }
            xeiDimAddress.setComplement(modificationAddressType.getComplement());
            xeiDimAddress.setLocality(modificationAddressType.getCity());
            xeiDimAddress.setCountry(modificationAddressType.getCountryCode());
        }
        return xeiDimAddress;
    }

    private void copyContractFields(ContractInfoType contractInfo, ContractType contract) {
        DimonaContractVisitor visitor = new DimonaContractVisitorImpl(contract);
        List<DimonaContractVisitable> visitables = new ArrayList<>();

        visitables.add(new VisitableStudentType(contractInfo.getContract().getStudent()));
        visitables.add(new VisitableXtraType(contractInfo.getContract().getXtra()));
        visitables.add(new VisitableFlexiDayType(contractInfo.getContract().getFlexiDay()));
        visitables.add(new VisitableFlexiPeriodType(contractInfo.getContract().getFlexiPeriod()));
        visitables.add(new VisitableDefiniteTermType(contractInfo.getContract().getDefiniteTerm()));
        visitables.add(new VisitableIndefiniteTermType(contractInfo.getContract().getIndefiniteTerm()));

        for (DimonaContractVisitable visitable : visitables) {
            visitable.accept(visitor);
        }
    }

    private PayGroupType getPayGroup(ContractInfoType contractInfo) {
        PayGroupType payGroup = new PayGroupType();
        if (contractInfo.getFDCP() != null) {
            payGroup.setFirmNr(contractInfo.getFDCP().getFirmNumber());
            payGroup.setDepNr(contractInfo.getFDCP().getDepartmentNumber());
            payGroup.setCatNr(contractInfo.getFDCP().getCategory());
        }
        return payGroup;
    }

    private String convertEmployeeStatute(EmployeeStatuteType employeeStatuteType) {
        if (employeeStatuteType.value().equals("worker")) {
            return "1";
        } else if (employeeStatuteType.value().equals("employee")) {
            return "2";
        }
        return "";
    }

}
