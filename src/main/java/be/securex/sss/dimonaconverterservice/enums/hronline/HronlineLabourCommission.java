package be.securex.sss.dimonaconverterservice.enums.hronline;

/**
 * Created by 8565 on 19/01/2016.
 */
public enum HronlineLabourCommission {

    PC_302000("302");

    private String code;

    private HronlineLabourCommission(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
