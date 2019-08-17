package com.twu.biblioteca;

import java.io.PrintStream;
import java.util.ArrayList;

public class Menu {

    private ArrayList<String> menuOptions;
    private ScannerWrapper scanner;
    private PrintStream printStream;

    // User input for menu option and book selection
    private String menuSelection;
    private String bookSelection;

    public Menu(PrintStream printStream , ScannerWrapper scanner) {
        this.menuOptions = new ArrayList<String>();
        this.scanner = scanner;
        this.printStream = printStream;
        menuOptions.add("1. See List of Books");
        menuOptions.add("2. Exit Biblioteca");
    }

    public ArrayList<String> getMenuOptions() {
        return menuOptions;
    }

    public String printMenuOptions(){
        StringBuilder options = new StringBuilder();
        options.append("Select an option from the menu below:\n");

        for(String option : menuOptions){
            options.append(option + '\n');
        }
        return options.toString();
    }
}

