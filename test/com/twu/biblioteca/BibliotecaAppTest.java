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
//import static org.mockito.Mockito.*;


public class BibliotecaAppTest {

    private ByteArrayOutputStream outputStream;
    private PrintStream out;
    private Biblioteca testBib;
    private ArrayList<Book> bookList;
    private ScannerWrapper mockScannerWrapper;

    @Before
    public void setUp() throws Exception {
        outputStream = new ByteArrayOutputStream();
        out = new PrintStream(outputStream);
        testBib = new Biblioteca(out);

        //Scanner mockScanner = mock(ScannerWrapper.class);

        //initialize list of books
        Book oneBook = new Book("Crazy", "Kevin", 1010);
        Book twoBook = new Book("Bible", "Jesus", -1);
        Book threeBook = new Book("Moment of Lift", "Melinda Gates", 2018);

        bookList = new ArrayList<Book>(Arrays.asList(oneBook, twoBook, threeBook));
        testBib.setBookList(bookList);

    }

    @Test
    public void printWelcomeMessageTest() {
        testBib.printWelcomeMessage();

        assertEquals(outputStream.toString(), "Welcome to Biblioteca!!! " +
                "Your librarians Megan and Clarisel at your service.\n");
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

    @Test
    public void shouldPrintBooksWithTitleAuthorAndYear(){

        testBib.printBookList();

        StringBuffer expected = new StringBuffer();

        // For each book in the list of books, add title, author and year to
        // expected string
        for (Book book : bookList){
            String title = book.getTitle();
            String author = book.getAuthor();
            String year = Integer.toString(book.getYear());

            expected.append(title + "," + author + "," + year + '\n');
        }

        assertThat(outputStream.toString(), is(expected.toString()));
    }

    @Test
    public void shouldDisplayMenuWithOptionsBeforeBookList(){
        testBib.displayMenu();
        assertThat(outputStream.toString(), is("Select an option from the menu below: \n1. See List of Books\n2. Exit Biblioteca\n"));
    }

    //TODO Test raw input function
//    @Test
//    public void shouldStoreUserSelection(){
//        int expected = 1;
//
//        //assertThat(testBib.selectMenuOption(), is(expected));
//    }
    //INPUT TEST
    //when (mockScanner.nextLine()).thenReturn("2"));
    // never called
    //verify(mockPrintStream, never()).println(expectedBook.toString())

    @Test
    public void shouldShowBookListForSelectionOne(){
        //mock scanner for input here
        int select = 1;
        testBib.makeSelection(select);

        assertThat(outputStream.toString(), is("Crazy,Kevin,1010\nBible,Jesus,-1\nMoment of Lift,Melinda Gates,2018\n"));
    }

    @Test
    public void shouldShowErrorForInvalidSelection(){
        //mock scanner for input here
        int select = 3;
        testBib.makeSelection(select);

        assertThat(outputStream.toString(), is("Error: Invalid Selection. Try Again.\n"));
    }

    @Test
    public void shouldCheckOutBook(){
       Book bk = bookList.get(0);
       testBib.checkOut(0);

        assertThat(bk.getCheckedOut(), is(true));
    }

    @Test
    public void shouldRemoveBookFromBookList(){
        Book bk = bookList.get(0);
        testBib.checkOut(0);
        StringBuffer expected = new StringBuffer();

        // For each book in the list of books, add title, author and year to
        // expected string except checked out book
        for (Book book : bookList){

            if (!book.equals(bk)) {
                String title = book.getTitle();
                String author = book.getAuthor();
                String year = Integer.toString(book.getYear());
                expected.append(title + "," + author + "," + year + '\n');
            }
        }
        testBib.printBookList();

        //TODO Fails because notification introduced in next test...
        assertThat(outputStream.toString(), is(expected.toString()));
    }

    @Test
    public void shouldNotifyUserIfBookSuccessfullyCheckedOut(){
        //user makes input selection from the bookList with index
        int bookIndex = 1;

        //call checkout with index
        testBib.checkOut(bookIndex);

        //Notify user that book has been checked out
        assertThat(outputStream.toString(), is("You successfully checked out book: " + bookIndex + '\n'));
    }



}
