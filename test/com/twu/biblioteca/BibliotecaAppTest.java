package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class BibliotecaAppTest {

    private ByteArrayOutputStream outputStream;
    private PrintStream out;
    private Biblioteca testBib;
    private ScannerWrapper mockScannerWrapper;
    private PrintStream mockPrintStream;

    @Before
    public void setUp() throws Exception {
        //initialize print stream
        outputStream = new ByteArrayOutputStream();
        out = new PrintStream(outputStream);
        mockPrintStream = mock(PrintStream.class);

        //initialize scanner
        mockScannerWrapper = mock(ScannerWrapper.class);

        //initialize library instance
        testBib = new Biblioteca(mockPrintStream, mockScannerWrapper);

        //initialize list of books
        Book oneBook = new Book("Crazy", "Kevin", 1010);
        Book twoBook = new Book("Bible", "Jesus", -1);
        Book threeBook = new Book("Moment of Lift", "Melinda Gates", 2018);

        //add books to library
        testBib.addBook(oneBook);
        testBib.addBook(twoBook);
        testBib.addBook(threeBook);
    }

    // STORY 1.1 VIEW WELCOME MESSAGE
    @Test
    public void printWelcomeMessageTest() {
        testBib.printWelcomeMessage();
        verify(mockPrintStream).println("Welcome to Biblioteca!!! " +
                "Your librarians Megan and Clarisel at your service.");
    }

//    @Test
//    public void shouldPrintBookListWithOneBook(){
//        //add relevant books here
//        Book oneBook = new Book("1984", "George Orwell", 1961);
//
//        ArrayList<Book> bl = new ArrayList<Book>(Arrays.asList(oneBook));
//
//        testBib.setBookList(bl);
//        testBib.printBookList();
//
//        assertThat(outputStream.toString(), is(oneBook.getTitle()));
//    }

//    @Test
//    public void shouldPrintTwoBookTitlesWhenIStarttheApp() {
//        //add relevant books here
//        Book oneBook = new Book("Crazy", "Kevin", 1010);
//        Book twoBook = new Book("Bible", "Jesus", -1);
//
//        ArrayList<Book> bl = new ArrayList<Book>(Arrays.asList(oneBook, twoBook));
//
//        testBib.setBookList(bl);
//        testBib.printBookList();
//
//        assertThat(outputStream.toString(), is(oneBook.getTitle() + '\n' + twoBook.getTitle() + '\n'));
//    }

    // STORY 1.3 VIEW AUTHOR AND PUBLICATION YEAR
    //TODO: Add mock print stream
    @Test
    public void shouldPrintBooksWithTitleAuthorAndYear(){
        testBib.printBookList();

        StringBuffer expected = new StringBuffer();

        // For each book in the list of books, add title, author and year to
        // expected string
        for (Book book : testBib.getBookList()){
            String title = book.getTitle();
            String author = book.getAuthor();
            String year = Integer.toString(book.getYear());

            expected.append(title + "," + author + "," + year + '\n');
        }

        assertThat(outputStream.toString(), is(expected.toString()));
    }

    // STORY 1.4 VIEW MAIN MENU OF OPTIONS
    //TODO: FIX ME - 3 lines printed instead of 1
    @Test
    public void shouldDisplayMenuWithOptionsBeforeBookList(){
        testBib.displayMenu();
        String expected1 = "Select an option from the menu below: \n";
        String expected2 = "1. See List of Books\n";
        String expected3 = "2. Exit Biblioteca\n";

        //verify(mockPrintStream).println(expected1);
        verify(mockPrintStream).println(expected1 + expected2 + expected3);
        //verify(mockPrintStream).println(expected3);
    }

    // STORY 1.2 VIEW LIST OF ALL BOOKS
    @Test
    public void shouldShowBookListForSelectionOne(){
        when(mockScannerWrapper.nextLine()).thenReturn("1");
        testBib.acceptOptionInput();
        verify(mockPrintStream).println(testBib.getBookList().toString());
    }

    @Test
    public void shouldNotShowBookListForSelectionThree(){
        when (mockScannerWrapper.nextLine()).thenReturn("3");
        testBib.acceptOptionInput();
        verify(mockPrintStream, never()).println(testBib.getBookList().toString());
    }

    // STORY 1.6 QUIT APPLICATION
    //TODO: Test if exited app? Maybe test exit code?
    @Test
    public void shouldNotShowBookListForSelectionTwo(){
        when (mockScannerWrapper.nextLine()).thenReturn("2");
        testBib.acceptOptionInput();
        //verify(mockPrintStream, never()).println(testBib.getBookList().toString());
    }

    // STORY 1.5 NOTIFICATION FOR INVALID SELECTION
    //TODO change to scanner mock
    @Test
    public void shouldShowErrorForInvalidSelection(){
        //mock scanner for input here
        int select = 3;
        testBib.makeSelection();

        assertThat(outputStream.toString(), is("Error: Invalid Selection. Try Again.\n"));
    }

    // STORY 1.7 CHECKOUT A BOOK
    @Test
    public void shouldCheckOutBook(){
        // Set up mock for When user selects book 2
        when (mockScannerWrapper.nextLine()).thenReturn("2");

        // accept user input to check out book
        testBib.selectBook();

        Book bk2 = testBib.getBookByID("2");
        boolean bk2status = bk2.getCheckedOut();

        assertThat(bk2status, is(true));
    }

//    //TODO Fails because success notification introduced in next test...
//    @Test
//    public void shouldRemoveBookFromBookList(){
//        Book bk = testBib.getBookList().get(0);
//        testBib.checkOut(0);
//        StringBuffer expected = new StringBuffer();
//
//        // For each book in the list of books, add title, author and year to
//        // expected string except checked out book
//        for (Book book : testBib.getBookList()){
//
//            if (!book.equals(bk)) {
//                String title = book.getTitle();
//                String author = book.getAuthor();
//                String year = Integer.toString(book.getYear());
//                expected.append(title + "," + author + "," + year + '\n');
//            }
//        }
//        testBib.printBookList();
//        assertThat(outputStream.toString(), is(expected.toString()));
//    }

    // STORY 1.8 SUCCESS MESSAGE FOR CHECKED OUT BOOK
    @Test
    public void shouldDisplaySuccessMessageWhenBookCheckedOut(){
        // Set up mock for When user selects book 2
        when (mockScannerWrapper.nextLine()).thenReturn("2");

        // accept user input to check out book
        testBib.selectBook();

        verify(mockPrintStream).println("You successfully checked out book: 2");
    }

    // STORY 1.9 UNSUCCESSFUL MESSAGE FOR CHECKING OUT
    // TODO: MAKE THIS TEST
    
    // check if book removed
    @Test
    public void shouldRemoveBookFromBookList(){
        when (mockScannerWrapper.nextLine()).thenReturn("1");
        testBib.selectBook();
        testBib.printBookList();
        //verify(mockPrintStream).println(testBib.getBookList().)
    }

    //notify if successfully book is returned

    //notify if book is not successfully returned
}
