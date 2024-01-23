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

public class WordleController {

    public Label timerLabel2;
    KeyEvent event;
    TimeManager tm = new TimeManager();
    WordManager wm = new WordManager();
    BuchstabenManager bm = new BuchstabenManager();
    String solutionWord;
    boolean initialize = true;
    int counter;
    int[] array;
    List<String> stringList = new ArrayList<>();
    String guessWord;

    public WordleController() {
        wm.generateWordList();
        this.solutionWord = wm.getSolutionWord();
        this.counter = 1;
        tm.setOnTimeTick(() -> {
            timerLabel2.setText(tm.getFormattedTime());
        });
    }

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
    public void setEnter() {
        if (initialize){
            tm.startTimer();
            initialize = false;
        }
        checkGuess(counter);
        counter++;
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
        guessInput.addEventFilter(KeyEvent.KEY_TYPED,  new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {

                if (" ".equals(event.getCharacter()) || !Character.isLetter(event.getCharacter().charAt(0))){
                    event.consume();
                }

            }

        });

        if (guessInput.getText().length() == 5 && !keyEvent.getCode().equals(KeyCode.ENTER)) {

            guessInput.setText(guessInput.getText().substring(0, guessInput.getLength()-1));

            ((TextField) event.getSource()).positionCaret(guessInput.getLength());

        }
        setEvent(keyEvent);


           if (keyEvent.getCode().equals(KeyCode.ENTER)) {

            if (initialize){

                tm.startTimer();
                initialize = false;

            }
            checkGuess(counter);
            counter++;

               new AnimationTimer() {
                   @Override
                   public void handle(long now) {
                       if (tm.getFormattedTime().equals("00:00")){
                           gameFinished(-1);
                           this.stop();
                       }
                   }
               }.start();

        }

        // checkguess(int i) -> switch int i case 1: checkguess1, case 2: checkguess2 ,....

    }

    public void checkGuess(int i) {
        guessWord = guessInput.getText().toUpperCase();
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

        System.out.println(solutionWord);


        if (!wm.wordExist(guessWord)) {

            wordDoesntExist();
            counter--;

            return;

        }


        handleGuess(guessWord,row);

        if (i==6 && counter == 6){
            try {

                gameFinished(0);

            } catch (NullPointerException ignored) {}
        }

    }


    public void colorBoxes(int[] anzeigeArray, String eingabe, Label[] row) {

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


    public void gameFinished(int b) {
        tm.stopTimer();
        timerLabel2.setText(tm.getFormattedTime());
        changeToResultState(getEvent(),b);

    }

    public void handleGuess(String guess, Label[] row2) {


        if (stringList.contains(guess)) {
            wordAlreadyUsed();
            counter--;
            return;
        } else stringList.add(guess);


        array = bm.comparisonOfLetters(solutionWord, guess);
        colorBoxes(array, guess, row2);


        if(solutionWord.equals(guess)){
            gameFinished(1);
        }


        guessInput.clear();

    }

    @FXML
    protected void changeToResultState(KeyEvent event, int b)  {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("LastScreen.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load());
            ControllerResultState resultState = fxmlLoader.getController();

            // Setze den Text der Labels direkt
            if (b==1){
                resultState.setResultText("CONGRATULATION! YOU WON!");
            } else if (b==0){
                resultState.setResultText("SORRY! YOU HAVE RUN OUT OF TRYS!"+System.lineSeparator()+"THE SOLUTION WORD WAS: "+ solutionWord);}
            else if (b==-1){
                resultState.setResultText("THE TIME HAS RUN OUT!"+System.lineSeparator()+"THE SOLUTION WORD WAS: "+ solutionWord);
            }

            resultState.updateTimerLabel(tm.getFormattedTime());

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene.getStylesheets().add(WordleApplication.class.getResource("Styles.css").toExternalForm());
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (NullPointerException ignored){}


    }

    public KeyEvent getEvent() {
        return event;
    }

    public void setEvent(KeyEvent event) {
        this.event = event;
    }


}