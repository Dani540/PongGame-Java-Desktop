package com.app.fxtestgame.secondVersion.controller;

import com.app.fxtestgame.pong.PongGame;
import com.app.fxtestgame.pong.entities.Ball;
import com.app.fxtestgame.pong.entities.Bot;
import com.app.fxtestgame.secondVersion.entities.Entity;
import com.app.fxtestgame.secondVersion.entities.Playable;
import com.app.fxtestgame.secondVersion.repo.StylesRepo;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.util.List;

public class GameController {
    private final Stage stage;
    private final Render render;
    private final com.app.fxtestgame.secondVersion.entities.Ball ball;
    private final com.app.fxtestgame.secondVersion.entities.Bot bot;
    private final Playable player;
    private final Label pauseLabel;
    private boolean isPause;

    public GameController(Stage stage, Scene scene, AnchorPane anchorPane) {
        this.stage = stage;

        isPause = true;

        pauseLabel = new Label();
        player = new Playable(scene);
        bot = new com.app.fxtestgame.secondVersion.entities.Bot();
        ball = new com.app.fxtestgame.secondVersion.entities.Ball(player, bot);

        initComponents();

        anchorPane.getChildren().addAll(player, ball, bot, pauseLabel);

        render = new Render(bot, ball, player);
        render.setPauseControl(anchorPane);
    }

    private void initComponents() {
        initPauseLabel();
        bot.setBall(ball);
        bot.setVisible(!isPause);
        ball.setVisible(!isPause);
        player.setVisible(!isPause);
    }

    private void initPauseLabel() {
        double height = 60;
        double width = height*2.5;

        pauseLabel.setVisible(isPause);

        pauseLabel.setText("""
                PAUSE!
                Move: Up, Down, W or S
                Pause: Enter or Space""");
        pauseLabel.setStyle(StylesRepo.getLabelStyle(width, height));

        pauseLabel.setLayoutX(PongGame.WIDTH/2 - width/2);
        pauseLabel.setLayoutY(PongGame.HEIGHT/2 - height);
    }

    public void start() {
        stage.show();
        render.renderer();
    }
}
