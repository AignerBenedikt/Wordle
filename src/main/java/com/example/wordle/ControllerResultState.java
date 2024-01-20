package com.example.wordle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;



    public class ControllerResultState {

        private WordleController wordleController;

        @FXML
        private Label ResultText;

        public void setResultText(String text) {
            ResultText.setText(text);
        }

        @FXML
        protected void quit() {
            System.exit(0);
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
    }