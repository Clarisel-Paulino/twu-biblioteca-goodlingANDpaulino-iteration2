package com.twu.biblioteca;

public class Movie extends RentalItem{

    private int rating;

    public Movie(final String title, final String director, final int year, final int rating){
        super(title, director, year);
        this.rating = rating;
    }

    @Override
    public String toString(){
        String i = String.format("%-10s", getID());
        String t = String.format("%-20s", getTitle());
        String m = String.format("%-20s", getMaker());
        String y = String.format("%-20s", getYear());
        String r = String.format("%-20s", this.rating);

        StringBuilder sb = new StringBuilder();
        sb.append(i + t + m + y + r + "\n");

        return sb.toString();
    }
}
