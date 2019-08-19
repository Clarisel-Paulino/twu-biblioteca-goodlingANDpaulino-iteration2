package com.twu.biblioteca;

import java.util.ArrayList;

public class UserList {
    private ArrayList<User> bibUsers;

    public UserList(){
        this.bibUsers = new ArrayList<User>();
        // Fake user
        User fakeUser = new User("222-7890", "*******",
                "Clarisel Goodling", "megan.paulino@gmail.com", "407-773-1551");
        bibUsers.add(fakeUser);
    }

    public logIn(String logInCredentials){

    }
}
