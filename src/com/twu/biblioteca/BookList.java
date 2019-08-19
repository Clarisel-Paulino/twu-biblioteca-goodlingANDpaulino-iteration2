package com.twu.biblioteca;

import java.util.ArrayList;

public class BookList extends RentalList{
    /**
     * BookList Constructor
     * Initializes availBookList and bookList
     */
    public BookList(){
        initBookLists();
    }

    /**
     * Initializes list of all books and list of available books
     */
    private void initBookLists(){
        // Set up book list
        Book book1 = new Book("Crazy Rich Asians", "Kevin Kwan", 2013);
        Book book2 = new Book("Beloved", "Toni Morrison", 1987);
        Book book3 = new Book("Moment of Lift", "Melinda Gates", 2018);

        book3.setCheckedOut();

        initAdd(book1);
        initAdd(book2);
        initAdd(book3);
    }

    /**
     * prints availBookList
     * @return list of available books as a String, with formatting for columns
     */
    public String toString(){
        String i = String.format("%-10s", "BOOK ID");
        String t = String.format("%-20s", "TITLE");
        String a = String.format("%-20s", "AUTHOR");
        String y = String.format("%-20s", "YEAR");
        String line = "\n================================================================\n";

        StringBuilder sb = new StringBuilder();
        sb.append(i+ t + a + y + line);

        for(RentalItem item : getAvailItemList()){
            sb.append(item.toString());
        }
        return sb.toString();
    }
}
