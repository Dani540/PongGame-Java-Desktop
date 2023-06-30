package com.app.fxtestgame.game.controller;

import com.app.fxtestgame.game.components.Component;
import com.app.fxtestgame.game.components.elements.Pause;
import com.app.fxtestgame.game.components.elements.PointerTable;
import com.app.fxtestgame.game.components.entities.Playable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.util.List;

public class GameController {
    private final Stage stage;
    private final Render render;
    private final com.app.fxtestgame.game.components.entities.Ball ball;
    private final com.app.fxtestgame.game.components.entities.Bot bot;
    private final Playable player;
    private final Pause pause;
    private final PointerTable pointerTable;
    private List<Component> components;

    public GameController(Stage stage, Scene scene, AnchorPane anchorPane) {
        this.stage = stage;

        pause = new Pause();
        pointerTable = new PointerTable(pause);
        player = new Playable(scene);
        bot = new com.app.fxtestgame.game.components.entities.Bot();
        ball = new com.app.fxtestgame.game.components.entities.Ball(pointerTable, player, bot);

        initComponents();

        anchorPane.getChildren().addAll( components.stream().map(n -> (Label)n).toList()  );

        render = new Render(pause, pointerTable, player, bot, ball);
        render.setPauseControl(anchorPane);
    }

    private void initComponents() {
        bot.setBall(ball);
        bot.setVisible(!pause.getPause());
        ball.setVisible(!pause.getPause());
        player.setVisible(!pause.getPause());

        components = List.of(bot, ball, player, pause, pointerTable);
    }

    public void start() {
        stage.show();
        render.renderer();
    }
}
