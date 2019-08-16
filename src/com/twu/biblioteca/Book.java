package com.twu.biblioteca;

public class Book {
    private final String author;
    private final int year;
    public boolean checkedOut;
    private String title;
    public int index;

    public Book(final String title, final String author, final int year, int index){
        this.title = title;
        this.author = author;
        this.year = year;
        this.index = index;
        this.checkedOut = false;
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

    public void setCheckedOut() {
        checkedOut = true;
    }

}
