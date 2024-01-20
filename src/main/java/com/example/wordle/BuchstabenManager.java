package com.example.wordle;




public class BuchstabenManager {

    StringBuilder let =new StringBuilder();

    public int[] comparisonOfLetters (String loesung, String eingabe) {

        let = new StringBuilder();
        int[] anzeigeArray = new int[loesung.length()];                 // 0 = Zeichen falsch; 1 = Zeichen gelb (vorhanden aber falsche Stelle), 2 = Zeichen richtig grün


            for (int i = 0; i < loesung.length(); i++) {

                if (eingabe.charAt(i) == loesung.charAt(i)) {// Zeichen ist richtig => anzeigeArray[i] = 2

                    anzeigeArray[i] = 2;

                } else {

                    for (int j = 0; j < loesung.length(); j++) {

                        if (eingabe.charAt(i) == loesung.charAt(j)) {// Zeichen ist vorhanden aber falsche Stelle => anzeigeArray[i] = 1

                            anzeigeArray[i] = 1;

                        }
                    }
                }
            }


        for (int m = 0; m < loesung.length(); m++) {

            int countletter = countLetter(String.valueOf(eingabe.charAt(m)), loesung);  //Anzahl eines Buchstaben in dem Lösungswort

            if (countletter >= 1) {                                                       //Falls der Buchstabe min 1 Mal vorkommt

                for (int n = 0; n < loesung.length(); n++) {

                    if (n == m) {                                                         //n==m steht für den Buchstabe der abgefragt wird und n stht für alle anderen Buchstaben die im Lösungswort vorkommen wäre die selbe Stelle und das wollen wir ja gar nicht abfragen

                        continue;

                    }
                    if (eingabe.charAt(m) == eingabe.charAt(n) && anzeigeArray[m] > 0 && anzeigeArray[n] > 0) { // Beide Stellen des Lösungswort müssen gleich sein, beide Stellen dürfen den Wert 0 nicht haben [0 != 0]

                            if (anzeigeArray[m] == 2) {                //Falls das Lösungswort einmal vorkommt oder das
                                if (countletter == 1) {
                                    anzeigeArray[n] = 0;
                                }
                                if (countletter > 1 && anzeigeArray[m]>anzeigeArray[n]) {
                                    anzeigeArray[n] = 0;
                                }else countletter--;
                            } else if (anzeigeArray[m] == 1) {
                                if (countletter == 1 && anzeigeArray[m]>anzeigeArray[n]) {
                                    anzeigeArray[n] = 0;
                                }
                                if (countletter > 1) {
                                    countletter--;
                                }
                            }




                    }
                }
            }
        }


        //return anzeigeFarbausgabeneu(anzeigeArray,eingabe);


        return anzeigeArray;
    }


    public int countLetter(String letter, String loesung){
        int count=0;
        let = new StringBuilder();

        if (let.toString().contains(letter)){
            return 0;
        }


        for(int i=0; i< loesung.length();i++){
            if (String.valueOf(loesung.charAt(i)).equals(letter)) {   // Zeichen ist vorhanden aber falsche Stelle => anzeigeArray[i] = 1
                count++;
            }
        }
        if (count>0){
            let.append(letter);

        }

        return count;
    }
}
