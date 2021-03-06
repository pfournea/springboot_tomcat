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
import java.util.UUID;


/**
 * <p>Java class for CONTRACTType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CONTRACTType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;all&gt;
 *         &lt;element name="PAYGROUP" type="{}PAYGROUPType"/&gt;
 *         &lt;element ref="{}CONTRACT_NUMBER" minOccurs="0"/&gt;
 *         &lt;element name="DIMONA_DECLARATION" type="{}DIMONA_DECLARATIONType"/&gt;
 *         &lt;element ref="{}AC_NR" minOccurs="0"/&gt;
 *         &lt;element ref="{}CONTRACT_NATURE" minOccurs="0"/&gt;
 *         &lt;element ref="{}DATE_EFF_START" minOccurs="0"/&gt;
 *         &lt;element ref="{}REASON_END_CONTRACT" minOccurs="0"/&gt;
 *         &lt;element ref="{}DATE_FORESEEN_END" minOccurs="0"/&gt;
 *         &lt;element ref="{}DATE_BREACH" minOccurs="0"/&gt;
 *         &lt;element name="WORKHOURS" type="{}WORKHOURSType" minOccurs="0"/&gt;
 *         &lt;element ref="{}CONTRACT_DIMONA" minOccurs="0"/&gt;
 *         &lt;element ref="{}NUM_C32A_START" minOccurs="0"/&gt;
 *         &lt;element ref="{}NUM_C32A_NEXT" minOccurs="0"/&gt;
 *         &lt;element ref="{}PAR_COM" minOccurs="0"/&gt;
 *         &lt;element name="AFFILIATION" type="{}AFFILIATIONType" minOccurs="0"/&gt;
 *         &lt;element name="CONTRACT_SOCIAL" type="{}CONTRACT_SOCIALType" minOccurs="0"/&gt;
 *         &lt;element name="EMPLOYEE_STATUTE" type="{}EMPLOYEE_STATUTEtype" minOccurs="0"/&gt;
 *         &lt;element name="CONTRACT_UUID" type="{}CONTRACT_UUIDtype" minOccurs="0"/&gt;
 *       &lt;/all&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CONTRACTType", propOrder = {

})
public class ContractType {

    @XmlElement(name = "PAYGROUP", required = true)
    protected PayGroupType payGroup;
    @XmlElement(name = "CONTRACT_NUMBER")
    protected String contractNumber;
    @XmlElement(name = "DIMONA_DECLARATION", required = true)
    protected DimonaDeclarationType dimonaDeclaration;
    @XmlElement(name = "AC_NR")
    protected String acNr;
    @XmlElement(name = "CONTRACT_NATURE")
    protected String contractNature;
    @XmlElement(name = "DATE_EFF_START")
    protected String dateEffStart;
    @XmlElement(name = "REASON_END_CONTRACT")
    protected String reasonEndContract;
    @XmlElement(name = "DATE_FORESEEN_END")
    protected String dateForeseenEnd;
    @XmlElement(name = "DATE_BREACH")
    protected String dateBreach;
    @XmlElement(name = "WORKHOURS")
    protected WorkHoursType workHours;
    @XmlElement(name = "CONTRACT_DIMONA")
    protected String contractDimona;
    @XmlElement(name = "NUM_C32A_START")
    protected String numC32aStart;
    @XmlElement(name = "NUM_C32A_NEXT")
    protected String numC32aNext;
    @XmlElement(name = "PAR_COM")
    protected String parCom;
    @XmlElement(name = "AFFILIATION")
    protected AffiliationType affiliation;
    @XmlElement(name = "CONTRACT_SOCIAL")
    protected ContractSocialType contractSocial;
    @XmlElement(name = "EMPLOYEE_STATUTE")
    protected String employeeStatute;
    @XmlElement(name = "CONTRACT_UUID")
    protected UUID contractuuid;

    public PayGroupType getPayGroup() {
        return payGroup;
    }

    public void setPayGroup(PayGroupType payGroup) {
        this.payGroup = payGroup;
    }

    public String getContractNumber() {
        return contractNumber;
    }

    public void setContractNumber(String contractNumber) {
        this.contractNumber = contractNumber;
    }

    public DimonaDeclarationType getDimonaDeclaration() {
        return dimonaDeclaration;
    }

    public void setDimonaDeclaration(DimonaDeclarationType dimonaDeclaration) {
        this.dimonaDeclaration = dimonaDeclaration;
    }

    public String getAcNr() {
        return acNr;
    }

    public void setAcNr(String acNr) {
        this.acNr = acNr;
    }

    public String getContractNature() {
        return contractNature;
    }

    public void setContractNature(String contractNature) {
        this.contractNature = contractNature;
    }

    public String getDateEffStart() {
        return dateEffStart;
    }

    public void setDateEffStart(String dateEffStart) {
        this.dateEffStart = dateEffStart;
    }

    public String getReasonEndContract() {
        return reasonEndContract;
    }

    public void setReasonEndContract(String reasonEndContract) {
        this.reasonEndContract = reasonEndContract;
    }

    public String getDateForeseenEnd() {
        return dateForeseenEnd;
    }

    public void setDateForeseenEnd(String dateForeseenEnd) {
        this.dateForeseenEnd = dateForeseenEnd;
    }

    public String getDateBreach() {
        return dateBreach;
    }

    public void setDateBreach(String dateBreach) {
        this.dateBreach = dateBreach;
    }

    public WorkHoursType getWorkHours() {
        return workHours;
    }

    public void setWorkHours(WorkHoursType workHours) {
        this.workHours = workHours;
    }

    public String getContractDimona() {
        return contractDimona;
    }

    public void setContractDimona(String contractDimona) {
        this.contractDimona = contractDimona;
    }

    public String getNumC32aStart() {
        return numC32aStart;
    }

    public void setNumC32aStart(String numC32aStart) {
        this.numC32aStart = numC32aStart;
    }

    public String getNumC32aNext() {
        return numC32aNext;
    }

    public void setNumC32aNext(String numC32aNext) {
        this.numC32aNext = numC32aNext;
    }

    public String getParCom() {
        return parCom;
    }

    public void setParCom(String parCom) {
        this.parCom = parCom;
    }

    public AffiliationType getAffiliation() {
        return affiliation;
    }

    public void setAffiliation(AffiliationType affiliation) {
        this.affiliation = affiliation;
    }

    public ContractSocialType getContractSocial() {
        return contractSocial;
    }

    public void setContractSocial(ContractSocialType contractSocial) {
        this.contractSocial = contractSocial;
    }

    public String getEmployeeStatute() {
        return employeeStatute;
    }

    public void setEmployeeStatute(String employeeStatute) {
        this.employeeStatute = employeeStatute;
    }

    public UUID getContractuuid() {
        return contractuuid;
    }

    public void setContractuuid(UUID contractuuid) {
        this.contractuuid = contractuuid;
    }
}
