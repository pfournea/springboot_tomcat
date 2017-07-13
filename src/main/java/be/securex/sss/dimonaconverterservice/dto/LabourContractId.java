package be.securex.sss.dimonaconverterservice.dto;

import java.io.Serializable;

/**
 * Created by 6060 on 7/10/2015.
 */
public class LabourContractId implements Serializable {
    private static final long serialVersionUID = 1587190373179427228L;
    private String id;

    public LabourContractId() {
    }

    private LabourContractId(String id) {
        this.id = id;
    }

    public static LabourContractId create(String id) {
        return new LabourContractId(id);
    }

    public String getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LabourContractId that = (LabourContractId) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
