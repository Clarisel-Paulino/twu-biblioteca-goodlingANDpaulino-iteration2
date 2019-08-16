package com.twu.biblioteca;

import com.sun.deploy.security.SelectableSecurityManager;

import java.io.PrintStream;
import java.util.ArrayList;

public class Biblioteca {

    private PrintStream printStream;
    private Menu menu;
    private ArrayList<Book> bookList;
    private int menuSelection;
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
        for(Book bk : bookList){
            if (!bk.getCheckedOut()) {
                //print index in list
                int index = bookList.indexOf(bk);
                String title = bk.getTitle();
                String author = bk.getAuthor();
                String year = Integer.toString(bk.getYear());

                printStream.println(title + ',' + author + ',' + year);
            }
        }
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
        menuSelection = scanner.nextInt();
        makeSelection();
    }

    // Input : user selection
    // Displays appropriate response
    public void makeSelection(){
         switch (menuSelection){
             case 1:
                 printBookList();
                 selectBook();
                 break;

             case 2:
                 printStream.println("Thanks, come again!" );
                 System.exit(0);
                 break;

             default:
                 printStream.println("Error: Invalid Selection. Try Again." );
                 break;
         }
    }

    public int selectBook(){
        printStream.println("SELECT A BOOK");
        int bookSelection = scanner.nextInt();
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
