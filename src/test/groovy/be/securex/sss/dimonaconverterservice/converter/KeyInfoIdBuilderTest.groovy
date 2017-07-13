package be.securex.sss.dimonaconverterservice.converter


import be.securex.sss.dimonaconverterservice.xsd.v4.hronline.KeyInfoCType
import spock.lang.Ignore
import spock.lang.Specification

/**
 * Created by 8565 on 23/02/2016.
 */

class KeyInfoIdBuilderTest extends Specification {

    def "uuid and combinedNgaIdentifier given"() {
        given:
        def keyInfoIdBuilder = new KeyInfoIdBuilder()
        def uuid = UUID.fromString("01234567-9ABC-DEF0-1234-56789ABCDEF0")
        def sapPerNr = "sapPerNr_1"
        KeyInfoCType.Id id

        when:
        id = keyInfoIdBuilder.uuid(uuid).sapPerNr(sapPerNr).build()

        then:
        id.UUID == uuid.toString()
        id.perNr == "sapPerNr_1"
    }

    def "only uuid given"() {
        given:
        def keyInfoIdBuilder = new KeyInfoIdBuilder();
        def uuid = UUID.fromString("01234567-9ABC-DEF0-1234-56789ABCDEF0")
        KeyInfoCType.Id id

        when:
        id = keyInfoIdBuilder.uuid(uuid).build()

        then:
        id.compositeId == null
        id.UUID.equalsIgnoreCase("01234567-9ABC-DEF0-1234-56789ABCDEF0")
        id.perNr == null
    }

    def "only sapPerNr given"() {
        given:
        def keyInfoIdBuilder = new KeyInfoIdBuilder();
        def sapPerNr = "sapPerNr_1"
        KeyInfoCType.Id id

        when:
        id = keyInfoIdBuilder.sapPerNr(sapPerNr).build()

        then:
        id.compositeId == null
        id.UUID == null
        id.perNr == "sapPerNr_1"
    }


}
