package com.future.experience.fsbk;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * https://www.geeksforgeeks.org/print-all-pairs-in-an-unsorted-array-with-equal-sum/amp/
 *
 * Given an unsorted array A[]. The task is to print all unique pairs in the unsorted array with equal sum.
 * Note: Print the result in the format as shown in the below examples.
 * Examples:
 *
 *
 * Input: A[] = { 6, 4, 12, 10, 22, 54, 32, 42, 21, 11}
 * Output:
 * Pairs : ( 4, 12) ( 6, 10)  have sum : 16
 * Pairs : ( 10, 22) ( 21, 11)  have sum : 32
 * Pairs : ( 12, 21) ( 22, 11)  have sum : 33
 * Pairs : ( 22, 21) ( 32, 11)  have sum : 43
 * Pairs : ( 32, 21) ( 42, 11)  have sum : 53
 * Pairs : ( 12, 42) ( 22, 32)  have sum : 54
 * Pairs : ( 10, 54) ( 22, 42)  have sum : 64
 *
 * Input:A[]= { 4, 23, 65, 67, 24, 12, 86}
 * Output:
 * Pairs : ( 4, 86) ( 23, 67) have sum : 90
 *
 */
public class PrintAllPairsWithEqualSum {
    /**
     * Generate all possible pairs, and use a map to store key (sum), value(pair)
     * @param array
     */
    public static void print(int[] array) {
        Map<Integer, List<int[]>> map = new HashMap<>();
        for(int i = 0; i < array.length - 1; i++) {
            for(int j = i + 1; j < array.length; j++) {
                int sum = array[i] + array[j];
                if(map.containsKey(sum)) {
                    for(int[] pair : map.get(sum)) {
                        System.out.println("(" + pair[0] + ", " + pair[1] + ") (" + array[i] + ", "  + array[j] + ") = " + sum);
                    }
                } else {
                    List<int[]> pairs = new ArrayList<>();
                    pairs.add(new int[]{array[i], array[j]});
                    map.put(sum, pairs);
                }
            }
        }
    }

    public static void main(String[] args) {
        print(new int[]{6, 4, 12, 10, 22, 54, 32, 42, 21, 11});
    }
}
