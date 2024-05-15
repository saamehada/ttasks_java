package task4;

public class SelectionSortWithRange {
    public static <T extends Comparable<T>> void sort(T[] data, int from, int to) {
        for (int i = from; i < to - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < to; j++) {
                if (data[j].compareTo(data[minIndex]) < 0) {
                    minIndex = j;
                }
            }

            T temp = data[minIndex];
            data[minIndex] = data[i];
            data[i] = temp;
        }
    }
}
