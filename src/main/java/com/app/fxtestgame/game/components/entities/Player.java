package com.app.fxtestgame.game.components.entities;

import com.app.fxtestgame.game.PongGame;
import com.app.fxtestgame.game.repo.StylesRepo;
import javafx.scene.control.Label;

public abstract class Player extends Label implements Entity{
    protected final double HEIGHT, WIDTH;
    protected double SPEED;

    public Player(){
        HEIGHT= 30;
        WIDTH = HEIGHT/2.5;
        SPEED = 5;

        initStyle();

        this.setLayoutY(PongGame.HEIGHT/2 - HEIGHT*1.5);
    }

    private void initStyle() {
        this.setStyle(StylesRepo.getPlayerStyle(WIDTH, HEIGHT));
    }


}
