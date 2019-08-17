package com.twu.biblioteca;

import java.awt.*;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class BibliotecaApp {

    public static void main(String[] args) {
        Biblioteca BA = new Biblioteca(new PrintStream(System.out), new ScannerWrapper());

        // Set up dummy book list
        Book oneBook = new Book("Crazy", "Kevin", 1010);
        Book twoBook = new Book("Bible", "Jesus", -1);
        Book threeBook = new Book("Moment of Lift", "Melinda Gates", 2018);
        ArrayList<Book> bookList = new ArrayList<Book>(Arrays.asList(oneBook, twoBook, threeBook));
        //BA.setBookList(bookList);

        BA.printWelcomeMessage();
        BA.displayMenu();

        // Ask user to select a menu option
        BA.acceptOptionInput();
    }
}
