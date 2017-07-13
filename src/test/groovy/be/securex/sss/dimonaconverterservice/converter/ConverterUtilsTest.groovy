package be.securex.sss.dimonaconverterservice.converter

import spock.lang.Specification

import javax.xml.datatype.DatatypeConfigurationException
import javax.xml.datatype.DatatypeFactory
import javax.xml.datatype.XMLGregorianCalendar
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneOffset

/**
 * Created by 8565 on 2/11/2015.
 */
class ConverterUtilsTest extends Specification {

    def "toDdmmyyyyString"() {
        given:
        GregorianCalendar gCalendar = new GregorianCalendar()
        gCalendar.setTime(Date.from(LocalDateTime.of(1970, 12, 25, 11, 55).toInstant(ZoneOffset.ofHours(1))))
        XMLGregorianCalendar xmlCalendar
        try {
            xmlCalendar = DatatypeFactory.newInstance().newXMLGregorianCalendar(gCalendar)
        } catch (DatatypeConfigurationException ex) {
            throw ex
        }
        def dateString

        when:
        dateString = ConverterUtils.toDdmmyyyyString(xmlCalendar)

        then:
        dateString == '25/12/1970'
    }

    def "toDdmmyyyyString with null date"() {
        when:
        def dateString = ConverterUtils.toDdmmyyyyString(null)

        then:
        dateString == null
    }

    def "toHhmmString"() {
        given:
        GregorianCalendar gCalendar = new GregorianCalendar()
        gCalendar.setTime(Date.from(LocalDateTime.of(1970, 12, 25, 17, 55).toInstant(ZoneOffset.ofHours(1))))
        XMLGregorianCalendar xmlCalendar
        try {
            xmlCalendar = DatatypeFactory.newInstance().newXMLGregorianCalendar(gCalendar)
        } catch (DatatypeConfigurationException ex) {
            throw ex
        }
        def dateString

        when:
        dateString = ConverterUtils.toHhmmString(xmlCalendar)

        then:
        dateString == '1755'
    }

    def "toHhmmString with null hours"() {
        given:
        GregorianCalendar gCalendar = new GregorianCalendar()
        gCalendar.setTime(Date.from(LocalDate.of(1970, 12, 25).atStartOfDay().toInstant(ZoneOffset.ofHours(1))))
        XMLGregorianCalendar xmlCalendar
        try {
            xmlCalendar = DatatypeFactory.newInstance().newXMLGregorianCalendar(gCalendar)
        } catch (DatatypeConfigurationException ex) {
            throw ex
        }
        def dateString

        when:
        dateString = ConverterUtils.toHhmmString(xmlCalendar)

        then:
        dateString == '0000'
    }

    def "toHhmmString with null date"() {
        when:
        def dateString = ConverterUtils.toHhmmString(null)

        then:
        dateString == null
    }

    def "toYyyymmddString"() {
        given:
        GregorianCalendar gCalendar = new GregorianCalendar()
        gCalendar.setTime(Date.from(LocalDateTime.of(1970, 12, 25, 11, 55).toInstant(ZoneOffset.ofHours(1))))
        XMLGregorianCalendar xmlCalendar
        try {
            xmlCalendar = DatatypeFactory.newInstance().newXMLGregorianCalendar(gCalendar)
        } catch (DatatypeConfigurationException ex) {
            throw ex
        }
        def dateString

        when:
        dateString = ConverterUtils.toYyyymmddString(xmlCalendar)

        then:
        dateString == '1970-12-25'
    }

    def "toYyyymmddString with null date"() {
        when:
        def dateString = ConverterUtils.toYyyymmddString(null)

        then:
        dateString == null
    }

    def "toDecimalHour"() {
        when:
        String result = ConverterUtils.toDecimalHour(DatatypeFactory.newInstance().newDuration(duration))

        then:
        result == target

        where:
        duration           | target
        "P0Y0M0DT0H10M0S"  | "0,17"
        "P0Y0M0DT0H0M0S"   | "0,0"
        "P0Y0M0DT1H10M0S"  | "1,17"
        "P0Y0M0DT1H0M0S"   | "1,0"
        "P0Y0M0DT1H30M0S"  | "1,50"
        "P0Y0M0DT0H60M0S"  | "1,0"
        "P0Y0M0DT0H87M0S"  | "1,45"
        "P0Y0M0DT0H119M0S" | "1,98"
        "P0Y0M0DT0H120M0S" | "2,0"
        "P0Y0M0DT0H121M0S" | "2,2"
        "P0Y0M0DT0H100M0S" | "1,67"
    }

}
