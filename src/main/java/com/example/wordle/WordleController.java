package com.example.wordle;

import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.*;


//https://www.youtube.com/watch?v=B4t0Y40U4HI

/**
 * Controller class for the Wordle game.
 */
public class WordleController {

    // Instance variables
    // Initialize managers
    TimeManager tm = new TimeManager();
    WordManager wm = new WordManager();
    LetterManager bm = new LetterManager();
    private boolean initializeTimer = true;
    private int counter;
    int[] arrayWordCompared;
    List<String> duplicateWordList = new ArrayList<>();
    String guessWord;
    String solutionWord;


    /**
     * Constructor for WordleController.
     */
    public WordleController() {
        // set initial values

        wm.generateWordList();
        this.solutionWord = wm.getSolutionWord();
        this.counter = 1;
        tm.setOnTimeTick(() -> {
            timerLabel2.setText(tm.getFormattedTime());
        });
    }

    // FXML fields for UI components (buttons, labels, text field, etc.)
    // ..
    public Label timerLabel2;
    private KeyEvent event;
    public Button Back;
    public Button Enter;
    public Button Q;
    public Button W;
    public Button E;
    public Button R;
    public Button T;
    public Button Z;
    public Button U;
    public Button I;
    public Button O;
    public Button P;
    public Button A;
    public Button S;
    public Button D;
    public Button F;
    public Button G;
    public Button H;
    public Button J;
    public Button K;
    public Button L;
    public Button Y;
    public Button X;
    public Button C;
    public Button V;
    public Button B;
    public Button N;
    public Button M;
    public TextField guessInput;
    public Button playAgain;
    public Button quit;
    public Label box00 = new Label();
    public Label box01 = new Label();
    public Label box02 = new Label();
    public Label box03 = new Label();
    public Label box04 = new Label();
    public Label box10 = new Label();
    public Label box11 = new Label();
    public Label box12 = new Label();
    public Label box13 = new Label();
    public Label box14 = new Label();
    public Label box20 = new Label();
    public Label box21 = new Label();
    public Label box22 = new Label();
    public Label box23 = new Label();
    public Label box24 = new Label();
    public Label box30 = new Label();
    public Label box31 = new Label();
    public Label box32 = new Label();
    public Label box33 = new Label();
    public Label box34 = new Label();
    public Label box40 = new Label();
    public Label box41 = new Label();
    public Label box42 = new Label();
    public Label box43 = new Label();
    public Label box44 = new Label();
    public Label box50 = new Label();
    public Label box51 = new Label();
    public Label box52 = new Label();
    public Label box53 = new Label();
    public Label box54 = new Label();


    /**
     * Beendet das Programm ordnungsgemäß, indem System.exit(0) aufgerufen wird.
     * Damit wird die Java Virtual Machine mit einem Statuscode von 0 beendet, was auf einen erfolgreichen Abschluss hinweist.
     */
    @FXML
    protected void quit() {
        System.exit(0);
    }


    /**
     * Entfernt das letzte Zeichen aus dem Textfeld der Benutzereingabe.
     * Diese Methode ist für die Behandlung der "Back"-Funktionalität vorgesehen.
     * Wenn das Eingabefeld leer ist, wird keine Aktion durchgeführt.
     */
    @FXML
    protected void setBack() {

        try {

            guessInput.setText(guessInput.getText().substring(0, guessInput.getLength() - 1));

        } catch (StringIndexOutOfBoundsException ignored) {
        }
        // Ignoriert die Ausnahme, falls das Eingabefeld leer ist

    }


    /**
     * Diese Methode wird aufgerufen, wenn die Enter-Taste betätigt wird.
     * Wenn das Spiel initialisiert ist, wird der Timer gestartet und die Variable initializeTimer wird auf false gesetzt.
     * Danach wird die Methode 'checkGuess' aufgerufen und der Counter wird erhöht.
     */
    @FXML
    public void setEnter() {


        if (initializeTimer) {                 // Starte den Timer, wenn der Timer noch nicht initialisiert wurde

            tm.startTimer();

            initializeTimer = false;          // Setze den initializeTimer auf false, damit nicht der Timer von vorne beginnt


        }

        checkGuess(counter);

        // Erhöhe den Counter für den nächste Versuch

        counter++;
    }

