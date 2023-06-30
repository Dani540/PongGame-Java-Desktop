package com.app.fxtestgame.game.components.elements;

import com.app.fxtestgame.game.PongGame;
import com.app.fxtestgame.game.repo.StylesRepo;
import javafx.scene.control.Label;

public class Pause extends Label implements Element{

    private final double HEIGHT = 60, WIDTH = HEIGHT *2.5;
    private boolean isPause;

    public Pause() {

        isPause = true;

        initStyle();

        this.setVisible(isPause);

        this.setLayoutX(PongGame.WIDTH/2 - WIDTH /2);
        this.setLayoutY(PongGame.HEIGHT/2 - HEIGHT);
    }

    private void initStyle() {
        this.setText("""
                PAUSE!
                Move: Up, Down, W or S
                Pause: Enter or Space""");
        this.setStyle(StylesRepo.getLabelStyle(WIDTH, HEIGHT));
    }

    @Override
    public void refresh() {
        this.setVisible(isPause);
    }

    public void setPause(boolean pause) {
        isPause = pause;
    }

    public boolean getPause() {
        return isPause;
    }
}
