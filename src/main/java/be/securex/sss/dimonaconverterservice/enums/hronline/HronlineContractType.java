package be.securex.sss.dimonaconverterservice.enums.hronline;

/**
 * Created by 8565 on 15/01/2016.
 */
public enum HronlineContractType {

    ONBEPAALDE_DUUR("(1"), BEPAALDE_DUUR("(2");

    private String code;

    HronlineContractType(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
