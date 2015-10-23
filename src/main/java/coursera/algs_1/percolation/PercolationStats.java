package coursera.algs_1.percolation;

import edu.princeton.cs.introcs.StdRandom;
import edu.princeton.cs.introcs.StdStats;

public class PercolationStats {

    private final double[] threshold;

    public PercolationStats(int matrixSize, int tests) {
        threshold = new double[tests];
        final int nTwoMatrixSize = matrixSize * matrixSize;
        if (matrixSize <= 0 || tests <= 0)
            throw new IllegalArgumentException("Argument out of bound");
        for (int t = 0; t < tests; t++) {
            threshold[t] = (double) fillMap(matrixSize) / (nTwoMatrixSize);
        }
    }

    public double confidenceLo() {
        return mean() - 1.96 * stddev() / Math.sqrt(threshold.length);
    }

    public double confidenceHi() {
        return mean() + 1.96 * stddev() / Math.sqrt(threshold.length);
    }

    public double mean() {
        return StdStats.mean(threshold);
    }

    public double stddev() {
        return StdStats.stddev(threshold);
    }

    private int fillMap(int matrixSize) {
        int i, j, c = 0, m = matrixSize + 1;
        final Percolation percolation = new Percolation(matrixSize);
        while (!percolation.percolates()) {
            j = StdRandom.uniform(1, m);
            i = StdRandom.uniform(1, m);
            if (!percolation.isOpen(i, j)) {
                percolation.open(i, j);
                c++;
            }
        }
        return c;
    }

    public static void main(String[] args) {
        PercolationStats percolationStats = new PercolationStats(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
        System.out.println("mean \t = " + percolationStats.mean());
        System.out.println("stddev \t = " + percolationStats.stddev());
        System.out.println("95% confidence interval \t = " +
                percolationStats.confidenceLo()
                + " , " +
                percolationStats.confidenceHi());
    }
}
