//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.11 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.03.08 at 12:28:12 PM CET 
//


package be.securex.sss.dimonaconverterservice.xsd.v1.contractevent;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CancelContractType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CancelContractType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="employeeInfo" type="{http://www.securex.be/contractevent/v1}ModificationEmployeeInfoType"/&gt;
 *         &lt;element name="originalEmployeeInfo" type="{http://www.securex.be/contractevent/v1}ModificationEmployeeInfoType"/&gt;
 *         &lt;element name="contractInfo" type="{http://www.securex.be/contractevent/v1}ContractInfoType"/&gt;
 *         &lt;element name="originalContractInfo" type="{http://www.securex.be/contractevent/v1}ContractInfoType"/&gt;
 *         &lt;element name="contractIdentifier" type="{http://www.securex.be/contractevent/v1}ContractIdentifierType"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CancelContractType", propOrder = {
    "employeeInfo",
    "originalEmployeeInfo",
    "contractInfo",
    "originalContractInfo",
    "contractIdentifier"
})
public class CancelContractType {

    @XmlElement(required = true)
    protected ModificationEmployeeInfoType employeeInfo;
    @XmlElement(required = true)
    protected ModificationEmployeeInfoType originalEmployeeInfo;
    @XmlElement(required = true)
    protected ContractInfoType contractInfo;
    @XmlElement(required = true)
    protected ContractInfoType originalContractInfo;
    @XmlElement(required = true)
    protected ContractIdentifierType contractIdentifier;

    /**
     * Gets the value of the employeeInfo property.
     * 
     * @return
     *     possible object is
     *     {@link ModificationEmployeeInfoType }
     *     
     */
    public ModificationEmployeeInfoType getEmployeeInfo() {
        return employeeInfo;
    }

    /**
     * Sets the value of the employeeInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link ModificationEmployeeInfoType }
     *     
     */
    public void setEmployeeInfo(ModificationEmployeeInfoType value) {
        this.employeeInfo = value;
    }

    /**
     * Gets the value of the originalEmployeeInfo property.
     * 
     * @return
     *     possible object is
     *     {@link ModificationEmployeeInfoType }
     *     
     */
    public ModificationEmployeeInfoType getOriginalEmployeeInfo() {
        return originalEmployeeInfo;
    }

    /**
     * Sets the value of the originalEmployeeInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link ModificationEmployeeInfoType }
     *     
     */
    public void setOriginalEmployeeInfo(ModificationEmployeeInfoType value) {
        this.originalEmployeeInfo = value;
    }

    /**
     * Gets the value of the contractInfo property.
     * 
     * @return
     *     possible object is
     *     {@link ContractInfoType }
     *     
     */
    public ContractInfoType getContractInfo() {
        return contractInfo;
    }

    /**
     * Sets the value of the contractInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link ContractInfoType }
     *     
     */
    public void setContractInfo(ContractInfoType value) {
        this.contractInfo = value;
    }

    /**
     * Gets the value of the originalContractInfo property.
     * 
     * @return
     *     possible object is
     *     {@link ContractInfoType }
     *     
     */
    public ContractInfoType getOriginalContractInfo() {
        return originalContractInfo;
    }

    /**
     * Sets the value of the originalContractInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link ContractInfoType }
     *     
     */
    public void setOriginalContractInfo(ContractInfoType value) {
        this.originalContractInfo = value;
    }

    /**
     * Gets the value of the contractIdentifier property.
     * 
     * @return
     *     possible object is
     *     {@link ContractIdentifierType }
     *     
     */
    public ContractIdentifierType getContractIdentifier() {
        return contractIdentifier;
    }

    /**
     * Sets the value of the contractIdentifier property.
     * 
     * @param value
     *     allowed object is
     *     {@link ContractIdentifierType }
     *     
     */
    public void setContractIdentifier(ContractIdentifierType value) {
        this.contractIdentifier = value;
    }

}
