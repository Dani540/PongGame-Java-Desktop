package com.app.fxtestgame.game.components.entities;

import com.app.fxtestgame.game.PongGame;

public class Bot extends Player {
    private Ball ball;
    private final double PERCENTAGE_OF_ERROR = 0.66, MARGIN = 10;

    public Bot() {
        this.setLayoutX(PongGame.WIDTH - WIDTH * 2 - 8);
    }

    @Override
    public void render() {

        // Logica del bot

        if (ball.getDirectionX() == 1) {
            // Choque con bordes de la pantalla
            if (this.getLayoutY() <= MARGIN)                            this.setLayoutY(MARGIN);
            if (this.getLayoutY() >= PongGame.HEIGHT - HEIGHT - MARGIN) this.setLayoutY(PongGame.HEIGHT - HEIGHT - MARGIN);

            // Paras seguimiento de la pelota
            double ballPos = ball.getLayoutY() - (HEIGHT / 2);
            double diffY = ballPos - this.getLayoutY();

            // Calculo de la velocidad en funcion de la velocidad de la pelota
            double speed = ball.getSpeed() * PERCENTAGE_OF_ERROR;

            if (Math.abs(diffY) <= speed) {
                this.setLayoutY(ballPos);
            } else {
                // Seguimiento de la pelota
                if (diffY < 0) {
                    this.setLayoutY(this.getLayoutY() - speed);
                } else {
                    this.setLayoutY(this.getLayoutY() + speed);
                }

            }
        }
    }


    public void setBall(Ball ball) {
        this.ball = ball;
        SPEED = ball.getSpeed() * PERCENTAGE_OF_ERROR;
    }
}
