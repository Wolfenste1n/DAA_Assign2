package metrics;

import java.io.PrintWriter;
import java.io.FileWriter;
import java.io.IOException;

public class PerformanceTracker {
    private long comparisons = 0;
    private long additions = 0;
    private long assignments = 0;
    private long arrayAccesses = 0;

    public void incComparisons() { comparisons++; }
    public void addComparisons(long v) { comparisons += v; }
    public void incAdditions() { additions++; }
    public void addAdditions(long v) { additions += v; }
    public void incAssignments() { assignments++; }
    public void addAssignments(long v) { assignments += v; }
    public void incArrayAccesses() { arrayAccesses++; }
    public void addArrayAccesses(long v) { arrayAccesses += v; }

    public long getComparisons() { return comparisons; }
    public long getAdditions() { return additions; }
    public long getAssignments() { return assignments; }
    public long getArrayAccesses() { return arrayAccesses; }

    public String toCsv() {
        return String.format("%d,%d,%d,%d", comparisons, additions, assignments, arrayAccesses);
    }

    public void writeCsvLine(String filepath, String line) throws IOException {
        try (PrintWriter pw = new PrintWriter(new FileWriter(filepath, true))) {
            pw.println(line);
        }
    }

    public void reset() {
        comparisons = additions = assignments = arrayAccesses = 0;
    }
}
