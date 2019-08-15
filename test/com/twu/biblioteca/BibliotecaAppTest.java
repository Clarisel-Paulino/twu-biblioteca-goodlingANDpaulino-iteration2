package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;

public class BibliotecaAppTest {

    private ByteArrayOutputStream outputStream;
    private PrintStream out;
    private Biblioteca testBib;

    @Before
    public void setUp() throws Exception {
        outputStream = new ByteArrayOutputStream();
        out = new PrintStream(outputStream);
        testBib = new Biblioteca(out);
    }

    @Test
    public void printWelcomeMessageTest() {
        testBib.printWelcomeMessage();

        assertEquals(outputStream.toString(), "Welcome to Biblioteca!!! " +
                "Your librarians Megan and Clarisel at your service.\n");
    }

    @Test
    public void shouldPrintBookListWithOneBook(){
        //add relevant books here
        Book oneBook = new Book("1984", "George Orwell", 1961);

        ArrayList<Book> bl = new ArrayList<Book>(Arrays.asList(oneBook));

        testBib.setBookList(bl);
        testBib.printBookList();

        assertThat(outputStream.toString(), is(oneBook.getTitle()));
    }

    @Test
    public void shouldPrintTwoBookTitlesWhenIStarttheApp() {
        //add relevant books here
        Book oneBook = new Book("Crazy", "Kevin", 1010);
        Book twoBook = new Book("Bible", "Jesus", -1);

        ArrayList<Book> bl = new ArrayList<Book>(Arrays.asList(oneBook, twoBook));

        testBib.setBookList(bl);
        testBib.printBookList();

        assertThat(outputStream.toString(), is(oneBook.getTitle() + '\n' + twoBook.getTitle() + '\n'));
    }

    @Test
    public void shouldPrintBookWithTitleAuthorAndYear(){
        Book oneBook = new Book("Bible", "Jesus", -1);

        ArrayList<Book> bl = new ArrayList<Book>(Arrays.asList(oneBook));

        testBib.setBookList(bl);
        testBib.printBookList();

        assertThat(outputStream.toString(), is(oneBook.getTitle() + ',' +
                oneBook.getAuthor() + ',' + Integer.toString(oneBook.getYear()) + '\n'));
    }

    @Test
    public void shouldDisplayMenuWithOptionsBeforeBookList(){
        testBib.displayMenu();
        assertThat(outputStream.toString(), is("Select an option from the menu below: \n1. See List of Books\n2. Exit Biblioteca\n"));

    }
}
