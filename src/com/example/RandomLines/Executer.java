package com.example.RandomLines;

import java.io.IOException;
import java.util.List;

/**
 * Created by maayan.s on 1/3/17.
 */
public class Executer {

    static final String IO_FILE  = "file.txt";
    static final int LINES_TO_READ = 10;


    public static void main(String[] args) throws IOException {

        //Read lines from file
        List<String> list = FileReader.readLines(IO_FILE);

        // print randomly several lines
        int readNum = LINES_TO_READ > list.size() ? list.size() :  LINES_TO_READ;

        for(int i= 0; i < readNum; i++) {
            ListPrintService.printRandomLine(list, readNum - 1);
        }



        System.exit(0);

    }

}
