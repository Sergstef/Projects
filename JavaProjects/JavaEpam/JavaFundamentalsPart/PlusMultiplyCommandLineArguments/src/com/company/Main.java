package com.company;

public class Main {

    public static void main(String[] args) {
        int plusResult = 0;
        int multiplyResult = 1;
        int tempNumberFromArray;
        for(int i = 0; i < args.length; i++) {
            tempNumberFromArray = Integer.parseInt(args[i]);
            plusResult += tempNumberFromArray;
            multiplyResult *= tempNumberFromArray;
        }
        System.out.println(plusResult);
        System.out.println(multiplyResult);
    }
}
