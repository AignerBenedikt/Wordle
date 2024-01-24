package com.example.wordle;
import javafx.animation.AnimationTimer;

import java.util.Timer;
import java.util.TimerTask;

public class TimeManager {

    private int secondsRemaining;
    private Timer timer;

    private final AnimationTimer aniTime;
    private Runnable onTimeTick;


    // dem Konstruktor werden Parameter und Klassen-Instanzen übergeben
    public TimeManager() {
            aniTime = new AnimationTimer() {
                @Override
                public void handle(long now) {
                    onTimeTick.run();
                }
            };
        }


    // Runnable = Java Schnittstelle (Java-Methode) -> Operation die ohne Argumente ausgeführt wird und keine Ergebnisse zurückgibt
    // ermöglicht das Ausführen einer Aktion während der Timer tickt
    // so kann die Zeit durchgehen angezeigt werden und gleichzeitig Code ausgeführt werden (gespielt werden)
    public void setOnTimeTick(Runnable onTimeTick) {
        this.onTimeTick = onTimeTick;
    }

    // setzt die verbleibende Zeit
    public void startTimer() {

        secondsRemaining = 200;
        timer = new Timer(true); // Deamon-Thread (wenn das Haupt Programm alle Non-Deamon Threads beendet hat wird auch der Timer beendet)
        timer.scheduleAtFixedRate(new TimerTask() { // es wird auf die scheduleAtFixedRate Methode der Java-Klasse Timer zugegriffen.
            @Override // stellt sicher das "run" ist eine Methode der übergeordneten java-Klasse "TimerTask" ist
            public void run() { // "run" wird überschrieben
                // Dieser werden 2 Parameter übergeben (ein abstrakte KLasse "TimerTask" [definiert eine "run" Methode"]
                // und zwei long Werte (Startverzögerung und die Periodendauer)
                // "TimerTask" Implementierung = ist eine anonyme Klasse die in der scheduleAtFixedRate Methode definiert ist.
                // in dieser anonymen Klasse wird die "run" Methode aufgerufen wenn der Timer sein Intervall erreicht hat.
                // Hier wird die handleTimerTick-Methode in einem festgelegten zeitlichen Intervall aufgerufen.

                secondsRemaining--;
            }
        }, 0, 1000); //  der Timer tickt jede Sekunde (1000 millisek)


        aniTime.start(); // ruft die Methode "start" der Java-Klasse "AnimationTimer" auf
        // um die handleTimerTick Methode bei jedem Frame-Update aufzurufen
        // Aktualisierungszyklus eines Frames erfolgt typischerweise mehrere Male pro Sekunde
    }

    // stoppt die Timer Methode und die AnimationTimer Methode
    public void stopTimer() {
        if (timer != null) {
            timer.cancel(); // ruft die Methode "cancel" der Java-Klasse "timer" auf.
            aniTime.stop();
        }
    }


    public String getFormattedTime() {
        int minutes = secondsRemaining / 60;
        int seconds = secondsRemaining % 60;

        // Gibt die formatierte Zeit im Format "MM:SS:HH" zurück.
        return String.format("%02d:%02d", minutes, seconds);

    }
}