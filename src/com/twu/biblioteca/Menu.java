package com.twu.biblioteca;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Menu {

    private ArrayList<String> menuOptions;
    private int selection;

    public Menu() {
        initializeMenu();
    }

    private void initializeMenu() {
        ArrayList<String> menuOptions = new ArrayList<String>();
        menuOptions.add("1. See List of Books");
        menuOptions.add("2. Exit Biblioteca");
        this.menuOptions = menuOptions;
    }

    public ArrayList<String> getMenuOptions() {
        return menuOptions;
    }

    public int selectMenuOption() {
        int selection;
        Scanner input = new Scanner(System.in);
        selection = input.nextInt();
        return selection;
    }
}

