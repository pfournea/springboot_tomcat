//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.11 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.10.19 at 01:40:01 PM CEST 
//


package be.securex.sss.dimonaconverterservice.xsd.dimonarequest;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for AFFILIATIONType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AFFILIATIONType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element ref="{}EXPL_NR"/&gt;
 *         &lt;element ref="{}EXPL_COMP_NAME" minOccurs="0"/&gt;
 *         &lt;element name="EXPL_ADDRESS" type="{}ADDRESSType" minOccurs="0"/&gt;
 *         &lt;element ref="{}FAO_NR" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AFFILIATIONType", propOrder = {
    "explNr",
    "explCompName",
    "explAddress",
    "faoNr"
})
public class AffiliationType {

    @XmlElement(name = "EXPL_NR", required = true)
    protected String explNr;
    @XmlElement(name = "EXPL_COMP_NAME")
    protected String explCompName;
    @XmlElement(name = "EXPL_ADDRESS")
    protected AddressType explAddress;
    @XmlElement(name = "FAO_NR")
    protected String faoNr;

    public String getExplNr() {
        return explNr;
    }

    public void setExplNr(String explNr) {
        this.explNr = explNr;
    }

    public String getExplCompName() {
        return explCompName;
    }

    public void setExplCompName(String explCompName) {
        this.explCompName = explCompName;
    }

    public AddressType getExplAddress() {
        return explAddress;
    }

    public void setExplAddress(AddressType explAddress) {
        this.explAddress = explAddress;
    }

    public String getFaoNr() {
        return faoNr;
    }

    public void setFaoNr(String faoNr) {
        this.faoNr = faoNr;
    }
}
