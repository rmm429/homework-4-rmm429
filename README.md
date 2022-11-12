
# SE320 Homework 4: Performance Evaluation
## Fall 2022

- This is a *one* week assignment!

### Overview
The goal for this assignment is to expose you to concepts and tools related to performance testing.

Your assignment is to choose reasonable operational profiles, explain the key ideas in provided benchmarking code, measure times for some operational profiles, and explain the consequences in terms of how much load a hypothetical system might handle.

### Experiments
You've been provided with an implementation of a red-black binary search tree.

Attached to this assignment, you'll find ```RedBlackBST.java``` (the red-black tree), and ```Benchmark.java```, which contains a simple benchmarking harness.  In ```Benchmark```, given a hard-coded operational profile for a mix of lookups, insertions, and deletions from the tree, the code performs a warmup run, then 10 times it will run a random mix of 1,000,000 (one million) operations distributed roughly according to the operational profile.  When Benchmark.main() is run, it will print to the console the running time for each iteration (including warmup), and the min, max, and average of the non-warmup runs.

You can execute that main method via ```./gradlew run```

### Submission
Please answer the following. Questions 1-7 and parts of question 8 should be in a text document (e.g., PDF), while some parts of question 8 require writing a few lines of code (for which you should submit the whole directory structure as in prior homeworks). Please remember to submit BOTH the code and written responses (save your written responses in a file in the directory structure for the code!)

1. A short (one paragraph) explanation of why the benchmark harness is structured as it is.  Why is there an initial run that isn't counted in the min/max/average?  Why are we measuring multiple runs?
2. Reasonable operational profiles for use of the RedBlackBST in the following use cases.  Each of these profiles, for our purposes, is simply a percentage of insert/delete/lookup operations (3 numbers that sum to 100 in each case).
    a. A logging data structure, which mostly records new data, with occasional queries and edits (deletions)
    b. A read-heavy database, with occasional updates and deletions.
    c. A read-only database, with no modifications performed.
3. A description of what steps you took in preparation for your performance measurements. For example, what other software is or isn't running on your machine? (For this assignment, you may ignore our discussion of frequency scaling's effect on performance tests, since disabling it is quite inconvenient.)
4. For each of your operational profiles, report the following, which should be produced by Benchmark.main() once you update the hard-coded operational profile.
    a. Warmup time
    b. Minimum iteration time
    c. Maximum iteration time
    d. Average iteration time
5. Which of your operational profiles has higher *throughput* (i.e., performs work work per unit
   time)?  What about the red-black tree might explain this outcome?
   Confirm (or disprove and revisit your hypothesis) by: modifying the benchmark harness to wait for a key press, loading up [VisualVM](https://visualvm.github.io/) (download Standalone), attaching to the program while it is waiting, and doing CPU profiling of the benchmark (go to Profiling, in the set of classes to profile enter "edu.drexel.se320.**" to indicate all of the code for the class, and click the CPU button; after it has run for a few seconds, click the "stop" button and look at the results). Pay attention to what percentage of *time* is spent in each of put, get, and delete (and their helper methods). A method that takes a higher percentage of time than it is given as a percentage of the operational profile is a likely bottleneck. (Note, however, that a random number generator is involved here.)
6. Are your warmup times noticeably different from the "main" iteration times?  Most likely yes, but either way, why might we *expect* a significant difference?
7. When I run the test for either of my operational profiles with instrumentation-based profiling enabled, my measurements slow down by a factor of 10 or more. Why?
8. Assume each run (each call to ```runOps```) simulates the activity of one remote request. Based on the average execution time for each operational profile, what would the throughput be for each of your profiles? (i.e., requests/second)
9. Assuming each remote user makes 5 requests/minute, your program's resource usage scales linearly, and we are only interested in CPU execution time, how many concurrent remote users could you support on your machine (again, do this for each operational profile) without degrading performance or overloading the system?
10. What aspects of load are we not testing, which could possibly reduce the capacity of your machine to service requests?



### Grading
Above there are 10 questions to respond to, each equally weighted (10% each).

This assignment is itself worth 10% of your term grade.

### Submission
Please submit your write-up (including your choices of operational profiles!) in PDF format via Learn.
