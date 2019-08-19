package com.twu.biblioteca;

import org.junit.Before;
//import org.junit.Rule;
import org.junit.Test;
//import org.junit.contrib.java.lang.system.*;
//import org.junit.contrib.java.lang.system.Assertion;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;


public class BibliotecaAppTest {

    private ByteArrayOutputStream outputStream;
    private Biblioteca testBib;
    private ScannerWrapper mockScannerWrapper;
    private PrintStream mockPrintStream;

    //@Rule
    //public final ExpectedSystemExit exit = ExpectedSystemExit.none();

    @Before
    public void setUp() throws Exception {
        //initialize print stream
        outputStream = new ByteArrayOutputStream();
        mockPrintStream = mock(PrintStream.class);

        //initialize scanner
        mockScannerWrapper = mock(ScannerWrapper.class);

        //initialize library instance
        testBib = new Biblioteca(mockPrintStream, mockScannerWrapper);
    }

    // STORY 1.1 VIEW WELCOME MESSAGE
    @Test
    public void printWelcomeMessageTest() {
        testBib.printWelcomeMessage();
        verify(mockPrintStream).println("Welcome to Biblioteca!!! " +
                "Your librarians Megan and Clarisel at your service.");
    }

    // STORY 1.2 VIEW LIST OF ALL BOOKS
    //TODO fix test - loops on 1
    @Test
    public void shouldShowBookListForSelectionOne(){
        when(mockScannerWrapper.nextLine()).thenReturn("1");
        testBib.makeSelection();
        verify(mockPrintStream).println(testBib.bookList.toString());
    }

    // STORY 1.3 VIEW AUTHOR AND PUBLICATION YEAR
    @Test
    public void shouldPrintBooksWithTitleAuthorAndYear(){
        testBib.printBookList();
        verify(mockPrintStream).println(testBib.bookList.toString());
    }

    // STORY 1.4 VIEW MAIN MENU OF OPTIONS
    @Test
    public void shouldDisplayMenuWithOptionsBeforeBookList(){
        testBib.displayMenu();
        String expected = "\nSelect an option from the menu below:\n 1. See List of Books\n" +
                " 2. See List of Movies\n 3. Return Book\n 4. Return Movie\n 5. Exit Biblioteca\n 6. Log In\n";
        verify(mockPrintStream).println(expected);
    }

    @Test
    public void shouldNotShowBookListForSelectionThatIsNot1(){
        when (mockScannerWrapper.nextLine()).thenReturn("2");
        testBib.makeSelection();
        verify(mockPrintStream, never()).println(testBib.bookList.toString());
    }

    // STORY 1.5 NOTIFICATION FOR INVALID SELECTION
    @Test
    public void shouldShowErrorForInvalidSelectionInt(){
        when (mockScannerWrapper.nextLine()).thenReturn("8");
        testBib.makeSelection();
        verify(mockPrintStream).println("Error: Invalid Selection. Try Again.\n");
    }

    @Test
    public void shouldShowErrorForInvalidSelectionChar(){
        when (mockScannerWrapper.nextLine()).thenReturn("m");
        testBib.makeSelection();
        verify(mockPrintStream).println("Error: Invalid Selection. Enter an ID.\n");
    }

    // STORY 1.6 QUIT APPLICATION
    //TODO: Test if exited app? Maybe test exit code?
    @Test
    public void shouldexitForSelection5() throws Exception{
        when (mockScannerWrapper.nextLine()).thenReturn("5");
        testBib.makeSelection();
        //exit.expectSystemExit();
    }

//
//    @Test
//    public void exitsWithStatusCode1() {
//        exit.expectSystemExitWithStatus(1);
//        AppWithExit.doSomethingAndExit();
//    }

    // STORY 1.7 CHECKOUT A BOOK
    @Test
    public void shouldCheckOutBook(){
        // set itemType to book
        testBib.itemType = "book";
        // Set up mock for when user selects book 1
        when (mockScannerWrapper.nextLine()).thenReturn("1");

        // accept user input to check out book
        testBib.checkOut();

        Book bk2 = (Book)testBib.bookList.getByID("1");
        boolean bk2status = bk2.isCheckedOut();

        assertThat(bk2status, is(true));
    }

    // Checks if book is removed from list when checked out
    @Test
    public void shouldRemoveBookFromBookListWhenBookIsSelected(){
        // set itemType to book
        testBib.itemType = "book";
        // Set up mock for when user selects book 2
        when(mockScannerWrapper.nextLine()).thenReturn("2");

        testBib.checkOut();
        testBib.printBookList();
        verify(mockPrintStream).println(testBib.bookList.toString());
    }

    // STORY 1.8 SUCCESS MESSAGE FOR CHECKED OUT BOOK
    @Test
    public void shouldDisplaySuccessMessageWhenBookCheckedOut(){
        // set itemType to book
        testBib.itemType = "book";
        // Set up mock for when user selects book 2
        when(mockScannerWrapper.nextLine()).thenReturn("2");

        // accept user input to check out book
        testBib.checkOut();

        verify(mockPrintStream).println("You successfully checked out book [2] Beloved");
    }

    // STORY 1.9 UNSUCCESSFUL MESSAGE FOR CHECKING OUT

    // CASE: Book exists but is already checked out
    @Test
    public void shouldDisplayUnsuccessfulMessageWhenBookAlreadyCheckedOut(){
        // set itemType to book
        testBib.itemType = "book";
        // Set up mock for When user selects book 3
        when (mockScannerWrapper.nextLine()).thenReturn("3");
        testBib.checkOut();

        verify(mockPrintStream).println("book is already checked out. Please choose from list above");
    }

