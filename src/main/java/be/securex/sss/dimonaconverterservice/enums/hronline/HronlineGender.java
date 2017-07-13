package be.securex.sss.dimonaconverterservice.enums.hronline;

/**
 * Created by 8565 on 20/01/2016.
 */
public enum HronlineGender {

    MALE("1"), FEMALE("2");

    private String code;

    private HronlineGender(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public static HronlineGender fromContractEventGender(Object gender) {
        if ("MALE".equalsIgnoreCase(gender.toString())) {
            return HronlineGender.MALE;
        } else if ("FEMALE".equalsIgnoreCase(gender.toString())) {
            return HronlineGender.FEMALE;
        }
        return null;
    }

}
