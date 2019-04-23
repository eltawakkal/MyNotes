package com.example.mynotes.model;

public class Notes {

    private String id;
    private String title;
    private String note;

    public Notes(String id, String title, String note) {
        this.id = id;
        this.title = title;
        this.note = note;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getNote() {
        return note;
    }
}
