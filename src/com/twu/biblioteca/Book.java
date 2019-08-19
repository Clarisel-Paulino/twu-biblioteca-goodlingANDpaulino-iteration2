package com.twu.biblioteca;

public class Book {
    private final String author;
    private final int year;
    private boolean checkedOut;
    private String title;
    private int id;

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

    public void setReturned() {
        checkedOut = false;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isCheckedOut() {
        return checkedOut;
    }

    public String toString(){
        String i = String.format("%-10s", id);
        String t = String.format("%-20s", title);
        String a = String.format("%-20s", author);
        String y = String.format("%-20s", year);

        StringBuilder sb = new StringBuilder();
        sb.append(i + t + a + y + "\n");

        return sb.toString();
    }
}
