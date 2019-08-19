package com.twu.biblioteca;

public class Menu {

    private ScannerWrapper scanner;

    // User input for menu option
    private int menuSelection;
    private boolean userLoggedIn;

    public Menu(ScannerWrapper scanner) {
        this.scanner = scanner;
        this.userLoggedIn = false;
    }

    /**
     * getMenuOptions
     * @return menu options as string based on whether or not user is logged in
     */
    public String getMenuOptions() {
        StringBuilder sb = new StringBuilder();

        // Initial Menu Options
        sb.append("\nSelect an option from the menu below:\n 1. See List of Books\n" +
                " 2. See List of Movies\n 3. Return Book\n 4. Return Movie\n 5. Exit Biblioteca\n");
        
        // User is logged in
        if(userLoggedIn){
            sb.append("6. See My Account Info\n");
        }
        // User is not logged in
        else {
            sb.append("6. Log In\n");
        }
        return sb.toString();
    }

    /**
     * sets userLoggedIn boolean to True
     */
    public void setUserLoggedIn(){
        this.userLoggedIn = true;
    }

    /**
     * sets userLoggedIn boolean to False
     */
    public void setUserLoggedOut(){
        this.userLoggedIn = false;
    }

    /**
     * Parses user input and stores valid input as the menu option selection
     */
    public void parseOptionInput() {
        String select = scanner.nextLine();

        //check if menuSelection is an int
        try {
            // sets menuSelection as valid int
            menuSelection = Integer.parseInt(select);
        }

        // CASE: user input was not an integer
        catch (NumberFormatException e){
            // Sets menu selection as -1
            menuSelection = -1;
        }
    }

    public int getMenuSelection(){
        return menuSelection;
    }
}

