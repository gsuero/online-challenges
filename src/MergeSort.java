import java.util.Arrays;

public class MergeSort {

    public static void main(String... argvs) {
        int[] values = { 1, 53, 54, 45, 54, 45, 654, 654, 84, 54, 844, 1, 2, 32, 145, 478, 41, 21, 2, 5, 8, 544};
        //int[] values = { 1, 53, 54, 45, 6, 8 };
        System.out.println(Arrays.toString(values));

        mergeSort(values, 0, values.length - 1);

        System.out.println("sorted values:");
        System.out.println(Arrays.toString(values));
    }

    private static void mergeSort(int[] values, int start, int end) {
        if (start < end) {
            int middle = (start + end) / 2;
            mergeSort(values, start, middle);
            mergeSort(values, middle + 1, end);

            merge(values, start, middle, end);
        }
    }

    private static void merge(int[] values, int start, int middle, int end) {

        int llength = middle - start + 1;
        int rlength = end - middle;

        int left[] = createArrayFrom(values, start, llength);
        int right[] = createArrayFrom(values, middle + 1, rlength);

        int subStart = start;

        int l = 0, r = 0;

        while (l < left.length && r < right.length) {
            if (left[l] <= right[r]) {
                values[subStart] = left[l];
                l++;
            } else {
                values[subStart] = right[r];
                r++;
            }
            subStart++;
        }

        while (l < llength) {
            values[subStart] = left[l];
            l++;
            subStart++;
        }

        while (r < rlength) {
            values[subStart] = right[r];
            r++;
            subStart++;
        }
    }
    
    private static int[] createArrayFrom(int[] source, int start, int arraySize) {
        int left[] = new int[arraySize];
        for (int i = 0; i < left.length; i++) {
            left[i] = source[start + i];
        }
        
        return left;
    }
}
