package task4;

import java.util.Arrays;

import static java.util.Arrays.sort;

public class task4 {
        public static void main(String[] args) {
            Integer[] arr = {35, 5, 30, 15, 8, 50, 7};
            System.out.println("Исходный массив: " + Arrays.toString(arr));

            sort(arr, 1, 5);
            System.out.println("Отсортированный массив с индексом от 1 до 5: " + Arrays.toString(arr));

            sort(arr, 0, arr.length);
            System.out.println("Отсортированный массив: " + Arrays.toString(arr));
        }

    }
