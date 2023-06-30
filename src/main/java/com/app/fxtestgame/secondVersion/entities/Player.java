package com.app.fxtestgame.secondVersion.entities;

import com.app.fxtestgame.secondVersion.repo.StylesRepo;
import javafx.scene.control.Label;

public abstract class Player extends Label implements Entity{
    protected final double HEIGHT, WIDTH;
    protected double SPEED;

    public Player(){
        HEIGHT= 30;
        WIDTH = HEIGHT/2.5;
        SPEED = 5;

        initStyle();
    }

    private void initStyle() {
        this.setStyle(StylesRepo.playerStyle(WIDTH, HEIGHT));
    }


}
