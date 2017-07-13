package be.securex.sss.dimonaconverterservice.converter;

import be.securex.sss.dimonaconverterservice.dto.*;
import be.securex.sss.dimonaconverterservice.validator.ContractEventValidator;
import be.securex.sss.dimonaconverterservice.xsd.v1.contractevent.*;
import be.securex.sss.dimonaconverterservice.xsd.v1.contractevent.ContractType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.xml.datatype.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static be.securex.sss.dimonaconverterservice.dto.ContractType.*;
import static javax.xml.datatype.DatatypeFactory.newInstance;

/**
 * Created by 6148 on 15/10/2015.
 */
@Component
public class ContractWijzigingToContractEventConverter {
    private static final Logger logger = LoggerFactory.getLogger(ContractWijzigingToContractEventConverter.class);
    @Autowired
    @Qualifier(value = "contractEventValidator")
    ContractEventValidator validator;

    private static DatatypeFactory datatypeFactory = null;

    static {
        try {
            datatypeFactory = newInstance();
        } catch (DatatypeConfigurationException e) {
            logger.error("Error retrieving new DataTypeFactoryInstance",e);
            throw new RuntimeException(e);
        }
    }

    public ContractEvent convert(ContractCreationDto contractCreation) throws Exception {
        ContractEvent contractEvent = new ContractEvent();
        contractEvent.setMetaData(getMetaData(contractCreation));
        contractEvent.setNewContract(getNewContract(contractCreation.getContract()));
        validator.validate(contractEvent);
        return contractEvent;
    }

    public ContractEvent convert(ContractUpdateDto contractUpdate) throws Exception {
        ContractEvent contractEvent = new ContractEvent();
        contractEvent.setMetaData(getMetaData(contractUpdate));
        contractEvent.setUpdateContract(getUpdateContract(contractUpdate.getOriginalContract(), contractUpdate.getUpdatedContract()));
        validator.validate(contractEvent);
        return contractEvent;
    }

    public ContractEvent convert(ContractCancellationDto contractCancellation) throws Exception {
        ContractEvent contractEvent = new ContractEvent();
        contractEvent.setMetaData(getMetaData(contractCancellation));
        contractEvent.setCancelContract(getCancelContract(contractCancellation.getOriginalContract(), contractCancellation.getUpdatedContract()));
        validator.validate(contractEvent);
        return contractEvent;
    }

    private MetaDataType getMetaData(WithMetaData contract) throws DatatypeConfigurationException {
        MetaDataType metadata = new MetaDataType();
        MetaDataDto metaData = contract.getMetaData();
        LocalDateTime creation = metaData.getCtime();
        XMLGregorianCalendar xcal = datatypeFactory
                .newXMLGregorianCalendar(creation.getYear(), creation.getMonthValue(), creation.getDayOfMonth(), creation.getHour(), creation.getMinute(), creation.getSecond(), 0, DatatypeConstants.FIELD_UNDEFINED);
        metadata.setCreationTimestamp(xcal);
        metadata.setSender(metaData.getSender());
        metadata.setFupMessageInstanceId(metaData.getFupMessageInstanceId());
        return metadata;
    }

    private ContractEvent.NewContract getNewContract(ContractDto contract) throws DatatypeConfigurationException {
        ContractEvent.NewContract newContract = new ContractEvent.NewContract();
        newContract.setContractUuid(contract.getContractUuid());
        newContract.setContractInfo(getContractInfo(contract));
        newContract.setEmployeeInfo((CreationEmployeeInfoType) getEmployeeInfo(contract, new CreationEmployeeInfoType()));
        return newContract;
    }

