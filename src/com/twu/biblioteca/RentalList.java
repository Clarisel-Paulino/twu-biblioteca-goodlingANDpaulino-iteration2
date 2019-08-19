package com.twu.biblioteca;

import java.util.ArrayList;
import java.util.HashMap;

public class RentalList {
    // List of all rental items in library
    private ArrayList<RentalItem> rentalList;

    // List of all rental items available for checkout
    public ArrayList<RentalItem> availItemList;

    //Hash map that maps item to user who checked it out
    public HashMap<RentalItem, User> checkedOutList;

    // Current number of items, used to give item an ID num
    private int numItems;

    /**
     * LibraryList Constructor
     * Initializes availItemList and rentalList
     */
    public RentalList(){
        this.availItemList = new ArrayList<RentalItem>();
        this.rentalList = new ArrayList<RentalItem>();
        this.checkedOutList = new HashMap<RentalItem, User>();
    }

    /**
     * getByID
     * @param idString - rental item id as a string
     * @return rental item object with the idString as its id
     */
    public RentalItem getByID(String idString){
        int id = Integer.parseInt(idString);
        return rentalList.get(id - 1);
    }

    /**
     * initAdd
     * @param item - instance of RentalItem
     * adds rental item to rentalList(and availItemList) and sets the item ID
     * to initialize it
     */
    public void initAdd(RentalItem item){
        numItems++;
        item.setId(numItems);
        this.rentalList.add(item);

        // Only add item to availItemList if not checked out
        if(!item.isCheckedOut()){
            this.availItemList.add(item);
        }
    }

    public ArrayList<RentalItem> getAvailItemList(){
        return this.availItemList;
    }

    /**
     * updateAvailList
     * @param item -- RentalItem object to be removed
     *  Removes item from the list of available rental items
     */
    public void updateAvailList(RentalItem item){
        if (item.isCheckedOut()){
            availItemList.remove(item);
        }
        else{
            availItemList.add(item);
        }
    }

    public int getNumItems(){
        return numItems;
    }

    public void addCheckedOutItem(User user, RentalItem item){
        checkedOutList.put(item, user);
    }

    public void removeCheckedOutItem(RentalItem item){
        checkedOutList.remove(item);
    }
}
