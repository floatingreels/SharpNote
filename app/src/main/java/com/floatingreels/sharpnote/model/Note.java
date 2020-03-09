package com.floatingreels.sharpnote.model;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import org.threeten.bp.LocalDate;
import org.threeten.bp.LocalDateTime;

import java.io.Serializable;

@Entity
public class Note implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String title, content;
    private LocalDate timeCreated;
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

    public int getId() {
        return id;
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

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setTimeCreated(LocalDate timeCreated) {
        this.timeCreated = timeCreated;
    }

    public void setTimeModified(LocalDate timeModified) {
        this.timeModified = timeModified;
    }
}
