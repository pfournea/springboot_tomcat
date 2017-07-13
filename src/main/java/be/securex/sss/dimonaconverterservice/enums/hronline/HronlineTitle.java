package be.securex.sss.dimonaconverterservice.enums.hronline;

/**
 * Created by 8565 on 18/01/2016.
 */
public enum HronlineTitle {

    MR("1"), MEVR("2"), JUFFR("3");

    private String code;

    private HronlineTitle(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public static HronlineTitle fromContractEventTitle(String contractEventTitle) {
        switch(contractEventTitle) {
            case "DEHEER":
                return MR;
            case "MEVROUW":
                return MEVR;
            case "JUFFROUW":
                return JUFFR;
            default:
                return null;
        }
    }
}
