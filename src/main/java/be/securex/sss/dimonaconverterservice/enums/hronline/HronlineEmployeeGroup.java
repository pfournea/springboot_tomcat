package be.securex.sss.dimonaconverterservice.enums.hronline;

/**
 * Created by 8565 on 15/01/2016.
 */
public enum HronlineEmployeeGroup {

    NIET_STUDENT("1"), STUDENT("B");

    private String code;

    HronlineEmployeeGroup(String s) {
        this.code = s;
    }

    public String getCode() {
        return code;
    }
}
