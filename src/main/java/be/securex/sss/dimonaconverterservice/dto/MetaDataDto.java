package be.securex.sss.dimonaconverterservice.dto;

import java.time.LocalDateTime;

/**
 * Created by 6148 on 20/10/2015.
 */
public class MetaDataDto {
    private LocalDateTime ctime;
    private String sender;
    private String fupMessageInstanceId;

    public LocalDateTime getCtime() {
        return ctime;
    }

    public void setCtime(LocalDateTime ctime) {
        this.ctime = ctime;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getFupMessageInstanceId() {
        return fupMessageInstanceId;
    }

    public void setFupMessageInstanceId(String fupMessageInstanceId) {
        this.fupMessageInstanceId = fupMessageInstanceId;
    }
}
