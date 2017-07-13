package be.securex.sss.dimonaconverterservice.dto;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * Created by 6148 on 30/10/2015.
 */
public enum EmployeeStatute {
    ARBEIDER("1"), BEDIENDE("2");

    private String code;
    EmployeeStatute(String code) {
        this.code = code;
    }

    public static EmployeeStatute fromCode(String code) {
        for(EmployeeStatute statute : EmployeeStatute.values()) {
            if(statute.getCode().equals(code)) {
                return statute;
            }
        }
        return null;
    }

    public String getCode() {
        return code;
    }

    @JsonCreator()
    public EmployeeStatute convert(String value) {
        return EmployeeStatute.valueOf(value);
    }
}
