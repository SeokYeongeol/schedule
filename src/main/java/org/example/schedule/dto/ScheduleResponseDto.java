package org.example.schedule.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.example.schedule.entity.Schedule;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@AllArgsConstructor
public class ScheduleResponseDto {
    private Long id;
    private String name;
    private String password;
    private String title;
    private String contents;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public ScheduleResponseDto(Schedule schedule) {
        this.id = schedule.getId();
        this.name = schedule.getName();
        this.title = schedule.getTitle();
        this.contents = schedule.getContents();
        this.createdAt = schedule.getCreatedAt();
        this.modifiedAt = schedule.getModifiedAt();
    }

    public ScheduleResponseDto(Long id, String name, String title, String contents, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.id = id;
        this.name = name;
        this.title = title;
        this.contents = contents;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }
}
