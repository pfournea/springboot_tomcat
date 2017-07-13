package be.securex.sss.dimonaconverterservice.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

/**
 * Created by 6148 on 4/11/2015.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ContractCreationDto implements WithMetaData, Serializable {
    private MetaDataDto metaData;
    private ContractDto contract;

    public ContractCreationDto() {
    }

    public ContractCreationDto(MetaDataDto metaData, ContractDto contract) {
        this.metaData = metaData;
        this.contract = contract;
    }

    public MetaDataDto getMetaData() {
        return metaData;
    }

    public void setMetaData(MetaDataDto metaData) {
        this.metaData = metaData;
    }

    public ContractDto getContract() {
        return contract;
    }

    public void setContract(ContractDto contract) {
        this.contract = contract;
    }
}
