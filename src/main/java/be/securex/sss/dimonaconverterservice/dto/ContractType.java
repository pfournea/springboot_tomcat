package be.securex.sss.dimonaconverterservice.dto;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * Created by 6060 on 6/10/2015.
 */
public enum ContractType {
    XTRA("1"),FLEXI_DAG("2"),FLEXI_PERIOD("3"),STUDENT("4"),VAST_BEPAALD("5"),VAST_ONBEPAALD("6");
    private String code;
    ContractType(String code) {
        this.code = code;
    }

    public static ContractType fromCode(String code) {
        for(ContractType contractType : ContractType.values()) {
            if(contractType.getCode().equals(code)) {
                return contractType;
            }
        }
        return null;
    }

    public String getCode() {
        return code;
    }

    @JsonCreator()
    public ContractType convert(String value) {
        return ContractType.valueOf(value);
    }
}
