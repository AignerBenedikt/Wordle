package com.example.wordle;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class WordManager {

    private int wordLength;
    String SolutionWord;
    List<String>filteredList;

    public void setFilteredList(List<String> filteredList) {
        this.filteredList = filteredList;
    }

    public int getWordLength() {
        return wordLength;
    }

    public String SolutionWord(){

        Random random = new Random();

        SolutionWord = filteredList.get(random.nextInt(filteredList.size()));

        return SolutionWord;
    }

    public void generateWordList(int wordLength) { // getWordlistbylenght || filterwordsbylength

        List<String> filteredList = new LinkedList<>();

        try {
            Path fl = Paths.get("C:\\Users\\Angelika Wild\\Desktop\\Weiterbildung 2023\\FH Campus\\1.Sem\\Programmieren\\Wordle\\src\\main\\java\\com\\example\\wordle\\wordlistG.txt"); //Pfad bestimmen

            List<String> list = Files.readAllLines(fl); // Das File in eine Liste aus Strings formen

            if (wordLength > 1) {

                for (int i = 0; i < list.size() - 1; i++) {

                    if (list.get(i).length() == wordLength) {

                        filteredList.add(list.get(i));


                    }
                }
                setFilteredList(filteredList);
               // return filteredList;
            }

        } catch (IOException e) {

            System.out.println("Dateipfad existiert nicht");

        } catch (IllegalArgumentException e) {

            System.out.println("Ich hab kein Wort mit Länge "+ getWordLength());

          //  return null;
        }
        // return filteredList;
    }

    public boolean checkSolutionWord(String loesung, String word) {

        if (loesung.equals(word)) {

            return true;

        }

        return false;
    }

    public boolean wordExist(String word) {
        return filteredList.contains(word);
    }

}

