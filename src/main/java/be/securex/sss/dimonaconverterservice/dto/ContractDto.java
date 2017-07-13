package be.securex.sss.dimonaconverterservice.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by 6148 on 15/10/2015.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ContractDto implements Serializable{
    private ContractType contractType;
    private LocalDate beginDate;
    private LocalDate endDate;
    private List<WorkTimePeriodDto> workHours = new ArrayList<>();
    private Integer numberOfDays;
    private Integer numberOfHours;
    private String exploitation;
    private EmployeeInfoDto employeeInfo;
    private FirmDto fdcp;
    private String paritairComite;
    private EmployeeStatute employeeStatute;
    private UUID contractUuid;
    private LabourContractId contractId;
    private SapPerNr sapPerNr;

    public ContractDto() {
    }

    public ContractDto(ContractType contractType, LocalDate beginDate, LocalDate endDate, List<WorkTimePeriodDto> workHours,
                       Integer numberOfDays, Integer numberOfHours, String exploitation, EmployeeInfoDto employeeInfo, FirmDto fdcp,
                       String paritairComite, EmployeeStatute employeeStatute,UUID contractUUID, LabourContractId contractId,
                       SapPerNr sapPerNr) {
        this();
        this.contractType = contractType;
        this.beginDate = beginDate;
        this.endDate = endDate;
        this.workHours = workHours;
        this.numberOfDays = numberOfDays;
        this.numberOfHours = numberOfHours;
        this.exploitation = exploitation;
        this.employeeInfo = employeeInfo;
        this.fdcp = fdcp;
        this.paritairComite = paritairComite;
        this.employeeStatute = employeeStatute;
        this.contractUuid = contractUUID;
        this.contractId = contractId;
        this.sapPerNr = sapPerNr;
    }

    public ContractType getContractType() {
        return contractType;
    }

    public void setContractType(ContractType contractType) {
        this.contractType = contractType;
    }

    public LocalDate getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(LocalDate beginDate) {
        this.beginDate = beginDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public List<WorkTimePeriodDto> getWorkHours() {
        return workHours;
    }

    public void setWorkHours(List<WorkTimePeriodDto> workHours) {
        this.workHours = workHours;
    }

    public Integer getNumberOfDays() {
        return numberOfDays;
    }

    public void setNumberOfDays(Integer numberOfDays) {
        this.numberOfDays = numberOfDays;
    }

    public Integer getNumberOfHours() {
        return numberOfHours;
    }

    public void setNumberOfHours(Integer numberOfHours) {
        this.numberOfHours = numberOfHours;
    }

    public String getExploitation() {
        return exploitation;
    }

    public void setExploitation(String exploitation) {
        this.exploitation = exploitation;
    }

    public EmployeeInfoDto getEmployeeInfo() {
        return employeeInfo;
    }

    public void setEmployeeInfo(EmployeeInfoDto employeeInfo) {
        this.employeeInfo = employeeInfo;
    }

    public FirmDto getFdcp() {
        return fdcp;
    }

    public void setFdcp(FirmDto fdcp) {
        this.fdcp = fdcp;
    }

    public String getParitairComite() {
        return paritairComite;
    }

    public void setParitairComite(String paritairComite) {
        this.paritairComite = paritairComite;
    }

    public EmployeeStatute getEmployeeStatute() {
        return employeeStatute;
    }

    public void setEmployeeStatute(EmployeeStatute employeeStatute) {
        this.employeeStatute = employeeStatute;
    }

    public UUID getContractUuid() {
        return contractUuid;
    }

    public void setContractUuid(UUID contractUuid) {
        this.contractUuid = contractUuid;
    }

    public LabourContractId getContractId() {
        return contractId;
    }

    public void setContractId(LabourContractId contractId) {
        this.contractId = contractId;
    }

    public SapPerNr getSapPerNr() {
        return sapPerNr;
    }

    public void setSapPerNr(SapPerNr sapPerNr) {
        this.sapPerNr = sapPerNr;
    }
}
