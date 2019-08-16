package com.twu.biblioteca;

import java.util.Scanner;

public class ScannerWrapper {

    private Scanner scan;

    public ScannerWrapper() {
        scan = new Scanner(System.in);
    }

    public String nextLine(){
        return scan.nextLine();
    }
}
