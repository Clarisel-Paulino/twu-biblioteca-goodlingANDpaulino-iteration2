package com.twu.biblioteca;

import com.sun.deploy.security.SelectableSecurityManager;

import java.io.PrintStream;
import java.util.ArrayList;

public class Biblioteca {

    private PrintStream printStream;
    private Menu menu;
    private ArrayList<Book> bookList;
    private String menuSelection;
    private String bookSelection;
    private ScannerWrapper scanner;

    //constructer
    public Biblioteca(PrintStream printStream, ScannerWrapper scanner){
        this.printStream = printStream;
        this.menu = new Menu();
        this.scanner = scanner;
        this.bookList = new ArrayList<Book>();
    }

    //set book list
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
        ArrayList<Book> availBooks = new ArrayList<Book>();
        for (Book bk : bookList){
            if (!bk.getCheckedOut()){
                availBooks.add(bk);
            }
        }
        printStream.println(availBooks.toString());
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

    public void selectBook(){
        printStream.println("SELECT A BOOK");
        //TODO: display books by ID

        //save as book selection
        bookSelection = scanner.nextLine();
        checkOut();
    }

    public void checkOut(){
        // Fetch selected book from bookList
        int bookIndex = Integer.parseInt(bookSelection);
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
