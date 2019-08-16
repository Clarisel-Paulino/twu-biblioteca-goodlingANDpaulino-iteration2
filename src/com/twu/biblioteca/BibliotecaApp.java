package com.twu.biblioteca;

import java.awt.*;
import java.io.PrintStream;

public class BibliotecaApp {

    public static void main(String[] args) {
        Biblioteca BA = new Biblioteca(new PrintStream(System.out));
        BA.printWelcomeMessage();
        BA.displayMenu();

        // Ask user to select a menu option
        int menuSelection = BA.acceptOptionInput();
        // Show appropriate information for selection
        BA.makeSelection(menuSelection);

        // Ask user to select a book index
        //int bookSelection = BA.selectBook();
        // Checkout selected book
        //BA.checkOut(bookSelection);
    }
}
