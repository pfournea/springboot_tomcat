package be.securex.sss.dimonaconverterservice.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * Created by 6148 on 15/10/2015.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class WorkTimePeriodDto implements Serializable{
    private int minutesBreak;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    public WorkTimePeriodDto() {
    }

    public WorkTimePeriodDto(int minutesBreak, LocalDateTime startTime, LocalDateTime endTime) {
        this.minutesBreak = minutesBreak;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public int getMinutesBreak() {
        return minutesBreak;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setMinutesBreak(int minutesBreak) {
        this.minutesBreak = minutesBreak;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }
}
