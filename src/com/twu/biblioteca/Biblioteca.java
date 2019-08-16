package com.twu.biblioteca;

import com.sun.deploy.security.SelectableSecurityManager;

import java.io.PrintStream;
import java.util.ArrayList;

public class Biblioteca {

    private PrintStream printStream;
    private Menu menu;
    private ArrayList<Book> bookList;
    private String menuSelection;
    private ScannerWrapper scanner;

    //constructer
    public Biblioteca(PrintStream printStream, ScannerWrapper scanner){
        this.printStream = printStream;
        this.menu = new Menu();
        this.scanner = scanner;
        this.bookList = new ArrayList<Book>();
    }

    public void setBookList(ArrayList<Book> books){
        this.bookList = books;
    }

    //welcome message
    public void printWelcomeMessage(){
        printStream.println("Welcome to Biblioteca!!! " +
                "Your librarians Megan and Clarisel at your service.");
    }

    //book titles, authors, years
    public void printBookList(){
        printStream.println(bookList.toString());
    }

    //display menu
    public void displayMenu(){
        printStream.println("Select an option from the menu below: ");
        for(String option : menu.getMenuOptions()){
            printStream.println(option);
        }
    }

    //select menu option
    public void acceptOptionInput() {
        printStream.println("PRESS A KEY");
        menuSelection = scanner.nextLine();
        makeSelection();
    }

    // Input : user selection
    // Displays appropriate response
    public void makeSelection(){
        //TODO: CASE / SWITCH
        if (menuSelection == "1"){
            printBookList();
            selectBook();
        }
        else if(menuSelection == "2"){
            printStream.println("Thanks, come again!" );
            System.exit(0);
        }
        else{
            printStream.println("Error: Invalid Selection. Try Again." );
        }
    }

    public String selectBook(){
        printStream.println("SELECT A BOOK");
        String bookSelection = scanner.nextLine();
        return bookSelection;
    }

    public void checkOut(int bookIndex){
        // Fetch selected book
        Book book = bookList.get(bookIndex);

        // Check if book is checked out
        if (book.getCheckedOut())
            // if so, return error message
            printStream.println("Book is already checked out. Please choose from list above");

        else {
            // mark book as checkedOut
            book.setCheckedOut();
            // Notify user of successful checkout
            printStream.println("You successfully checked out book: " + bookIndex);
        }
    }
}
