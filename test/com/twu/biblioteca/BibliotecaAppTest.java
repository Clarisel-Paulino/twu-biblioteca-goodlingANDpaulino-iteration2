package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Test;
//import org.junit.contrib.java.lang.system.Assertion;
//import org.junit.contrib.java.lang.system.ExpectedSystemExit;

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
                " 2. See List of Movies\n 3. Return Book\n 4. Return Movie\n 5. Exit Biblioteca\n";
        verify(mockPrintStream).println(expected);
    }

    @Test
    public void shouldNotShowBookListForSelectionThatIsNot1(){
        when (mockScannerWrapper.nextLine()).thenReturn("3");
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
        verify(mockPrintStream).println("Error: Invalid Selection. Try Again.\n");
    }

    // STORY 1.6 QUIT APPLICATION
    //TODO: Test if exited app? Maybe test exit code?
    @Test
    public void shouldexitForSelection5() throws Exception{
            when (mockScannerWrapper.nextLine()).thenReturn("5");
            testBib.makeSelection();
            assertEquals("Exit status", 0);
    }

//    @Rule
//    public final ExpectedSystemExit exit = ExpectedSystemExit.none();
//
//    @Test
//    public void exits() {
//        exit.expectSystemExit();
//        AppWithExit.doSomethingAndExit(); // acceptoptions()
//    }
//
//    @Test
//    public void exitsWithStatusCode1() {
//        exit.expectSystemExitWithStatus(1);
//        AppWithExit.doSomethingAndExit();
//    }

    // STORY 1.7 CHECKOUT A BOOK
    @Test
    public void shouldCheckOutBook(){
        // Set up mock for When user selects book 2
        when (mockScannerWrapper.nextLine()).thenReturn("2");

        // accept user input to check out book
        testBib.checkOut();

        Book bk2 = testBib.bookList.getBookByID("2");
        boolean bk2status = bk2.isCheckedOut();

        assertThat(bk2status, is(true));
    }

    // Checks if book is removed from list when checked out
    @Test
    public void shouldRemoveBookFromBookListWhenBookIsSelected(){
        when (mockScannerWrapper.nextLine()).thenReturn("1");
        testBib.checkOut();
        testBib.printBookList();
        verify(mockPrintStream).println(testBib.bookList.toString());
    }

    // STORY 1.8 SUCCESS MESSAGE FOR CHECKED OUT BOOK
    @Test
    public void shouldDisplaySuccessMessageWhenBookCheckedOut(){
        // Set up mock for When user selects book 2
        when (mockScannerWrapper.nextLine()).thenReturn("2");

        // accept user input to check out book
        testBib.checkOut();

        verify(mockPrintStream).println("You successfully checked out book: 2");
    }

    // STORY 1.9 UNSUCCESSFUL MESSAGE FOR CHECKING OUT

    // CASE: Book exists but is already checked out
    @Test
    public void shouldDisplayUnsuccessfulMessageWhenBookAlreadyCheckedOut(){
        // Set up mock for When user selects book 2
        when (mockScannerWrapper.nextLine()).thenReturn("3");
        testBib.checkOut();

        verify(mockPrintStream).println("Book is already checked out. Please choose from list above");
    }

    // CASE: Book does not exist, out of bounds
    @Test
    public void shouldDisplayUnsuccessfulMessageWhenBookDoesNotExist(){
        // Set up mock for When user selects book 2
        when (mockScannerWrapper.nextLine()).thenReturn("6");
        testBib.checkOut();

        verify(mockPrintStream).println("Book does not exist, try again");
    }

    //CASE: User input was not an integer
    @Test
    public void shouldDisplayUnsuccessfulMessageWhenUserInputNotInt(){
        // Set up mock for when user inputs character that is not integer
        when (mockScannerWrapper.nextLine()).thenReturn("p");
        testBib.checkOut();

        verify(mockPrintStream).println("Invalid selection, please enter a book ID and try again");
    }

    // STORY 1.10 RETURN A BOOK
    @Test
    public void shouldReturnBook() {
        // Set up mock for When user selects book 3
        when(mockScannerWrapper.nextLine()).thenReturn("3");

        testBib.returnBook();

        // This book is initially checked out
        Book bk3 = testBib.bookList.getBookByID("3");
        boolean bk3status = bk3.isCheckedOut();

        assertThat(bk3status, is(false));
    }

    // STORY 1.11 NOTIFIED ON SUCCESSFUL RETURN
    @Test
    public void shouldDisplaySuccessMessageForReturnBook(){
        // Set up mock for when user returns book 3
        when (mockScannerWrapper.nextLine()).thenReturn("3");
        testBib.returnBook();

        verify(mockPrintStream).println("You successfully returned book: 3");
    }

    // STORY 1.11 NOTIFIED ON UNSUCCESSFUL RETURN
    // CASE: Book exists but is already checked out
    @Test
    public void shouldDisplayUnsuccessMessageForReturnAvailableBook(){
        // Set up mock for when user returns book 3
        when (mockScannerWrapper.nextLine()).thenReturn("2");
        testBib.returnBook();

        verify(mockPrintStream).println("Book is already at the library.");
    }

    // CASE: Book does not exist, out of bounds
    @Test
    public void shouldDisplayUnsuccessMessageForReturnNonExistentBook(){
        // Set up mock for when user returns book 3
        when (mockScannerWrapper.nextLine()).thenReturn("9");
        testBib.returnBook();

        verify(mockPrintStream).println("Book does not exist, try again");
    }

    //CASE: User input was not an integer
    @Test
    public void shouldDisplayUnsuccessMessageForInvalidInput(){
        // Set up mock for when user returns book 3
        when (mockScannerWrapper.nextLine()).thenReturn("n");
        testBib.returnBook();

        verify(mockPrintStream).println("Invalid selection, please enter a book ID and try again");
    }
}
