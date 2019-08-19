package com.twu.biblioteca;

import java.util.ArrayList;
import java.util.HashMap;

public class UserList {

    // Key: library number,  Value: User
    private HashMap<String,User> bibUsers;

    public UserList(){
        this.bibUsers = new HashMap<String,User>();
        // Fake user
        User fakeUser = new User("222-7890", "*******",
                "Clarisel Goodling", "megan.paulino@gmail.com", "407-773-1551");
        bibUsers.put("222-7890",fakeUser);
    }

    /**
     * logIn
     * @param logInCredentials - "libraryNumber,password"
     * @return User whose credentials match given input
     */
    public User logIn(String logInCredentials){
        //parse log in credentials
        String[] creds = logInCredentials.split(",");

        String libNum = creds[0];
        String password = creds[1];

        //verify that fake user's libraryNumber and password match

        // CASE: user not in hash map
        if (!bibUsers.containsKey(libNum)) return null;

        // CASE: User is in system
        User user = bibUsers.get(libNum);
        String expectedPass = user.getPassword();

        // compare given password with expected password
       if (password.equals(expectedPass)) {
           //Log in successful, return user
           return user;
       }
       else {
           // Error logging in
           return null;
       }
    }

    /**
     * getUser
     * @param libNum - library number as string "xxx-xxxx"
     * @return User that corresponds to libNum in bibUsers
     */
    public User getUser(String libNum){
        return bibUsers.get(libNum);
    }


}
