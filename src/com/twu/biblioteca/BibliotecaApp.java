package com.twu.biblioteca;

import java.io.PrintStream;

public class BibliotecaApp {

    private PrintStream printStream;

    public BibliotecaApp(PrintStream printStream){
        this.printStream = printStream;
    }

    public void printWelcomeMessage(){
        printStream.println("Welcome to Biblioteca!!! " +
                "Your librarians Megan and Clarisel at your service.");
    }

    public static void main(String[] args) {
        BibliotecaApp BA = new BibliotecaApp(System.out);
        BA.printWelcomeMessage();
    }
}
