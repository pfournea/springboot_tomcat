package be.securex.sss.dimonaconverterservice.enums.hronline;

/**
 * Created by 8565 on 15/01/2016.
 */
public enum HronlineWorkContract {

    STUDENT("XP"), EXTRA("XC"), OTHER("ZZ"),
    FLEXI("YO");

    private String code;

    HronlineWorkContract(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
