package be.securex.sss.dimonaconverterservice.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

/**
 * Created by 6148 on 5/12/2016.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ContractCancellationDto implements WithMetaData, Serializable {
    private MetaDataDto metaData;
    private ContractDto originalContract;
    private ContractDto updatedContract;

    public ContractCancellationDto() {
    }

    public ContractCancellationDto(MetaDataDto metaData, ContractDto originalContract, ContractDto updatedContract) {
        this.metaData = metaData;
        this.originalContract = originalContract;
        this.updatedContract = updatedContract;
    }

    public MetaDataDto getMetaData() {
        return metaData;
    }

    public void setMetaData(MetaDataDto metaData) {
        this.metaData = metaData;
    }

    public ContractDto getOriginalContract() {
        return originalContract;
    }

    public void setOriginalContract(ContractDto originalContract) {
        this.originalContract = originalContract;
    }

    public ContractDto getUpdatedContract() {
        return updatedContract;
    }

    public void setUpdatedContract(ContractDto updatedContract) {
        this.updatedContract = updatedContract;
    }
}