    // CASE: Book does not exist, out of bounds
    @Test
    public void shouldDisplayUnsuccessfulMessageWhenBookDoesNotExist(){
        // set itemType to book
        testBib.itemType = "book";
        // Set up mock for When user selects book 2
        when (mockScannerWrapper.nextLine()).thenReturn("6");
        testBib.checkOut();

        verify(mockPrintStream).println("book does not exist, try again");
    }

    // CASE: User input was not an integer
    @Test
    public void shouldDisplayUnsuccessfulMessageWhenUserInputNotInt(){
        // set itemType to book
        testBib.itemType = "book";
        // Set up mock for when user inputs character that is not integer
        when (mockScannerWrapper.nextLine()).thenReturn("p");
        testBib.checkOut();

        verify(mockPrintStream).println("Invalid selection, please enter a book ID and try again");
    }

    // STORY 1.10 RETURN A BOOK
    @Test
    public void shouldReturnBook() {
        // set itemType to book
        testBib.itemType = "book";
        // Set up mock for When user selects book 3
        when(mockScannerWrapper.nextLine()).thenReturn("3");

        testBib.returnItem();

        // This book is initially checked out
        Book bk3 = (Book)testBib.bookList.getByID("3");
        boolean bk3status = bk3.isCheckedOut();

        assertThat(bk3status, is(false));
    }

    // STORY 1.11 NOTIFIED ON SUCCESSFUL RETURN
    @Test
    public void shouldDisplaySuccessMessageForReturnBook(){
        // set itemType to book
        testBib.itemType = "book";
        // Set up mock for when user returns book 3
        when (mockScannerWrapper.nextLine()).thenReturn("3");
        testBib.returnItem();

        verify(mockPrintStream).println("You successfully returned book [3] Moment of Lift");
    }

    // STORY 1.12 NOTIFIED ON UNSUCCESSFUL RETURN
    // CASE: Book exists but is already checked out
    @Test
    public void shouldDisplayUnsuccessMessageForReturnAvailableBook(){
        // set itemType to book
        testBib.itemType = "book";
        // Set up mock for when user returns book 3
        when (mockScannerWrapper.nextLine()).thenReturn("2");
        testBib.returnItem();

        verify(mockPrintStream).println("book is already at the library. Please choose from list above");
    }

    // CASE: Book does not exist, out of bounds
    @Test
    public void shouldDisplayUnsuccessMessageForReturnNonExistentBook(){
        // set itemType to book
        testBib.itemType = "book";
        // Set up mock for when user returns book 9 (nonexistent)
        when (mockScannerWrapper.nextLine()).thenReturn("9");
        testBib.returnItem();

        verify(mockPrintStream).println("book does not exist, try again");
    }

    //CASE: User input was not an integer
    @Test
    public void shouldDisplayUnsuccessMessageForInvalidInputReturn(){
        // set itemType to book
        testBib.itemType = "book";
        // Set up mock for when user inputs invalid char
        when (mockScannerWrapper.nextLine()).thenReturn("n");
        testBib.returnItem();

        verify(mockPrintStream).println("Invalid selection, please enter a book ID and try again");
    }

    // STORY 2.1 VIEW LIST OF AVAILABLE MOVIES

    // STORY 2.2 CHECKOUT A MOVIE
    @Test
    public void shouldCheckOutMovie(){
        // set itemType to book
        testBib.itemType = "movie";
        // Set up mock for when user selects movie 2
        when (mockScannerWrapper.nextLine()).thenReturn("2");

        // accept user input to make menu selection
        testBib.checkOut();

        Movie mv2 = (Movie)testBib.movieList.getByID("2");
        boolean mv2status = mv2.isCheckedOut();

        assertThat(mv2status, is(true));
    }

    // Checks if movie is removed from list when checked out
    @Test
    public void shouldRemoveMovieFromListWhenMovieIsSelected(){
        // set itemType to book
        testBib.itemType = "movie";
        // Set up mock for when user selects movie 2
        when (mockScannerWrapper.nextLine()).thenReturn("2");
        testBib.checkOut();
        testBib.printMovieList();
        verify(mockPrintStream).println(testBib.movieList.toString());
    }

    // STORY 2.3 LOG IN AND VIEW BOOKS CHECKED OUT
    @Test
    //CASE: User not logged in
    public void shouldShowLogInOptionIfNoUserLoggedIn(){
        testBib.displayMenu();
        String expected = "\nSelect an option from the menu below:\n 1. See List of Books\n" +
                " 2. See List of Movies\n 3. Return Book\n 4. Return Movie\n 5. Exit Biblioteca\n 6. Log In\n";
        verify(mockPrintStream).println(expected);
    }

    @Test
    public void shouldShowSeeInfoOptionIfUserLoggedIn(){
        testBib.menu.setUserLoggedIn();
        testBib.displayMenu();
        String expected = "\nSelect an option from the menu below:\n 1. See List of Books\n" +
                " 2. See List of Movies\n 3. Return Book\n 4. Return Movie\n 5. Exit Biblioteca\n 6. See My Account Info\n";
        verify(mockPrintStream).println(expected);
    }

    @Test
    public void shouldNotifySuccessfulLogin(){
        // Set up user entering log in credentials
        when (mockScannerWrapper.nextLine()).thenReturn("222-7890,*******");
        testBib.promptLogIn();

        verify(mockPrintStream).println("\nWelcome back Clarisel Goodling");
    }

    //CASE: User logged in
    @Test
    public void shouldShowUserInformationIfUserLoggedIn(){
        when (mockScannerWrapper.nextLine()).thenReturn("6");
        testBib.menu.setUserLoggedIn();
        testBib.currentUser = testBib.userList.getUser("222-7890");
        testBib.makeSelection();
        String expected = testBib.currentUser.getUserInfo();
        verify(mockPrintStream).println(expected);
    }
}
