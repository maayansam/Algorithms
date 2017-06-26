package com.example.IOOperations;

import java.io.*;
import java.io.IOException;
import java.text.DateFormatSymbols;

/**
 * Created by maayan.s on 12/27/16.
 */
public class RunIOs {

    static final String IO_FILE = "IOOperations.txt";
    static final int MONTH_JANUARY = 0;
    static final int[] januaryBirthdays = {1, 12, 14, 28};
    static final double[] ages = {33.2, 34.5, 37, 25.2};
    static final String[] names = {"Tal", "Ran", "Lori", "Reef"};

    public static void main(String[] args) throws IOException {

        writeEmplyeeDataToFile(MONTH_JANUARY, names,  januaryBirthdays, ages);
        readEmployeeDataToFile();

        System.exit(0);

    }

    private static void writeEmplyeeDataToFile(int month, String[] names,  int[] birthdays, double[] ages) throws IOException
    {

        int size = names.length;
        try(DataOutputStream out = new DataOutputStream(new FileOutputStream(IO_FILE))) {
            out.writeInt(month);
            out.writeInt(size);
            for (int i = 0; i < size; i ++) {
                out.writeDouble(ages[i]);
                out.writeInt(birthdays[i]);
                out.writeUTF(names[i]);
            }
        }
        //catch(IOException e2){ // just for debug
        //    System.out.println(String.format("could not write to file %s", IO_FILE));
        //}
    }

    private static void readEmployeeDataToFile() throws IOException {

        int size = 0;
        int month = 0;
        double age = 0;
        int birthday = 0;
        String name = "";

        try(DataInputStream in = new DataInputStream(new FileInputStream(IO_FILE))) {

            month = in.readInt();
            System.out.println(String.format("%s Birthdays:", getMonth(month)));
            size = in.readInt();
            for (int i=0; i<size; i++) {
                age = in.readDouble();
                birthday = in.readInt();
                name = in.readUTF();
                System.out.println(String.format("Name: %s Day: %s Age: %s", name, birthday, age));
            }
        }
        //catch (IOException e) { // just fro debug
        //    System.out.println(String.format("could not find file %s for read", IO_FILE));
        //}

    }

    private static String getMonth(int month) {
        // note that month is zero based in java
        return new DateFormatSymbols().getMonths()[MONTH_JANUARY];
    }

}
