package com.example.Algo.Premutation;

/**
 * Created by maayan.s on 3/25/17.
 */
public class premutation {

    public static void execute(String str) {
        int length = str.length();
        boolean[] used = new boolean[length];
        StringBuffer buffer = new StringBuffer(length);
        premute(str, length, used, buffer, 0);
    }

    private static void premute(String str, int length, boolean[] used, StringBuffer buffer, int position) {

        if(length ==  position) {
            System.out.println(buffer);
            return;
        }

        for (int i=0; i<length; i++) {
            if(used[i]) {
                continue;
            }
            buffer.append(str.charAt(i));
            used[i] = true;
            premute(str, length, used, buffer, position+1);
            used[i] = false;
            buffer.setLength(buffer.length() -1);

        }
    }
}
