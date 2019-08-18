package com.twu.biblioteca;

import java.awt.*;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class BibliotecaApp {

    public static void main(String[] args) {
        Biblioteca BA = new Biblioteca(new PrintStream(System.out), new ScannerWrapper());

        BA.start();
    }
}
