package com.twu.biblioteca;

import java.util.ArrayList;
import java.util.Arrays;

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
        Book book1 = new Book("Crazy", "Kevin", 1010);
        Book book2 = new Book("Bible", "Jesus", -1);
        Book book3 = new Book("Moment of Lift", "Melinda Gates", 2018);

        addBook(book1);
        addBook(book2);
        addBook(book3);
    }

    /**
     * addBook
     * @param book - instance of Book object
     * adds book to bookList (and availBookList) and sets the book ID
     */
    public void addBook(Book book){
        numBooks++;
        book.setId(numBooks);
        this.bookList.add(book);
        this.availBookList.add(book);
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
     * printBookList
     * @return list of available books as a String
     */
    public String printBookList(){
        return availBookList.toString();
    }

    public ArrayList<Book> getBookList(){
        return availBookList;
    }

    public String checkOut(String bookSelection){
        // Fetch selected book from bookList
        Book book = getBookByID(bookSelection);

        // Check if book is checked out
        if (book.isCheckedOut())
            // if so, return error message
            return "Book is already checked out. Please choose from list above";
        else {
            // mark book as checkedOut
            book.setCheckedOut();
            // Notify user of successful checkout
            return "You successfully checked out book: " + bookSelection;
        }
    }
}
