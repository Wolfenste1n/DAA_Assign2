package algorithms;

public class Kadanes_Algorithm {

    public static class Result {
        public long maxSum;
        public int startIndex;
        public int endIndex;
        public long comparisons;
        public long additions;
        public long assignments;
        public long arrayAccesses;
    }

    public static Result run(int[] arr) {
        Result r = new Result();
        if (arr == null || arr.length == 0) {
            r.maxSum = 0;
            r.startIndex = -1;
            r.endIndex = -1;
            return r;
        }
        if (arr.length == 1) {
            r.maxSum = arr[0];
            r.startIndex = 0;
            r.endIndex = 0;
            return r;
        }

        long maxSum = arr[0];
        long currentSum = arr[0];
        r.arrayAccesses += 2;
        int start = 0, tempStart = 0, end = 0;

        for (int i = 1; i < arr.length; i++) {
            r.comparisons++;
            r.arrayAccesses++;
            if (currentSum < 0) {
                currentSum = arr[i];
                tempStart = i;
                r.assignments += 2;
            } else {
                currentSum += arr[i];
                r.additions++;
                r.arrayAccesses++;
            }

            r.comparisons++;
            if (currentSum > maxSum) {
                maxSum = currentSum;
                start = tempStart;
                end = i;
                r.assignments += 3;
            }
        }

        r.maxSum = maxSum;
        r.startIndex = start;
        r.endIndex = end;
        return r;
    }

    public static long runNoMetrics(int[] arr) {
        if (arr == null || arr.length == 0) return 0;
        if (arr.length == 1) return arr[0];

        long maxSum = arr[0];
        long currentSum = arr[0];
        for (int i = 1; i < arr.length; i++) {
            currentSum = Math.max(arr[i], currentSum + arr[i]);
            maxSum = Math.max(maxSum, currentSum);
        }
        return maxSum;
    }

    public static long runOptimized(int[] arr) {
        if (arr == null || arr.length == 0) return 0;
        if (arr.length == 1) return arr[0];

        long maxSum = 0;
        long currentSum = 0;
        for (int num : arr) {
            currentSum = Math.max(0, currentSum + num);
            maxSum = Math.max(maxSum, currentSum);
        }
        return maxSum;
    }
}
