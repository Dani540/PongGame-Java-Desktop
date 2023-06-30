package com.app.fxtestgame.secondVersion.entities;

import com.app.fxtestgame.secondVersion.Game;
import com.app.fxtestgame.secondVersion.repo.StylesRepo;
import javafx.scene.control.Label;

import java.util.List;
import java.util.Random;

public class Ball extends Label implements Entity{
    private final List<Entity> entities;
    private final double SIZE, ANGLE, OUTSIDERANGE, SPEEDINCREMENT;
    private double speed;
    private int directionX, directionY;
    public Ball(Entity ... entities){
        this.entities = List.of(entities);
        SIZE = 15;
        speed = 4;
        SPEEDINCREMENT = 0.2;
        ANGLE = 25;
        OUTSIDERANGE = SIZE*2;

        initBall();
    }

    private void initBall() {
        this.setWidth(SIZE);
        this.setHeight(SIZE);

        restartPosition();

        this.setStyle(StylesRepo.ballStyle(SIZE));
    }

    @Override
    public void render() {

        outsideX();
        outsideY();

        obsCollision(entities);

        move();

    }

    private void move() {
        double speedX = Math.cos(Math.toRadians(ANGLE)) * speed;
        double speedY = Math.sin(Math.toRadians(ANGLE)) * speed;

        this.setLayoutX(this.getLayoutX() + speedX * directionX);
        this.setLayoutY(this.getLayoutY() + speedY * directionY);
    }

    private void outsideX() {
        boolean topMax = this.getLayoutX() <= -OUTSIDERANGE;
        boolean topMin = this.getLayoutX() >= Game.WIDTH + OUTSIDERANGE;

        if (topMax || topMin){
            restartPosition();
            if (speed > 4) speed -= (speed *0.3);
        }
    }

    private void outsideY() {
        boolean topMax = this.getLayoutY() <= 0;
        boolean topMin = this.getLayoutY() >= Game.HEIGHT - SIZE*3.5;

        if (topMax || topMin) directionY = -directionY;
    }

    private void restartPosition() {
        this.setLayoutX(Game.WIDTH / 2 - SIZE / 2);
        this.setLayoutY(Game.HEIGHT / 2 - SIZE);
        directionX = new Random().nextBoolean()? -1 : 1;
        directionY = new Random().nextBoolean()? -1 : 1;
    }

    private void obsCollision(List<Entity> entities) {
        if ( entities.stream().anyMatch(this::collision) ){
            speed += SPEEDINCREMENT;
            directionX = -directionX;
        }
    }

    private boolean collision(Entity n) {
        boolean collision = false;
        if (n instanceof Label aux) collision = this.getBoundsInParent().intersects(aux.getBoundsInParent());
        return collision;
    }

    public int getDirectionX() {
        return directionX;
    }

    public double getSpeed() {
        return speed;
    }
}
