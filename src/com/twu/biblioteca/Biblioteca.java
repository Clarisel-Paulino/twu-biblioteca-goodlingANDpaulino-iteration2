package com.twu.biblioteca;

import java.io.PrintStream;
import java.util.ArrayList;

public class Biblioteca {

    private PrintStream printStream;
    private ScannerWrapper scanner;
    private Menu menu;

    BookList bookList;

    // User input for book selection
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
        this.menu = new Menu(scanner);
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
        printStream.println(menu.getMenuOptions());
    }

    /**
     * makeSelection prompts user to select option from menu and executes
     *  appropriate actions
     */
    public void makeSelection() {
        menu.parseOptionInput();
        int menuSelect = menu.getMenuSelection();

            switch (menuSelect) {

                case 1:
                    printBookList();
                    printStream.println("SELECT A BOOK TO CHECK OUT BY ID");
                    checkOut();
                    break;

                case 2:
                    break;

                case 3:
                    printStream.println("SELECT A BOOK TO RETURN BY ID");
                    returnBook();
                    break;

                case 4:
                    break;

                case 5:
                    printStream.println("Thanks, come again!");
                    System.exit(0);
                    break;

                case -1: // input was not an integer
                    printStream.println("Error: Invalid Selection. Try Again.\n");
                    break;

                default: // menu option is nonexistent
                    printStream.println("Error: Invalid Selection. Try Again.\n");
                    break;
            }

    }

    /**
     * checkOut checks out an available book or throws error message for attempt
     * to check out unavailable book
     */
    public void checkOut(){
        //save user input as book selection
        bookSelection = scanner.nextLine();

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
     * returnBook checks out an available book or throws error message for attempt
     * to check out unavailable book
     */
    public void returnBook(){
        //save user input as book selection
        bookSelection = scanner.nextLine();

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
                if (!book.isCheckedOut())
                    // if so, error message
                    printStream.println("Book is already at the library.");
                else {
                    // mark book as returned
                    book.setReturned();
                    bookList.updateAvailBooks(book);

                    // Notify user of successful return
                    printStream.println("You successfully returned book: " + bookSelection);
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
            makeSelection();
        }
    }


}
