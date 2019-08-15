package com.twu.biblioteca;

public class Book {
    private final String author;
    private final int year;
    private String title;

    public Book(final String title, final String author, final int year){
        this.title = title;
        this.author = author;
        this.year = year;

    }

    public String getTitle(){
        return this.title;
    }

    public int getYear() {
        return this.year;
    }

    public String getAuthor() {
        return this.author;
    }
}
