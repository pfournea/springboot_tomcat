package be.securex.sss.dimonaconverterservice.validator;

import be.securex.sss.dimonaconverterservice.xsd.v1.contractevent.ContractEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.util.JAXBSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.IOException;
import java.net.URL;

/**
 * Created by 6148 on 20/10/2015.
 */
@Component("contractEventValidator")
public class DefaultContractEventValidator implements ContractEventValidator {
    private static final Logger logger = LoggerFactory.getLogger(DefaultContractEventValidator.class);
    private static JAXBContext jaxbContext;
    static {
        try {
            jaxbContext = JAXBContext.newInstance(ContractEvent.class);
        } catch (JAXBException e) {
            logger.error("Could not create jaxbContext",e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean validate(ContractEvent contractEvent) throws Exception {
        JAXBSource source = new JAXBSource(jaxbContext, contractEvent);

        SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        Schema schema = sf.newSchema(getXsd());

        Validator validator = schema.newValidator();
        validator.setErrorHandler(new ContractEventErrorHandler());
        validator.validate(source);
        return true;
    }

    private URL getXsd() throws IOException {
        Resource resource = new ClassPathResource("xsd/contractevent/ContractEvent.xsd");
        return resource.getURL();
    }
}

class ContractEventErrorHandler implements ErrorHandler {
    Logger logger = LoggerFactory.getLogger(ContractEventValidator.class);

    public void warning(SAXParseException exception) throws SAXException {
        logger.info("\nWARNING", exception);
    }

    public void error(SAXParseException exception) throws SAXException {
        logger.info("\nERROR", exception);
        throw exception;
    }

    public void fatalError(SAXParseException exception) throws SAXException {
        logger.info("\nFATAL ERROR", exception);
        throw exception;
    }
}
