package be.securex.sss.dimonaconverterservice.converter

import be.securex.sss.dimonaconverterservice.xsd.v1.contractevent.*
import be.securex.sss.dimonaconverterservice.xsd.v4.hronline.Employee
import be.securex.sss.dimonaconverterservice.xsd.v4.hronline.HireCType
import be.securex.sss.dimonaconverterservice.xsd.v4.hronline.ScenarioInfoCType
import be.securex.sss.dimonaconverterservice.xsd.v4.hronline.ScenarioInfoContentCType
import spock.lang.Specification

import javax.xml.datatype.DatatypeConstants
import javax.xml.datatype.DatatypeFactory
import javax.xml.datatype.XMLGregorianCalendar

/**
 * Created by 8565 on 11/05/2016.
 */
class NewHireContractVisitorImplTest extends Specification {


    XMLGregorianCalendar startXmlCalendar = DatatypeFactory.newInstance().newXMLGregorianCalendar(1970, 1, 1, 9, 0, 0, 0, DatatypeConstants.FIELD_UNDEFINED)
    XMLGregorianCalendar stopXmlCalendar = DatatypeFactory.newInstance().newXMLGregorianCalendar(1970, 1, 2, 17, 55, 0, 0, DatatypeConstants.FIELD_UNDEFINED)
    WorkHoursType workHours = new WorkHoursType()
    HireCType hire = new HireCType()
    Employee employee
    HireContractVisitor newHireVisitor

    def setup() {
        workHours.setStartDate(startXmlCalendar)
        workHours.setStartTime(startXmlCalendar)
        workHours.setEndDate(stopXmlCalendar)
        workHours.setEndTime(stopXmlCalendar)
        workHours.setBreak(DatatypeFactory.newInstance().newDuration(20 * 60 * 1000))

        hire.setIT0000(new HireCType.IT0000())
        hire.setIT0001(new HireCType.IT0001())
        hire.setIT0002(new HireCType.IT0002())
        hire.setIT0100(new HireCType.IT0100())
        hire.setIT0016(new HireCType.IT0016())

        ScenarioInfoContentCType scenarioInfoContentCType = new ScenarioInfoContentCType()
        scenarioInfoContentCType.setHire(hire)

        ScenarioInfoCType scenarioInfoCType = new ScenarioInfoCType()
        scenarioInfoCType.setScenarioInfoContent(scenarioInfoContentCType)
        employee = new Employee()
        employee.setScenarioInfo(scenarioInfoCType)
        newHireVisitor = new NewHireContractVisitorImpl(employee)
    }

    def "Visit ExtraType"() {
        given:
        XtraType xtraType = new XtraType()
        xtraType.setDate(startXmlCalendar)
        xtraType.getWorkHours().add(workHours)
        def objectsReturningNothing = ['IT0109', 'IT0006', 'IT9753', 'GIT0016']

        when:
        newHireVisitor.visit(xtraType)

        then:
        hire.getIT0000().getContractStartDate() == startXmlCalendar
        hire.getIT0000().getContractEndDate() == startXmlCalendar
        hire.getIT0001().getEmployeeGroup() == "1"
        hire.getIT0001().workContract == "XC"
        hire.getIT0016().getContractType() == "(2"
        hire.getIT0016().getLabourCommission() == "302"
        hire.getIT2003().size() == 1
        HireCType.IT2003 it2003 = hire.getIT2003()[0]
        it2003.getStartTime() == startXmlCalendar
        it2003.getValidFrom() == startXmlCalendar
        it2003.getEndTime() == stopXmlCalendar
        it2003.getValidTo() == startXmlCalendar
        it2003.isEndTimeOnNextDay()
        hire.getIT0007().getCONTINGENT() == "1"
        objectsReturningNothing.every {hire."get$it"() == null || hire."get$it"().size() == 0}
    }

