package com.example.wordle;
import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.util.Timer;
import java.util.TimerTask;

public class TimeManager {

    private int secondsRemaining;
    private Timer timer;

    // private GameManagerSingleton GaMaSi;
    private AnimationTimer aniTime;
    private Runnable onTimeTick;

    private String formattedTime;



    // dem Konstruktor werden Parameter und Klassen-Instanzen übergeben
    public TimeManager() {
            aniTime = new AnimationTimer() {
                @Override
                public void handle(long now) {
                    onTimeTick.run();
                }
            };
        }

    /*
            // AnimationTime Methode aktualisiert die handleTimeTick Methode (bei jedem Frame-Update) updated
            this.aniTime = new AnimationTimer() {
                @Override
                public void handle(long now) {
                    handleTimerTick();
                }
            };
        }
    */
    // Runnable = Java Schnittstelle (Java-Methode) -> Operation die ohne Argumente ausgeführt wird und keine Ergebnisse zurückgibt
    // ermöglicht das Ausführen einer Aktion während der Timer tickt
    // so kann die Zeit durchgehen angezeigt werden und gleichzeitig Code ausgeführt werden (gespielt werden)
    public void setOnTimeTick(Runnable onTimeTick) {
        this.onTimeTick = onTimeTick;
    }

    // setzt die verbleibende Zeit
    public void startTimer() {

        secondsRemaining = 300;
        timer = new Timer(true); // Deamon-Thread (wenn das Haupt Programm alle Non-Deamon Threads beendet hat wird auch der Timer beendet)
        timer.scheduleAtFixedRate(new TimerTask() { // es wird auf die scheduleAtFixedRate Methode der Java-Klasse Timer zugegriffen.
            @Override // stellt sicher das "run" ist eine Methode der übergeordneten java-Klasse "TimerTask" ist
            public void run() { // "run" wird überschrieben
                // Dieser werden 2 Parameter übergeben (ein abstrakte KLasse "TimerTask" [definiert eine "run" Methode"]
                // und zwei long Werte (Startverzögerung und die Periodendauer)
                // "TimerTask" Implementierung = ist eine anonyme Klasse die in der scheduleAtFixedRate Methode definiert ist.
                // in dieser anonymen Klasse wird die "run" Methode aufgerufen wenn der Timer sein Intervall erreicht hat.
                // Hier wird die handleTimerTick-Methode in einem festgelegten zeitlichen Intervall aufgerufen.
                /*
                new TimerTask() {
                    @Override
                       public void run() {
                            handleTimerTick();
                        }
                }
                 */
                handleTimerTick();
            }
        }, 0, 1000); //  der Timer tickt jede Sekunde (1000 millisek)


        aniTime.start(); // ruft die Methode "start" der Java-Klasse "AnimationTimer" auf
        // um die handleTimerTick Methode bei jedem Frame-Update aufzurufen
        // Aktualisierungszyklus eines Frames erfolgt typischerweise mehrere Male pro Sekunde
    }

    // stopt die Timer Methode und die AnimationTimer Methode
    public void stopTimer() {
        if (timer != null) {
            timer.cancel(); // ruft die Methode "cancel" der Java-Klasse "timer" auf.
            aniTime.stop();
        }
    }

    private void handleTimerTick() {
        secondsRemaining--; // verringert den Timer im 1 Sekunde --

        formattedTime = getFormattedTime();

        if (secondsRemaining <= 0) {
            timer.cancel(); // die Methode "Timer" wird abgebrochen
            handleTimeUp(); // die Methode "handleTimeUp" im BuchstabenManger wird aufgerufen
        }
    }

    @FXML
    private Button ChangeSreenButton;
    public void handleTimeUp() {

       /* ActionEvent simulatedEvent = new ActionEvent(ChangeSreenButton, ChangeSreenButton);
        try {
            // Rufe changeToResultState mit dem simulierten Event auf
            wc.changeToResultState(simulatedEvent);
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }
    public String getFormattedTime() {
        int minutes = secondsRemaining / 60;
        int seconds = secondsRemaining % 60;
        int hundredths = (int) ((secondsRemaining - Math.floor(secondsRemaining)) * 100);

        // Gibt die formatierte Zeit im Format "MM:SS:HH" zurück.
        return String.format("%02d:%02d:%02d", minutes, seconds, hundredths);

    }
}