package be.securex.sss.dimonaconverterservice.controller;

import be.securex.sss.dimonaconverterservice.xsd.v1.contractevent.ContractEvent;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.validation.SchemaFactory;

/**
 * Created by Peter on 13/07/2017.
 */
@RestController
@RequestMapping(value = "/api/performance")
public class PerformanceController {

    @RequestMapping(value = "/jaxb")
    public String getJaxbContex() throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(ContractEvent.class);
        return "OK";
    }

    @RequestMapping(value = "/schema")
    public String getSchemaFactory() {
        SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        return "OK";
    }
}
