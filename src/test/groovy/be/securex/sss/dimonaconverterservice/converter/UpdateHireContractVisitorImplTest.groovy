package be.securex.sss.dimonaconverterservice.converter

import be.securex.sss.dimonaconverterservice.xsd.v1.contractevent.DefiniteTermType
import be.securex.sss.dimonaconverterservice.xsd.v1.contractevent.FlexiDayType
import be.securex.sss.dimonaconverterservice.xsd.v1.contractevent.FlexiPeriodType
import be.securex.sss.dimonaconverterservice.xsd.v1.contractevent.IndefiniteTermType
import be.securex.sss.dimonaconverterservice.xsd.v1.contractevent.StudentType
import be.securex.sss.dimonaconverterservice.xsd.v1.contractevent.WorkHoursType
import be.securex.sss.dimonaconverterservice.xsd.v1.contractevent.XtraType
import spock.lang.Ignore
import spock.lang.Specification

import javax.xml.datatype.DatatypeConstants
import javax.xml.datatype.DatatypeFactory
import javax.xml.datatype.XMLGregorianCalendar

/**
 * Created by 8565 on 11/05/2016.
 */
class UpdateHireContractVisitorImplTest extends Specification {

    UpdateHireConversionContext context = new UpdateHireConversionContext()
    HireContractVisitor updateHireVisitor = new UpdateHireContractVisitorImpl(context)
    XMLGregorianCalendar startXmlCalendar = DatatypeFactory.newInstance().newXMLGregorianCalendar(1970, 1, 1, 9, 0, 0, 0, DatatypeConstants.FIELD_UNDEFINED)
    XMLGregorianCalendar stopXmlCalendar = DatatypeFactory.newInstance().newXMLGregorianCalendar(1970, 1, 2, 17, 55, 0, 0, DatatypeConstants.FIELD_UNDEFINED)
    XMLGregorianCalendar newStopXmlCalendar = DatatypeFactory.newInstance().newXMLGregorianCalendar(1970, 1, 3, 17, 55, 0, 0, DatatypeConstants.FIELD_UNDEFINED)
    WorkHoursType workHours = new WorkHoursType()

    def setup() {
        workHours.setStartDate(startXmlCalendar)
        workHours.setStartTime(startXmlCalendar)
        workHours.setEndDate(stopXmlCalendar)
        workHours.setEndTime(stopXmlCalendar)
        workHours.setBreak(DatatypeFactory.newInstance().newDuration(20 * 60 * 1000))
    }

    def "Visit ExtraType"() {
        given:
        XtraType xtraType = new XtraType()
        xtraType.getWorkHours().add(workHours)

        when:
        updateHireVisitor.visit(xtraType)

        then:
        context.getChangeExtraFlexi() != null
        context.getChangeExtraFlexi().getIT2003() != null
        context.getChangeExtraFlexi().getIT2003().size() == 1
        context.getChangeExtraFlexi().getIT2003()[0].getValidFrom() == startXmlCalendar
        context.getChangeExtraFlexi().getIT2003()[0].getValidTo() == stopXmlCalendar
        context.getChangeExtraFlexi().getIT2003()[0].getStartTime() == startXmlCalendar
        context.getChangeExtraFlexi().getIT2003()[0].getEndTime() == stopXmlCalendar
        context.getChangeExtraFlexi().getIT2003()[0].getBreak() == "0,33"
    }

    def "VisitStudentType"() {
        given:
        StudentType originalstudentType = new StudentType()
        originalstudentType.setStartDate(startXmlCalendar)
        originalstudentType.setEndDate(stopXmlCalendar)
        originalstudentType.setFAONumber("123")
        originalstudentType.setNumberOfDays(3)

        StudentType updatedStudentType = new StudentType()
        updatedStudentType.setStartDate(startXmlCalendar)
        updatedStudentType.setEndDate(newStopXmlCalendar)
        updatedStudentType.setFAONumber("124")
        updatedStudentType.setNumberOfDays(4)

        when:
        updateHireVisitor.visit(updatedStudentType, originalstudentType)

        then:
        context.getTermination() != null
        context.getTermination().getIT0000() != null
        context.getTermination().getIT0000().getContractEndDate() == newStopXmlCalendar

        context.getChangeStudent() != null
        context.getChangeStudent().getIT9753() != null
        context.getChangeStudent().getIT9753().getEmploymentAddressRefNumber() == "124"
        context.getChangeStudent().getGIT0016() != null
        context.getChangeStudent().getGIT0016().getPlanneddaysnbr() == "4"
    }

    def "Visit FlexiDayType"() {
        given:
        FlexiDayType flexiDayType = new FlexiDayType()
        flexiDayType.getWorkHours().add(workHours)

        when:
        updateHireVisitor.visit(flexiDayType)

        then:
        context.getChangeExtraFlexi() != null
        context.getChangeExtraFlexi().getIT2003() != null
        context.getChangeExtraFlexi().getIT2003().size() == 1
        context.getChangeExtraFlexi().getIT2003()[0].getValidFrom() == startXmlCalendar
        context.getChangeExtraFlexi().getIT2003()[0].getValidTo() == stopXmlCalendar
        context.getChangeExtraFlexi().getIT2003()[0].getStartTime() == startXmlCalendar
        context.getChangeExtraFlexi().getIT2003()[0].getEndTime() == stopXmlCalendar
        context.getChangeExtraFlexi().getIT2003()[0].getBreak() == "0,33"

    }

    def "Visit FlexiPeriodType"() {
        given:
        FlexiPeriodType flexiPeriodType = new FlexiPeriodType()
        flexiPeriodType.setEndDate(newStopXmlCalendar)

        when:
        updateHireVisitor.visit(flexiPeriodType)

        then:
        context.getTermination() != null
        context.getTermination().getIT0000() != null
        context.getTermination().getIT0000().getContractEndDate() == newStopXmlCalendar
    }

    def "Visit DefiniteTermType"() {
        given:
        DefiniteTermType definiteTermType = new DefiniteTermType()
        definiteTermType.setEndDate(newStopXmlCalendar)

        when:
        updateHireVisitor.visit(definiteTermType)

        then:
        context.getTermination() != null
        context.getTermination().getIT0000() != null
        context.getTermination().getIT0000().getContractEndDate() == newStopXmlCalendar
    }

    def "Visit IndefiniteTermType - change enddate"() {
        given:
        IndefiniteTermType originalIndefiniteTermType = new IndefiniteTermType()
        originalIndefiniteTermType.setEndDate(stopXmlCalendar)
        IndefiniteTermType updatedIndefiniteTermType = new IndefiniteTermType()
        updatedIndefiniteTermType.setEndDate(newStopXmlCalendar)

        when:
        updateHireVisitor.visit(updatedIndefiniteTermType, originalIndefiniteTermType)

        then:
        context.getTermination() != null
        context.getTermination().getIT0000() != null
        context.getTermination().getIT0000().getContractEndDate() == newStopXmlCalendar
    }

    def "Visit IndefiniteTermType - empty the enddate"() {
        given:
        IndefiniteTermType originalIndefiniteTermType = new IndefiniteTermType()
        originalIndefiniteTermType.setEndDate(stopXmlCalendar)
        IndefiniteTermType updatedIndefiniteTermType = new IndefiniteTermType()
        updatedIndefiniteTermType.setEndDate(null)

        when:
        updateHireVisitor.visit(updatedIndefiniteTermType, originalIndefiniteTermType)

        then:
        context.getDeleteTermination() != null
    }
}
