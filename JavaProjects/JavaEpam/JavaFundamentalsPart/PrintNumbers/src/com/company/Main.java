package com.company;

import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Random rand = new Random();
        Scanner input = new Scanner(System.in);
        System.out.print("Enter the number of numbers: ");
        int currentNumber;
        int numberOfNumbers = input.nextInt();
        for(int i = 0; i <= numberOfNumbers; i++) {
            currentNumber = rand.nextInt();
            System.out.println(currentNumber);
        }
        for(int i = 0; i <= numberOfNumbers; i++) {
            currentNumber = rand.nextInt();
            System.out.print(currentNumber + " ");
        }
    }
}
