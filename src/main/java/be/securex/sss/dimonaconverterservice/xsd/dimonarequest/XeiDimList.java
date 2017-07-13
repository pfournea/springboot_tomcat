package be.securex.sss.dimonaconverterservice.xsd.dimonarequest;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.Serializable;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 6148 on 24/11/2015.
 */
public class XeiDimList implements Serializable {
    private List<String> xeiDims = new ArrayList<>();

    public void add(XeiDim xeiDim) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(XeiDim.class);
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
        final StringWriter stringWriter = new StringWriter();

        jaxbMarshaller.marshal(xeiDim, stringWriter);

        String xeiDimXml =  stringWriter.toString();
        xeiDims.add(xeiDimXml);
    }

    public List<String> getXeiDims() {
        return xeiDims;
    }
}
