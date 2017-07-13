package be.securex.sss.dimonaconverterservice.converter

import be.securex.sss.dimonaconverterservice.enums.DeclarationType
import be.securex.sss.dimonaconverterservice.xsd.v1.contractevent.ContractInfoType
import be.securex.sss.dimonaconverterservice.xsd.v1.contractevent.ContractType
import be.securex.sss.dimonaconverterservice.xsd.v1.contractevent.StudentType
import be.securex.sss.dimonaconverterservice.xsd.v1.contractevent.UpdateContractType
import be.securex.sss.dimonaconverterservice.xsd.v1.contractevent.XtraType
import spock.lang.Specification

import javax.xml.datatype.DatatypeConstants
import javax.xml.datatype.DatatypeFactory

/**
 * Created by 8565 on 24/12/2015.
 */
class UpdateConversionContextTest extends Specification {

    def updateContractTypeStudent
    def updateContractTypeExtra
    def date_19700101 = DatatypeFactory.newInstance().newXMLGregorianCalendar(1970, 1, 1, 9, 0, 0, 0, DatatypeConstants.FIELD_UNDEFINED)
    def date_19700102 = DatatypeFactory.newInstance().newXMLGregorianCalendar(1970, 1, 2, 9, 0, 0, 0, DatatypeConstants.FIELD_UNDEFINED)

    def setup() {
        updateContractTypeStudent = new UpdateContractType()
        updateContractTypeStudent.setOriginalContractInfo(new ContractInfoType())
        updateContractTypeStudent.getOriginalContractInfo().setContract(new ContractType())
        updateContractTypeStudent.getOriginalContractInfo().getContract().setStudent(new StudentType())
        updateContractTypeStudent.getOriginalContractInfo().getContract().getStudent().setNumberOfDays(1)
        updateContractTypeStudent.getOriginalContractInfo().getContract().getStudent().setFAONumber('123')
        updateContractTypeStudent.getOriginalContractInfo().getContract().getStudent().setEndDate(date_19700101)

        updateContractTypeStudent.setContractInfo(new ContractInfoType())
        updateContractTypeStudent.getContractInfo().setContract(new ContractType())
        updateContractTypeStudent.getContractInfo().getContract().setStudent(new StudentType())
        updateContractTypeStudent.getContractInfo().getContract().getStudent().setNumberOfDays(1)
        updateContractTypeStudent.getContractInfo().getContract().getStudent().setFAONumber('123')
        updateContractTypeStudent.getContractInfo().getContract().getStudent().setEndDate(date_19700101)

        updateContractTypeExtra = new UpdateContractType()
        updateContractTypeExtra.setOriginalContractInfo(new ContractInfoType())
        updateContractTypeExtra.getOriginalContractInfo().setContract(new ContractType())
        updateContractTypeExtra.getOriginalContractInfo().getContract().setXtra((new XtraType()))
        updateContractTypeExtra.getOriginalContractInfo().getContract().getXtra().setDate(date_19700101)

        updateContractTypeExtra.setContractInfo(new ContractInfoType())
        updateContractTypeExtra.getContractInfo().setContract(new ContractType())
        updateContractTypeExtra.getContractInfo().getContract().setXtra(new XtraType())
        updateContractTypeExtra.getContractInfo().getContract().getXtra().setDate(date_19700101)
    }

    def "test Student met wijziging einddatum - tewerkstellingsplaats - aantaldagen"() {
        given:
        updateContractTypeStudent.getContractInfo().getContract().getStudent().setNumberOfDays(2)
        updateContractTypeStudent.getContractInfo().getContract().getStudent().setFAONumber('321')
        updateContractTypeStudent.getContractInfo().getContract().getStudent().setEndDate(date_19700102)
        def updateConversionContext = UpdateConversionContext.create(updateContractTypeStudent)

        when:
        def declarationTypes = updateConversionContext.getDeclarationTypes()

        then:
        declarationTypes.contains(DeclarationType.SIGNALITIEK)
        declarationTypes.contains(DeclarationType.WIJZIGING)
    }

    def "test Student met wijziging einddatum - aantaldagen"() {
        given:
        updateContractTypeStudent.getContractInfo().getContract().getStudent().setNumberOfDays(2)
        updateContractTypeStudent.getContractInfo().getContract().getStudent().setEndDate(date_19700102)
        def updateConversionContext = UpdateConversionContext.create(updateContractTypeStudent)

        when:
        def declarationTypes = updateConversionContext.getDeclarationTypes()

        then:
        declarationTypes.contains(DeclarationType.SIGNALITIEK)
        declarationTypes.contains(DeclarationType.WIJZIGING)
    }

    def "test Student met wijziging aantaldagen"() {
        given:
        updateContractTypeStudent.getContractInfo().getContract().getStudent().setNumberOfDays(2)
        def updateConversionContext = UpdateConversionContext.create(updateContractTypeStudent)

        when:
        def declarationTypes = updateConversionContext.getDeclarationTypes()

        then:
        declarationTypes.contains(DeclarationType.SIGNALITIEK)
        declarationTypes.size() == 1
    }

    def "test Student met wijziging tewerkstellingsplaats"() {
        given:
        updateContractTypeStudent.getContractInfo().getContract().getStudent().setFAONumber('321')
        def updateConversionContext = UpdateConversionContext.create(updateContractTypeStudent)

        when:
        def declarationTypes = updateConversionContext.getDeclarationTypes()

        then:
        declarationTypes.contains(DeclarationType.SIGNALITIEK)
        declarationTypes.size() == 1
    }

    def "test Student met wijziging einddatum"() {
        given:
        updateContractTypeStudent.getContractInfo().getContract().getStudent().setEndDate(date_19700102)
        def updateConversionContext = UpdateConversionContext.create(updateContractTypeStudent)

        when:
        def declarationTypes = updateConversionContext.getDeclarationTypes()

        then:
        declarationTypes.contains(DeclarationType.WIJZIGING)
        declarationTypes.size() == 1
    }

    def "test Extra met wijziging einddatum"() {
        given:
        updateContractTypeExtra.getContractInfo().getContract().getXtra().setDate(date_19700102)
        def updateConversionContext = UpdateConversionContext.create(updateContractTypeExtra)

        when:
        def declarationTypes = updateConversionContext.getDeclarationTypes()

        then:
        declarationTypes.contains(DeclarationType.WIJZIGING)
        declarationTypes.size() == 1
    }

}
