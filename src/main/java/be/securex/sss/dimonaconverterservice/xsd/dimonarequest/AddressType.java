//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.11 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.03.07 at 04:43:53 PM CET 
//


package be.securex.sss.dimonaconverterservice.xsd.dimonarequest;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ADDRESSType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ADDRESSType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element ref="{}STREET_FULL"/&gt;
 *         &lt;element ref="{}STREET" minOccurs="0"/&gt;
 *         &lt;element ref="{}HOUSE_NR" minOccurs="0"/&gt;
 *         &lt;element ref="{}BOX_NR" minOccurs="0"/&gt;
 *         &lt;element ref="{}COMPLEMENT" minOccurs="0"/&gt;
 *         &lt;element ref="{}ZIP"/&gt;
 *         &lt;element ref="{}LOCALITY" minOccurs="0"/&gt;
 *         &lt;element ref="{}COUNTRY"/&gt;
 *         &lt;element ref="{}TEL" minOccurs="0"/&gt;
 *         &lt;element ref="{}FAX" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ADDRESSType", propOrder = {
    "streetFull",
    "street",
    "houseNr",
    "boxNr",
    "complement",
    "zip",
    "locality",
    "country",
    "tel",
    "fax"
})
public class AddressType {

    @XmlElement(name = "STREET_FULL", required = true)
    protected String streetFull;
    @XmlElement(name = "STREET")
    protected String street;
    @XmlElement(name = "HOUSE_NR")
    protected String houseNr;
    @XmlElement(name = "BOX_NR")
    protected String boxNr;
    @XmlElement(name = "COMPLEMENT")
    protected String complement;
    @XmlElement(name = "ZIP", required = true)
    protected String zip;
    @XmlElement(name = "LOCALITY")
    protected String locality;
    @XmlElement(name = "COUNTRY", required = true)
    protected String country;
    @XmlElement(name = "TEL")
    protected String tel;
    @XmlElement(name = "FAX")
    protected String fax;

    public String getStreetFull() {
        return streetFull;
    }

    public void setStreetFull(String streetFull) {
        this.streetFull = streetFull;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHouseNr() {
        return houseNr;
    }

    public void setHouseNr(String houseNr) {
        this.houseNr = houseNr;
    }

    public String getBoxNr() {
        return boxNr;
    }

    public void setBoxNr(String boxNr) {
        this.boxNr = boxNr;
    }

    public String getComplement() {
        return complement;
    }

    /**
     * Sets the value of the complement property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setComplement(String value) {
        this.complement = value;
    }

    /**
     * Gets the value of the zip property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getZip() {
        return zip;
    }

    public void setZip(String value) {
        this.zip = value;
    }

    public String getLocality() {
        return locality;
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }
}
