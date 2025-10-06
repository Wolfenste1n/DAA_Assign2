package algorithms;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Kadanes_AlgorithmTest {

    @Test
    public void testSingleElement() {
        int[] a = {5};
        Kadanes_Algorithm.Result r = Kadanes_Algorithm.run(a);
        assertEquals(5, r.maxSum);
        assertEquals(0, r.startIndex);
        assertEquals(0, r.endIndex);
    }

    @Test
    public void testAllNegative() {
        int[] a = {-5, -2, -8, -3};
        Kadanes_Algorithm.Result r = Kadanes_Algorithm.run(a);
        assertEquals(-2, r.maxSum);
        assertEquals(1, r.startIndex);
        assertEquals(1, r.endIndex);
    }

    @Test
    public void testMixed() {
        int[] a = { -2, 1, -3, 4, -1, 2, 1, -5, 4 };
        Kadanes_Algorithm.Result r = Kadanes_Algorithm.run(a);
        assertEquals(6, r.maxSum);
        assertEquals(3, r.startIndex);
        assertEquals(6, r.endIndex);
    }

    @Test
    public void testEmptyArrayReturnsSentinel() {
        int[] a = {};
        Kadanes_Algorithm.Result r = Kadanes_Algorithm.run(a);
        assertEquals(Long.MIN_VALUE, r.maxSum);
        assertEquals(-1, r.startIndex);
        assertEquals(-1, r.endIndex);
    }

    @Test
    public void testRunNoMetricsMatchesResult() {
        int[] a = { -2, 1, -3, 4, -1, 2, 1, -5, 4 };
        long fast = Kadanes_Algorithm.runNoMetrics(a);
        Kadanes_Algorithm.Result r = Kadanes_Algorithm.run(a);
        assertEquals(fast, r.maxSum);
    }
}
