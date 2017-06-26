package com.example.Concurrency;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.CountDownLatch;
import java.util.stream.Collectors;

/**
 * Created by maayan.s on 1/1/17.
 */

public class ReadFromFilesThread implements Runnable {

        private final String fileName;
        private final CountDownLatch latch;

        public ReadFromFilesThread(String name, CountDownLatch latch) {

            this.fileName = name;
            this.latch = latch;
        }

        @Override
        public void run()  {

            try {

                String[] arrFile = readFromFile(fileName);
                Map<String, Long> map = Arrays.stream(arrFile).collect(Collectors.groupingBy(s-> s, Collectors.counting()));
                AddToCache(map);

            }
            catch (IOException e) {

            }


            latch.countDown();
            System.out.println(String.format("After Latch Count %s", latch.getCount()));

        }

        private static String[] readFromFile(String fileName) throws IOException {

            String[] arr = null;
            try(Scanner in = new Scanner (new File(fileName))) {

                String text = in.useDelimiter("\\Z").next(); // \Z means EOF
                arr  = text.split("\\W+"); // read only string words
            }
            return arr;
        }

        private void AddToCache(Map<String, Long> map) {

            for (Map.Entry<String, Long> entry : map.entrySet()) {
                ConcurrentWordsCache.add(entry.getKey(), entry.getValue());
            }

        }
}
