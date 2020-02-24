package com.floatingreels.sharpnote.model;

import java.io.Serializable;
import java.time.LocalDate;

public class Note implements Serializable {
    private String title, content;
    private LocalDate time;

    public Note(String title, String content) {
        this.title = title;
        this.content = content;
        this.time = LocalDate.now();
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public LocalDate getTime() {
        return time;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setTime(LocalDate time) {
        this.time = time;
    }
}
