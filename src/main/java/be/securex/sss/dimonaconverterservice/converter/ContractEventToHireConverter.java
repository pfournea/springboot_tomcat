package be.securex.sss.dimonaconverterservice.converter;

import be.securex.sss.dimonaconverterservice.enums.hronline.HronlineEmployeeSubgroup;
import be.securex.sss.dimonaconverterservice.enums.hronline.HronlineGender;
import be.securex.sss.dimonaconverterservice.enums.hronline.HronlineTitle;
import be.securex.sss.dimonaconverterservice.service.RemoveSelfClosingIT0100TagsService;
import be.securex.sss.dimonaconverterservice.xsd.v1.contractevent.*;
import be.securex.sss.dimonaconverterservice.xsd.v4.hronline.*;
import be.securex.sss.dimonaconverterservice.xsd.v4.hronline.HireList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.xml.bind.JAXBException;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeConstants;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.time.LocalDateTime;
import java.util.*;

/**
 * Created by 8565 on 14/01/2016.
 */

@Component
public class ContractEventToHireConverter {
    private final RemoveSelfClosingIT0100TagsService removeSelfClosingIT0100TagsService;

    Logger logger = LoggerFactory.getLogger(ContractEventToHireConverter.class);

    public ContractEventToHireConverter(RemoveSelfClosingIT0100TagsService removeSelfClosingIT0100TagsService) {
        this.removeSelfClosingIT0100TagsService = removeSelfClosingIT0100TagsService;
    }

    public HireList convert(ContractEvent contractEvent) throws JAXBException {
        HireList hireList = new HireList();

        List<Employee> employeeTypes = new ArrayList<>();
        if (contractEvent.getNewContract() != null) {
            employeeTypes.add(convertNewContract(contractEvent));
        } else if (contractEvent.getUpdateContract() != null) {
            employeeTypes.addAll(convertUpdateContract(contractEvent));
        } else if (contractEvent.getCancelContract() != null) {
            employeeTypes.add(convertCancelContract(contractEvent));
        }

        for (Employee employee : employeeTypes) {
            hireList.add(employee, removeSelfClosingIT0100TagsService);
        }

        return hireList;
    }

    private Employee convertNewContract(ContractEvent contractEvent) {
        Employee employee = initEmployeeForNewContract(contractEvent);
        fillHire(contractEvent.getNewContract(), employee);
        return employee;
    }

    private Collection<? extends Employee> convertUpdateContract(ContractEvent contractEvent) {
        UpdateHireConversionContext context = new UpdateHireConversionContext();
        UpdateHireContractVisitorImpl visitor = new UpdateHireContractVisitorImpl(context);
        copyContractFieldsForUpdate(contractEvent.getUpdateContract().getContractInfo(), contractEvent.getUpdateContract().getOriginalContractInfo(), visitor);
        return updatedEmployees(contractEvent, context);
    }

    private Collection<? extends Employee> updatedEmployees(ContractEvent contractEvent, UpdateHireConversionContext context) {
        List<Employee> employees = new ArrayList<>();

        if (context.getAnnulation() != null) {
            Employee employee = initEmployeeForUpdateContract(contractEvent);
            employee.getScenarioInfo().getScenarioInfoContent().setAnnulation(context.getAnnulation());
            employees.add(employee);
        }
        if (context.getChangeExtraFlexi() != null) {
            Employee employee = initEmployeeForUpdateContract(contractEvent);
            employee.getScenarioInfo().getScenarioInfoContent().setChangeExtraFlexi(context.getChangeExtraFlexi());
            employees.add(employee);
        }
        if (context.getChangeStudent() != null) {
            Employee employee = initEmployeeForUpdateContract(contractEvent);
            employee.getScenarioInfo().getScenarioInfoContent().setChangeStudent(context.getChangeStudent());
            employees.add(employee);
        }
        if (context.getDeleteTermination() != null) {
            Employee employee = initEmployeeForUpdateContract(contractEvent);
            employee.getScenarioInfo().getScenarioInfoContent().setDeleteTermination(context.getDeleteTermination());
            employees.add(employee);
        }
        if (context.getTermination() != null) {
            Employee employee = initEmployeeForUpdateContract(contractEvent);
            employee.getScenarioInfo().getScenarioInfoContent().setTermination(context.getTermination());
            employees.add(employee);
        }

        return employees;
    }

    private Employee convertCancelContract(ContractEvent contractEvent) {
        Employee employee = initEmployeeForCancelContract(contractEvent);
        employee.getScenarioInfo().getScenarioInfoContent().setAnnulation(new AnnulationCType());
        return employee;
    }

