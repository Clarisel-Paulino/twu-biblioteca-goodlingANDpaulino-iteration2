package com.twu.biblioteca;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Menu {

    private ArrayList<String> menuOptions;

    public Menu(){
        initializeMenu();
    }

    public ArrayList<String> getMenuOptions(){
        return this.menuOptions;
    }

    private void initializeMenu(){
        ArrayList<String> menuOptions = new ArrayList<String>();
        menuOptions.add("1. See List of Books");
        menuOptions.add("2. Exit Biblioteca");
        this.menuOptions = menuOptions;
    }

    public String printMenuOptions(){
        return menuOptions.toString();
    }



//    public int printOptions(){
//        int selection;
//        Scanner input = new Scanner(System.in);
//
//        /***************************************************/
//
//        System.out.println("What would you like to do?");
//        System.out.println("-------------------------\n");
//        System.out.println("1 - View list of books");
//
//        selection = input.nextInt();
//        return selection;
//    }
}
