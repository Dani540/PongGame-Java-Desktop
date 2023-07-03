/**
 * Esta clase se encarga de renderizar todos los componentes necesarios del juego.
 */

package com.app.fxtestgame.game.controller;

import com.app.fxtestgame.game.components.Component;
import com.app.fxtestgame.game.components.elements.Element;
import com.app.fxtestgame.game.components.elements.Pause;
import com.app.fxtestgame.game.components.entities.Entity;
import javafx.animation.AnimationTimer;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;

import java.util.List;
import java.util.stream.Stream;

public class Render {
    private final List<Component> components;
    private final Pause pause;

    public Render(Component... components) {
        this.components = List.of(components);
        pause = (Pause) Stream.of(components).filter(n -> n instanceof Pause).toList().get(0);
    }

    /**
     * Es el metodo que renderiza el juego, contiene el bucle principal.
     * La renderizacion se basa en la actualizacion de los componentes
     * segun la pausa del juego.
     */
    public void renderer() {
        AnimationTimer animationTimer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                if (!pause.getPause()) components.forEach(n -> {
                    if (n instanceof Entity e) e.render();
                });
            }
        };
        animationTimer.start();
    }

    public void setPauseControl(AnchorPane pane) {
        pane.requestFocus();
        pane.setOnKeyPressed(event -> {
            if (pauseKeyboards(event)) {
                pause.setPause(!pause.getPause());
                pane.getChildren().forEach(n -> {
                    // La idea es que con un userdata se controlen los componentes pero de momento es a pelo
                    if      (n instanceof Entity aux) ((Label) aux).setVisible(!pause.getPause());
                    else if (n instanceof Element aux) aux.refresh();
                });
            }
        });
    }

    private boolean pauseKeyboards(KeyEvent event) {
        List<KeyCode> keyCodeList = List.of(KeyCode.SPACE, KeyCode.ENTER, KeyCode.BACK_SPACE);
        return keyCodeList.stream().anyMatch(n -> event.getCode().equals(n));
    }
}
