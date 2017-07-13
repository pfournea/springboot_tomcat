package be.securex.sss.dimonaconverterservice.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * Created by 5848 on 9/10/2015.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class EmployeeInfoDto implements Serializable {
    private String title;
    private String firstName;
    private String secondNameInitials;
    private String lastName;
    private String gender;
    private LocalDate birthDate;
    private EmployeeIdDto employeeId;
    private String birthPlace;
    private CountryCodeDto birthCountry;
    private CountryCodeDto nationality;
    private AddressDto address;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondNameInitials() {
        return secondNameInitials;
    }

    public void setSecondNameInitials(String secondNameInitials) {
        this.secondNameInitials = secondNameInitials;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthdate) {
        this.birthDate = birthdate;
    }

    public EmployeeIdDto getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(EmployeeIdDto employeeId) {
        this.employeeId = employeeId;
    }

    public String getBirthPlace() {
        return birthPlace;
    }

    public void setBirthPlace(String birthPlace) {
        this.birthPlace = birthPlace;
    }

    public CountryCodeDto getBirthCountry() {
        return birthCountry;
    }

    public void setBirthCountry(CountryCodeDto birthCountry) {
        this.birthCountry = birthCountry;
    }

    public CountryCodeDto getNationality() {
        return nationality;
    }

    public void setNationality(CountryCodeDto nationality) {
        this.nationality = nationality;
    }

    public AddressDto getAddress() {
        return address;
    }

    public void setAddress(AddressDto address) {
        this.address = address;
    }
}
