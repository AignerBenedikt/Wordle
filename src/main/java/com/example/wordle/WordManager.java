package com.example.wordle;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Random;

public class WordManager {

    String SolutionWord;
    List<String>filteredList;

    public void setFilteredList(List<String> filteredList) {
        this.filteredList = filteredList;
    }

    public String getSolutionWord(){

        Random random = new Random();

        SolutionWord = filteredList.get(random.nextInt(filteredList.size()));

        return SolutionWord;
    }

    public void generateWordList() { // getWordlistbylenght || filterwordsbylength

        try {
            Path fl = Paths.get("src/main/resources/com/example/wordle/wordlistG.txt"); //Pfad bestimmen

            List<String> list = Files.readAllLines(fl); // Das File in eine Liste aus Strings formen

            setFilteredList(list);

        } catch (IOException e) {

            System.out.println("Dateipfad existiert nicht");

        } catch (NullPointerException e) {

            System.out.println("Die Liste ist null oder der Pfad ist falsch angegeben ");

        }
    }


    public boolean wordExist(String word) {
        return filteredList.contains(word);
    }

}

