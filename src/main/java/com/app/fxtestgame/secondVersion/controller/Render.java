package com.app.fxtestgame.secondVersion.controller;

import com.app.fxtestgame.secondVersion.entities.Entity;
import javafx.animation.AnimationTimer;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;

import java.util.List;

public class Render {
    private final List<Entity> entities;
    private boolean isPause;

    public Render(Entity ... entities) {
        this.entities = List.of(entities);
    }

    public void renderer() {
        AnimationTimer animationTimer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                if (!isPause) entities.forEach(Entity::render);
            }
        };
        animationTimer.start();
    }

    public void setPauseControl(AnchorPane pane) {
        pane.requestFocus();
        pane.setOnKeyPressed(event -> {
            if ( pauseKeyboards(event) ){
                isPause = !isPause;
                pane.getChildren().forEach(n->{
                    // La idea es que con un userdata se controlen los componentes pero de momento es a pelo
                    if (n instanceof Entity aux) ((Label)aux).setVisible(!isPause);
                    else if (n instanceof Label aux) aux.setVisible(isPause);
                });
            }
        });
    }

    private boolean pauseKeyboards(KeyEvent event) {
        List<KeyCode> keyCodeList = List.of(KeyCode.SPACE, KeyCode.ENTER, KeyCode.BACK_SPACE);
        return keyCodeList.stream().anyMatch(n -> event.getCode().equals(n));
    }
}
