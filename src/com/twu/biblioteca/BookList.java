package com.twu.biblioteca;

import java.util.ArrayList;

public class BookList {

    // List of all books in library
    private ArrayList<Book> bookList;
    // List of books available for checkout
    public ArrayList<Book> availBookList;

    // Current number of books, used to give book ID
    public int numBooks;

    /**
     * BookList Constructor
     * Initializes availBookList and bookList
     */
    public BookList(){
        this.availBookList = new ArrayList<Book>();
        this.bookList = new ArrayList<Book>();
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

        initAddBook(book1);
        initAddBook(book2);
        initAddBook(book3);
    }

    /**
     * initAddBook
     * @param book - instance of Book object
     * adds book to bookList (and availBookList) and sets the book ID
     */
    private void initAddBook(Book book){
        numBooks++;
        book.setId(numBooks);
        this.bookList.add(book);

        // Only add book to availBookList if not checked out
        if(!book.isCheckedOut()){
            this.availBookList.add(book);
        }
    }

    /**
     * getBookByID
     * @param idString - book id as a string
     * @return Book object with the idString as its id
     */
    public Book getBookByID(String idString){
        int id = Integer.parseInt(idString);
        return bookList.get(id - 1);
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

        for(Book book : availBookList){
            sb.append(book.toString());
        }
        return sb.toString();
    }

    public ArrayList<Book> getBookList(){
        return availBookList;
    }

    /**
     * updateAvailBooks
     * @param book -- Book object to be removed
     *  Removes book from the list of available books
     */
    public void updateAvailBooks(Book book){
        if (book.isCheckedOut()){
            availBookList.remove(book);
        }
        else{
            availBookList.add(book);
        }
    }
}
