package be.securex.sss.dimonaconverterservice.service

import spock.lang.Specification

/**
 * Created by 6060 on 11/07/2017.
 */
class RemoveSelfClosingIT0100TagsServiceTest extends Specification {
    RemoveSelfClosingIT0100TagsService service

    def setup() {
        service = new RemoveSelfClosingIT0100TagsService()
    }

    def xml = "{\"hires\":[\"<?xml version=\\\"1.0\\\" encoding=\\\"UTF-8\\\" standalone=\\\"yes\\\"?><Employee><ScenarioInfo><KeyInfo><UpdateUser>HIREAPP</UpdateUser><UpdateTimestamp></UpdateTimestamp><Firm>123</Firm><Id><UUID>534d9621-3b3d-4d46-b74b-388011872b43</UUID></Id></KeyInfo><ScenarioInfoContent><Hire><IT0000><ContractStartDate>2016-01-01</ContractStartDate><ContractEndDate>2016-01-31</ContractEndDate></IT0000><IT0001><EmployeeGroup>1</EmployeeGroup><EmployeeSubgroup>03</EmployeeSubgroup><WorkContract>ZZ</WorkContract><Departement>0003</Departement></IT0001><IT0002><Title>1</Title><FirstName>firstname</FirstName><LastName>lastname</LastName><Gender>1</Gender><BirthDate>2015-10-16</BirthDate><CountryOfBirth>000</CountryOfBirth><Nationality>000</Nationality></IT0002><IT0100/><IT0016><ContractType>(2</ContractType><LabourCommission>302</LabourCommission></IT0016><IT0006><Street>rue de</Street><HouseNumber>1</HouseNumber><PostalCode>75008</PostalCode><City>Paris</City><Country>004</Country></IT0006></Hire></ScenarioInfoContent></ScenarioInfo></Employee>\"]}"

    def "Test perform"() {
        given:

        when:
        def result = service.perform(xml)

        then:
        result == "{\"hires\":[\"<?xml version=\\\"1.0\\\" encoding=\\\"UTF-8\\\" standalone=\\\"yes\\\"?><Employee><ScenarioInfo><KeyInfo><UpdateUser>HIREAPP</UpdateUser><UpdateTimestamp></UpdateTimestamp><Firm>123</Firm><Id><UUID>534d9621-3b3d-4d46-b74b-388011872b43</UUID></Id></KeyInfo><ScenarioInfoContent><Hire><IT0000><ContractStartDate>2016-01-01</ContractStartDate><ContractEndDate>2016-01-31</ContractEndDate></IT0000><IT0001><EmployeeGroup>1</EmployeeGroup><EmployeeSubgroup>03</EmployeeSubgroup><WorkContract>ZZ</WorkContract><Departement>0003</Departement></IT0001><IT0002><Title>1</Title><FirstName>firstname</FirstName><LastName>lastname</LastName><Gender>1</Gender><BirthDate>2015-10-16</BirthDate><CountryOfBirth>000</CountryOfBirth><Nationality>000</Nationality></IT0002><IT0016><ContractType>(2</ContractType><LabourCommission>302</LabourCommission></IT0016><IT0006><Street>rue de</Street><HouseNumber>1</HouseNumber><PostalCode>75008</PostalCode><City>Paris</City><Country>004</Country></IT0006></Hire></ScenarioInfoContent></ScenarioInfo></Employee>\"]}"
    }
}
