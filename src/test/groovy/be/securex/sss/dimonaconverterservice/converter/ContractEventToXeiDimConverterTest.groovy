package be.securex.sss.dimonaconverterservice.converter

import be.securex.sss.dimonaconverterservice.xsd.dimonarequest.PayGroupType
import be.securex.sss.dimonaconverterservice.xsd.v1.contractevent.ContractInfoType
import be.securex.sss.dimonaconverterservice.xsd.v1.contractevent.FDCPType
import spock.lang.Specification

/**
 * Created by 8565 on 24/10/2015.
 */


class ContractEventToXeiDimConverterTest extends Specification {

    def "convert paygroup"() {
        given:
        ContractEventToXeiDimConverter converter = new ContractEventToXeiDimConverter()

        FDCPType fdcpType = new FDCPType()
        fdcpType.setCategory("1")
        fdcpType.setDepartmentNumber("01")
        fdcpType.setFirmNumber("0000012345")

        ContractInfoType contractInfoType = new ContractInfoType()
        contractInfoType.setFDCP(fdcpType)
        PayGroupType payGroupType

        when:
        payGroupType = converter.getPayGroup(contractInfoType)

        then:
        payGroupType.getFirmNr() == "0000012345"
        payGroupType.getDepNr() == "01"
        payGroupType.getCatNr() == "1"
    }

    def "convert paygroup with empty FDCP"() {
        given:
        ContractEventToXeiDimConverter converter = new ContractEventToXeiDimConverter()

        ContractInfoType contractInfoType = new ContractInfoType()
        PayGroupType payGroupType

        when:
        payGroupType = converter.getPayGroup(contractInfoType)

        then:
        payGroupType.getFirmNr() == null
        payGroupType.getDepNr() == null
        payGroupType.getCatNr() == null
    }
}
