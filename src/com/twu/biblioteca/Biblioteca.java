package com.twu.biblioteca;

import java.io.PrintStream;
import java.util.ArrayList;

public class Biblioteca {

    private PrintStream printStream;
    private Menu menu;
    private ArrayList<Book> bookList;

    //constructer
    public Biblioteca(PrintStream printStream){
        this.printStream = printStream;
        this.menu = new Menu();
        this.bookList = new ArrayList<Book>();
    }

    public void setBookList(ArrayList<Book> books){
        this.bookList = books;
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
