package com.example.wordle;

import javafx.event.ActionEvent;
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

import static javafx.application.Application.launch;

//https://www.youtube.com/watch?v=B4t0Y40U4HI

public class WordleController{
    WordManager wm = new WordManager();
    BuchstabenManager bm = new BuchstabenManager();
    String word = "";
    List<String> stringList = new ArrayList<>();

    boolean gameWinState = false;

    public WordleController() {
        wm.generateWordList(5);
        this.word = wm.SolutionWord();
    }

    public Button Guess1;
    public Button Guess2;
    public Button Guess3;
    public Button Guess4;
    public Button Guess5;
    public Button Guess6;
    public Button Back;
    public Button Delete;
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

    @FXML
    protected void setDelete() {
        guessInput.clear();
    }
    @FXML
    protected void playAgain(ActionEvent event) throws IOException {
        /*
        // NEU = "Action Event" erhält das ursprüngliche Fenster, auf dem die Aktion ausgelöst wird
        "event.getSource()§ gibt das ursprüngliche Objekt zurück, auf dem die Aktion stattgefunden hat.
        dieses Event ist ein "Node" =  getScene().getWindow() wird verwendet, um das Window-Objekt (die Stage) zu erhalten.
         */
        FXMLLoader fxmlLoader = new FXMLLoader(WordleApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage =(Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();

        /*
        // ALT = ein neues Stage-Objekt wird erstellt ->  eine neue Instanz der Stage-Klasse erstellt, und das Fenster wird darauf aufgebaut.
        protected void playAgain() throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(WordleApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Wordle");
        stage.setScene(scene);
        stage.show();
         */
    }

    public void disableAllGuessButtons() {
        Guess1.setDisable(true);
        Guess2.setDisable(true);
        Guess3.setDisable(true);
        Guess4.setDisable(true);
        Guess5.setDisable(true);
        Guess6.setDisable(true);
    }
    @FXML
    protected void quit() {
        System.exit(0);
    }
    @FXML
    protected void setBack() {
        try {
            guessInput.setText(guessInput.getText().substring(0, guessInput.getLength() - 1));
        } catch (StringIndexOutOfBoundsException ignored) {

        }
    }

    @FXML
    protected void Letter(ActionEvent actionEvent) {

        if (guessInput.getText().length()>=5){
            return;
        }
        Character text = actionEvent.getTarget().toString().charAt(10);
        guessInput.appendText(String.valueOf(text));
    }
    public void realKeyboardInput(KeyEvent keyEvent) {

        if (guessInput.getText().length()>5){
            guessInput.setText(guessInput.getText().substring(0, guessInput.getLength() - 1));
            guessInput.setEditable(false);

        } else guessInput.setEditable(true);
        if (keyEvent.getCode().equals(KeyCode.ENTER)){
            if (!Guess1.isDisable()){
                checkGuess1();
            } else if (!Guess2.isDisable()) {
                checkGuess2();

            } else if (!Guess3.isDisable()) {
                checkGuess3();

            } else if (!Guess4.isDisable()) {
                checkGuess4();

            } else if (!Guess5.isDisable()) {
                checkGuess5();

            }
        }


    }
    @FXML
    protected void checkGuess1() {
         // TODO setDisable nach der wordexists implementieren
        String guess = guessInput.getText().toUpperCase();
        Label[] row0 = {box00, box01, box02, box03, box04};
        System.out.println(word);


        if (!wm.wordExist(guess)){
            guessInput.clear();
            Guess1.setDisable(false);
            System.out.println("FALSCH");
            return;
        } else Guess1.setDisable(true);

        stringList.add(guess);

        int [] array=bm.comparisonOfLetters(word,guess);
        colorBoxes(array,guess,row0);
        Guess2.setDisable(false);

        if (word.equals(guess)){
            disableAllGuessButtons();
            playAgain.setVisible(true);
            quit.setVisible(true);
            return;
        }

        guessInput.clear();
    }
    @FXML
    protected void checkGuess2() {
        String guess = guessInput.getText().toUpperCase();
        Label[] row1 = {box10, box11, box12, box13, box14};

        System.out.println(word);

        if (!wm.wordExist(guess)){
            guessInput.clear();
            Guess2.setDisable(false);
            System.out.println("FALSCH");
            return;
        } else  Guess2.setDisable(true);


        if (stringList.contains(guess)){
            guessInput.clear();
            Guess2.setDisable(false);
            return;
        }else {
            stringList.add(guess);
        }

        int [] array=bm.comparisonOfLetters(word,guess);
        colorBoxes(array,guess,row1);
        Guess3.setDisable(false);

        if (word.equals(guess)){
            disableAllGuessButtons();
            gameWinState = true;

            ActionEvent simulatedEvent = new ActionEvent(ChangeSreenButton, ChangeSreenButton);
            try {
                // Rufe changeToResultState mit dem simulierten Event auf
                changeToResultState(simulatedEvent);
            } catch (IOException e) {
                e.printStackTrace();
            }

            playAgain.setVisible(true);
            quit.setVisible(true);

            return;
        }

        guessInput.clear();
    }
    @FXML
    protected void checkGuess3() {

        String guess = guessInput.getText().toUpperCase();
        Label[] row2 = {box20, box21, box22, box23, box24};

        if (!wm.wordExist(guess)){
            guessInput.clear();
            Guess3.setDisable(false);
            System.out.println("FALSCH");
            return;
        } else  Guess3.setDisable(true);


        if (stringList.contains(guess)){
            guessInput.clear();
            Guess3.setDisable(false);
            return;
        }else {
            stringList.add(guess);
        }



        int [] array=bm.comparisonOfLetters(word,guess);
        colorBoxes(array,guess,row2);
        Guess4.setDisable(false);

        if (word.equals(guess)){
            disableAllGuessButtons();
            playAgain.setVisible(true);
            quit.setVisible(true);
            return;
        }

        guessInput.clear();
    }
    @FXML
    protected void checkGuess4() {
        String guess = guessInput.getText().toUpperCase();
        Label[] row3 = {box30, box31, box32, box33, box34};


        if (!wm.wordExist(guess)){
            guessInput.clear();
            Guess4.setDisable(false);
            System.out.println("FALSCH");
            return;
        } else   Guess4.setDisable(true);


        if (stringList.contains(guess)){
            guessInput.clear();
            Guess4.setDisable(false);
            return;
        }else {
            stringList.add(guess);
        }



        int [] array=bm.comparisonOfLetters(word,guess);
        colorBoxes(array,guess,row3);
        Guess5.setDisable(false);

        if (word.equals(guess)){
            disableAllGuessButtons();
            playAgain.setVisible(true);
            quit.setVisible(true);
            return;
        }

        guessInput.clear();
    }
    @FXML
    protected void checkGuess5() {
        String guess = guessInput.getText().toUpperCase();
        Label[] row4 = {box40, box41, box42, box43, box44};

        if (!wm.wordExist(guess)){
            guessInput.clear();
            Guess5.setDisable(false);
            System.out.println("FALSCH");
            return;
        } else  Guess5.setDisable(true);


        if (stringList.contains(guess)){
            guessInput.clear();
            Guess5.setDisable(false);
            return;

        }else {
            stringList.add(guess);
        }


        int [] array=bm.comparisonOfLetters(word,guess);
        colorBoxes(array,guess,row4);
        Guess6.setDisable(false);

        if (word.equals(guess)){
            disableAllGuessButtons();
            playAgain.setVisible(true);
            quit.setVisible(true);

            return;
        }

        guessInput.clear();
    }
    @FXML
    protected void checkGuess6() {

        String guess = guessInput.getText().toUpperCase();
        Label[] row5 = {box50, box51, box52, box53, box54};

        if (!wm.wordExist(guess)){
            guessInput.clear();
            Guess6.setDisable(false);
            System.out.println("FALSCH");
            return;
        } else  Guess6.setDisable(true);


        if (stringList.contains(guess)){
            guessInput.clear();
            Guess6.setDisable(false);
            return;
        }else {
            stringList.add(guess);
        }

        int [] array=bm.comparisonOfLetters(word,guess);
        colorBoxes(array,guess,row5);
        if (word.equals(guess)){
        gameWinState = true;
        }
        // to du Button fire !
        playAgain.setVisible(true);
        quit.setVisible(true);
        guessInput.clear();
    }

    public void colorBoxes (int[]anzeigeArray,String eingabe, Label[] row) {
        //System.out.println(Arrays.toString(anzeigeArray));
        Map<String, Button>buttonMap  = new HashMap<>() {{
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
        for (int i = 0; i < eingabe.length(); i++) {
            row[i].setText(String.valueOf(eingabe.charAt(i)));


            if (anzeigeArray[i] == 0) {

                row[i].setStyle("-fx-background-color: #919191;");

                if (buttonMap.get(row[i].getText()).getStyle().equals("-fx-background-color: #31a127;")){

                } else if (buttonMap.get(row[i].getText()).getStyle().equals("-fx-background-color: #f0b63a;")){

                } else {
                    buttonMap.get(row[i].getText()).setStyle("-fx-background-color: #919191;");

                }



            } else {

                if (anzeigeArray[i] == 1) {

                    row[i].setStyle("-fx-background-color: #f0b63a;");

                    if (buttonMap.get(row[i].getText()).getStyle().equals("-fx-background-color: #31a127;")) {

                    } else {
                        buttonMap.get(row[i].getText()).setStyle("-fx-background-color: #f0b63a;");

                    }


                } else {

                    row[i].setStyle("-fx-background-color: #31a127;");

                    buttonMap.get(row[i].getText()).setStyle("-fx-background-color: #31a127;");

                }
            }
        }
    }

    @FXML
    private Button ChangeSreenButton;
    @FXML
    protected void changeToResultState(ActionEvent event) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("LastScreen.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        ControllerResultState resultState = fxmlLoader.getController();

        // Setze den Text der Labels direkt
        if (gameWinState == true){

            resultState.setResultText("CONGRATULATION! YOU WON!");
        } else {
            resultState.setResultText("SORRY! YOU LOST");}

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

}