package org.example.schedule.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

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
}
