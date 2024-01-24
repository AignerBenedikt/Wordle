package com.example.wordle;

import java.util.Arrays;

/**
 * Verwaltet die Buchstaben für das Spiel, einschließlich des Vergleichs zwischen Benutzereingabe und Lösungswort.
 */
public class LetterManager {

    StringBuilder let = new StringBuilder();

    /**
     * Vergleicht die Buchstaben zwischen der Benutzereingabe und dem Lösungswort und gibt ein Array mit Indikatoren(Zahlen 0, 1 und 2) zurück.
     *
     * @param solution  Das Lösungswort.
     * @param userInput Die Benutzereingabe.
     * @return Ein Array von Indikatoren für jede Position im Lösungswort.
     */
    public int[] comparisonOfLetters(String solution, String userInput) {

        let = new StringBuilder();
        int[] anzeigeArray = new int[solution.length()];                 // 0 = Zeichen falsch; 1 = Zeichen gelb (vorhanden aber falsche Stelle), 2 = Zeichen richtig grün


        for (int i = 0; i < solution.length(); i++) {

            if (userInput.charAt(i) == solution.charAt(i)) {            // Zeichen ist richtig => anzeigeArray[i] = 2

                anzeigeArray[i] = 2;

            } else {

                for (int j = 0; j < solution.length(); j++) {

                    if (userInput.charAt(i) == solution.charAt(j)) {    // Zeichen ist vorhanden aber falsche Stelle => anzeigeArray[i] = 1 unabhängig wie oft der Buchstabe vorkommt

                        anzeigeArray[i] = 1;

                    }
                }
            }
        }

        System.out.println(userInput+":  "+ Arrays.toString(anzeigeArray) +"   SOLUTION:"+solution);

        for (int m = 0; m < 5; m++) {
            // M steht für den Buchstaben der gerade abgefragt wird und n steht für den Buchstaben der mit M identisch ist und verglichen wird
            // statt 5 bei inviduelle Länge solution.length angeben

            int countletter = countLetter(String.valueOf(userInput.charAt(m)), solution);
            //Anzahl eines Buchstaben in dem Lösungswort

            if (countletter >= 1) {
                //Falls der Buchstabe min 1 Mal vorkommt, wenn nicht passiert nichts

                for (int n = 0; n < 5; n++) {

                    if (n == m) {
                        //n==m m steht für den Buchstabe der abgefragt wird und n steht für alle anderen Buchstaben die im Lösungswort vorkommen wäre die selbe Stelle und das wollen wir ja gar nicht abfragen

                        continue;

                    }

                    if (userInput.charAt(m) == userInput.charAt(n) && anzeigeArray[m] > 0 && anzeigeArray[n] > 0) { // Beide Stellen des Lösungswort müssen gleich sein, beide Stellen dürfen den Wert 0 nicht haben [0 != 0]

                        if (anzeigeArray[m] == 2) {                                                                 //Falls das Lösungswort einmal vorkommt

                            if (countletter == 1) {

                                anzeigeArray[n] = 0;
                                System.out.println(userInput+":  "+ Arrays.toString(anzeigeArray) +"   SOLUTION:"+solution);

                            }
                            if (countletter > 1 && anzeigeArray[m] > anzeigeArray[n]) {

                                countletter--;

                            }

                        } else if (anzeigeArray[m] == 1) {


                            if (countletter == 1 && (anzeigeArray[m] > anzeigeArray[n] || anzeigeArray[m] == anzeigeArray[n])) { // GUESS: NONNE LW: TANNE

                                anzeigeArray[n] = 0;
                                break;

                            }

                            if (countletter > 1 && anzeigeArray[m] > anzeigeArray[n]  || anzeigeArray[m] < anzeigeArray[n]) {

                                countletter--;


                            } else anzeigeArray[m] = 0;


                        }


                    }
                }
            }
        }
        System.out.println(userInput+":  "+ Arrays.toString(anzeigeArray) +"   SOLUTION:"+solution);



        //return anzeigeFarbausgabeneu(anzeigeArray,userInput);


        return anzeigeArray;
    }


    /**
     * Zählt die Anzahl eines Buchstabens in einem Lösungswort und verhindert Doppelzählungen desselben Buchstabens.
     *
     * @param letter  Der Buchstabe, dessen Anzahl gezählt werden soll.
     * @param solution Das Lösungswort, in dem der Buchstabe gezählt wird.
     * @return Die Anzahl des Buchstabens im Lösungswort.
     */
    public int countLetter(String letter, String solution) {
        int count = 0;
        // StringBuilder zur Verhinderung von Doppelzählungen initialisieren
        let = new StringBuilder();

        if (let.toString().contains(letter)) {
            // Einmalige Zählung des Buchstabens bereits erfolgt, daher Rückgabe von 0

            return 0;
        }


        for (int i = 0; i < solution.length(); i++) {
            if (String.valueOf(solution.charAt(i)).equals(letter)) {
                // Der Buchstabe ist im Lösungswort vorhanden => Zähle ihn
                count++;
            }
        }
        if (count > 0) {
            // Der Buchstabe wurde mindestens einmal gezählt, füge ihn zum StringBuilder hinzu
            let.append(letter);

        }

        return count;
    }
}
