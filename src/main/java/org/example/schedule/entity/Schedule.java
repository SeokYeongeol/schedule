package org.example.schedule.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@AllArgsConstructor
public class Schedule {
    private Long id;
    private String name;
    private String password;
    private String title;
    private String contents;

    @Setter
    private Date date;

    public Schedule(String name, String password, String title, String contents) {
        this.name = name;
        this.password = password;
        this.title = title;
        this.contents = contents;
    }

    public Schedule(String name, String password, String title, String contents, Date date) {
        this.name = name;
        this.password = password;
        this.title = title;
        this.contents = contents;
        this.date = new Date();
    }
}