    /**
     * Fügt einen Buchstaben aud der virtuellen Tastatur zum Eingabefeld hinzu, wenn die Länge des aktuellen Textes im Eingabefeld kleiner als 5 ist (mit der virtuellen Tastatur).
     *
     * @param actionEvent Der Methodenaufruf erfolgt durch das Klicken auf einen Buchstaben in der virtuellen Tastatur..
     **/
    @FXML
    protected void Letter(ActionEvent actionEvent) {


        if (guessInput.getText().length() >= 5) {                          // Überprüfen, ob die Länge des aktuellen Textes im Eingabefeld größer oder gleich 5 ist
            // Wenn ja, beende die Methode vorzeitig
            return;

        }

        // Holt sich den Char aus dem 10. Index des Strings-> Button[id=R, styleClass=button]'R'
        //                                                              ^ ... Index 10
        Character text = actionEvent.getTarget().toString().charAt(10);

        // Füge den extrahierten Char dem Eingabefeld hinzu, doch zuvor muss man in ein String umwandeln
        guessInput.appendText(String.valueOf(text));
    }

    /**
     * Verarbeitet Tastatureingaben für das Spiel. Lässt nur Buchstaben zum Eingabefeld hinzu und überwacht die Enter-Taste.
     * Wenn die Eingabe die maximale Länge erreicht ist wird die letzte Eingabe entfernt.
     * Startet den Timer beim erstmaligen Drücken der Enter-Taste.
     * Überwacht die verbleibende Spielzeit
     *
     * @param keyEvent Das KeyEvent-Objekt, das durch die Tastatureingabe ausgelöst wurde.
     */
    public void realKeyboardInput(KeyEvent keyEvent) {
        // Filtert Tastatureingaben, um nur Buchstaben zuzulassen und Leerzeichen zu verhindern
        guessInput.addEventFilter(KeyEvent.KEY_TYPED, new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {

                if (" ".equals(event.getCharacter()) || !Character.isLetter(event.getCharacter().charAt(0))) {
                    event.consume();
                }

            }

        });

        // Entfernt den letzten Buchstaben, wenn die Eingabe ihre maximale Länge erreicht und Enter nicht gedrückt wird (Über die echte Tastatur)
        if (guessInput.getText().length() == 5 && !keyEvent.getCode().equals(KeyCode.ENTER)) {

            guessInput.setText(guessInput.getText().substring(0, guessInput.getLength() - 1));

            ((TextField) event.getSource()).positionCaret(guessInput.getLength());

        }

        // Setzt das KeyEvent für spätere Verwendungszwecke
        setEvent(keyEvent);

