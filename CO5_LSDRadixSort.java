import java.util.Arrays;

public class CO5_LSDRadixSort {

    static int[] radixSort(int[] arr, int d) {
        int[] current = arr.clone();
        int divisor = 1;

        for (int pass = 0; pass < d; pass++) {
            current = countingSortByDigit(current, divisor);
            divisor *= 10;
        }

        return current;
    }

    static int[] countingSortByDigit(int[] in, int divisor) {
        int[] out = new int[in.length];
        int[] count = new int[10];

        // Count occurrences
        for (int x : in) {
            int digit = (x / divisor) % 10;
            count[digit]++;
        }

        // Prefix sums
        for (int i = 1; i < 10; i++) {
            count[i] += count[i - 1];
        }

        // Stable placement
        for (int i = in.length - 1; i >= 0; i--) {
            int digit = (in[i] / divisor) % 10;
            out[--count[digit]] = in[i];
        }

        return out;
    }

    public static void main(String[] args) {

        int[] arr = {473, 152, 681, 247, 539, 826, 715, 304};

        System.out.println("Original Array:");
        System.out.println(Arrays.toString(arr));

        int[] sorted = radixSort(arr, 3);

        System.out.println("Sorted Array:");
        System.out.println(Arrays.toString(sorted));
    }
}