package be.securex.sss.dimonaconverterservice.enums;

/**
 * Created by 8565 on 26/10/2015.
 */
public enum ContingentType {

    BINNEN_CONTINGENT("1"), BUITEN_CONTINGENT("2");

    private String contingentType;

    ContingentType(String contingentType) {
        this.contingentType = contingentType;
    }

    public String getCode() {
        return contingentType;
    }

}
