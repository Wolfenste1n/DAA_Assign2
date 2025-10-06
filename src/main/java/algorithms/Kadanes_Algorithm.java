package algorithms;

public class Kadanes_Algorithm {

    public static final class Result {
        public final long maxSum;
        public final int startIndex;
        public final int endIndex;
        public final long comparisons;
        public final long additions;
        public final long assignments;
        public final long arrayAccesses;
        public Result(long maxSum, int startIndex, int endIndex,
                      long comparisons, long additions, long assignments, long arrayAccesses) {
            this.maxSum = maxSum;
            this.startIndex = startIndex;
            this.endIndex = endIndex;
            this.comparisons = comparisons;
            this.additions = additions;
            this.assignments = assignments;
            this.arrayAccesses = arrayAccesses;
        }
        @Override
        public String toString() {
            return String.format("maxSum=%d, start=%d, end=%d, comps=%d, adds=%d, assigns=%d, acc=%d",
                    maxSum, startIndex, endIndex, comparisons, additions, assignments, arrayAccesses);
        }
    }

    public static Result run(int[] arr) {
        if (arr == null) throw new IllegalArgumentException("Input array must not be null");
        if (arr.length == 0) {
            return new Result(Long.MIN_VALUE, -1, -1, 0, 0, 0, 0);
        }

        long comparisons = 0;
        long additions = 0;
        long assignments = 0;
        long arrayAccesses = 0;

        arrayAccesses++;
        long currentSum = arr[0]; assignments++;
        long maxSum = arr[0]; assignments++;
        int start = 0; assignments++;
        int end = 0; assignments++;
        int tempStart = 0; assignments++;

        for (int i = 1; i < arr.length; i++) {
            arrayAccesses++;
            long x = arr[i]; assignments++;

            additions++;
            comparisons++;
            if (currentSum + x < x) {
                currentSum = x; assignments++;
                tempStart = i; assignments++;
            } else {
                currentSum = currentSum + x; additions++; assignments++;
            }

            comparisons++;
            if (currentSum > maxSum) {
                maxSum = currentSum; assignments++;
                start = tempStart; assignments++;
                end = i; assignments++;
            }
        }

        return new Result(maxSum, start, end, comparisons, additions, assignments, arrayAccesses);
    }

    public static long runNoMetrics(int[] arr) {
        if (arr == null) throw new IllegalArgumentException("Input array must not be null");
        if (arr.length == 0) return Long.MIN_VALUE;

        long currentSum = arr[0];
        long maxSum = arr[0];
        for (int i = 1; i < arr.length; i++) {
            long x = arr[i];
            if (currentSum + x < x) currentSum = x;
            else currentSum = currentSum + x;
            if (currentSum > maxSum) maxSum = currentSum;
        }
        return maxSum;
    }

    public static long runOptimized(int[] arr) {
        if (arr == null) throw new IllegalArgumentException("Input array must not be null");
        if (arr.length == 0) return Long.MIN_VALUE;

        long cur = arr[0];
        long max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            long x = arr[i];
            cur = Math.max(x, cur + x);
            max = Math.max(max, cur);
        }
        return max;
    }
}
