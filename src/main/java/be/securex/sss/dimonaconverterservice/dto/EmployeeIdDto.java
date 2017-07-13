package be.securex.sss.dimonaconverterservice.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

/**
 * Created by 6148 on 19/01/2016.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class EmployeeIdDto implements Serializable {
    private InszNumberDto inszNumber;
    private NameBirthDayIdDto nameBirthDayId;

    public EmployeeIdDto() {
    }

    public EmployeeIdDto(InszNumberDto inszNumber) {
        this.inszNumber = inszNumber;
    }

    public EmployeeIdDto(NameBirthDayIdDto nameBirthDayId) {
        this.nameBirthDayId = nameBirthDayId;
    }

    public InszNumberDto getInszNumber() {
        return inszNumber;
    }

    public void setInszNumber(InszNumberDto inszNumber) {
        this.inszNumber = inszNumber;
    }

    public NameBirthDayIdDto getNameBirthDayId() {
        return nameBirthDayId;
    }

    public void setNameBirthDayId(NameBirthDayIdDto nameBirthDayId) {
        this.nameBirthDayId = nameBirthDayId;
    }
}
