package com.twu.biblioteca;

import java.awt.*;
import java.io.PrintStream;

public class BibliotecaApp {

    public static void main(String[] args) {
        Biblioteca BA = new Biblioteca(new PrintStream(System.out));
        BA.printWelcomeMessage();
        BA.printMenuOptions();
    }
}
