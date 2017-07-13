package be.securex.sss.dimonaconverterservice.dto;

/**
 * Created by 6148 on 16/10/2015.
 */
public class InszNumberDto {

    public InszNumberDto() {
    }

    public InszNumberDto(String inszNumber) {
        this.inszNumber = inszNumber;
    }

    private String inszNumber;

    public String getInszNumber() {
        return inszNumber;
    }

    public void setInszNumber(String inszNumber) {
        this.inszNumber = inszNumber;
    }
}
