package be.securex.sss.dimonaconverterservice.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by 6148 on 14/10/2015.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class FirmDto implements Serializable {
    private FirmNumberDto firmNumber;
    private String departementNumber;
    private String category;
    private String personNumber;

    public FirmNumberDto getFirmNumber() {
        return firmNumber;
    }

    public String getDepartementNumber() {
        return departementNumber;
    }

    public String getCategory() {
        return category;
    }

    public String getPersonNumber() {
        return personNumber;
    }

    public void setFirmNumber(FirmNumberDto firmNumber) {
        this.firmNumber = firmNumber;
    }

    public void setDepartementNumber(String departementNumber) {
        this.departementNumber = departementNumber;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setPersonNumber(String personNumber) {
        this.personNumber = personNumber;
    }
}
