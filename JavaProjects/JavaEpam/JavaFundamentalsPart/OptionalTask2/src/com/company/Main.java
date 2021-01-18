package com.company;

import java.util.Random;
import java.util.Scanner;

public class Main {

    static void setMatrixValues (int [][] matrix, int dimension, int rangeMin, int rangeMax) {
        Random rand = new Random();
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                matrix[i][j] = rand.nextInt((rangeMax - rangeMin) + 1) + rangeMin;
            }
        }
    }

    static void printMatrix (int [][] matrix, int dimension) {
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                System.out.print(matrix[i][j] + "\t");
            }
            System.out.println();
        }
    }

    static void sortStringsInAscendingOrder (int [][] matrix, int dimension) {
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                int minOfString = matrix[i][j];
                int indexOfMin = j;
                for (int k = j + 1; k < dimension; k++) {
                    if (matrix[i][k] < minOfString) {
                        minOfString = matrix[i][k];
                        indexOfMin = k;
                    }
                }
                if (j != indexOfMin) {
                    int tmp = matrix[i][j];
                    matrix[i][j] = matrix[i][indexOfMin];
                    matrix[i][indexOfMin] = tmp;
                }
            }
        }
    }

    static void findTheBiggestNumberOfAscendingElements (int [][] matrix, int dimension) {
        int max = 1;
        int result;
        for (int i = 0; i < dimension; i++) {
            result = 1;
            for (int j = 0; j < dimension; j++) {
                if (j != dimension - 1) {
                    if (matrix[i][j] < matrix[i][j + 1]) {
                        result += 1;
                        max = result > max ? result : max;
                    }
                    else {
                        result = 1;
                    }
                }
            }
        }
        System.out.println("The biggest number of ascending elements - " + max);
    }

    public static void main(String[] args) {
	    Scanner inputNumber = new Scanner(System.in);
        System.out.println("Enter dimension of matrix");
	    int matrixDimension = inputNumber.nextInt();
	    int [][] matrix = new int [matrixDimension][matrixDimension];
	    setMatrixValues(matrix, matrixDimension, -10, 10);
        printMatrix(matrix, matrixDimension);
        sortStringsInAscendingOrder(matrix, matrixDimension);
        System.out.println();
        printMatrix(matrix, matrixDimension);
        findTheBiggestNumberOfAscendingElements(matrix, matrixDimension);
    }
}