    private UpdateContractType getUpdateContract(ContractDto originalContract, ContractDto updatedContract) throws DatatypeConfigurationException {
        UpdateContractType updateContract = new UpdateContractType();
        ContractIdentifierType contractIdentifier = new ContractIdentifierType();

        if (originalContract.getContractId() != null) {
            contractIdentifier.setContractId(originalContract.getContractId().getId());
        }
        contractIdentifier.setContractUuid(originalContract.getContractUuid());
        if (originalContract.getSapPerNr() != null) {
            contractIdentifier.setSapPerNr(originalContract.getSapPerNr().getNumber());
        }

        updateContract.setContractIdentifier(contractIdentifier);
        updateContract.setOriginalContractInfo(getContractInfo(originalContract));
        updateContract.setContractInfo(getContractInfo(updatedContract));
        updateContract.setOriginalEmployeeInfo((ModificationEmployeeInfoType) getEmployeeInfo(originalContract, new ModificationEmployeeInfoType()));
        updateContract.setEmployeeInfo((ModificationEmployeeInfoType) getEmployeeInfo(updatedContract, new ModificationEmployeeInfoType()));
        return updateContract;
    }

    private CancelContractType getCancelContract(ContractDto originalContract, ContractDto updatedContract) throws DatatypeConfigurationException {
        CancelContractType cancelContract = new CancelContractType();
        ContractIdentifierType contractIdentifier = new ContractIdentifierType();

        if (originalContract.getContractId() != null) {
            contractIdentifier.setContractId(originalContract.getContractId().getId());
        }
        contractIdentifier.setContractUuid(originalContract.getContractUuid());
        if (originalContract.getSapPerNr() != null) {
            contractIdentifier.setSapPerNr(originalContract.getSapPerNr().getNumber());
        }
        cancelContract.setContractIdentifier(contractIdentifier);
        cancelContract.setOriginalContractInfo(getContractInfo(originalContract));
        cancelContract.setContractInfo(getContractInfo(updatedContract));
        cancelContract.setOriginalEmployeeInfo((ModificationEmployeeInfoType) getEmployeeInfo(originalContract,
                new ModificationEmployeeInfoType()));
        cancelContract.setEmployeeInfo((ModificationEmployeeInfoType) getEmployeeInfo(updatedContract, new ModificationEmployeeInfoType()));
        return cancelContract;
    }

    private ContractInfoType getContractInfo(ContractDto contract) throws DatatypeConfigurationException {
        ContractInfoType contractInfoType = new ContractInfoType();
        ContractType contractType = new ContractType();
        if (contract.getContractType() == FLEXI_DAG) {
            contractType.setFlexiDay(getFlexyDayType(contract));
        } else if (contract.getContractType() == XTRA) {
            contractType.setXtra(getExtraType(contract));
        } else if (contract.getContractType() == FLEXI_PERIOD) {
            contractType.setFlexiPeriod(getFlexiPeriodType(contract));
        } else if (contract.getContractType() == STUDENT) {
            contractType.setStudent(getStudentType(contract));
        } else if (contract.getContractType() == VAST_BEPAALD) {
            contractType.setDefiniteTerm(getDefiniteTermType(contract));
        } else if (contract.getContractType() == VAST_ONBEPAALD) {
            contractType.setIndefiniteTerm(getIndefiniteTermType(contract));
        }
        contractInfoType.setContract(contractType);
        contractInfoType.setEmployeeStatute(getEmployeeStatute(contract));
        contractInfoType.setFDCP(getFCDP(contract));
        contractInfoType.setParitairComite(contract.getParitairComite());
        return contractInfoType;
    }

