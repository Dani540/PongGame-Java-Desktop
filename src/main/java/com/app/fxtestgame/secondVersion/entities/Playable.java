package com.app.fxtestgame.secondVersion.entities;

import com.app.fxtestgame.secondVersion.Game;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.util.List;

public class Playable extends Player{

    private int moveDirection;

    public Playable(Scene scene) {
        this.setLayoutX(5);
        moveDirection = 0;
        setMovement(scene);
    }


    public void setMovement(Scene scene){

        // Movimiento por teclado
        scene.setOnKeyPressed( event -> {
            if (upKeyboards(event)) moveDirection = 1;
            if (downKeyboards(event)) moveDirection = -1;
        });

        scene.setOnKeyReleased(event -> {
            moveDirection = 0;
        });

        // Movimiento por mouse

        this.setOnMouseDragged(mouseEvent -> {
            scene.setCursor(Cursor.NONE);
            this.setLayoutY( this.getLayoutY() + mouseEvent.getY());
        });

        this.setOnMouseReleased(mouseDragEvent -> {
            scene.setCursor(Cursor.DEFAULT);
        });
    }

    private boolean upKeyboards(KeyEvent event) {
        List<KeyCode> keyCodes = List.of(
                KeyCode.W,
                KeyCode.UP,
                KeyCode.SHIFT
        );
        return keyCodes.stream().anyMatch(n -> event.getCode().equals(n));
    }

    private boolean downKeyboards(KeyEvent event) {
        List<KeyCode> keyCodes = List.of(
                KeyCode.S,
                KeyCode.DOWN,
                KeyCode.CONTROL
        );
        return keyCodes.stream().anyMatch(n -> event.getCode().equals(n));
    }

    @Override
    public void render() {
        switch (moveDirection){
            case 1 -> this.setLayoutY(this.getLayoutY() - SPEED);
            case -1 ->this.setLayoutY(this.getLayoutY() + SPEED);
        }
        renderRangeOfMovement();
    }

    private void renderRangeOfMovement() {
        boolean topMax = this.getLayoutY() >= Game.HEIGHT - HEIGHT*2.23;
        boolean topMin = this.getLayoutY() < 0;

        if (topMax) this.setLayoutY( Game.HEIGHT - HEIGHT*2.23 );
        if (topMin) this.setLayoutY(0);
    }
}
