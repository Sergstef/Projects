package com.company;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter number of numbers");
        int numberOfNumbers = input.nextInt();
        int [] arrayOfInputNumbers = new int[numberOfNumbers];
        int i = 0;
        while (i < numberOfNumbers) {
            System.out.println("Enter the number");
            arrayOfInputNumbers[i] = input.nextInt();
            i++;
        }

        // Find the shortest and longest number
        String shortestNumber = String.valueOf(Math.abs(arrayOfInputNumbers[0]));
        String longestNumber = String.valueOf(Math.abs(arrayOfInputNumbers[0]));
        String strInputNumber;
        for(i = 1; i < arrayOfInputNumbers.length; i++) {
            strInputNumber = String.valueOf(Math.abs(arrayOfInputNumbers[i]));
            if (strInputNumber.length() <= shortestNumber.length()) {
                shortestNumber = strInputNumber;
            }
            if (strInputNumber.length() >= longestNumber.length()) {
                longestNumber = strInputNumber;
            }
        }
        System.out.println("The last shortest number of input - " + shortestNumber);
        System.out.println("Length - " + shortestNumber.length());
        System.out.println("The last longest number of input - " + longestNumber);
        System.out.println("Length - " + longestNumber.length());

        //Print numbers in ascending order of their lengths
        int indexOfShortestNumber;
        int tmp;
        for(i = 0; i < arrayOfInputNumbers.length; i++) {
            shortestNumber = String.valueOf(Math.abs(arrayOfInputNumbers[i]));
            indexOfShortestNumber = i;
            for(int j = i + 1; j < arrayOfInputNumbers.length; j++) {
                if(String.valueOf(Math.abs(arrayOfInputNumbers[j])).length() < shortestNumber.length()) {
                    shortestNumber = String.valueOf(Math.abs(arrayOfInputNumbers[j]));
                    indexOfShortestNumber = j;
                }
            }
            if(i != indexOfShortestNumber) {
                tmp = arrayOfInputNumbers[i];
                arrayOfInputNumbers[i] = arrayOfInputNumbers[indexOfShortestNumber];
                arrayOfInputNumbers[indexOfShortestNumber] = tmp;
            }

        }
        for(i = 0; i < arrayOfInputNumbers.length; i++) {
            System.out.print(arrayOfInputNumbers[i] + " ");
        }
        System.out.println();

        //Print to the console those numbers whose length is less (more)
        // than the average length for all numbers, as well as the length.
        int averageLengthOfNumbers = 0;
        for(i = 0; i < arrayOfInputNumbers.length; i++)  {
            averageLengthOfNumbers += String.valueOf(Math.abs(arrayOfInputNumbers[i])).length();
        }
        averageLengthOfNumbers = averageLengthOfNumbers / arrayOfInputNumbers.length;
        System.out.println("Average length = " + averageLengthOfNumbers);
        System.out.println("Numbers whose length is less " +
                "than the average length for all numbers, as well as the length");
        for(i = 0; i < arrayOfInputNumbers.length; i++) {
            if (String.valueOf(Math.abs(arrayOfInputNumbers[i])).length() < averageLengthOfNumbers) {
                System.out.print(arrayOfInputNumbers[i] + " ");
            }
        }
        System.out.println();
        System.out.println("Numbers whose length is more " +
                "than the average length for all numbers, as well as the length");
        for(i = 0; i < arrayOfInputNumbers.length; i++) {
            if (String.valueOf(Math.abs(arrayOfInputNumbers[i])).length() > averageLengthOfNumbers) {
                System.out.print(arrayOfInputNumbers[i] + " ");
            }
        }
        System.out.println();

        //Find the number with the minimum number of
        // distinct digits. If there are several such numbers, find the first one
        int numberWithMinimumDistinctDigits = 0;
        int numberOfUniqueDigits = 0;
        for(i = 0; i < arrayOfInputNumbers.length; i++) {
            strInputNumber = String.valueOf(Math.abs(arrayOfInputNumbers[i]));
            HashSet<String> uniqueDigitsOfNumber = new HashSet<String>(Arrays.asList(strInputNumber.split("")));
            if (i == 0) {
                numberWithMinimumDistinctDigits = arrayOfInputNumbers[i];
                numberOfUniqueDigits = uniqueDigitsOfNumber.size();
                continue;
            }
            if (numberOfUniqueDigits > uniqueDigitsOfNumber.size()) {
                numberOfUniqueDigits = uniqueDigitsOfNumber.size();
                numberWithMinimumDistinctDigits = arrayOfInputNumbers[i];
            }
        }
        System.out.println("Number with minimum distinct digits");
        System.out.println(numberWithMinimumDistinctDigits);

        //Find the number of numbers containing only even digits,
        //and among the rest - the number of numbers with an equal number of even and odd digits
        int numberOfNumbersWithOnlyEvenDigits = 0;
        int numberOfNumbersWithEqualNumberOfEvenAndOddDigits = 0;
        for(i = 0; i < arrayOfInputNumbers.length; i++) {
            String [] digitsOfNumber = String.valueOf(Math.abs(arrayOfInputNumbers[i])).split("");
            List<Integer> remainders = new ArrayList<>();
            for(int j = 0; j < digitsOfNumber.length; j++) {
                if (Integer.parseInt(digitsOfNumber[j]) % 2 == 0) {
                    remainders.add(0);
                }
                else {
                    remainders.add(1);
                }
            }
            if (!remainders.contains(1)) {
                numberOfNumbersWithOnlyEvenDigits += 1;
            }
            else {
                int numberOfZeros = 0;
                int numberOfOnes = 0;
                for(Integer number: remainders) {
                    if (number == 0) numberOfZeros += 1;
                    else numberOfOnes += 1;
                }
                if (numberOfZeros == numberOfOnes) {
                    numberOfNumbersWithEqualNumberOfEvenAndOddDigits += 1;
                }
            }
        }
        System.out.println("Number of numbers with only even digits - " + numberOfNumbersWithOnlyEvenDigits);
        System.out.println("Number of numbers with equal number of even and odd digits - " +
                numberOfNumbersWithEqualNumberOfEvenAndOddDigits);

        //Find a number with digits in strict ascending order.
        // If there are several such numbers, find the first of them
        boolean isNumberWithDigitsInStrictAscendingOrder = true;
        List<Integer> result = new ArrayList<>();
        for(i = 0; i < arrayOfInputNumbers.length; i++) {
            List<Integer> intDigitsOfNumber = new ArrayList<>();
            String [] strDigitsOfNumber = String.valueOf(Math.abs(arrayOfInputNumbers[i])).split("");
            for(int j = 0; j < strDigitsOfNumber.length; j++) {
                intDigitsOfNumber.add(Integer.parseInt(strDigitsOfNumber[j]));
            }
            Integer [] intDigitsOfNumberInArray = intDigitsOfNumber.toArray(new Integer[intDigitsOfNumber.size()]);
            for(int j = 0; j < intDigitsOfNumberInArray.length; j++) {
                for(int k = j + 1; j < intDigitsOfNumberInArray.length - 1; j++) {
                    if (intDigitsOfNumberInArray[j] > intDigitsOfNumberInArray[k]) {
                        isNumberWithDigitsInStrictAscendingOrder = false;
                        break;
                    }
                }
            }
            intDigitsOfNumber.clear();
            if (isNumberWithDigitsInStrictAscendingOrder) {
                result.add(arrayOfInputNumbers[i]);
            }
            isNumberWithDigitsInStrictAscendingOrder = true;
        }
        System.out.println("Number with digits in strict ascending order - " + result.get(0));
        result.clear();

        //Find a number containing only distinct digits.
        //If there are several such numbers, find the first one
        for(i = 0; i < arrayOfInputNumbers.length; i++) {
            strInputNumber = String.valueOf(Math.abs(arrayOfInputNumbers[i]));
            HashSet<String> uniqueDigitsOfNumber = new HashSet<String>(Arrays.asList(strInputNumber.split("")));
            if (strInputNumber.length() == uniqueDigitsOfNumber.size()) {
                result.add(arrayOfInputNumbers[i]);
            }
        }
        System.out.println("Number containing only distinct digits - " + result.get(0));
    }
}
