package be.securex.sss.dimonaconverterservice.enums;

/**
 * Created by 8565 on 26/10/2015.
 */
public enum DeclarationType {

    INDIENST("1"),
    UITDIENST("2"),
    WIJZIGING("3"),
    ANNULATIE("4"),
    SIGNALITIEK("5"),
    NATIONAALNR("6");

    private String code;

    DeclarationType(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public static DeclarationType getByCode(String code) {
        for (DeclarationType type : values()) {
            if (type.code.equals(code)) {
                return type;
            }
        }
        throw new RuntimeException("No declarationType found with code: " + code);
    }

}
