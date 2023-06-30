package com.app.fxtestgame.secondVersion;

import com.app.fxtestgame.secondVersion.controller.GameController;
import com.app.fxtestgame.secondVersion.repo.StylesRepo;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Game {
    public static final double HEIGHT=400, WIDTH=600;
    private final Stage stage;
    private GameController gameController;
    private AnchorPane anchorPane;
    private Scene scene;
    public Game(Stage stage) {
        this.stage = stage;
    }

    public void startGame() {

        initPane();
        initScene();
        initStage();

        gameController = new GameController(stage, scene, anchorPane);

        gameController.start();
    }

    private void initPane() {
        anchorPane = new AnchorPane();
        anchorPane.setStyle(StylesRepo.paneStyle(WIDTH, HEIGHT));
    }

    private void initScene() {
        scene = new Scene(anchorPane);
    }

    private void initStage() {
        stage.setHeight(HEIGHT);
        stage.setWidth(WIDTH);
        stage.setResizable(false);
        stage.setTitle("Pong by dani-tk");
        stage.setScene(scene);
    }

}
