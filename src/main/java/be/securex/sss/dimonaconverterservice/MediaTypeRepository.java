package be.securex.sss.dimonaconverterservice;

import org.springframework.http.MediaType;

/**
 * Created by 6060 on 30/09/2015.
 */
public class MediaTypeRepository {
    public static final String CONTRACTEVENT_MEDIA_TYPE_STRING = "application/vnd.be.securex.sss.dimonaconverterservice.xsd.v1.contractevent.ContractEventV1+json";
    public static final MediaType CONTRACTEVENT_MEDIA_TYPE = MediaType.valueOf(CONTRACTEVENT_MEDIA_TYPE_STRING);
}
