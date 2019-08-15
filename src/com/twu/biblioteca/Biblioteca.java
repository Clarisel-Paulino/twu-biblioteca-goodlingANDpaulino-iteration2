package com.twu.biblioteca;

import java.io.PrintStream;
import java.util.ArrayList;

public class Biblioteca {

    private PrintStream printStream;
    private Menu menu;
    private ArrayList<String> bookList;

    //constructer
    public Biblioteca(PrintStream printStream){
        this.printStream = printStream;
        this.menu = new Menu();
        this.bookList = new ArrayList<String>();
        bookList.add("1984");
    }

    //welcome message
    public void printWelcomeMessage(){
        printStream.println("Welcome to Biblioteca!!! " +
                "Your librarians Megan and Clarisel at your service.");
    }

    //menu options
    public void printMenuOptions(){
        printStream.println("Select an option from the menu below:");
    }

    public void printBookList(){
        printStream. println(bookList);
    }
}
