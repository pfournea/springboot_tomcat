package be.securex.sss.dimonaconverterservice.xsd.v4.hronline;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.Serializable;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 8565 on 14/01/2016.
 */
public class HireList implements Serializable {

    private List<String> hires = new ArrayList<>();

    public void add(Employee employee) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(Employee.class);
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
        final StringWriter stringWriter = new StringWriter();

        jaxbMarshaller.marshal(employee, stringWriter);

        String employeeXml = stringWriter.toString();
        hires.add(employeeXml);
    }

    public List<String> getHires() {
        return hires;
    }
}
