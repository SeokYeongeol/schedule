package org.example.schedule.entity;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class Schedule {
    private Long id;
    private String name;
    private String password;
    private String title;
    private String contents;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public Schedule(Long id, String password) {
        this.id = id;
        this.password = password;
    }

    public Schedule(String name, String password, String title, String contents, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.name = name;
        this.password = password;
        this.title = title;
        this.contents = contents;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }

    public Schedule(Long id, String name, String title, String contents, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.id = id;
        this.name = name;
        this.title = title;
        this.contents = contents;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }
}
