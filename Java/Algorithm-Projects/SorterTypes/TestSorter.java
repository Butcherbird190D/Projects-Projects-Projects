package Projects.Assignment1;


import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class TestSorter {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter number of elements (n): ");
        int n = in.nextInt();
        System.out.print("Enter number of test runs (m): ");
        int m = in.nextInt();
        
        double[][] times = new double[m][6];
        Random rand = new Random();

        for (int test = 0; test < m; test++) {
            int[] original = new int[n];
            for (int i = 0; i < n; i++) {
                original[i] = rand.nextInt(n);
            }

            // Individual copies for each algorithm
            int[][] copies = {
                Arrays.copyOf(original, n),
                Arrays.copyOf(original, n),
                Arrays.copyOf(original, n),
                Arrays.copyOf(original, n),
                Arrays.copyOf(original, n),
                Arrays.copyOf(original, n)
            };

            // Measure sorting times (set to nanoseconds)
            times[test][0] = measureTime(() -> Sorter.isort(copies[0]));
            times[test][1] = measureTime(() -> Sorter.ssort(copies[1]));
            times[test][2] = measureTime(() -> Sorter.bsort(copies[2]));
            times[test][3] = measureTime(() -> Sorter.qsort(copies[3], 0, n - 1));
            times[test][4] = measureTime(() -> Sorter.msort(copies[4], 0, n - 1));
            times[test][5] = measureTime(() -> Sorter.shsort(copies[5]));
        }

        // Compute best / average / and worst times
        double[] best = new double[6];
        double[] worst = new double[6];
        double[] sum = new double[6];
        Arrays.fill(best, Double.MAX_VALUE);
        Arrays.fill(worst, Double.MIN_VALUE);

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < 6; j++) {
                sum[j] += times[i][j];
                best[j] = Math.min(best[j], times[i][j]);
                worst[j] = Math.max(worst[j], times[i][j]);
            }
        }

        // Print results
        System.out.println("\nSorting Performance (Time in ms):");
        System.out.println("--------------------------------------------------------------------------------------------------------");
        System.out.printf("%-12s %-12s %-12s %-12s %-12s %-12s%n",
                          "Insertion", "Selection", "Bubble", "Quick", "Merge", "Shell");
        System.out.println("--------------------------------------------------------------------------------------------------------");
        
        for (int i = 0; i < m; i++) {
            System.out.printf("%-12.5f %-12.5f %-12.5f %-12.5f %-12.5f %-12.5f%n",
                              times[i][0], times[i][1], times[i][2], times[i][3], times[i][4], times[i][5]);
        }

        System.out.println("--------------------------------------------------------------------------------------------------------");
        System.out.printf("%-9s %-12s %-12s %-12s %-12s %-12s %-12s%n",
                          "", "Insertion", "Selection", "Bubble", "Quick", "Merge", "Shell");
        System.out.println("--------------------------------------------------------------------------------------------------------");
        System.out.printf("Best:     %-12.5f %-12.5f %-12.5f %-12.5f %-12.5f %-12.5f%n",
                          best[0], best[1], best[2], best[3], best[4], best[5]);
        System.out.printf("Avg :     %-12.5f %-12.5f %-12.5f %-12.5f %-12.5f %-12.5f%n",
                          sum[0] / m, sum[1] / m, sum[2] / m, sum[3] / m, sum[4] / m, sum[5] / m);
        System.out.printf("Worst:    %-12.5f %-12.5f %-12.5f %-12.5f %-12.5f %-12.5f%n",
                          worst[0], worst[1], worst[2], worst[3], worst[4], worst[5]);
        System.out.println("--------------------------------------------------------------------------------------------------------");

        in.close();
    }

    private static double measureTime(Runnable sortFunction) {
        long start = System.nanoTime();
        sortFunction.run();
        long end = System.nanoTime();
        return (end - start) / 1_000_000.0; 
    }
}
