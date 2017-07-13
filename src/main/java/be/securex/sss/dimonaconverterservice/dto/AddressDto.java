package be.securex.sss.dimonaconverterservice.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

/**
 * Created by 6148 on 12/01/2016.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class AddressDto implements Serializable {
    private String street;
    private String houseNumber;
    private String boxNumber;
    private String complement;
    private String zip;
    private String city;
    private CountryCodeDto countryCode;

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getBoxNumber() {
        return boxNumber;
    }

    public void setBoxNumber(String boxNumber) {
        this.boxNumber = boxNumber;
    }

    public String getComplement() {
        return complement;
    }

    public void setComplement(String complement) {
        this.complement = complement;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public CountryCodeDto getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(CountryCodeDto countryCode) {
        this.countryCode = countryCode;
    }
}
