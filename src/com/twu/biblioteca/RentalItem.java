package com.twu.biblioteca;

public class RentalItem {
    private final String maker;
    private final int year;
    private boolean checkedOut;
    private String title;
    private int id;

    // User currently in possession of the rentalItem
    private User currUser;


    public RentalItem(final String title, final String maker, final int year){
        this.title = title;
        this.maker = maker;
        this.year = year;
        this.checkedOut = false;
        this.currUser = null;
    }

    public String getTitle(){
        return this.title;
    }

    public int getYear() {
        return this.year;
    }

    public String getMaker() {
        return this.maker;
    }

    public int getID() {
        return this.id;
    }

    public void setCheckedOut(User user) {
        checkedOut = true;
        this.currUser = user;
    }

    public void setReturned() {
        checkedOut = false;
        this.currUser = null;
    }

    public User getCurrUser(){
        return this.currUser;
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
        String m = String.format("%-20s", maker);
        String y = String.format("%-20s", year);

        StringBuilder sb = new StringBuilder();
        sb.append(i + t + m + y + "\n");

        return sb.toString();
    }
}

