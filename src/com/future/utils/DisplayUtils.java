package com.future.utils;

import java.util.Collection;
import java.util.List;

/**
 * Created by xingfeiy on 5/24/17.
 */
public class DisplayUtils {
    public static void printTwoDimensionsArray(int[][] array) {
        for(int i = 0; i < array.length; i++) {
            for(int j = 0; j < array[0].length; j++) {
                System.out.print(String.format("|%-3d", array[i][j]));
            }
            System.out.println();
        }
    }


    public static void printArray(int[] array) {
        for(int i = 0; i < array.length; i++) {
            System.out.print(String.format("|%-3d", array[i]));
        }
        System.out.println();
    }

    public static void printList(List<Integer> list) {
        for(int i : list) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    public static void printCollection(Collection<Integer> list) {
        for(int i : list) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    public static String toString(List<Integer> list) {
        String str = "";
        for(int i : list) {
            str = str + i + " ";
        }
        return str;
    }
}
