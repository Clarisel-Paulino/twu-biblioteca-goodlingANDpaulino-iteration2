package com.twu.biblioteca;

import java.util.ArrayList;

public class LibraryList {

    // List of all rental items in library
    private ArrayList<RentalItem> libList;
    // List of all rental items available for checkout
    public ArrayList<RentalItem> availItemList;

    // Current number of items, used to give item an ID num
    private int numItems;

    /**
     * LibraryList Constructor
     * Initializes availItemList and libList
     */
    public LibraryList(){
        this.availItemList = new ArrayList<RentalItem>();
        this.libList = new ArrayList<RentalItem>();
    }

    /**
     * getByID
     * @param idString - rental item id as a string
     * @return rental item object with the idString as its id
     */
    public RentalItem getByID(String idString){
        int id = Integer.parseInt(idString);
        return libList.get(id - 1);
    }

    /**
     * initAdd
     * @param item - instance of RentalItem
     * adds rental item to libList(and availItemList) and sets the item ID
     * to initialize it
     */
    private void initAdd(RentalItem item){
        numItems++;
        item.setId(numItems);
        this.libList.add(item);

        // Only add item to availItemList if not checked out
        if(!item.isCheckedOut()){
            this.availItemList.add(item);
        }
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

}
