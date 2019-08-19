package com.twu.biblioteca;

import java.util.ArrayList;

public class User {
    private String libraryNumber;
    private String password;
    private String name;
    private String email;
    private String phoneNumber;
    private ArrayList<RentalItem> checkedOutItems;

    public void User(String libraryNumber, String password, String name, String  email, String phoneNumber){
        this.libraryNumber = libraryNumber;
        this.password = password;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.checkedOutItems = new ArrayList<RentalItem>();
    }

    public String getUserInfo(){
        String ln = String.format("%-30s", "Library Number: ");
        String nm = String.format("%-30s", "Name: ");
        String em = String.format("%-30s", "Email: ");
        String pn = String.format("%-30s", "Phone Number:" );
        String line = "\n===========================================\n";

        StringBuilder sb = new StringBuilder();
        sb.append(line);
        sb.append(ln + libraryNumber + "\n");
        sb.append(nm + name + "\n");
        sb.append(em + email + "\n");
        sb.append(pn + phoneNumber + "\n");
        sb.append(line);
        return sb.toString();
    }
}
