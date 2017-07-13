package be.securex.sss.dimonaconverterservice.dto;

/**
 * Created by 6060 on 1/10/2015.
 */
public class FirmNumberDto {
    private String firmNumber;

    public FirmNumberDto() {
    }

    public FirmNumberDto(String firmNumber) {
        this.firmNumber = firmNumber;
    }

    public String getFirmNumber() {
        return firmNumber;
    }

    public void setFirmNumber(String firmNumber) {
        this.firmNumber = firmNumber;
    }
}
