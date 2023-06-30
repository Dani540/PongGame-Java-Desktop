package com.app.fxtestgame.game.components.entities;

import com.app.fxtestgame.game.PongGame;

public class Bot extends Player{
    private Ball ball;
    private final double PERCENTAGE_OF_ERROR=0.66, MARGIN = 10;
    public Bot() {
        this.setLayoutX(PongGame.WIDTH - WIDTH*2 - 8 );
    }

    @Override
    public void render() {

        // Logica del bot

        if (ball.getDirectionX() == 1) {
            // Choque con bordes de la pantalla
            if (this.getLayoutY() <= MARGIN) this.setLayoutY(MARGIN);
            if (this.getLayoutY() >= PongGame.HEIGHT - HEIGHT - MARGIN) this.setLayoutY(PongGame.HEIGHT - HEIGHT - MARGIN);

            // Seguimiento de la pelota
            double targetY = ball.getLayoutY() - (HEIGHT / 2);
            double diffY = targetY - this.getLayoutY();

            // Calculo de la velocidad relativa al de la pelota
            double scaledSpeed = ball.getSpeed() * PERCENTAGE_OF_ERROR;

            if (Math.abs(diffY) <= scaledSpeed) {
                // Si la diferencia es menor o igual a la velocidad escalada, ajustamos directamente a la posición de la pelota
                this.setLayoutY(targetY);
            } else {
                // Movimiento gradual hacia la posición de la pelota
                if (diffY < 0) {
                    this.setLayoutY(this.getLayoutY() - scaledSpeed);
                } else {
                    this.setLayoutY(this.getLayoutY() + scaledSpeed);
                }
            }
        }
    }


    public void setBall(Ball ball) {
        this.ball = ball;
        SPEED = ball.getSpeed()*PERCENTAGE_OF_ERROR;
    }
}
