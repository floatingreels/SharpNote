package com.floatingreels.sharpnote.model;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
public class Note implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String title, content;
    private final LocalDate timeCreated;
    private LocalDate timeModified;

    public Note() {
    }

    @Ignore
    public Note(String title, String content) {
        this.title = title;
        this.content = content;
        this.timeCreated = LocalDate.now();
        this.timeModified = LocalDate.now();
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
