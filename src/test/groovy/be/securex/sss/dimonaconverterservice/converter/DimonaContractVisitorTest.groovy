package be.securex.sss.dimonaconverterservice.converter

import be.securex.sss.dimonaconverterservice.enums.ContingentType
import be.securex.sss.dimonaconverterservice.enums.EmployeeType
import be.securex.sss.dimonaconverterservice.xsd.dimonarequest.ContractSocialType
import be.securex.sss.dimonaconverterservice.xsd.dimonarequest.HourLineType
import be.securex.sss.dimonaconverterservice.xsd.dimonarequest.WorkHoursType
import spock.lang.Specification

import javax.xml.datatype.DatatypeConstants
import javax.xml.datatype.DatatypeFactory
import javax.xml.datatype.XMLGregorianCalendar

/**
 * Created by 8565 on 2/11/2015.
 */
class DimonaContractVisitorTest extends Specification {

    def "convert workhours without pauze"() {
        given:
        DimonaContractVisitorImpl visitorImpl = new DimonaContractVisitorImpl()

        XMLGregorianCalendar startXmlCalendar, stopXmlCalendar
        startXmlCalendar = DatatypeFactory.newInstance().newXMLGregorianCalendar(1970, 1, 1, 9, 0, 0, 0, DatatypeConstants.FIELD_UNDEFINED)
        stopXmlCalendar = DatatypeFactory.newInstance().newXMLGregorianCalendar(1970, 1, 2, 17, 55, 0, 0, DatatypeConstants.FIELD_UNDEFINED)

        List<be.securex.sss.dimonaconverterservice.xsd.v1.contractevent.WorkHoursType> workHoursTypeList = new ArrayList<>();
        be.securex.sss.dimonaconverterservice.xsd.v1.contractevent.WorkHoursType workHours1 = new be.securex.sss.dimonaconverterservice.xsd.v1.contractevent.WorkHoursType()
        workHours1.setStartDate(startXmlCalendar)
        workHours1.setStartTime(startXmlCalendar)
        workHours1.setEndDate(stopXmlCalendar)
        workHours1.setEndTime(stopXmlCalendar)
        workHoursTypeList.add(workHours1)

        WorkHoursType legacyWorkHours;

        when:
        legacyWorkHours = visitorImpl.convertWorkHours(workHoursTypeList);

        then:
        List<HourLineType> hourlines = legacyWorkHours.getHOURLINE();

        hourlines.size() == 1

        hourlines[0].getDateStart() == "01/01/1970"
        hourlines[0].getDateStop() == "02/01/1970"
        hourlines[0].getTimeStart() == 900
        hourlines[0].getTimeStop() == 1755
        hourlines[0].getPauze() == null
    }

    def "convert workhours with pauze"() {
        given:
        DimonaContractVisitorImpl visitorImpl = new DimonaContractVisitorImpl()

        XMLGregorianCalendar startXmlCalendar, stopXmlCalendar
        startXmlCalendar = DatatypeFactory.newInstance().newXMLGregorianCalendar(1970, 1, 1, 9, 0, 0, 0, DatatypeConstants.FIELD_UNDEFINED)
        stopXmlCalendar = DatatypeFactory.newInstance().newXMLGregorianCalendar(1970, 1, 1, 17, 55, 0, 0, DatatypeConstants.FIELD_UNDEFINED)

        List<be.securex.sss.dimonaconverterservice.xsd.v1.contractevent.WorkHoursType> workHoursTypeList = new ArrayList<>();
        be.securex.sss.dimonaconverterservice.xsd.v1.contractevent.WorkHoursType workHours1 = new be.securex.sss.dimonaconverterservice.xsd.v1.contractevent.WorkHoursType()
        workHours1.setStartDate(startXmlCalendar)
        workHours1.setStartTime(startXmlCalendar)
        workHours1.setEndDate(stopXmlCalendar)
        workHours1.setEndTime(stopXmlCalendar)
        workHours1.setBreak(DatatypeFactory.newInstance().newDuration(20 * 60 * 1000)) //milliseconds, 20 minutes
        workHoursTypeList.add(workHours1)

        WorkHoursType legacyWorkHours;

        when:
        legacyWorkHours = visitorImpl.convertWorkHours(workHoursTypeList);

        then:
        List<HourLineType> hourlines = legacyWorkHours.getHOURLINE();

        hourlines.size() == 1

        hourlines[0].getDateStart() == "01/01/1970"
        hourlines[0].getDateStop() == "01/01/1970"
        hourlines[0].getTimeStart() == 900
        hourlines[0].getTimeStop() == 1755
        hourlines[0].getPauze() == "20"
    }

