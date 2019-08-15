package com.twu.biblioteca;

import java.io.PrintStream;

public class Biblioteca {

    private PrintStream printStream;
    private Menu menu;

    //constructer
    public Biblioteca(PrintStream printStream){
        this.printStream = printStream;
        this.menu = new Menu();
    }

    //welcome message
    public void printWelcomeMessage(){
        printStream.println("Welcome to Biblioteca!!! " +
                "Your librarians Megan and Clarisel at your service.");
    }

    //menu options
    public void printMenuOptions(){
        printStream.println("Select an option from the menu below " + menu.printMenuOptions());
    }

}
