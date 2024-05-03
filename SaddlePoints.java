package saddlePoints;
import java.util.Random;

/**
 * Creates a number of random arrays, and checks each array to see
 * if it contains a saddle point. Prints the arrays and the results.
 */
public class SaddlePoints {
    /**
     * Creates arrays various sizes (including some 2x2 arrays and some larger),
     * fills them with random values, and prints each array and information about
     * it. Keeps generating arrays until it has printed at least one with and
     * one without a saddle point.
     */
    void run() {

        boolean withSaddlePoint = false;
        boolean withoutSaddlePoint = false;
        int rowCount = 1;
        int colCount = 1;

        while (! (withSaddlePoint && withoutSaddlePoint)) {

            //generate larger matrices starting from 2x2
            rowCount += 1;
            colCount += 1;

            //generate random numbers to fill minValue and maxValue
            Random random = new Random();
            int arbitraryRandomInt1, arbitraryRandomInt2, min, max;
            arbitraryRandomInt1 = random.nextInt(61) - 30;
            arbitraryRandomInt2 = random.nextInt(61) - 30;
            if (arbitraryRandomInt1 > arbitraryRandomInt2) {
                max = arbitraryRandomInt1;
                min = arbitraryRandomInt2;
            }
            else {
                max = arbitraryRandomInt2;
                min = arbitraryRandomInt1;
            }

            int[][] array = createRandomArray(rowCount, colCount, min, max);
            printArray(array);
            printArrayInfo(array);

            if (hasSaddlePoint(array)) {
                withSaddlePoint = true;
            }
            else {
                withoutSaddlePoint = true;
            }
        }
    }

    /**
     * Prints the array.
     *
     * @param array The array to be printed.
     */
    void printArray(int[][] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                System.out.print(array[i][j] + " ");
            }
            System.out.println();
        }
    }

    /**
     * Prints whether the given array has a saddle point, and if so, where it is (row and column)
     * and what its value is. (If there are multiple saddle points, just prints the first.)
     *
     * @param array The array to be checked.
     */
    void printArrayInfo(int[][] array) {
        if (hasSaddlePoint(array)) {
            int row = saddlePointRow(array);
            int col = saddlePointColumn(array);
            System.out.println("Board has a saddle point");
            System.out.println("Saddle point row: " + row);
            System.out.println("Saddle point column: " + col);
            System.out.println("Saddle point value: " + array[row][col]);
        }
        else {
            System.out.println("No saddle point");
        }
    }

    /**
     * Creates and returns an array of the given size and fills it with random
     * values in the specified range.
     *
     * @param numberOfRows    The number of rows desired.
     * @param numberOfColumns The number of columns desired.
     * @param minValue        The smallest number allowable in the array.
     * @param maxValue        The largest number allowable in the array.
     * @return
     */
    int[][] createRandomArray(int numberOfRows, int numberOfColumns, int minValue, int maxValue) {

        Random random = new Random();
        int[][] array = new int[numberOfRows][numberOfColumns];

        for (int i = 0; i < numberOfRows; i++) {
            for (int j = 0; j < numberOfColumns; j++) {
                array[i][j] = random.nextInt(maxValue - minValue + 1) + minValue;
            }
        }

        return array;
    }

    /**
     * Finds the largest value in an array of integers.
     *
     * @param array The array to be searched.
     * @return The largest value in the array.
     */
    int largest(int[] array) {

        int max = array[0];

        for (int num : array) {
            if (num > max) {
                max = num;
            }
        }

        return max;
    }

    /**
     * Finds the smallest value in an array of integers.
     *
     * @param array The array to be searched.
     * @return The smallest value in the array.
     */
    int smallest(int[] array) {

        int min = array[0];

        for (int num: array) {
            if (num < min) {
                min = num;
            }
        }

        return min;
    }

    /**
     * Returns an array containing the largest values in each column of the given array.
     *
     * @param array The array to be searched.
     * @return An array of the largest values in each column.
     */
    int[] largestValues(int[][] array) {

        //create new array with reversed row and column
        int numRows = array.length;
        int numCols = array[0].length;
        int[][] newArray = new int[numCols][numRows];
        for (int i = 0; i < numCols; i++) {
            for (int j = 0; j < numRows; j++) {
                newArray[i][j] = array[j][i];
            }
        }

        //find largest values in each row of the new array
        int[] largestValuesArray = new int[newArray.length];
        for (int i = 0; i < newArray.length; i++) {
            largestValuesArray[i] = largest (newArray[i]);
        }

        return largestValuesArray;
    }

    /**
     * Returns an array containing the smallest values in each row of the given array.
     *
     * @param array The array to be searched.
     * @return An array of the smallest values in each row.
     */
    int[] smallestValues(int[][] array) {

        int[] smallestValuesArray = new int[array.length];

        for (int i = 0; i < array.length; i++) {
            smallestValuesArray[i] = smallest (array[i]);
        }

        return smallestValuesArray;
    }


    /**
     * Returns true if the given array has a saddle point, and false if it does not.
     *
     * @param array The array to be checked.
     * @return True if the array has a saddle point, else false.
     */
    boolean hasSaddlePoint(int[][] array) {

        int[] largestValuesArray = largestValues(array);
        int[] smallestValuesArray = smallestValues(array);

        for (int num1 : largestValuesArray) {
            for (int num2 : smallestValuesArray) {
                if (num1 == num2) {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * Given an array that is known to have a saddle point, returns the number of a
     * row containing a saddle point. If more than one row contains a saddle point,
     * the first such row will be returned.
     *
     * @param array An array containing one or more saddle points.
     * @return The lowest-numbered row containing a saddle point.
     */
    int saddlePointRow(int[][] array) {

        int[] largestValuesArray = largestValues(array);
        int[] smallestValuesArray = smallestValues(array);

        for (int i = 0; i < largestValuesArray.length; i++) {
            for (int j = 0; j < smallestValuesArray.length; j++) {
                if (largestValuesArray[i] == smallestValuesArray[j]){
                    return j;
                }
            }
        }

        return -1;
    }

    /**
     * Given an array that is known to have a saddle point, returns the number of a
     * column containing a saddle point. If more than one column contains a saddle point,
     * the first such column will be returned.
     *
     * @param array An array containing one or more saddle points.
     * @return The lowest-numbered column containing a saddle point.
     */

    int saddlePointColumn(int[][] array) {

        int[] largestValuesArray = largestValues(array);
        int[] smallestValuesArray = smallestValues(array);

        for (int i = 0; i < largestValuesArray.length; i++) {
            for (int j = 0; j < smallestValuesArray.length; j++) {
                if (largestValuesArray[i] == smallestValuesArray[j]){
                    return i;
                }
            }
        }
        
        return -1;
    }
}