        if (keyEvent.getCode().equals(KeyCode.ENTER)) {

            if (initializeTimer) {

                tm.startTimer();
                initializeTimer = false;

            }
            checkGuess(counter);
            counter++;

            // Überwacht die verbleibende Spielzeit und beendet das Spiel, wenn sie auf null sinkt
            new AnimationTimer() {
                @Override
                public void handle(long now) {
                    if (tm.getFormattedTime().equals("00:00")) {
                        gameFinished(-1);
                        this.stop();
                    }
                }
            }.start();
        }
    }

    /**
     * Überprüft das eingegebene Wort, aktualisiert die Anzeige der Buchstaben in der entsprechenden Zeile,
     * und führt je nach Ergebnis weitere Aktionen aus.
     *
     * @param i Die aktuelle Zeile, in der sich der User befindet.
     * @see WordManager#wordExist(String)
     * @see WordleController#handleGuess(String, Label[])
     * @see WordleController#wordDoesntExist()
     * @see WordleController#gameFinished(int)
     */
    public void checkGuess(int i) {
        // Konvertiert dem Input von der Eingabe in Großbuchstaben
        guessWord = guessInput.getText().toUpperCase();

        // Initialisiert das Array für die Labels in der Zeile
        Label[] row = new Label[0];

        // Weist den Labels der aktuellen Zeile zu, basierend auf dem Wert von i
        switch (i) {
            case 1:
                row = new Label[]{box00, box01, box02, box03, box04};
                break;

            case 2:
                row = new Label[]{box10, box11, box12, box13, box14};
                break;

            case 3:
                row = new Label[]{box20, box21, box22, box23, box24};
                break;

            case 4:
                row = new Label[]{box30, box31, box32, box33, box34};
                break;

            case 5:
                row = new Label[]{box40, box41, box42, box43, box44};
                break;

            case 6:
                row = new Label[]{box50, box51, box52, box53, box54};
                break;
        }

        // Gibt das Lösungswort aus (zum Debuggen)
        System.out.println(solutionWord);

        // Überprüft, ob das eingegebene Wort existiert

        if (!wm.wordExist(guessWord)) {
            // Behandelt den Fall, wenn das Wort nicht existiert
            wordDoesntExist();
            counter--;

            return;

        }

        // Behandelt das eingegebene Wort und aktualisiert die Anzeige der Labels
        handleGuess(guessWord, row);

        // Überprüft, ob die sechste Zeile erreicht wurde und der Counter bei 6 ist (Damit wird verhindert falls bei der sechsten Zeile ein Fehler auftritt, dass das Spiel nicht vorbei ist)
        if (i == 6 && counter == 6) {

            try {
                // Beendet das Spiel (verloren,Zeit abgelaufen), falls die Bedingung erfüllt ist

                gameFinished(0);

            } catch (NullPointerException ignored) {
            }
            // Ignoriert eine mögliche NullPointerException

        }

    }

    /**
     * Aktualisiert die Darstellung der Buchstaben in der Zeile entsprechend den Ergebnissen der Wortüberprüfung.
     *
     * @param anzeigeArray Ein Array, das die Anzeige für jeden Buchstaben im eingegebenen Wort angibt in Form aus 0, 1 oder 2 z.B -> FLUSS:  [0, 0, 0, 1, 2]   SOLUTION:DOSIS.
     * @param userInput    Das eingegebene Wort.
     * @param row          Ein Array von Labels, das die visuelle Darstellung der Buchstaben repräsentiert.
     * @see LetterManager#comparisonOfLetters(String, String)
     */
    public void colorBoxes(int[] anzeigeArray, String userInput, Label[] row) {
        // Map für die Zuordnung von Buchstaben zu den entsprechenden Buttons
        Map<String, Button> buttonMap = new HashMap<>() {{
            put("A", A);
            put("B", B);
            put("C", C);
            put("D", D);
            put("E", E);
            put("F", F);
            put("G", G);
            put("H", H);
            put("I", I);
            put("J", J);
            put("K", K);
            put("L", L);
            put("M", M);
            put("N", N);
            put("O", O);
            put("P", P);
            put("Q", Q);
            put("R", R);
            put("S", S);
            put("T", T);
            put("U", U);
            put("V", V);
            put("W", W);
            put("X", X);
            put("Y", Y);
            put("Z", Z);
        }};

        // Iteriert über jeden Buchstaben im eingegebenen Wort
        for (int i = 0; i < userInput.length(); i++) {

            // Setzt den Text des Labels auf den entsprechenden Buchstaben im eingegebenen Wort
            row[i].setText(String.valueOf(userInput.charAt(i)));

            // Überprüft den Status des Buchstabens im AnzeigeArray
            if (anzeigeArray[i] == 0) {

                // Setzt den Hintergrund des Labels auf grau, wenn der Buchstabe nicht im Lösungswort ist
                row[i].setStyle("-fx-background-color: #919191;");

                // Überprüft den aktuellen Hintergrund des Buttons und aktualisiert ihn entsprechend
                if (buttonMap.get(row[i].getText()).getStyle().equals("-fx-background-color: #31a127;")) {
                    // Nichts tun, wenn der Button bereits grün ist
                } else if (buttonMap.get(row[i].getText()).getStyle().equals("-fx-background-color: #f0b63a;")) {
                    // Nichts tun, wenn der Button bereits gelb ist
                } else {
                    // Setzt den Hintergrund des Buttons auf grau
                    buttonMap.get(row[i].getText()).setStyle("-fx-background-color: #919191;");
                }


            } else {

                if (anzeigeArray[i] == 1) {
                    // Setzt den Hintergrund des Labels auf gelb, wenn der Buchstabe im Wort ist, aber an der falschen Position

                    row[i].setStyle("-fx-background-color: #f0b63a;");

                    // Überprüft den aktuellen Hintergrund des Buttons und aktualisiert ihn entsprechend
                    if (buttonMap.get(row[i].getText()).getStyle().equals("-fx-background-color: #31a127;")) {

                    } else {

                        buttonMap.get(row[i].getText()).setStyle("-fx-background-color: #f0b63a;");

                    }


                } else {
                    // Setzt den Hintergrund des Labels auf grün, wenn der Buchstabe im Wort ist und an der richtigen Position ist
                    row[i].setStyle("-fx-background-color: #31a127;");

                    // Setzt den Hintergrund des Buttons auf grün
                    buttonMap.get(row[i].getText()).setStyle("-fx-background-color: #31a127;");

                }
            }
        }


    }

    /**
     * Behandelt den Fall, wenn das eingegebene Wort nicht im Wörterbuch existiert.
     * Löscht den Inhalt des Eingabefelds und zeigt eine Fehlermeldung an.
     */
    public void wordDoesntExist() {
        // Löscht den Inhalt des Eingabefelds
        guessInput.clear();

        // Erstellt eine Fehlermeldung(Pop-up)
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("The Word doesn't exits");
        alert.setHeaderText(null);
        alert.setContentText("The Word doesn't match with any Words in our List");

        // Zeigt die Fehlermeldung an und wartet auf eine Benutzerinteraktion
        alert.showAndWait();
    }

    /**
     * Behandelt den Fall, wenn das eingegebene Wort bereits zuvor verwendet wurde.
     * Löscht den Inhalt des Eingabefelds und zeigt eine Warnmeldung an.
     */
    public void wordAlreadyUsed() {
        // Löscht den Inhalt des Eingabefelds
        guessInput.clear();

        // Erstellt eine Warnmeldung(Pop-up)
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("The Word was already written");
        alert.setHeaderText(null);
        alert.setContentText("Try another word (Duplicate)");

        // Zeigt die Warnmeldung an und wartet auf eine Benutzerinteraktion
        alert.showAndWait();


    }

    /**
     * Beendet das Spiel und führt die entsprechenden Aktionen für den Spielabschluss durch.
     *
     * @param b Der Status des Spielendes: 1 für gewonnen, 0 für verloren, -1 für die Zeit ist abgelaufen.
     * @see TimeManager#stopTimer()
     * @see WordleController#changeToResultState(KeyEvent, int)
     */
    public void gameFinished(int b) {
        // Stoppt den Timer
        tm.stopTimer();

        // Speichert die Zeit die noch übrig geblieben ist, nach Spielende
        timerLabel2.setText(tm.getFormattedTime());

        // Wechselt zur End Szen, übergibt den Spielstatus und den KeyEvent
        changeToResultState(getEvent(), b);

    }


    /**
     * Verarbeitet das eingegebene Wort und führt die entsprechenden Aktionen aus.
     *
     * @param userInput Das vom Benutzer eingegebene Wort.
     * @param labelRow  Ein Array von Labels, das die visuelle Darstellung der Buchstaben repräsentiert.
     * @see WordleController#wordAlreadyUsed()
     * @see WordleController#colorBoxes(int[], String, Label[])
     * @see WordleController#gameFinished(int)
     * @see LetterManager#comparisonOfLetters(String, String)
     */
    public void handleGuess(String userInput, Label[] labelRow) {

        // Überprüft, ob das eingegebene Wort bereits verwendet wurde
        if (duplicateWordList.contains(userInput)) {
            wordAlreadyUsed();
            counter--;
            return;
        } else {
            // Fügt das eingegebene Wort zur Liste der bereits verwendeten Wörter hinzu
            duplicateWordList.add(userInput);
        }

        // Vergleicht die Buchstaben des eingegebenen Worts mit dem Lösungswort
        arrayWordCompared = bm.comparisonOfLetters(solutionWord, userInput);

        // Aktualisiert die Anzeige der Buchstaben
        colorBoxes(arrayWordCompared, userInput, labelRow);

        // Überprüft, ob das eingegebene Wort mit dem Lösungswort übereinstimmt und beendet das Spiel entsprechend
        if (solutionWord.equals(userInput)) {
            gameFinished(1);
        }


        // Löscht den Inhalt des Eingabefelds für die nächste Runde
        guessInput.clear();


    }

    /**
     * Wechselt zum End Screen, wenn das Spiel abgeschlossen ist, und zeigt die entsprechende Nachricht an.
     *
     * @param event Das KeyEvent-Objekt, das den Zustandswechsel ausgelöst hat.
     * @param b     Der Status des Spielendes: 1 für gewonnen, 0 für verloren, -1 für Zeitablauf.
     * @see ControllerResultState#updateTimerLabel(String)
     * @see ControllerResultState#setResultText(String)
     * @see WordleApplication
     */
    @FXML
    protected void changeToResultState(KeyEvent event, int b) {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("LastScreen.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load());
            ControllerResultState resultState = fxmlLoader.getController();

            // Setzt den Text der Labels basierend auf dem Spielstatus
            if (b == 1) {
                resultState.setResultText("CONGRATULATION! YOU WON!"+ System.lineSeparator() + "THE SOLUTION WORD WAS: " + solutionWord);
            } else if (b == 0) {
                resultState.setResultText("SORRY! YOU HAVE RUN OUT OF TRYS!" + System.lineSeparator() + "THE SOLUTION WORD WAS: " + solutionWord);
            } else if (b == -1) {
                resultState.setResultText("THE TIME HAS RUN OUT!" + System.lineSeparator() + "THE SOLUTION WORD WAS: " + solutionWord);
            }

            resultState.updateTimerLabel(tm.getFormattedTime());

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene.getStylesheets().add(WordleApplication.class.getResource("Styles.css").toExternalForm());
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (NullPointerException ignored) {
        }


    }

    public KeyEvent getEvent() {
        return event;
    }

    public void setEvent(KeyEvent event) {
        this.event = event;
    }


}