package com.floatingreels.sharpnote.model;

import java.io.Serializable;
import java.time.LocalDate;

public class Note implements Serializable {
    private String title, content;
    private final LocalDate timeCreated;
    private LocalDate timeModified;


    public Note(String title, String content) {
        this.title = title;
        this.content = content;
        this.timeCreated = LocalDate.now();
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public LocalDate getTimeCreated() {
        return timeCreated;
    }

    public LocalDate getTimeModified() {
        return timeModified;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setTimeModified(LocalDate timeModified) {
        this.timeModified = timeModified;
    }
}
