package be.securex.sss.dimonaconverterservice.converter;

import be.securex.sss.dimonaconverterservice.xsd.v4.hronline.KeyInfoCType;
import org.apache.commons.lang3.StringUtils;

import java.util.UUID;

/**
 * Created by 8565 on 23/02/2016.
 */
public class KeyInfoIdBuilder {

    private UUID uuid;

    private String sapPerNr;

    public KeyInfoIdBuilder uuid(UUID uuid) {
        this.uuid = uuid;
        return this;
    }

    public KeyInfoIdBuilder sapPerNr(String sapPerNr) {
        this.sapPerNr = sapPerNr;
        return this;
    }

    public KeyInfoCType.Id build() {
        KeyInfoCType.Id id = new KeyInfoCType.Id();
        if (uuid != null && StringUtils.isNotBlank(uuid.toString())) {
            id.setUUID(uuid.toString());
            }
        id.setPerNr(sapPerNr);
        return id;
    }

}
