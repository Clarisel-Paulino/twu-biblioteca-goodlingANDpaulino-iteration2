package com.twu.biblioteca;

import java.io.PrintStream;
import java.util.ArrayList;

public class Biblioteca {

    private PrintStream printStream;
    private ScannerWrapper scanner;

    private Menu menu;
    public BookList bookList;

    // User input for menu option and book selection
    private String menuSelection;
    private String bookSelection;

    /**
     * Biblioteca Constructor
     * @param printStream - instance of printStream
     * @param scanner - scanner for user input
     * initializes scanner, printStream, the book lists, and numBooks
     */
    public Biblioteca(PrintStream printStream, ScannerWrapper scanner){
        this.printStream = printStream;
        this.scanner = scanner;
        this.menu = new Menu(printStream, scanner);
        this.bookList = new BookList();
    }

    /**
     * prints initial Welcome Message
     */
    public void printWelcomeMessage(){
        printStream.println("Welcome to Biblioteca!!! " +
                "Your librarians Megan and Clarisel at your service.");
    }

    /**
     * Prints list of available books displaying the title, author, year and book id
     */
    public void printBookList(){
        printStream.println(bookList.toString());
    }

    /**
     * displays the menu of options
     */
    public void displayMenu(){
        printStream.println(menu.printMenuOptions());
    }

    /**
     * Accepts user input and stores it as the menu option selection
     * and calls makeSelection()
     */
    public void acceptOptionInput() {
        menuSelection = scanner.nextLine();
        makeSelection();
    }

    public void makeSelection(){
        //TODO: CASE / SWITCH
        if (menuSelection.equals("1")){
            printBookList();
            printStream.println("SELECT A BOOK BY ID");
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

    /**
     * selectBook saves user input as the desired book selection(by ID)
     * and calls checkOut()
     */
    public void selectBook(){
        //save user input as book selection
        bookSelection = scanner.nextLine();
        checkOut();
    }

    /**
     * checkOut checks out an available book or throws error message for attempt
     * to check out unavailable book
     */
    public void checkOut(){
        //check if bookSelection is an int
        try
        {
            // checking valid integer using parseInt() method
            int id = Integer.parseInt(bookSelection);

            // check if book is in list bounds
            if(id <= bookList.numBooks){
                // Fetch selected book from bookList
                Book book = bookList.getBookByID(bookSelection);

                // Check if book is already checked out
                if (book.isCheckedOut())
                    // if so, return error message
                    printStream.println("Book is already checked out. Please choose from list above");
                else {
                    // mark book as checkedOut
                    book.setCheckedOut();
                    bookList.updateAvailBooks(book);

                    // Notify user of successful checkout
                    printStream.println("You successfully checked out book: " + bookSelection);
                }
            }
            else {
                printStream.println("Book does not exist, try again");
            }
        }

        // CASE: user input was not an integer
        catch (NumberFormatException e)
        {
            printStream.println("Invalid selection, please enter a book ID and try again");
        }
    }

    /**
     * start() prints welcome message, keeps menu of options displayed,
     * and accepts user input until user quits program
     */
    public void start(){
        printWelcomeMessage();

        while(true) {
            displayMenu();
            // Ask user to select a menu option
            acceptOptionInput();
        }
    }


}
