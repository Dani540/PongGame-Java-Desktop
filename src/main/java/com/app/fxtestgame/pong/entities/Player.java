/**
 * Esta clase sirve como molde para crear jugadores (de momento solo 1)
 */

package com.app.fxtestgame.pong.entities;

import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;

public class Player extends Label {
    private final AnchorPane anchorPane;  // Panel principal (usado para medidas).
    private final int HEIGHT, WIDTH, SPEED; // Alto, ancho y velocidad del jugador.
    private int isMoving; // Variable para control de movimiento ( 1: arriba, -1: abajo) .

    /**
     * El constructor establece las dependencias y el estilo del jugador.
     * @param anchorPane Es el panel principal.
     */
    public Player(AnchorPane anchorPane) {

        this.anchorPane = anchorPane;

        HEIGHT = 30;
        WIDTH = 15;
        SPEED = 5;

        addStyle();
    }

    /**
     * Este metodo se ejectua durante la renderizacion (bucle principal) del juego.
     * Maneja el movimiento del personaje en funcion de la variable booleana "isMoving".
     */
    public void update(){
            switch (isMoving) {
                case -1 -> {    // Hacia abajo
                    this.setLayoutY(this.getLayoutY() + SPEED);
                    rangeOfMovement(anchorPane);
                }

                case 1 -> { // Hacia arriba
                    this.setLayoutY(this.getLayoutY() - SPEED);
                    rangeOfMovement(anchorPane);
                }
            }
    }

    /**
     * Este metodo añade el estilo al personaje usando notacion JavaFX en CSS.
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
     * Asigna el movimiento al personaje (mouse y teclado) en funcion del panel principal.
     * @param anchorPane Es el panel principal
     */
    public void setMovement(AnchorPane anchorPane){
        setMouseMovement(anchorPane);
        setKeyboardMovement(anchorPane);
    }

    /**
     * Asigna el movimiento con mouse del jugador.
     * @param anchorPane Es el panel principal (o el panel actual en vista).
     */
    private void setMouseMovement(AnchorPane anchorPane) {
        this.setOnMouseDragged( mouseEvent -> {
                this.setLayoutY(this.getLayoutY() + mouseEvent.getY());
                rangeOfMovement(anchorPane);
        });
    }

    /**
     * Asigna el movimiento con mouse del jugador.
     * @param anchorPane Es el panel principal (o el panel actual en vista).
     */
    private void setKeyboardMovement(AnchorPane anchorPane) {

        anchorPane.requestFocus();  // Ponemos el foco de deteccion de teclas en panel.
        anchorPane.setOnKeyPressed(event -> {
                if      (upKeyboards(event))   isMoving = 1;
                else if (downKeyboards(event)) isMoving = -1;
        });

        anchorPane.setOnKeyReleased(event -> {
            if (upKeyboards(event) || downKeyboards(event)) isMoving = 0;   // Cuando soltemos la teclas dejamos de mover al personaje.
        });
    }

    /**
     * Son las teclas para movernos hacia arriba, como pueden ser una o varias las guarde en un metodo.
     * @param event Es el evento de teclado ejecutado (para este caso puede ser presionar o soltar).
     * @return Devuelve el booleano con el respectivo evento de estas teclas.
     */
    private boolean upKeyboards(KeyEvent event) {
        return event.getCode().equals(KeyCode.W) || event.getCode().equals(KeyCode.UP);
    }

    /**
     * Son las teclas para movernos hacia abajo, como pueden ser una o varias las guarde en un metodo.
     * @param event Es el evento de teclado ejecutado (para este caso puede ser presionar o soltar).
     * @return Devuelve el booleano con el respectivo evento de estas teclas.
     */
    private boolean downKeyboards(KeyEvent event) {
        return event.getCode().equals(KeyCode.S) || event.getCode().equals(KeyCode.DOWN);
    }

    /**
     * Es el rango de movimiento del jugador en funcion del panel, para este caso
     * el tope maximo son los limites de la ventana.
     * @param anchorPane Es el panel princiapl.
     */
    private void rangeOfMovement(AnchorPane anchorPane) {
        boolean topMax = this.getLayoutY() > anchorPane.getHeight() - HEIGHT;
        boolean topMin = this.getLayoutY() < 0;
        if (topMax) this.setLayoutY(anchorPane.getHeight() - HEIGHT);
        if (topMin) this.setLayoutY(0);
    }
}
