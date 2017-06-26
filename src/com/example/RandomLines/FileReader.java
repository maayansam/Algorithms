package com.example.RandomLines;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;


public class FileReader {


    public static List<String> readLines (String fileName) throws IOException {

        String[] arr = null;
        try(Scanner in = new Scanner (new File(fileName))){

            String text = in.useDelimiter("\\Z").next(); // \Z means EOF

            arr  = text.split("\\r?\\n"); // seperate by lines
        }
        return Arrays.asList(arr);
    }
}
