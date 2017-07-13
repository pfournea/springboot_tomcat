package be.securex.sss.dimonaconverterservice.converter;

import be.securex.sss.dimonaconverterservice.enums.ContingentType;
import be.securex.sss.dimonaconverterservice.enums.hronline.*;
import be.securex.sss.dimonaconverterservice.xsd.v1.contractevent.*;
import be.securex.sss.dimonaconverterservice.xsd.v4.hronline.Employee;
import be.securex.sss.dimonaconverterservice.xsd.v4.hronline.HireCType;

import javax.xml.datatype.DatatypeFactory;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 8565 on 15/01/2016.
 */
public class NewHireContractVisitorImpl implements HireContractVisitor {

    private Employee employee;
    private HireCType hire;

    public NewHireContractVisitorImpl(Employee employee) {
        this.employee = employee;
        this.hire = employee.getScenarioInfo().getScenarioInfoContent().getHire();
    }

    public Employee getEmployee() {
        return employee;
    }

    @Override
    public void visit(XtraType xtraType) {
        if (xtraType != null) {
            hire.setIT0007(new HireCType.IT0007());

            hire.getIT0000().setContractStartDate(xtraType.getDate());
            hire.getIT0000().setContractEndDate(xtraType.getDate());

            hire.getIT0001().setEmployeeGroup(HronlineEmployeeGroup.NIET_STUDENT.getCode());
            hire.getIT0001().setWorkContract(HronlineWorkContract.EXTRA.getCode());

            hire.getIT0016().setContractType(HronlineContractType.BEPAALDE_DUUR.getCode());
            hire.getIT0016().setLabourCommission(HronlineLabourCommission.PC_302000.getCode());

            hire.getIT2003().addAll(convertWorkHours(xtraType.getWorkHours()));

            hire.getIT0007().setCONTINGENT(ContingentType.BINNEN_CONTINGENT.getCode());
        }
    }

    @Override
    public void visit(StudentType studentType, StudentType originalStudent) {
        if (studentType != null) {
            hire.setIT9753(new HireCType.IT9753());
            hire.setGIT0016(new HireCType.GIT0016());

            hire.getIT0000().setContractStartDate(studentType.getStartDate());
            hire.getIT0000().setContractEndDate(studentType.getEndDate());

            hire.getIT0001().setEmployeeGroup(HronlineEmployeeGroup.STUDENT.getCode());
            hire.getIT0001().setWorkContract(HronlineWorkContract.STUDENT.getCode());

            hire.getIT0016().setContractType(HronlineContractType.BEPAALDE_DUUR.getCode());
            hire.getIT0016().setLabourCommission(HronlineLabourCommission.PC_302000.getCode());

            hire.getIT9753().setEmploymentAddressRefNumber(studentType.getFAONumber());
            LocalDate startDate = LocalDate.of(studentType.getStartDate().getYear(), studentType.getStartDate().getMonth(), studentType.getStartDate().getDay());
            if (startDate.isBefore(LocalDate.of(2017, 1, 1))) {
                hire.getGIT0016().setPlanneddaysnbr(String.valueOf(studentType.getNumberOfDays()));
            } else {
                hire.getGIT0016().setPlannedhoursnbr(String.valueOf(studentType.getNumberOfHours()));
            }
        }
    }

    @Override
    public void visit(FlexiDayType flexiDayType) {
        if (flexiDayType != null) {
            hire.getIT0000().setContractStartDate(flexiDayType.getDate());
            hire.getIT0000().setContractEndDate(flexiDayType.getDate());

            hire.getIT0001().setEmployeeGroup(HronlineEmployeeGroup.NIET_STUDENT.getCode());
            hire.getIT0001().setWorkContract(HronlineWorkContract.FLEXI.getCode());

            hire.getIT0016().setContractType(HronlineContractType.BEPAALDE_DUUR.getCode());
            hire.getIT0016().setLabourCommission(HronlineLabourCommission.PC_302000.getCode());

            hire.getIT2003().addAll(convertWorkHours(flexiDayType.getWorkHours()));
        }
    }

    @Override
    public void visit(FlexiPeriodType flexiPeriod) {
        if (flexiPeriod != null) {
            hire.getIT0000().setContractStartDate(flexiPeriod.getStartDate());
            hire.getIT0000().setContractEndDate(flexiPeriod.getEndDate());

            hire.getIT0001().setEmployeeGroup(HronlineEmployeeGroup.NIET_STUDENT.getCode());
            hire.getIT0001().setWorkContract(HronlineWorkContract.FLEXI.getCode());

            hire.getIT0016().setContractType(HronlineContractType.BEPAALDE_DUUR.getCode());
            hire.getIT0016().setLabourCommission(HronlineLabourCommission.PC_302000.getCode());
        }
    }

    @Override
    public void visit(DefiniteTermType definiteTerm) {
        if (definiteTerm != null) {
            hire.getIT0000().setContractStartDate(definiteTerm.getStartDate());
            hire.getIT0000().setContractEndDate(definiteTerm.getEndDate());

            hire.getIT0001().setEmployeeGroup(HronlineEmployeeGroup.NIET_STUDENT.getCode());
            hire.getIT0001().setWorkContract(HronlineWorkContract.OTHER.getCode());

            hire.getIT0016().setContractType(HronlineContractType.BEPAALDE_DUUR.getCode());
            hire.getIT0016().setLabourCommission(HronlineLabourCommission.PC_302000.getCode());
        }
    }

    @Override
    public void visit(IndefiniteTermType indefiniteTerm, IndefiniteTermType originalIndefiniteTermType) {
        if (indefiniteTerm != null) {
            hire.getIT0000().setContractStartDate(indefiniteTerm.getStartDate());
            hire.getIT0000().setContractEndDate(indefiniteTerm.getEndDate());

            hire.getIT0001().setEmployeeGroup(HronlineEmployeeGroup.NIET_STUDENT.getCode());
            hire.getIT0001().setWorkContract(HronlineWorkContract.OTHER.getCode());

            hire.getIT0016().setContractType(HronlineContractType.ONBEPAALDE_DUUR.getCode());
            hire.getIT0016().setLabourCommission(HronlineLabourCommission.PC_302000.getCode());
        }
    }

    private List<HireCType.IT2003> convertWorkHours(List<WorkHoursType> workHoursTypes) {
        List<HireCType.IT2003> hronlineWorkHours = new ArrayList<>();
        if (workHoursTypes != null) {
            for (be.securex.sss.dimonaconverterservice.xsd.v1.contractevent.WorkHoursType workHoursType : workHoursTypes) {
                HireCType.IT2003 hronlineHourLine = new HireCType.IT2003();
                hronlineHourLine.setValidFrom(workHoursType.getStartDate());
                hronlineHourLine.setValidTo(workHoursType.getStartDate()); //start date: see OAPPSSSSVC-183
                hronlineHourLine.setEndTimeOnNextDay(workHoursType.getStartDate().getDay() != workHoursType.getEndDate().getDay());
                hronlineHourLine.setStartTime(workHoursType.getStartTime());
                hronlineHourLine.setEndTime(workHoursType.getEndTime());
                hronlineHourLine.setBreak(ConverterUtils.toDecimalHour(workHoursType.getBreak()));
                hronlineWorkHours.add(hronlineHourLine);
            }
        }
        return hronlineWorkHours;
    }

}
