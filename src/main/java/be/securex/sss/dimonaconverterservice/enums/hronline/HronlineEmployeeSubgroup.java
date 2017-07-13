package be.securex.sss.dimonaconverterservice.enums.hronline;

import be.securex.sss.dimonaconverterservice.xsd.v1.contractevent.ContractInfoType;
import be.securex.sss.dimonaconverterservice.xsd.v1.contractevent.EmployeeStatuteType;

/**
 * Created by 8565 on 15/01/2016.
 */
public enum HronlineEmployeeSubgroup {

    ARBEIDER("01"), BEDIENDE("03"), STUDENT_ARBEIDER("06"), STUDENT_BEDIENDE("07");

    private String code;

    private HronlineEmployeeSubgroup(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public static HronlineEmployeeSubgroup fromEmployeeStatuteType(ContractInfoType contractInfoType) {
        switch (contractInfoType.getEmployeeStatute()) {
            case WORKER:
                return contractInfoType.getContract().getStudent() == null ? ARBEIDER : STUDENT_ARBEIDER;
            case EMPLOYEE:
                return contractInfoType.getContract().getStudent() == null ? BEDIENDE : STUDENT_BEDIENDE;
            default:
                return null;
        }
    }
}
