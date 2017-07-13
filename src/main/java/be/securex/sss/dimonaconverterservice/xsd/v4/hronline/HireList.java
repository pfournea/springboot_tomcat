package be.securex.sss.dimonaconverterservice.xsd.v4.hronline;

import be.securex.sss.dimonaconverterservice.service.RemoveSelfClosingIT0100TagsService;

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

    public void add(Employee employee, RemoveSelfClosingIT0100TagsService removeSelfClosingIT0100TagsService) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(Employee.class);
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
        final StringWriter stringWriter = new StringWriter();

        jaxbMarshaller.marshal(employee, stringWriter);

        String employeeXml = stringWriter.toString();
        employeeXml = removeSelfClosingIT0100TagsService.perform(employeeXml);
        hires.add(employeeXml);
    }

    public List<String> getHires() {
        return hires;
    }
}