    def "convert multiple workhours"() {
        given:
        DimonaContractVisitorImpl visitorImpl = new DimonaContractVisitorImpl()

        XMLGregorianCalendar start1XmlCalendar, stop1XmlCalendar, start2XmlCalendar, stop2XmlCalendar
        start1XmlCalendar = DatatypeFactory.newInstance().newXMLGregorianCalendar(1970, 1, 1, 9, 0, 0, 0, DatatypeConstants.FIELD_UNDEFINED)
        stop1XmlCalendar = DatatypeFactory.newInstance().newXMLGregorianCalendar(1970, 1, 1, 17, 55, 0, 0, DatatypeConstants.FIELD_UNDEFINED)
        start2XmlCalendar = DatatypeFactory.newInstance().newXMLGregorianCalendar(1970, 1, 1, 19, 5, 0, 0, DatatypeConstants.FIELD_UNDEFINED)
        stop2XmlCalendar = DatatypeFactory.newInstance().newXMLGregorianCalendar(1970, 1, 2, 22, 55, 0, 0, DatatypeConstants.FIELD_UNDEFINED)

        List<be.securex.sss.dimonaconverterservice.xsd.v1.contractevent.WorkHoursType> workHoursTypeList = new ArrayList<>();
        be.securex.sss.dimonaconverterservice.xsd.v1.contractevent.WorkHoursType workHours1 = new be.securex.sss.dimonaconverterservice.xsd.v1.contractevent.WorkHoursType()
        workHours1.setStartDate(start1XmlCalendar)
        workHours1.setStartTime(start1XmlCalendar)
        workHours1.setEndDate(stop1XmlCalendar)
        workHours1.setEndTime(stop1XmlCalendar)
        workHours1.setBreak(DatatypeFactory.newInstance().newDuration(20 * 60 * 1000)) //milliseconds, 20 minutes
        workHoursTypeList.add(workHours1)

        be.securex.sss.dimonaconverterservice.xsd.v1.contractevent.WorkHoursType workHours2 = new be.securex.sss.dimonaconverterservice.xsd.v1.contractevent.WorkHoursType()
        workHours2.setStartDate(start2XmlCalendar)
        workHours2.setStartTime(start2XmlCalendar)
        workHours2.setEndDate(stop2XmlCalendar)
        workHours2.setEndTime(stop2XmlCalendar)
        workHours2.setBreak(null)
        workHoursTypeList.add(workHours2)

        WorkHoursType legacyWorkHours;

        when:
        legacyWorkHours = visitorImpl.convertWorkHours(workHoursTypeList);

        then:
        List<HourLineType> hourlines = legacyWorkHours.getHOURLINE();

        hourlines.size() == 2

        hourlines[0].getDateStart() == "01/01/1970"
        hourlines[0].getDateStop() == "01/01/1970"
        hourlines[0].getTimeStart() == 900
        hourlines[0].getTimeStop() == 1755
        hourlines[0].getPauze() == "20"
        hourlines[1].getDateStart() == "01/01/1970"
        hourlines[1].getDateStop() == "02/01/1970"
        hourlines[1].getTimeStart() == 1905
        hourlines[1].getTimeStop() == 2255
        hourlines[1].getPauze() == null
    }

    def "convert hours with empty workhours"() {
        given:
        DimonaContractVisitorImpl visitorImpl = new DimonaContractVisitorImpl()
        XMLGregorianCalendar start1XmlCalendar, stop1XmlCalendar
        List<be.securex.sss.dimonaconverterservice.xsd.v1.contractevent.WorkHoursType> workHoursTypeList = new ArrayList<>();

        WorkHoursType legacyWorkHours;

        when:
        legacyWorkHours = visitorImpl.convertWorkHours(workHoursTypeList);

        then:
        legacyWorkHours.getHOURLINE().size() == 0
    }

    def "convert hours with null workhours"() {
        given:
        DimonaContractVisitorImpl visitorImpl = new DimonaContractVisitorImpl()

        WorkHoursType legacyWorkHours;

        when:
        legacyWorkHours = visitorImpl.convertWorkHours(null);

        then:
        legacyWorkHours.getHOURLINE().size() == 0
    }

    def "convert contractSocial for extra"() {
        given:
        DimonaContractVisitorImpl visitorImpl = new DimonaContractVisitorImpl()

        when:
        ContractSocialType contractSocialType = visitorImpl.getContractSocialType(EmployeeType.EXT, ContingentType.BINNEN_CONTINGENT, null, null)

        then:
        contractSocialType.getEmployeeType() == "EXT"
        contractSocialType.getHorecaContingentFlag() == "1"
        contractSocialType.getPlannedDaysNr() == null
    }

    def "convert contractSocial for flexi"() {
        given:
        DimonaContractVisitorImpl visitorImpl = new DimonaContractVisitorImpl()

        when:
        ContractSocialType contractSocialType = visitorImpl.getContractSocialType(EmployeeType.FLX, null, null, null)

        then:
        contractSocialType.getEmployeeType() == "FLX"
        contractSocialType.getHorecaContingentFlag() == null
        contractSocialType.getPlannedDaysNr() == null
    }

    def "convert contractSocial for student"() {
        given:
        DimonaContractVisitorImpl visitorImpl = new DimonaContractVisitorImpl()

        when:
        ContractSocialType contractSocialType = visitorImpl.getContractSocialType(EmployeeType.STU, ContingentType.BINNEN_CONTINGENT, 10, null)

        then:
        contractSocialType.getEmployeeType() == "STU"
        contractSocialType.getHorecaContingentFlag() == "1"
        contractSocialType.getPlannedDaysNr() == "10"
        contractSocialType.getPlannedHoursNr() == null
    }    

    def "convert contractSocial for student with hours"() {
        given:
        DimonaContractVisitorImpl visitorImpl = new DimonaContractVisitorImpl()

        when:
        ContractSocialType contractSocialType = visitorImpl.getContractSocialType(EmployeeType.STU, ContingentType.BINNEN_CONTINGENT, null, 100)

        then:
        contractSocialType.getEmployeeType() == "STU"
        contractSocialType.getHorecaContingentFlag() == "1"
        contractSocialType.getPlannedDaysNr() == null
        contractSocialType.getPlannedHoursNr() == "100"
    }
}
