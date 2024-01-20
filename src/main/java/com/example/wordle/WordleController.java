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
    WordManager wm = new WordManager();
    BuchstabenManager bm = new BuchstabenManager();
    String word = "";
    int[] array;
    List<String> stringList = new ArrayList<>();

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
    protected void playAgain(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(WordleApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene.getStylesheets().add(WordleApplication.class.getResource("Styles.css").toExternalForm());
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    protected void quit() {
        System.exit(0);
    }

    public void disableAllGuessButtons() {
        Guess1.setDisable(true);
        Guess2.setDisable(true);
        Guess3.setDisable(true);
        Guess4.setDisable(true);
        Guess5.setDisable(true);
        Guess6.setDisable(true);
    }
    public void disableAllLetter() {
        LetterRow1.setDisable(true);
        LetterRow2.setDisable(true);
        LetterRow3.setDisable(true);

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

        if (guessInput.getText().length() > 5) {
            guessInput.setText(guessInput.getText().substring(0, guessInput.getLength() - 1));
            guessInput.setEditable(false);

        } else guessInput.setEditable(true);
/*
        if (keyEvent.getCode().isLetterKey()){

        }

 */

        if (keyEvent.getCode().equals(KeyCode.ENTER)) {

            if (!Guess1.isDisable()) {
              //  checkGuess1();
                checkGuess(1);
            } else if (!Guess2.isDisable()) {
               // checkGuess2();
                checkGuess(2);

            } else if (!Guess3.isDisable()) {
              //  checkGuess3();
                checkGuess(3);

            } else if (!Guess4.isDisable()) {
               // checkGuess4();
                checkGuess(4);

            } else if (!Guess5.isDisable()) {
               // checkGuess5();
                checkGuess(5);

            } else {
              //  checkGuess6();
                checkGuess(6);

            }
        }

        // checkguess(int i) -> switch int i case 1: checkguess1, case 2: checkguess2 ,....

    }

    public void checkGuess(int i) {
        String guess = guessInput.getText().toUpperCase();
        Label[] row = new Label[0];
        Button button = null;
        Button nextButton = null;

        switch (i) {
            case 1:
                row = new Label[]{box00, box01, box02, box03, box04};
                button = Guess1;
                nextButton = Guess2;
                break;

            case 2:
                row = new Label[]{box10, box11, box12, box13, box14};
                button = Guess2;
                nextButton = Guess3;
                break;

            case 3:
                row = new Label[]{box20, box21, box22, box23, box24};
                button = Guess3;
                nextButton = Guess4;
                break;

            case 4:
                row = new Label[]{box30, box31, box32, box33, box34};
                button = Guess4;
                nextButton = Guess5;
                break;

            case 5:
                row = new Label[]{box40, box41, box42, box43, box44};
                button = Guess5;
                nextButton = Guess6;
                break;

            case 6:
                row = new Label[]{box50, box51, box52, box53, box54};
                button = Guess6;
                nextButton = Guess6;
                break;
        }

        System.out.println(word);


        if (!wm.wordExist(guess)) {

            wordDoesntExist(button);

            return;

        } else button.setDisable(true);


        handleGuess(guess,row,button,nextButton);

    }
    @FXML
    protected void checkGuess1() {
        //eine große checkGuess Mwthode wo die funktionialität gliech ist aber die variablen(Buttons und labels) durch eine if abfrage verändern
        checkGuess(1);
/*
        String guess = guessInput.getText().toUpperCase();
        Label[] row0 = {box00, box01, box02, box03, box04};
        System.out.println(word);


        if (!wm.wordExist(guess)) {

            wordDoesntExist(Guess1);

            return;

        } else Guess1.setDisable(true);

        stringList.add(guess);

        array = bm.comparisonOfLetters(word, guess);

        colorBoxes(array, guess, row0, Guess2);

        youWon(word.equals(guess));

        //HBox1.setDisable(true);
        guessInput.clear();

 */
    }

    @FXML
    protected void checkGuess2() {
        checkGuess(2);
    }

    @FXML
    protected void checkGuess3() {
        checkGuess(3);
    }

    @FXML
    protected void checkGuess4() {
        checkGuess(4);
    }

    @FXML
    protected void checkGuess5() {
        checkGuess(5);

    }

    @FXML
    protected void checkGuess6() {
        checkGuess(6);
    }

    public void colorBoxes(int[] anzeigeArray, String eingabe, Label[] row, Button button) {
        //System.out.println(Arrays.toString(anzeigeArray));
        if (button.getText().equals("Guess 6")) {
            button.setDisable(true);
        } else button.setDisable(false);
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

    public void wordDoesntExist(Button button) {

        guessInput.clear();
        button.setDisable(false);

        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Word doesnt Exitst");
        alert.setHeaderText(null);
        alert.setContentText("The Word doesnt match with any Words in our List");

        alert.showAndWait();
    }

    public void wordAlreadyUsed(Button button) {

        guessInput.clear();
        button.setDisable(false);

        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Word already was written");
        alert.setHeaderText(null);
        alert.setContentText("Try another word out (Duplicate)");

        alert.showAndWait();


    }

    public void youWon(boolean b) {
        if (b) {
            disableAllGuessButtons();
            disableAllLetter();
            playAgain.setVisible(true);
            quit.setVisible(true);
            guessInput.setDisable(true);
        }
    }

    public void handleGuess(String guess, Label[] row2, Button mainButton, Button nextButton) {


        if (stringList.contains(guess)){
            wordAlreadyUsed(mainButton);
            return;
        } else stringList.add(guess);


        array = bm.comparisonOfLetters(word, guess);
        colorBoxes(array, guess, row2, nextButton);
        if (nextButton.getText().equals("Guess 6")){
            nextButton.setDisable(false);
        }

        youWon(word.equals(guess));


        guessInput.clear();
    }


}