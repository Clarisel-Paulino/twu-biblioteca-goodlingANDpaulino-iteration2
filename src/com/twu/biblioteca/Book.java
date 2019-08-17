package com.twu.biblioteca;

public class Book {
    private final String author;
    private final int year;
    private boolean checkedOut;
    private String title;
    private int id;

    //TODO add index var

    public Book(final String title, final String author, final int year){
        this.title = title;
        this.author = author;
        this.year = year;
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


    public void setId(int id) {
        this.id = id;
    }

    public boolean getCheckedOut() {
        return checkedOut;
    }

    //TODO also print by ID
    public String toString(){
        return id + ": " + title + " by " + author + ", " + year + '\n';
    }
}
