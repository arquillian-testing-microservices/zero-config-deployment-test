package com.example.guestbook;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.atomic.AtomicLong;

public class GuestBookEntry {
    private static AtomicLong ID_GENERATOR = new AtomicLong(1000);
    private long id;
    private String name;
    private String date;
    private String text;

    public GuestBookEntry() {
    }

    public GuestBookEntry(String name, String text) {
        this.id = ID_GENERATOR.getAndIncrement();
        this.name = name;
        this.text = text;
        this.date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "GuestBookEntry{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", text='" + text + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
