package com.twu.biblioteca;

import java.io.PrintStream;

public class BibliotecaApp {

    public static void main(String[] args) {
        Biblioteca BA = new Biblioteca(new PrintStream(System.out), new ScannerWrapper());

        BA.start();
    }
}
