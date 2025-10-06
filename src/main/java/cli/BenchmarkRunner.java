package cli;

import algorithms.Kadanes_Algorithm;
import algorithms.Kadanes_Algorithm.Result;
import org.openjdk.jmh.annotations.*;
import java.util.Random;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class BenchmarkRunner {

    // === JMH SECTION ===
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    @Warmup(iterations = 2)
    @Measurement(iterations = 5)
    @Fork(1)
    public static class KadanesJMH {

        @State(Scope.Thread)
        public static class InputData {
            @Param({"1000", "10000", "100000"})
            int n;
            int[] arr;

            @Setup(Level.Trial)
            public void setup() {
                arr = new int[n];
                Random rnd = new Random(42);
                for (int i = 0; i < n; i++) arr[i] = rnd.nextInt(201) - 100;
            }
        }

        @Benchmark
        public long baseline(InputData data) {
            return Kadanes_Algorithm.runNoMetrics(data.arr);
        }

        @Benchmark
        public long optimized(InputData data) {
            return Kadanes_Algorithm.runOptimized(data.arr);
        }
    }

    // === CLI SECTION ===
    public static void main(String[] args) {
        int[] defaultSizes = new int[]{100, 1000, 10000, 100000};
        int[] sizes;
        if (args.length == 0) {
            sizes = defaultSizes;
        } else {
            List<Integer> list = new ArrayList<>();
            for (String a : args) {
                try {
                    list.add(Integer.parseInt(a));
                } catch (NumberFormatException ignored) {
                }
            }
            if (list.isEmpty()) sizes = defaultSizes;
            else {
                sizes = new int[list.size()];
                for (int i = 0; i < list.size(); i++) sizes[i] = list.get(i);
            }
        }

        System.out.println("n,mode,time_ms,maxSum,start,end,comparisons,additions,assignments,array_accesses");
        for (int n : sizes) {
            int[] arr = generateRandom(n);

            long t0 = System.nanoTime();
            Result r = Kadanes_Algorithm.run(arr);
            long t1 = System.nanoTime();
            long ms = (t1 - t0) / 1_000_000;
            System.out.printf("%d,with-metrics,%d,%d,%d,%d,%d,%d,%d,%d%n",
                    n, ms, r.maxSum, r.startIndex, r.endIndex,
                    r.comparisons, r.additions, r.assignments, r.arrayAccesses);

            long t2 = System.nanoTime();
            long max = Kadanes_Algorithm.runNoMetrics(arr);
            long t3 = System.nanoTime();
            long ms2 = (t3 - t2) / 1_000_000;
            System.out.printf("%d,no-metrics,%d,%d,NA,NA,0,0,0,0%n",
                    n, ms2, max);

            long t4 = System.nanoTime();
            long maxOpt = Kadanes_Algorithm.runOptimized(arr);
            long t5 = System.nanoTime();
            long ms3 = (t5 - t4) / 1_000_000;
            System.out.printf("%d,optimized,%d,%d,NA,NA,0,0,0,0%n",
                    n, ms3, maxOpt);
        }
    }

    private static int[] generateRandom(int n) {
        Random rnd = new Random(System.nanoTime());
        int[] a = new int[n];
        for (int i = 0; i < n; i++) a[i] = rnd.nextInt(201) - 100;
        return a;
    }
}
