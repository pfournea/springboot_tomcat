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


/**
 * <p>Java class for ContractEventType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ContractEventType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;choice&gt;
 *         &lt;element name="hire" type="{http://www.w3.org/2001/XMLSchema}anyType"/&gt;
 *         &lt;element name="update" type="{http://www.w3.org/2001/XMLSchema}anyType"/&gt;
 *         &lt;element name="fire" type="{http://www.w3.org/2001/XMLSchema}anyType"/&gt;
 *       &lt;/choice&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ContractEventType", propOrder = {
    "hire",
    "update",
    "fire"
})
public class ContractEventType {

    protected Object hire;
    protected Object update;
    protected Object fire;

    /**
     * Gets the value of the hire property.
     * 
     * @return
     *     possible object is
     *     {@link Object }
     *     
     */
    public Object getHire() {
        return hire;
    }

    /**
     * Sets the value of the hire property.
     * 
     * @param value
     *     allowed object is
     *     {@link Object }
     *     
     */
    public void setHire(Object value) {
        this.hire = value;
    }

    /**
     * Gets the value of the update property.
     * 
     * @return
     *     possible object is
     *     {@link Object }
     *     
     */
    public Object getUpdate() {
        return update;
    }

    /**
     * Sets the value of the update property.
     * 
     * @param value
     *     allowed object is
     *     {@link Object }
     *     
     */
    public void setUpdate(Object value) {
        this.update = value;
    }

    /**
     * Gets the value of the fire property.
     * 
     * @return
     *     possible object is
     *     {@link Object }
     *     
     */
    public Object getFire() {
        return fire;
    }

    /**
     * Sets the value of the fire property.
     * 
     * @param value
     *     allowed object is
     *     {@link Object }
     *     
     */
    public void setFire(Object value) {
        this.fire = value;
    }

}
