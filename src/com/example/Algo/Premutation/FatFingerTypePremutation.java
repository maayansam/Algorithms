package com.example.Algo.Premutation;

import java.util.HashSet;
import java.util.Set;
import java.util.*;

/**
 * Created by maayan.s on 4/8/17.
 */
public class FatFingerTypePremutation {

    private static final List<String> abcLetters = new ArrayList<String>(Arrays.asList("a", "b", "c", "d", "e","f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"));
    private static final int SIZE = abcLetters.size();

    public FatFingerTypePremutation() {}
    // get all possible premutation of thw original word by replacing them with a near by character
    // then filter out these premutation for only valid words

    //for a given character return all near by characters
    private static Set<String> nearByCharacters(String e) {
        Set<String> nearBy = new HashSet<>();
        int i = abcLetters.indexOf(e);

        if(i==0){
            nearBy.add(abcLetters.get(0));
            nearBy.add(abcLetters.get(++i));
        }
        else if(i == SIZE-1) {
            nearBy.add(abcLetters.get(i));
            nearBy.add(abcLetters.get(--i));
        }
        else if(i > 0) {

            nearBy.add(abcLetters.get(--i));
            ++i;
            nearBy.add(abcLetters.get(i));
            nearBy.add(abcLetters.get(++i));
        }

        return nearBy;
    }

    public static Set<String> nearByWords(String s) {
        Set<String> newWords = new HashSet<>();
        int length = s.length();
        StringBuffer buffer = new StringBuffer();
        int position = 0;
        premuteNearBy(s, length,buffer, position, newWords);
        return newWords;
    }

    public static void premuteNearBy(String str, int length, StringBuffer buffer, int position, Set<String> newWords) {

        if(length == position) {
            newWords.add(buffer.toString());
            return;
        }

        Set<String> nearByChars = nearByCharacters((str.substring(position, position+1)));
        int size = nearByChars.size();
        for(String s: nearByChars) {
            buffer.append(s);
            premuteNearBy(str, length, buffer, position+1, newWords);
            buffer.setLength(buffer.length() -1);
        }
    }

    public static void printWords(Set<String> words) {
        for(String a : words) {
            System.out.println(a);
        }
    }

    public static Set<String> premuteNearBy(String s, int index) {

        Set<String> newSet = new HashSet<String>();
        if(index > s.length()) {
            newSet.add("");
            return newSet;
        }

        //subwords
        //premutations
        //loop
        //return premutation
        //todo remove just for compile
        return newSet;
    }

    public static boolean isWord(String s) {
        return true;
    }
    public static void main(String[] args) {

        String str = "alr";
        Set<String> words = nearByWords(str);
        words.stream()
                .filter(p->isWord(p))
                .forEach(p->System.out.println(p));

        System.exit(0);
    }

}
