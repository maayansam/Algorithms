package com.example.Concurrency;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;
import java.util.Scanner;
import java.util.Spliterators;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class SequentialReadFiles {

    //static final String[] files = {"file.txt", "file1.txt", "file2.txt"};
    static final String IO_FILE  = "file.txt";
    static final String IO_FILE1 = "file1.txt";
    static final String IO_FILE2 = "file2.txt";


    public static void main(String[] args) throws IOException{

        String[] arrFile1 = seqReadFromFile(IO_FILE);
        String[] arrFile2 = seqReadFromFile(IO_FILE1);
        String[] arrFile3 = seqReadFromFile(IO_FILE2);

        //combine arrays to one stream
        Stream<String> combined = Stream.of(arrFile1, arrFile2, arrFile3).flatMap(array -> Arrays.stream(array));

        // collect to one map with count (build  a hash of a word->count overall in all 3 files)
        Map<String, Long> map = combined.collect(Collectors.groupingBy(s-> s, Collectors.counting()));

        System.exit(0);
    }

    private static String[] seqReadFromFile(String fileName) throws IOException {

        String[] arr = null;
        try(Scanner in = new Scanner (new File(fileName))){

            String text = in.useDelimiter("\\Z").next(); // \Z means EOF
            arr  = text.split("\\W+"); // read only string words
        }
        return arr;
    }

}
