package saddlePoints;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SaddlePointsTest {
    private SaddlePoints sp;

    @Before
    public void setUp() throws Exception {
         sp = new SaddlePoints(); // create an instance variable
    }

    @Test
    public void createRandomArray() {

        int[][] array = sp.createRandomArray(3,5,-20,20);

        //test array size
        assertEquals(3, array.length);
        assertEquals(5, array[0].length);

        //test numbers
        boolean isArrayAllSame = true;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[0].length; j++) {
                assertTrue(array[i][j]<=20 && array[i][j]>=-20);
                if (array[i][j] != array[0][0]) {
                    isArrayAllSame = false;
                }
            }
        }
        assertFalse(isArrayAllSame);
    }

    @Test
    public void largest() {
        int[] array1 = {-20, 0, 1000, 1000, 1000};
        int[] array2 = {-4, 0, -4};
        int[] array3 = {5, 9, 1, 4, -5};
        int[] array4 = {-1, -1, -1};
        int[] array5 = {7, 10, 50, 14, 8};
        assertEquals(1000, sp.largest(array1));
        assertEquals(0, sp.largest(array2));
        assertEquals(9, sp.largest(array3));
        assertEquals(-1, sp.largest(array4));
        assertEquals(50, sp.largest(array5));
    }

    @Test
    public void smallest() {
        int[] array1 = {-9, -9, 1000, -9, 10};
        int[] array2 = {80, 2, -8, 9, 0};
        int[] array3 = {-7, -7};
        int[] array4 = {2, 5, -78, 678, 23};
        int[] array5 = {455, -543};
        assertEquals(-9, sp.smallest(array1));
        assertEquals(-8, sp.smallest(array2));
        assertEquals(-7, sp.smallest(array3));
        assertEquals(-78, sp.smallest(array4));
        assertEquals(-543, sp.smallest(array5));
    }

    @Test
    public void largestValues() {
        int[][] testArray1 = {{-9, 12, -6},
                { 7, 14,  5},
                {10, -8,  3},
                { 6, 17,-10}};
        int[] expectedArray1 = {10, 17, 5};
        int[][] testArray2 = {{0, 23},
                {543, -5},
                { 56, 54}};
        int[] expectedArray2 = {543, 54};
        int[][] testArray3 = {{5, 876, -56, -6},
                { 8, 0, -8, -6}};
        int[] expectedArray3 = {8, 876, -8, -6};
        int[][] testArray4 = {{0, 0},
                {0, 0}};
        int[] expectedArray4 = {0, 0};
        int[][] testArray5 = {{-6, -20, 6},
                { 9,   65,  -67},
                {-8,   -8,    7},
                {-6, 1754, 5454},
                {-5,  534,    4}};
        int[] expectedArray5 = {9, 1754, 5454};
        assertArrayEquals(expectedArray1, sp.largestValues(testArray1));
        assertArrayEquals(expectedArray2, sp.largestValues(testArray2));
        assertArrayEquals(expectedArray3, sp.largestValues(testArray3));
        assertArrayEquals(expectedArray4, sp.largestValues(testArray4));
        assertArrayEquals(expectedArray5, sp.largestValues(testArray5));
    }

    @Test
    public void smallestValues() {
        int[][] testArray1 = {{-50, 9, 20,-3},
                {-5, 1, 2,  30},
                {-6, 0, 4, -49}};
        int[] expectedArray1 = {-50, -5, -49};
        int[][] testArray2 = {{-534534, 5, 2,-8},
                {0, 0, 0, 0}};
        int[] expectedArray2 = {-534534, 0};
        int[][] testArray3 = {{-3},
                {-8768},
                {0}};
        int[] expectedArray3 = {-3, -8768, 0};
        int[][] testArray4 = {{5, 2},
                { -2, 7},
                {-68, 7}};
        int[] expectedArray4 = {2, -2, -68};
        int[][] testArray5 = {{1, 1, 1},
                {1, 1, 1}};
        int[] expectedArray5 = {1, 1};
        assertArrayEquals(expectedArray1, sp.smallestValues(testArray1));
        assertArrayEquals(expectedArray2, sp.smallestValues(testArray2));
        assertArrayEquals(expectedArray3, sp.smallestValues(testArray3));
        assertArrayEquals(expectedArray4, sp.smallestValues(testArray4));
        assertArrayEquals(expectedArray5, sp.smallestValues(testArray5));
    }

    @Test
    public void hasSaddlePoint() {
        int[][] with1 = {{-9, 12, -6},
                { 7, 14,  5},
                {10, -8,  3},
                { 6, 17,-10}};
        int[][] with2 = {{ 5, 2, 3},
                { 40, 60, 7},
                {  8,  9, 1}};
        int[][] with3 = {{ 2, 5, 9},
                { 4,  8,  6},
                { 7, 30, 10}};
        int[][] with4 = {{ 8, 20, 90},
                { 4, 7, 6},
                { 3, 8, 1}};
        int[][] with5 = {{ 3, 2, 3},
                { 40, 90, 6},
                {  7,  8, 5}};
        int[][] without1 = {{ 1, -2,  3},
                {-6,  5, -4},
                { 7, -8,  9}};
        int[][] without2 = {{ 2, 5, 9},
                { 4, 8, 6},
                { 7, 3, 1}};
        int[][] without3 = {{ 1, 3, 5},
                { 7, 9, 2},
                { 4, 6, 8}};
        int[][] without4 = {{ 5, 2, 3},
                { 4, 6, 7},
                { 8, 9, 1}};
        int[][] without5 = {{ 8, 2, 9},
                { 4, 7, 6},
                { 3, 8, 1}};
        assertTrue(sp.hasSaddlePoint(with1));
        assertFalse(sp.hasSaddlePoint(without1));
        assertTrue(sp.hasSaddlePoint(with2));
        assertFalse(sp.hasSaddlePoint(without2));
        assertTrue(sp.hasSaddlePoint(with3));
        assertFalse(sp.hasSaddlePoint(without3));
        assertTrue(sp.hasSaddlePoint(with4));
        assertFalse(sp.hasSaddlePoint(without4));
        assertTrue(sp.hasSaddlePoint(with5));
        assertFalse(sp.hasSaddlePoint(without5));
    }

    @Test
    public void saddlePointRow() {
        int[][] with1 = {{-9, 12, -6},
                { 7, 14,  5},
                {10, -8,  3},
                { 6, 17,-10}};
        int[][] with2 = {{ 5, 5, 7},
                { 5, 5, 6},
                { 2, 3, 1}};
        int[][] with3 = {{ 2, 5, 9},
                { 4,  8,  6},
                { 7, 30, 10}};
        int[][] with4 = {{ 8, 20, 90},
                { 4, 7, 6},
                { 3, 8, 1}};
        int[][] with5 = {{ 3, 2, 3},
                { 40, 90, 6},
                {  7,  8, 5}};
        assertEquals(1, sp.saddlePointRow(with1));
        assertEquals(0, sp.saddlePointRow(with2));
        assertEquals(2, sp.saddlePointRow(with3));
        assertEquals(0, sp.saddlePointRow(with4));
        assertEquals(1, sp.saddlePointRow(with5));
    }

    @Test
    public void saddlePointColumn() {
        int[][] with1 = {{-9, 12, -6},
                { 7, 14,  5},
                {10, -8,  3},
                { 6, 17,-10}};
        int[][] with2 = {{ 5, 5, 7},
                { 5, 5, 6},
                { 2, 3, 1}};
        int[][] with3 = {{ 2, 5, 9},
                { 4,  8,  6},
                { 7, 30, 10}};
        int[][] with4 = {{ 8, 20, 90},
                { 4, 7, 6},
                { 3, 8, 1}};
        int[][] with5 = {{ 3, 2, 3},
                { 40, 90, 6},
                {  7,  8, 5}};
        assertEquals(2, sp.saddlePointColumn(with1));
        assertEquals(0, sp.saddlePointColumn(with2));
        assertEquals(0, sp.saddlePointColumn(with3));
        assertEquals(0, sp.saddlePointColumn(with4));
        assertEquals(2, sp.saddlePointColumn(with5));
    }

    private int[] setupArray(int elements){
        return new int[0];
    }
}