    private EmployeeInfoType getEmployeeInfo(ContractDto contract, EmployeeInfoType employeeInfoType) throws DatatypeConfigurationException {
        employeeInfoType.setTitle(contract.getEmployeeInfo().getTitle());
        employeeInfoType.setFirstName(contract.getEmployeeInfo().getFirstName());
        employeeInfoType.setSecondNameInitial(contract.getEmployeeInfo().getSecondNameInitials());
        employeeInfoType.setLastName(contract.getEmployeeInfo().getLastName());
        if (employeeInfoType instanceof CreationEmployeeInfoType) {
            ((CreationEmployeeInfoType) employeeInfoType).setAddress(getCreationAddress(contract.getEmployeeInfo().getAddress()));
        } else {
            ((ModificationEmployeeInfoType) employeeInfoType).setAddress(getModificationAddress(contract.getEmployeeInfo().getAddress()));
        }
        employeeInfoType.setBirthDate(getXmlGregorionCalendarDate(contract.getEmployeeInfo().getBirthDate()));
        employeeInfoType.setBirthPlace(contract.getEmployeeInfo().getBirthPlace());
        String birthCountry = (contract.getEmployeeInfo().getBirthCountry() != null) ? contract.getEmployeeInfo().getBirthCountry().getCode() : null;
        employeeInfoType.setBirthCountry(birthCountry);
        employeeInfoType.setGender(contract.getEmployeeInfo().getGender());
        employeeInfoType.setNationality(contract.getEmployeeInfo().getNationality().getCode());
        if (contract.getEmployeeInfo().getEmployeeId().getInszNumber() != null) {
            employeeInfoType.setRegisterNumber(contract.getEmployeeInfo().getEmployeeId().getInszNumber().getInszNumber());
        }
        return employeeInfoType;
    }

    private ModificationAddressType getModificationAddress(AddressDto address) {
        if (address != null) {
            ModificationAddressType addressType = new ModificationAddressType();
            addressType.setStreet(address.getStreet());
            addressType.setHouseNumber(address.getHouseNumber());
            addressType.setBoxNumber(address.getBoxNumber());
            addressType.setComplement(address.getComplement());
            addressType.setZip(address.getZip());
            addressType.setCity(address.getCity());
            addressType.setCountryCode(address.getCountryCode().getCode());
            return addressType;
        }
        return null;
    }

    private CreationAddressType getCreationAddress(AddressDto address) {
        if (address != null) {
            CreationAddressType addressType = new CreationAddressType();
            addressType.setStreet(address.getStreet());
            addressType.setHouseNumber(address.getHouseNumber());
            addressType.setBoxNumber(address.getBoxNumber());
            addressType.setComplement(address.getComplement());
            addressType.setZip(address.getZip());
            addressType.setCity(address.getCity());
            addressType.setCountryCode(address.getCountryCode().getCode());
            return addressType;
        }
        return null;
    }

    private FlexiDayType getFlexyDayType(ContractDto contract) throws DatatypeConfigurationException {
        FlexiDayType flexiDayType = new FlexiDayType();
        flexiDayType.setDate(getXmlGregorionCalendarDate(contract.getBeginDate()));
        for (WorkTimePeriodDto worktime : contract.getWorkHours()) {
            WorkHoursType workhours = new WorkHoursType();
            Duration duration = datatypeFactory.newDuration(true, 0, 0, 0, 0, worktime.getMinutesBreak(), 0);
            workhours.setBreak(duration);
            workhours.setStartDate(getXmlGregorionCalendarDate(worktime.getStartTime()));
            workhours.setStartTime(getXmlGregorionCalendarTime(worktime.getStartTime()));
            workhours.setEndDate(getXmlGregorionCalendarDate(worktime.getEndTime()));
            workhours.setEndTime(getXmlGregorionCalendarTime(worktime.getEndTime()));
            flexiDayType.getWorkHours().add(workhours);
        }
        return flexiDayType;
    }

    private XtraType getExtraType(ContractDto contract) throws DatatypeConfigurationException {
        XtraType xtraType = new XtraType();
        xtraType.setDate(getXmlGregorionCalendarDate(contract.getBeginDate()));
        for (WorkTimePeriodDto worktime : contract.getWorkHours()) {
            WorkHoursType workhours = new WorkHoursType();
            Duration duration = datatypeFactory.newDuration(true, 0, 0, 0, 0, worktime.getMinutesBreak(), 0);
            workhours.setBreak(duration);
            workhours.setStartDate(getXmlGregorionCalendarDate(worktime.getStartTime()));
            workhours.setStartTime(getXmlGregorionCalendarTime(worktime.getStartTime()));
            workhours.setEndDate(getXmlGregorionCalendarDate(worktime.getEndTime()));
            workhours.setEndTime(getXmlGregorionCalendarTime(worktime.getEndTime()));
            xtraType.getWorkHours().add(workhours);
        }
        return xtraType;
    }

