package se320;

import java.util.Random;
import java.util.Scanner;

public class Benchmark {

    public static int OPS = 1000000;

    public static Random rand = new Random();

    public static void main(String[] args) {

        // Wait for input (use with VisualVM)
//        Scanner in = new Scanner(System.in);
//        in.nextLine();

        /*
         * Test Case
         * 10/10/80 insert/delete/lookup
         * 10% put, 10% delete, 80% get
         */
        int reads = 80;
        int inserts = 10;
        int deletes = 10;
        System.out.println("---TEST---");

        /*
         * Logging Data Structure
         * Mostly inserts, occasional lookups and deletes
         * 80/10/10 insert/delete/lookup
         * 80% put, 10% delete, 10% get
         */
//        int reads = 10;
//        int inserts = 80;
//        int deletes = 10;
//        System.out.println("---LOGGING---");

        /*
         * Read-heavy Database
         * Mostly reads, occasional inserts and deletes
         * 10/10/80 insert/delete/lookup
         * 10% put, 10% delete, 80% get
         */
//        int reads = 80;
//        int inserts = 10;
//        int deletes = 10;
//        System.out.println("---READ-HEAVY---");

        /*
         * Read-only Database
         * Only reads, no inserts and deletes
         * 10/10/80 insert/delete/lookup
         * 10% put, 10% delete, 80% get
         */
//        int reads = 100;
//        int inserts = 0;
//        int deletes = 0;
//        System.out.println("---READ-ONLY---");

        // one warmup run (1mil ops)
        long warmupStart = System.nanoTime();
        runOps(reads, inserts, deletes);
        long warmupElapsed = System.nanoTime() - warmupStart;
        System.out.println("Warmup round:    "+String.format("%12s",warmupElapsed)+"ns");

        // 10 sequential runs (1mil ops per run - 10mil ops total)
        long[] elapsed = new long[10];
        for (int i = 0; i < 10; i++) {
            long startTime = System.nanoTime();
            runOps(reads, inserts, deletes);
            long estimated = System.nanoTime() - startTime;
            elapsed[i] = estimated;
        }

        /* Print the times, as well as the max, min, and average */
        long total = 0;
        long min = Long.MAX_VALUE;
        long max = Long.MIN_VALUE;
        for (int i = 0; i < 10; i++) {
            System.out.println("Benchmark run "+i+": "+String.format("%12s",elapsed[i])+"ns");
            total += elapsed[i];
            if (elapsed[i] < min) min = elapsed[i];
            if (elapsed[i] > max) max = elapsed[i];
        }
        System.out.println("Average for "+OPS+" operations: "+String.format("%12s",(total/10))+"ns");
        System.out.println("Minimum for "+OPS+" operations: "+String.format("%12s",min)+"ns");
        System.out.println("Maximum for "+OPS+" operations: "+String.format("%12s",max)+"ns\n");

    }

    public static void runOps(int reads, int inserts, int deletes) {

        RedBlackBST<Integer,Integer> tree = new RedBlackBST<Integer,Integer>();
        int insert_bound = reads + inserts;

        int lastn = 0;

        for(int i = 0; i < OPS; i++) {

            int v = rand.nextInt(100); // random number between 0 and 99

            if (v < reads) {
                // do read
                tree.get(lastn);
            } else if (v < insert_bound) {
                // do insertion of random key
                tree.put(lastn, lastn);
            } else {
                // do deletion of random key
                tree.delete(lastn);
            }

            lastn = v;

        }
    }
}




