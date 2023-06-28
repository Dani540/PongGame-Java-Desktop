package com.app.fxtestgame.pong.entities;

import com.app.fxtestgame.pong.PongGame;
import javafx.scene.control.Label;

public class Bot extends Label {

    private final int HEIGHT, WIDTH;  // Alto y ancho del bot.

    /**
     * El constructor establece las medidas, la posicion en pantalla y el estilo del bot.
     */
    public Bot() {

        HEIGHT = 30;
        WIDTH = 15;

        this.setLayoutX(PongGame.WIDTH - (WIDTH*2) - 10 );

        addStyle();
    }

    /**
     * El estilo se añade con notacion de JavaFX en CSS.
     */
    private void addStyle() {
        this.setStyle(
                "-fx-background-color: white;" +
                        "-fx-border-width:     2;" +
                        "-fx-border-color:     black;" +
                        "-fx-min-height:   " + HEIGHT + ";" +
                        "-fx-max-height:   " + HEIGHT + ";" +
                        "-fx-pref-height:  " + HEIGHT + ";" +
                        "-fx-min-width:    " + WIDTH + ";" +
                        "-fx-max-width:    " + WIDTH + ";" +
                        "-fx-pref-width:   " + WIDTH + ";"
        );
    }

    /**
     * Este metodo se ejecuta en el bucle principal y sirve como "IA" para el bot.
     * De momento solo detecta cuando la pelota venga hacia este para moverse hacia ella.
     * @param ball Es la pelota.
     */
    public void render(Ball ball) {
        if (ball.getDirectionX() == 1) {
            this.setLayoutY( ball.getLayoutY() );
        }
    }
}
