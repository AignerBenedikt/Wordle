package com.example.wordle;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Random;

/**
 * Verwaltet die Wörter für das Spiel, einschließlich der Auswahl des Lösungsworts und der Wortliste.
 */
public class WordManager {

    String SolutionWord;
    List<String>filteredList;

    public void setFilteredList(List<String> filteredList) {
        this.filteredList = filteredList;
    }

    /**
     * Gibt ein zufällig ausgewähltes Lösungswort aus der gefilterten Liste zurück.
     *
     * @return Das ausgewählte Lösungswort.
     */
    public String getSolutionWord(){

        Random random = new Random();

        SolutionWord = filteredList.get(random.nextInt(filteredList.size()));

        return SolutionWord;
    }

    /**
     * Generiert die Wortliste, indem sie aus einer Textdatei eingelesen wird.
     * Die Datei wird im Projektverzeichnis unter "src/main/resources/com/example/wordle/wordlistG.txt" erwartet.
     */
    public void generateWordList() { // getWordlistbylenght || filterwordsbylength

        try {
            Path fl = Paths.get("src/main/resources/com/example/wordle/wordlistG.txt"); //Pfad bestimmen

            List<String> list = Files.readAllLines(fl); // Das File in eine Liste aus Strings formen

            setFilteredList(list);

        } catch (IOException e) {

            System.out.println("Dateipfad existiert nicht");

        } catch (NullPointerException e) {

            System.out.println(
                    "Die Liste ist null oder der Pfad ist falsch angegeben ");

        }
    }


    /**
     * Überprüft, ob ein bestimmtes Wort in der gefilterten Liste existiert.
     *
     * @param word Das zu überprüfende Wort.
     * @return true, wenn das Wort existiert, andernfalls false.
     */
    public boolean wordExist(String word) {
        return filteredList.contains(word);
    }

}

