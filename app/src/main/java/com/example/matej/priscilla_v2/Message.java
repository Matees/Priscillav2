package com.example.matej.priscilla_v2;

public class Message {
    private String text;
    private String title;

    public Message(String text, String title) {
        this.title = title;
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
