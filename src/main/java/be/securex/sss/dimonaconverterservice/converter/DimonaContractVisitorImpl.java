package be.securex.sss.dimonaconverterservice.converter;

import be.securex.sss.dimonaconverterservice.enums.ContingentType;
import be.securex.sss.dimonaconverterservice.enums.ContractNature;
import be.securex.sss.dimonaconverterservice.enums.EmployeeType;
import be.securex.sss.dimonaconverterservice.xsd.dimonarequest.*;
import be.securex.sss.dimonaconverterservice.xsd.dimonarequest.ContractType;
import be.securex.sss.dimonaconverterservice.xsd.v1.contractevent.*;
import be.securex.sss.dimonaconverterservice.xsd.v1.contractevent.WorkHoursType;

import java.util.List;

/**
 * Created by 8565 on 2/11/2015.
 */
public class DimonaContractVisitorImpl implements DimonaContractVisitor {

    private ContractType contract;

    public DimonaContractVisitorImpl(ContractType contract) {
        this.contract = contract;
    }

    @Override
    public void visit(XtraType extra) {
        if (extra != null) {
            String startDate = ConverterUtils.toDdmmyyyyString(extra.getDate());
            contract.setDateEffStart(startDate);
            contract.setDateBreach(startDate);
            contract.setDateForeseenEnd(startDate);
            contract.setWorkHours(convertWorkHours(extra.getWorkHours()));
            contract.setContractNature(ContractNature.DEFINITE_TERM.getCbCode());
            contract.setContractSocial(getContractSocialType(EmployeeType.EXT, ContingentType.BINNEN_CONTINGENT, null, null));
        }
    }

    @Override
    public void visit(StudentType student, StudentType originalStudentType) {
        if (student != null) {
            String startDate = ConverterUtils.toDdmmyyyyString(student.getStartDate());
            String endDate = ConverterUtils.toDdmmyyyyString(student.getEndDate());
            contract.setDateEffStart(startDate);
            contract.setDateBreach(endDate);
            contract.setDateForeseenEnd(endDate);
            contract.setContractNature(ContractNature.DEFINITE_TERM.getCbCode());
            contract.setContractSocial(getContractSocialType(EmployeeType.STU, ContingentType.BINNEN_CONTINGENT, student.getNumberOfDays(), student.getNumberOfHours()));
            contract.setAffiliation(getAffiliationType(student.getFAONumber()));
        }
    }

    @Override
    public void visit(FlexiDayType flexiDay) {
        if (flexiDay != null) {
            String startDate = ConverterUtils.toDdmmyyyyString(flexiDay.getDate());
            contract.setDateEffStart(startDate);
            contract.setDateBreach(startDate);
            contract.setDateForeseenEnd(startDate);
            contract.setWorkHours(convertWorkHours(flexiDay.getWorkHours()));
            contract.setContractNature(ContractNature.DEFINITE_TERM.getCbCode());
            contract.setContractSocial(getContractSocialType(EmployeeType.FLX, null, null, null));
        }
    }

    @Override
    public void visit(FlexiPeriodType flexiPeriod) {
        if (flexiPeriod != null) {
            String startDate = ConverterUtils.toDdmmyyyyString(flexiPeriod.getStartDate());
            String endDate = ConverterUtils.toDdmmyyyyString(flexiPeriod.getEndDate());
            contract.setDateEffStart(startDate);
            contract.setDateBreach(endDate);
            contract.setDateForeseenEnd(endDate);
            contract.setContractNature(ContractNature.DEFINITE_TERM.getCbCode());
            contract.setContractSocial(getContractSocialType(EmployeeType.FLX, null, null, null));
        }
    }

    public void visit(DefiniteTermType definiteTerm) {
        if (definiteTerm != null) {
            String startDate = ConverterUtils.toDdmmyyyyString(definiteTerm.getStartDate());
            String endDate = ConverterUtils.toDdmmyyyyString(definiteTerm.getEndDate());
            contract.setDateEffStart(startDate);
            contract.setDateBreach(endDate);
            contract.setDateForeseenEnd(endDate);
            contract.setContractNature(ContractNature.DEFINITE_TERM.getCbCode());
        }
    }

    public void visit(IndefiniteTermType indefiniteTerm, IndefiniteTermType originalIndefiniteTermType) {
        if (indefiniteTerm != null) {
            String startDate = ConverterUtils.toDdmmyyyyString(indefiniteTerm.getStartDate());
            String endDate = ConverterUtils.toDdmmyyyyString(indefiniteTerm.getEndDate());
            contract.setDateEffStart(startDate);
            contract.setDateBreach(endDate);
            contract.setDateForeseenEnd(endDate);
            contract.setContractNature(ContractNature.INDEFINITE_TERM.getCbCode());
        }
    }

    public ContractType getContract() {
        return contract;
    }

    private be.securex.sss.dimonaconverterservice.xsd.dimonarequest.WorkHoursType convertWorkHours(List<WorkHoursType> workHoursTypes) {
        be.securex.sss.dimonaconverterservice.xsd.dimonarequest.WorkHoursType workHours = new be.securex.sss.dimonaconverterservice.xsd.dimonarequest.WorkHoursType();
        if (workHoursTypes != null) {
            for (be.securex.sss.dimonaconverterservice.xsd.v1.contractevent.WorkHoursType workHoursType : workHoursTypes) {
                HourLineType hourLineType = new HourLineType();
                String startDate = ConverterUtils.toDdmmyyyyString(workHoursType.getStartDate());
                hourLineType.setDateStart(startDate);
                hourLineType.setTimeStart(Integer.parseInt(ConverterUtils.toHhmmString(workHoursType.getStartTime())));
                String endDate = ConverterUtils.toDdmmyyyyString(workHoursType.getEndDate());
                hourLineType.setDateStop(endDate);
                hourLineType.setTimeStop(Integer.parseInt(ConverterUtils.toHhmmString(workHoursType.getEndTime())));
                hourLineType.setPauze(workHoursType.getBreak() == null ? null : workHoursType.getBreak().getMinutes() + "");
                workHours.getHOURLINE().add(hourLineType);
            }
        }
        return workHours;
    }

    private AffiliationType getAffiliationType(String faoNumber) {
        AffiliationType affiliationType = new AffiliationType();
        affiliationType.setExplNr(faoNumber);
        return affiliationType;
    }

    private ContractSocialType getContractSocialType(EmployeeType employeeType, ContingentType contingentType, Integer numberOfDays, Integer numberOfHours) {
        ContractSocialType contractSocialType = new ContractSocialType();
        contractSocialType.setEmployeeType(employeeType.name());
        contractSocialType.setHorecaContingentFlag(contingentType == null ? null : contingentType.getCode());
        contractSocialType.setPlannedDaysNr(numberOfDays == null ? null : numberOfDays.toString());
        contractSocialType.setPlannedHoursNr(numberOfHours == null ? null : numberOfHours.toString());
        return contractSocialType;
    }

}
