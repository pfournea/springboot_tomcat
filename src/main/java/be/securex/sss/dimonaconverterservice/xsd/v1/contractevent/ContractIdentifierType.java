//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.11 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.02.25 at 11:26:09 AM CET 
//


package be.securex.sss.dimonaconverterservice.xsd.v1.contractevent;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import java.util.UUID;


/**
 * <p>Java class for ContractIdentifierType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ContractIdentifierType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="contractId" type="{http://www.securex.be/contractevent/v1}ContractIdType" minOccurs="0"/&gt;
 *         &lt;element name="contractUuid" type="{http://www.securex.be/contractevent/v1}ContractUuidType" minOccurs="0"/&gt;
 *         &lt;element name="sapPerNr" type="{http://www.securex.be/contractevent/v1}SapPerNrType" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ContractIdentifierType", propOrder = {
    "contractId",
    "contractUuid",
    "sapPerNr"
})
public class ContractIdentifierType {

    protected String contractId;
    protected UUID contractUuid;
    protected String sapPerNr;

    /**
     * Gets the value of the contractId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContractId() {
        return contractId;
    }

    /**
     * Sets the value of the contractId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContractId(String value) {
        this.contractId = value;
    }

    /**
     * Gets the value of the contractUuid property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public UUID getContractUuid() {
        return contractUuid;
    }

    /**
     * Sets the value of the contractUuid property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContractUuid(UUID value) {
        this.contractUuid = value;
    }

    /**
     * Gets the value of the sapPerNr property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSapPerNr() {
        return sapPerNr;
    }

    /**
     * Sets the value of the sapPerNr property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSapPerNr(String value) {
        this.sapPerNr = value;
    }

}