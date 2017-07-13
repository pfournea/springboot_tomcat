package be.securex.sss.dimonaconverterservice.enums;

/**
 * Created by 8565 on 26/10/2015.
 */
public enum ContractNature {

    INDEFINITE_TERM("1"), DEFINITE_TERM("2");

    private String cbCode;

    ContractNature(String cbCode) {
        this.cbCode = cbCode;
    }

    public String getCbCode() {
        return cbCode;
    }
}
