package com.example.Concurrency;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * Created by maayan.s on 1/1/17.
 */
public class ConcurrentReadAndHashMap {

    static final String IO_FILE  = "file.txt";
    static final String IO_FILE1 = "file1.txt";
    static final String IO_FILE2 = "file2.txt";

    static final int NUM_OF_THREADS = 3;


    public static void main(String[] args) {


        final CountDownLatch latch = new CountDownLatch(NUM_OF_THREADS);

        ConcurrentWordsCache.restart();

        ExecutorService executorService = Executors.newFixedThreadPool(NUM_OF_THREADS);
        executorService.execute(new ReadFromFilesThread(IO_FILE, latch));
        executorService.execute(new ReadFromFilesThread(IO_FILE1, latch));
        executorService.execute(new ReadFromFilesThread(IO_FILE2, latch));


        try {
            // main thread is waiting on count down to finish
            latch.await();
            //executorService.shutdown();
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }

        ConcurrentWordsCache.print();

        System.exit(0);
    }


}
