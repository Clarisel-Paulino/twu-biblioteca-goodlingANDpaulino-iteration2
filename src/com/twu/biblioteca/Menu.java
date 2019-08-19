package com.twu.biblioteca;

public class Menu {

    private String menuOptions;
    private ScannerWrapper scanner;

    // User input for menu option
    private int menuSelection;

    public Menu(ScannerWrapper scanner) {
        this.menuOptions = "Select an option from the menu below:\n 1. See List of Books\n" +
                " 2. See List of Movies\n 3. Return Book\n 4. Return Movie\n 5. Exit Biblioteca\n";
        this.scanner = scanner;
    }

    public String getMenuOptions() {
        return menuOptions;
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

