package be.securex.sss.dimonaconverterservice.dto;

import java.io.Serializable;

/**
 * Created by 8565 on 19/02/2016.
 */
public class SapPerNr implements Serializable {

    private String number;

    private SapPerNr() {}

    private SapPerNr(String sapPerNr) {
        this.number = sapPerNr;
    }

    public static SapPerNr create(String sapPerNr) {
        return new SapPerNr(sapPerNr);
    }

    public String getNumber() {
        return number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SapPerNr sapPerNr = (SapPerNr) o;

        if (number != null ? !number.equals(sapPerNr.number) : sapPerNr.number != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return number != null ? number.hashCode() : 0;
    }
}
