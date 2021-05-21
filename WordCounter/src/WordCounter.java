/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author YAREN POLAT
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;

public class WordCounter {

    public HashMap<String, Integer> countWord(String filename) {
        HashMap<String, Integer> firstMap = new HashMap<String, Integer>();
        try {
            File myFile = new File(filename);
            Scanner reader = new Scanner(myFile);

            while (reader.hasNext()) {
                String line = reader.nextLine();
                String[] words = line.toLowerCase().split("[^a-zA-Z']");

                for (int i = 0; i < words.length; i++) {

                    if (firstMap.containsKey(words[i])) {
                        int value = Integer.parseInt(String.valueOf(firstMap.get(words[i])));
                        value++;
                        firstMap.put(words[i].toLowerCase(), value);
                    } else if (firstMap.containsKey(words[i]) == false) {
                        firstMap.put(words[i], 1);
                        int value = Integer.parseInt(String.valueOf(firstMap.get(words[i])));
                    }
                }

            }
        } catch (Exception e) {
            System.out.println("There is no such file try again please");
        }
        firstMap.remove("");
        firstMap.remove("t");

        return firstMap;
    }

    public static void main(String[] args) {
        WordCounter object = new WordCounter();
        HashMap<String, Integer> FileMap = new HashMap<>();
        HashMap<String, Integer> commonwordsMap = new HashMap<>();
        Scanner input = new Scanner(System.in);
        System.out.println("PLEASE ENTER a file name(totc.txt,tomsawyer.txt or dyssy10.txt ) !!");
        String filename = input.next();
        System.out.println("PLEASE ENTER A THRESHOLD VALUE !!");
        int thresholder = input.nextInt();
        FileMap = object.countWord(filename);
        commonwordsMap = object.countWord("commonwords.txt");

        HashMap<String, Integer> specialWordsMap = new HashMap<>(FileMap);
        specialWordsMap.putAll(commonwordsMap);
        specialWordsMap.remove(commonwordsMap);

        for (Object value : specialWordsMap.keySet()) {
            if (specialWordsMap.get(value) > thresholder) {
                System.out.println(value + " " + specialWordsMap.get(value));
            }

        }
    }
}