    private void fillHire(ContractEvent.NewContract newContract, Employee employee) {
        HireCType hire = employee.getScenarioInfo().getScenarioInfoContent().getHire();

        hire.getIT0002().setTitle(HronlineTitle.fromContractEventTitle(newContract.getEmployeeInfo().getTitle()).getCode());
        NewHireContractVisitorImpl visitor = new NewHireContractVisitorImpl(employee);
        copyContractFields(newContract.getContractInfo(), visitor);
        hire.getIT0002().setFirstName(newContract.getEmployeeInfo().getFirstName());
        hire.getIT0002().setLastName(newContract.getEmployeeInfo().getLastName());
        hire.getIT0002().setInitials(newContract.getEmployeeInfo().getSecondNameInitial());
        hire.getIT0002().setGender(HronlineGender.fromContractEventGender(newContract.getEmployeeInfo().getGender()).getCode());
        hire.getIT0002().setBirthDate(newContract.getEmployeeInfo().getBirthDate());
        hire.getIT0002().setBirthPlace(newContract.getEmployeeInfo().getBirthPlace());
        hire.getIT0002().setCountryOfBirth(newContract.getEmployeeInfo().getBirthCountry());
        hire.getIT0002().setNationality(newContract.getEmployeeInfo().getNationality());

        hire.getIT0001().setEmployeeSubgroup(HronlineEmployeeSubgroup.
                fromEmployeeStatuteType(newContract.getContractInfo()).getCode());
        if (newContract.getContractInfo().getFDCP() != null
                && StringUtils.hasText(newContract.getContractInfo().getFDCP().getDepartmentNumber())) {
            hire.getIT0001().setDepartement(ConverterUtils.departmentNumber4Pos(newContract.getContractInfo().getFDCP().getDepartmentNumber()));
        }
        hire.getIT0100().setINSSNumber(newContract.getEmployeeInfo().getRegisterNumber());

        if (newContract.getEmployeeInfo().getAddress() != null) {
            hire.setIT0006(new HireCType.IT0006());
            hire.getIT0006().setStreet(newContract.getEmployeeInfo().getAddress().getStreet());
            hire.getIT0006().setHouseNumber(newContract.getEmployeeInfo().getAddress().getHouseNumber());
            hire.getIT0006().setMailBoxNumber(newContract.getEmployeeInfo().getAddress().getBoxNumber());
            hire.getIT0006().setSecondAddressLine(newContract.getEmployeeInfo().getAddress().getComplement());
            hire.getIT0006().setPostalCode(newContract.getEmployeeInfo().getAddress().getZip());
            hire.getIT0006().setCity(newContract.getEmployeeInfo().getAddress().getCity());
            hire.getIT0006().setCountry(newContract.getEmployeeInfo().getAddress().getCountryCode());
        }
    }

    private void copyContractFieldsForUpdate(ContractInfoType contractInfo, ContractInfoType originalContractInfo, DimonaContractVisitor visitor) {
        List<DimonaContractVisitable> visitables = new ArrayList<>();

        visitables.add(new VisitableStudentType(contractInfo.getContract().getStudent(), originalContractInfo.getContract().getStudent()));
        visitables.add(new VisitableXtraType(contractInfo.getContract().getXtra()));
        visitables.add(new VisitableFlexiDayType(contractInfo.getContract().getFlexiDay()));
        visitables.add(new VisitableFlexiPeriodType(contractInfo.getContract().getFlexiPeriod()));
        visitables.add(new VisitableDefiniteTermType(contractInfo.getContract().getDefiniteTerm()));
        visitables.add(new VisitableIndefiniteTermType(contractInfo.getContract().getIndefiniteTerm(), originalContractInfo.getContract().getIndefiniteTerm()));

        for (DimonaContractVisitable visitable : visitables) {
            visitable.accept(visitor);
        }
    }

    private void copyContractFields(ContractInfoType contractInfo, DimonaContractVisitor visitor) {
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

    private HireCType initHire() {
        HireCType hire = new HireCType();
        hire.setIT0000(new HireCType.IT0000());
        hire.setIT0001(new HireCType.IT0001());
        hire.setIT0002(new HireCType.IT0002());
        hire.setIT0100(new HireCType.IT0100());
        hire.setIT0016(new HireCType.IT0016());
        return hire;
    }

    private Employee initEmployeeForNewContract(ContractEvent contractEvent) {
        ContractIdentifierType contractIdentifierType = new ContractIdentifierType();
        contractIdentifierType.setContractUuid(contractEvent.getNewContract().getContractUuid());
        Employee employee = initEmployee(contractEvent.getNewContract().getContractInfo(), contractIdentifierType);
        employee.getScenarioInfo().getScenarioInfoContent().setHire(initHire());
        return employee;
    }

    private Employee initEmployeeForUpdateContract(ContractEvent contractEvent) {
        return initEmployee(contractEvent.getUpdateContract().getContractInfo(), contractEvent.getUpdateContract().getContractIdentifier());
    }

    private Employee initEmployeeForCancelContract(ContractEvent contractEvent) {
        return initEmployee(contractEvent.getCancelContract().getContractInfo(), contractEvent.getCancelContract().getContractIdentifier());
    }

    private Employee initEmployee(ContractInfoType contractInfo, ContractIdentifierType contractIdentifier) {
        Employee employee = new Employee();
        ScenarioInfoCType scenarioInfoCType = new ScenarioInfoCType();
        employee.setScenarioInfo(scenarioInfoCType);
        ScenarioInfoContentCType scenarioInfoContentCType = new ScenarioInfoContentCType();
        scenarioInfoCType.setScenarioInfoContent(scenarioInfoContentCType);
        KeyInfoCType keyInfoCType = new KeyInfoCType();
        scenarioInfoCType.setKeyInfo(keyInfoCType);

        keyInfoCType.setFirm(contractInfo.getFDCP().getFirmNumber());
        keyInfoCType.setUpdateUser("HIREAPP");
        try {
            LocalDateTime now = LocalDateTime.now();
            XMLGregorianCalendar xcal = DatatypeFactory.newInstance()
                    .newXMLGregorianCalendar(now.getYear(), now.getMonthValue(), now.getDayOfMonth(),
                            now.getHour(), now.getMinute(), now.getSecond(), 0, DatatypeConstants.FIELD_UNDEFINED);
            keyInfoCType.setUpdateTimestamp(xcal);
        } catch (DatatypeConfigurationException e) {
            logger.error("Error while setting update timestamp", e);
        }
        KeyInfoIdBuilder keyInfoIdBuilder = new KeyInfoIdBuilder();
        keyInfoCType.setId(keyInfoIdBuilder.uuid(contractIdentifier.getContractUuid()).sapPerNr(contractIdentifier.getSapPerNr()).build());

        return employee;
    }


}