    private FlexiPeriodType getFlexiPeriodType(ContractDto contract) throws DatatypeConfigurationException {
        FlexiPeriodType flexiPeriodType = new FlexiPeriodType();
        flexiPeriodType.setStartDate(getXmlGregorionCalendarDate(contract.getBeginDate()));
        flexiPeriodType.setEndDate(getXmlGregorionCalendarDate(contract.getEndDate()));
        return flexiPeriodType;
    }

    private StudentType getStudentType(ContractDto contract) throws DatatypeConfigurationException {
        StudentType studentType = new StudentType();
        studentType.setStartDate(getXmlGregorionCalendarDate(contract.getBeginDate()));
        studentType.setEndDate(getXmlGregorionCalendarDate(contract.getEndDate()));
        if (contract.getBeginDate().isBefore(LocalDate.of(2017, 1, 1))) {
            studentType.setNumberOfDays(contract.getNumberOfDays());
        } else {
            studentType.setNumberOfHours(contract.getNumberOfHours());
        }
        studentType.setFAONumber(contract.getExploitation());
        return studentType;
    }

    private DefiniteTermType getDefiniteTermType(ContractDto contract) throws DatatypeConfigurationException {
        DefiniteTermType definiteTermType = new DefiniteTermType();
        definiteTermType.setStartDate(getXmlGregorionCalendarDate(contract.getBeginDate()));
        definiteTermType.setEndDate(getXmlGregorionCalendarDate(contract.getEndDate()));
        return definiteTermType;
    }

    private IndefiniteTermType getIndefiniteTermType(ContractDto contract) throws DatatypeConfigurationException {
        IndefiniteTermType indefiniteTermType = new IndefiniteTermType();
        indefiniteTermType.setStartDate(getXmlGregorionCalendarDate(contract.getBeginDate()));
        indefiniteTermType.setEndDate(getXmlGregorionCalendarDate(contract.getEndDate()));
        return indefiniteTermType;
    }

    private EmployeeStatuteType getEmployeeStatute(ContractDto contract) {
        switch (contract.getEmployeeStatute()) {
            case ARBEIDER:
                return EmployeeStatuteType.fromValue("worker");
            case BEDIENDE:
                return EmployeeStatuteType.fromValue("employee");
            default:
                return null;
        }
    }

    private FDCPType getFCDP(ContractDto contract) {
        FDCPType fdcpType = new FDCPType();
        fdcpType.setFirmNumber(contract.getFdcp().getFirmNumber().getFirmNumber());
        fdcpType.setDepartmentNumber(contract.getFdcp().getDepartementNumber());
        fdcpType.setCategory(contract.getFdcp().getCategory());
        fdcpType.setPersonNumber(null);     //TODO
        return fdcpType;
    }

    private XMLGregorianCalendar getXmlGregorionCalendarDate(LocalDateTime dateTime) throws DatatypeConfigurationException {
        return getXmlGregorionCalendarDate(dateTime.toLocalDate());
    }

    private XMLGregorianCalendar getXmlGregorionCalendarDate(LocalDate date) throws DatatypeConfigurationException {
        if (date != null) {
            XMLGregorianCalendar xcal = datatypeFactory.newXMLGregorianCalendarDate(date.getYear(), date.getMonthValue(), date.getDayOfMonth(), DatatypeConstants.FIELD_UNDEFINED);
            return xcal;
        }
        return null;
    }

    private XMLGregorianCalendar getXmlGregorionCalendarTime(LocalDateTime dateTime) throws DatatypeConfigurationException {
        return getXmlGregorionCalendarTime(dateTime.toLocalTime());
    }

    private XMLGregorianCalendar getXmlGregorionCalendarTime(LocalTime time) throws DatatypeConfigurationException {
        if (time != null) {
            XMLGregorianCalendar xcal = datatypeFactory
                    .newXMLGregorianCalendarTime(time.getHour(), time.getMinute(), time.getSecond(), DatatypeConstants.FIELD_UNDEFINED);
            return xcal;
        }
        return null;
    }
}
