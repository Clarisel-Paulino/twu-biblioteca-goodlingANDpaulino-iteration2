package com.twu.biblioteca;

import java.util.ArrayList;

public class Menu {

    private ArrayList<String> menuOptions;

    public Menu() {
        ArrayList<String> menuOptions = new ArrayList<String>();
        menuOptions.add("1. See List of Books");
        menuOptions.add("2. Exit Biblioteca");
        this.menuOptions = menuOptions;
    }

    public ArrayList<String> getMenuOptions() {
        return menuOptions;
    }
}

