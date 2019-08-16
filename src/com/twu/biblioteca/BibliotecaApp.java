package com.twu.biblioteca;

import java.awt.*;
import java.io.PrintStream;
import java.util.Scanner;

public class BibliotecaApp {

    public static void main(String[] args) {
        Biblioteca BA = new Biblioteca(new PrintStream(System.out), new ScannerWrapper());
        BA.printWelcomeMessage();
        BA.displayMenu();

        // Ask user to select a menu option
        BA.acceptOptionInput();
    }
}
