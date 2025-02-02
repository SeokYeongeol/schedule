package org.example.schedule.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.example.schedule.entity.Schedule;

import java.util.Date;

@Getter
@AllArgsConstructor
public class ScheduleResponseDto {
    private Long id;
    private String name;
    private String password;
    private String title;
    private String contents;
    private Date date;

    public ScheduleResponseDto(Schedule schedule) {
        this.id = schedule.getId();
        this.name = schedule.getName();
        this.title = schedule.getTitle();
        this.contents = schedule.getContents();
        this.date = schedule.getDate();
    }

    public ScheduleResponseDto(Long id, String name, String title, String contents, Date date) {
        this.id = id;
        this.name = name;
        this.title = title;
        this.contents = contents;
        this.date = date;
    }
}
