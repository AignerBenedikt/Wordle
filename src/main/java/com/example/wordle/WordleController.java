package com.example.wordle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.*;


//https://www.youtube.com/watch?v=B4t0Y40U4HI

public class WordleController {

    public HBox LetterRow3;
    public HBox LetterRow2;
    public HBox LetterRow1;
    public Label timerLabel2;
    KeyEvent event;
    TimeManager tm = new TimeManager();
    WordManager wm = new WordManager();
    BuchstabenManager bm = new BuchstabenManager();
    String word = "";
    boolean initialize = true;
    int counter;
    int[] array;
   // int counter = 6;
    List<String> stringList = new ArrayList<>();

    public WordleController() {
        wm.generateWordList(5);
        this.word = wm.SolutionWord();
        this.counter = 1;
        tm.setOnTimeTick(() -> {
            timerLabel2.setText(tm.getFormattedTime());
        });
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
    protected void quit() {
        System.exit(0);
    }

    @FXML
    protected void setDelete() {
        guessInput.clear();
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

        if (guessInput.getText().length() >= 5) {
            return;
        }
        Character text = actionEvent.getTarget().toString().charAt(10);

        guessInput.appendText(String.valueOf(text));
    }

    public void realKeyboardInput(KeyEvent keyEvent) {

        setEvent(keyEvent);
        if (guessInput.getText().length() > 5) {
            guessInput.setText(guessInput.getText().substring(0, guessInput.getLength() - 1));
            guessInput.setEditable(false);

        } else guessInput.setEditable(true);
/*
        if (keyEvent.getCode().isLetterKey()){

        }

 */

        if (keyEvent.getCode().equals(KeyCode.ENTER)) {
            if (initialize){
                tm.startTimer();
                initialize = false;
            }
            checkGuess(counter);
            counter++;
        }

        // checkguess(int i) -> switch int i case 1: checkguess1, case 2: checkguess2 ,....

    }

    public void checkGuess(int i) {
        String guess = guessInput.getText().toUpperCase();
        Label[] row = new Label[0];


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

        System.out.println(word);


        if (!wm.wordExist(guess)) {

            wordDoesntExist();
            counter--;

            return;

        }


        handleGuess(guess,row);

        if (i==6){
            try {

                youLost();

            } catch (NullPointerException ignored) {}
        }

    }


    public void colorBoxes(int[] anzeigeArray, String eingabe, Label[] row) {
        //System.out.println(Arrays.toString(anzeigeArray));

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

        for (int i = 0; i < eingabe.length(); i++) {

            row[i].setText(String.valueOf(eingabe.charAt(i)));

            if (anzeigeArray[i] == 0) {

                row[i].setStyle("-fx-background-color: #919191;");

                if (buttonMap.get(row[i].getText()).getStyle().equals("-fx-background-color: #31a127;")) {

                } else if (buttonMap.get(row[i].getText()).getStyle().equals("-fx-background-color: #f0b63a;")) {

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

    public void wordDoesntExist() {

        guessInput.clear();

        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Word doesnt Exitst");
        alert.setHeaderText(null);
        alert.setContentText("The Word doesnt match with any Words in our List");

        alert.showAndWait();
    }

    public void wordAlreadyUsed() {

        guessInput.clear();

        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Word already was written");
        alert.setHeaderText(null);
        alert.setContentText("Try another word out (Duplicate)");

        alert.showAndWait();


    }

    public void youWon(boolean b) {

        if (b) {
            /*
            disableAllGuessButtons();
            disableAllLetter();
            playAgain.setVisible(true);
            quit.setVisible(true);
            guessInput.setDisable(true);
            */
            try {
                tm.stopTimer();
                timerLabel2.setText(tm.getFormattedTime());
                changeToResultState(getEvent(),b);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
    }
    public void youLost() {

            try {
                tm.stopTimer();
                timerLabel2.setText(tm.getFormattedTime());
                changeToResultState(getEvent(),false);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }


    }

    public void handleGuess(String guess, Label[] row2) {


        if (stringList.contains(guess)) {
            wordAlreadyUsed();
            counter--;
            return;
        } else stringList.add(guess);


        array = bm.comparisonOfLetters(word, guess);
        colorBoxes(array, guess, row2);


        youWon(word.equals(guess));


        guessInput.clear();

    }

    @FXML
    protected void changeToResultState(KeyEvent event, boolean b) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("LastScreen.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        ControllerResultState resultState = fxmlLoader.getController();

        // Setze den Text der Labels direkt
        if (b){
            resultState.setResultText("CONGRATULATION! YOU WON!");
        } else {
            resultState.setResultText("SORRY! YOU LOST");}

       // resultState.updateTimerLabel(tm.getFormattedTime());
        resultState.updateTimerLabel(tm.getFormattedTime());

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    public KeyEvent getEvent() {
        return event;
    }

    public void setEvent(KeyEvent event) {
        this.event = event;
    }
}