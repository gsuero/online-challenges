package com.ca.online;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class OnlineChallenge {

    public static void main(String[] args) {
        String[] strings = new String[4];
        
        strings[0] = "we promptly judged antique ivory buckles for the next prize";
        strings[1] = "we promptly judged antique ivory buckles for the prizes";
        strings[2] = "the quick brown fox jumps over the lazy dog";
        strings[3] = "the quick brown fox jump over the lazy dog";
        System.out.println(isPangram(strings));
    }
    
    static String alphabet = "abcdefghijklmnopqrstuvwxyz";
    
    static String isPangram(String[] strings) {
        if (strings == null) {
            return "";
        }
        StringBuilder values = new StringBuilder(strings.length);
        for (int a = 0; a < strings.length; a++) {
            values.append(isPangram(strings[a]));
        }

        return values.toString();
    }
    
    
    static String isPangram(String string) {
        if (string == null || string.length() == 0) {
            return "0";
        }
        
        Map<Character, Boolean> map = getMap(alphabet);
        String cleanString = string.toLowerCase().replaceAll("[^A-Za-z]+", "");
        for (char ch :  cleanString.toCharArray()) {
            map.put(ch, true);
        }
        
        return validatePangramCharMap(map) ? "1" : "0";
    }
    
    static Map<Character, Boolean> getMap(String alphabet) {
        Map<Character, Boolean> chars = new HashMap<>();
        for (int a = 0; a < alphabet.length(); a++) {
            chars.put(alphabet.charAt(a), false);
        }
        return chars;
    }
    
    static boolean validatePangramCharMap(Map<Character, Boolean> map) {
        return map.entrySet().stream()
                .filter(e -> !e.getValue())
                .map(Map.Entry::getKey)
                .findFirst().isPresent() == false;
    }
    
    
    
    
    // Complete the getIntegerComplement function below.
    static int getIntegerComplement(int n) {
        int ones = (Integer.highestOneBit(n) << 1) - 1;
        return n ^ ones;
    }
    
    static int getIntegerComplement2(int n) {
        String binaryValue = Integer.toBinaryString(n);
        StringBuilder val = new StringBuilder();
        
        for (int a = 0; a < binaryValue.length(); a++) {
            char ch = binaryValue.charAt(a);
            val.append(ch == '1' ? '0' : '1'); 
        }
        return Integer.parseInt(val.toString(), 2);
    }
}
