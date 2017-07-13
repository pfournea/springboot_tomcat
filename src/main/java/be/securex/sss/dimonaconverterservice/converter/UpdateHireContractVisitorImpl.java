package be.securex.sss.dimonaconverterservice.converter;

import be.securex.sss.dimonaconverterservice.xsd.v1.contractevent.*;
import be.securex.sss.dimonaconverterservice.xsd.v4.hronline.*;

import javax.xml.datatype.DatatypeConstants;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by 8565 on 22/01/2016.
 */
@SuppressWarnings("Duplicates")
public class UpdateHireContractVisitorImpl implements HireContractVisitor {

    UpdateHireConversionContext context;

    public UpdateHireContractVisitorImpl(UpdateHireConversionContext context) {
        this.context = context;
    }

    @Override
    public void visit(XtraType xtraType) {
        if (xtraType != null) {
            ChangeExtraFlexiCType changeExtraFlexiCType = new ChangeExtraFlexiCType();
            List<ChangeExtraFlexiCType.IT2003> hourLines = convertWorkHours(xtraType.getWorkHours());
            changeExtraFlexiCType.getIT2003().addAll(hourLines);
            context.setChangeExtraFlexi(changeExtraFlexiCType);
        }
    }

    @Override
    public void visit(StudentType studentType, StudentType originalStudentType) {
        if (studentType != null) {
            if (studentType.getEndDate().compare(originalStudentType.getEndDate()) != DatatypeConstants.EQUAL) {
                TerminationCType terminationCType = new TerminationCType();
                TerminationCType.IT0000 it0000 = new TerminationCType.IT0000();
                it0000.setContractEndDate(studentType.getEndDate());
                terminationCType.setIT0000(it0000);
                context.setTermination(terminationCType);
            }

            ChangeStudentCType.IT9753 studentFaoChange = null;
            ChangeStudentCType.GIT0016 studentNbrOfDaysHoursChange = null;
            if (!studentType.getFAONumber().equals(originalStudentType.getFAONumber())) {
                studentFaoChange = new ChangeStudentCType.IT9753();
                studentFaoChange.setEmploymentAddressRefNumber(studentType.getFAONumber());
            }
            if (studentType.getNumberOfDays() != originalStudentType.getNumberOfDays()) {
                studentNbrOfDaysHoursChange = new ChangeStudentCType.GIT0016();
                studentNbrOfDaysHoursChange.setPlanneddaysnbr(Integer.toString(studentType.getNumberOfDays()));
            }
            if (studentType.getNumberOfHours() != originalStudentType.getNumberOfHours()) {
                studentNbrOfDaysHoursChange = new ChangeStudentCType.GIT0016();
                studentNbrOfDaysHoursChange.setPlannedhoursnbr(Integer.toString(studentType.getNumberOfHours()));
            }
            if (studentFaoChange != null || studentNbrOfDaysHoursChange != null) {
                ChangeStudentCType changeStudentCType = new ChangeStudentCType();
                changeStudentCType.setIT9753(studentFaoChange);
                changeStudentCType.setGIT0016(studentNbrOfDaysHoursChange);
                context.setChangeStudent(changeStudentCType);
            }
        }
    }

    @Override
    public void visit(FlexiDayType flexiDay) {
        if (flexiDay != null) {
            ChangeExtraFlexiCType changeExtraFlexiCType = new ChangeExtraFlexiCType();
            List<ChangeExtraFlexiCType.IT2003> hourLines = convertWorkHours(flexiDay.getWorkHours());
            changeExtraFlexiCType.getIT2003().addAll(hourLines);
            context.setChangeExtraFlexi(changeExtraFlexiCType);
        }
    }

    @Override
    public void visit(FlexiPeriodType flexiPeriod) {
        if (flexiPeriod != null) {
            TerminationCType terminationCType = new TerminationCType();
            TerminationCType.IT0000 it0000 = new TerminationCType.IT0000();
            it0000.setContractEndDate(flexiPeriod.getEndDate());
            terminationCType.setIT0000(it0000);
            context.setTermination(terminationCType);
        }
    }

    @Override
    public void visit(DefiniteTermType definiteTerm) {
        if (definiteTerm != null) {
            TerminationCType terminationCType = new TerminationCType();
            TerminationCType.IT0000 it0000 = new TerminationCType.IT0000();
            it0000.setContractEndDate(definiteTerm.getEndDate());
            terminationCType.setIT0000(it0000);
            context.setTermination(terminationCType);
        }
    }

    @Override
    public void visit(IndefiniteTermType indefiniteTerm, IndefiniteTermType originalIndefiniteTerm) {
        if (indefiniteTerm != null) {
            if (originalIndefiniteTerm.getEndDate() != null && indefiniteTerm.getEndDate() == null) {
                context.setDeleteTermination(new DeleteTerminationCType());
                return;
            }
            if ((originalIndefiniteTerm.getEndDate() == null || indefiniteTerm.getEndDate().compare(originalIndefiniteTerm.getEndDate()) != DatatypeConstants.EQUAL) &&
                    indefiniteTerm.getEndDate() != null) {
                TerminationCType terminationCType = new TerminationCType();
                TerminationCType.IT0000 it0000 = (new TerminationCType.IT0000());
                it0000.setContractEndDate(indefiniteTerm.getEndDate());
                terminationCType.setIT0000(it0000);
                context.setTermination(terminationCType);
            }
        }
    }

    private List<ChangeExtraFlexiCType.IT2003> convertWorkHours(List<WorkHoursType> workHoursTypes) {
        List<ChangeExtraFlexiCType.IT2003> hronlineWorkHours = new ArrayList<>();
        if (workHoursTypes != null) {
            for (be.securex.sss.dimonaconverterservice.xsd.v1.contractevent.WorkHoursType workHoursType : workHoursTypes) {
                ChangeExtraFlexiCType.IT2003 hronlineHourLine = new ChangeExtraFlexiCType.IT2003();
                hronlineHourLine.setValidFrom(workHoursType.getStartDate());
                hronlineHourLine.setValidTo(workHoursType.getEndDate());
                hronlineHourLine.setStartTime(workHoursType.getStartTime());
                hronlineHourLine.setEndTime(workHoursType.getEndTime());
                hronlineHourLine.setBreak(ConverterUtils.toDecimalHour(workHoursType.getBreak()));
                hronlineWorkHours.add(hronlineHourLine);
            }
        }
        return hronlineWorkHours;
    }

}