    def "Visit StudentType"() {
        given:
        StudentType studentType = new StudentType()
        studentType.setStartDate(startXmlCalendar)
        studentType.setEndDate(stopXmlCalendar)
        studentType.setFAONumber("123")
        studentType.setNumberOfDays(3)
        def objectsReturningNothing = ['IT0109', 'IT0006', 'IT2003', 'IT0007']

        when:
        newHireVisitor.visit(studentType, null)

        then:
        hire.getIT0000().getContractStartDate() == startXmlCalendar
        hire.getIT0000().getContractEndDate() == stopXmlCalendar
        hire.getIT0001().getEmployeeGroup() == "B"
        hire.getIT0001().workContract == "XP"
        hire.getIT0016().getContractType() == "(2"
        hire.getIT0016().getLabourCommission() == "302"
        hire.getIT9753().getEmploymentAddressRefNumber() == "123"
        hire.getGIT0016().getPlanneddaysnbr() == "3"
        objectsReturningNothing.every {hire."get$it"() == null || hire."get$it"().size() == 0}
    }

    def "Visit FlexiDayType"() {
        given:
        FlexiDayType flexiDayType = new FlexiDayType()
        flexiDayType.setDate(startXmlCalendar)
        flexiDayType.getWorkHours().add(workHours)
        def objectsReturningNothing = ['IT0109', 'IT0006', 'IT9753', 'IT0007', 'GIT0016']

        when:
        newHireVisitor.visit(flexiDayType)

        then:
        hire.getIT0000().getContractStartDate() == startXmlCalendar
        hire.getIT0000().getContractEndDate() == startXmlCalendar
        hire.getIT0001().getEmployeeGroup() == "1"
        hire.getIT0001().workContract == "YO"
        hire.getIT0016().getContractType() == "(2"
        hire.getIT0016().getLabourCommission() == "302"
        objectsReturningNothing.every {hire."get$it"() == null || hire."get$it"().size() == 0}
    }

    def "Visit FlexiPeriodType"() {
        given:
        FlexiPeriodType flexiPeriodType = new FlexiPeriodType()
        flexiPeriodType.setStartDate(startXmlCalendar)
        flexiPeriodType.setEndDate(stopXmlCalendar)
        def objectsReturningNothing = ['IT0109', 'IT0006', 'IT2003', 'IT9753', 'IT0007', 'GIT0016']

        when:
        newHireVisitor.visit(flexiPeriodType)

        then:
        hire.getIT0000().getContractStartDate() == startXmlCalendar
        hire.getIT0000().getContractEndDate() == stopXmlCalendar
        hire.getIT0001().getEmployeeGroup() == "1"
        hire.getIT0001().workContract == "YO"
        hire.getIT0016().getContractType() == "(2"
        hire.getIT0016().getLabourCommission() == "302"
        objectsReturningNothing.every {hire."get$it"() == null || hire."get$it"().size() == 0}
    }

    def "Visit DefiniteTermType"() {
        given:
        DefiniteTermType definiteTermType = new DefiniteTermType()
        definiteTermType.setStartDate(startXmlCalendar)
        definiteTermType.setEndDate(stopXmlCalendar)
        def objectsReturningNothing = ['IT0109', 'IT0006', 'IT2003', 'IT9753', 'IT0007', 'GIT0016']

        when:
        newHireVisitor.visit(definiteTermType)

        then:
        hire.getIT0000().getContractStartDate() == startXmlCalendar
        hire.getIT0000().getContractEndDate() == stopXmlCalendar
        hire.getIT0001().getEmployeeGroup() == "1"
        hire.getIT0001().workContract == "ZZ"
        hire.getIT0016().getContractType() == "(2"
        hire.getIT0016().getLabourCommission() == "302"
        objectsReturningNothing.every {hire."get$it"() == null || hire."get$it"().size() == 0}
    }

    def "Visit IndefiniteTermType"() {
        given:
        IndefiniteTermType indefiniteTermType = new IndefiniteTermType()
        indefiniteTermType.setStartDate(startXmlCalendar)
        indefiniteTermType.setEndDate(stopXmlCalendar)
        def objectsReturningNothing = ['IT0109', 'IT0006', 'IT2003', 'IT9753', 'IT0007', 'GIT0016']

        when:
        newHireVisitor.visit(indefiniteTermType, null)

        then:
        hire.getIT0000().getContractStartDate() == startXmlCalendar
        hire.getIT0000().getContractEndDate() == stopXmlCalendar
        hire.getIT0001().getEmployeeGroup() == "1"
        hire.getIT0001().workContract == "ZZ"
        hire.getIT0016().getContractType() == "(1"
        hire.getIT0016().getLabourCommission() == "302"
        objectsReturningNothing.every {hire."get$it"() == null || hire."get$it"().size() == 0}
    }
}
