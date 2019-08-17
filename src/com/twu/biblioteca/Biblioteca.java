package com.twu.biblioteca;

import com.sun.deploy.security.SelectableSecurityManager;

import java.io.PrintStream;
import java.util.ArrayList;

public class Biblioteca {

    private PrintStream printStream;
    //private Menu menu;
    private String menu;
    private ArrayList<Book> bookList;
    public ArrayList<Book> availBookList;
    private String menuSelection;
    private String bookSelection;
    private ScannerWrapper scanner;
    public int numBooks;

    //constructer
    public Biblioteca(PrintStream printStream, ScannerWrapper scanner){
        this.printStream = printStream;
        this.menu = "1. See List of Books\n2. Exit Biblioteca\n";
        this.scanner = scanner;
        this.bookList = new ArrayList<Book>();
        this.numBooks = 0;
    }

    //get book list
    public ArrayList<Book> getBookList(){
        return this.bookList;
    }

    // adds book to the bookList and sets book id
    public void addBook(Book book){
        numBooks++;
        book.setId(numBooks);
        this.bookList.add(book);
    }

    //welcome message
    public void printWelcomeMessage(){
        printStream.println("Welcome to Biblioteca!!! " +
                "Your librarians Megan and Clarisel at your service.");
    }

    //book titles, authors, years
    public void printBookList(){
        ArrayList<Book> availBooks = new ArrayList<Book>();
        for (Book bk : bookList){
            if (!bk.getCheckedOut()){
                availBooks.add(bk);
            }
        }
        availBookList = availBooks;
        printStream.println(availBooks.toString());
    }

    //display menu
    public void displayMenu(){
        printStream.println("Select an option from the menu below:\n" + menu);
//        for(String option : menu.getMenuOptions()){
//            printStream.println(option);
//        }
    }

    //select menu option
    public void acceptOptionInput() {
        menuSelection = scanner.nextLine();
        makeSelection();
    }

    // Input : user selection
    // Displays appropriate response
    public void makeSelection(){
        //TODO: CASE / SWITCH
        if (menuSelection.equals("1")){
            printBookList();
            selectBook();
        }
        else if(menuSelection.equals("2")){
            printStream.println("Thanks, come again!" );
            System.exit(0);
        }
        else{
            printStream.println("Error: Invalid Selection. Try Again.\n");
        }
    }

    public void selectBook(){
        printStream.println("SELECT A BOOK BY ID");
        //save as book selection
        bookSelection = scanner.nextLine();
        checkOut();
    }

    public Book getBookByID(String idString){
        int id = Integer.parseInt(idString);
        return bookList.get(id - 1);
    }

    public void checkOut(){
        // Fetch selected book from bookList
        Book book = getBookByID(bookSelection);

        // Check if book is checked out
        if (book.getCheckedOut())
            // if so, return error message
            printStream.println("Book is already checked out. Please choose from list above");
        else {
            // mark book as checkedOut
            book.setCheckedOut();
            // Notify user of successful checkout
            printStream.println("You successfully checked out book: " + bookSelection);
        }
    }
}
