/**
 * Esta clase se encarga de contener el juego e iniciarlo.
 *
 * @author dani-tk
 */

package com.app.fxtestgame.game;

import com.app.fxtestgame.game.controller.GameController;
import com.app.fxtestgame.game.repo.StylesRepo;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class PongGame {
    public static final double HEIGHT = 400, WIDTH = 600;   // Dimensiones de la pantalla
    private final Stage stage;  // Ventana principal, se usa para contener la escena.
    private Scene scene;    // Escena principal, se usa para contener el panel.
    private AnchorPane anchorPane; // Panel principal, aqui se ubican todos los componentes del juego.
    private GameController gameController; // Controlador del juego, contiene toda la logica y el renderizado.

    /**
     * El controlador asigna solo la ventana principal.
     *
     * @param stage Es la ventana principal.
     */
    public PongGame(Stage stage) {
        this.stage = stage;
    }

    /**
     * Inicializa los componentes pricipales e inicia el juego.
     */
    public void startGame() {

        initPane();
        initScene();
        initStage();

        gameController = new GameController(stage, scene, anchorPane);

        gameController.start();
    }

    /**
     * Instancia el panel principal y le asigna su estilo. (Todos lo estilos estan contenidos en la clase de repositorio de estilos).
     */
    private void initPane() {
        anchorPane = new AnchorPane();
        anchorPane.setStyle(StylesRepo.getPaneStyle(WIDTH, HEIGHT));
    }

    /**
     * Instancia la escena principal asignandole el panel principal.
     */
    private void initScene() {
        scene = new Scene(anchorPane);
    }

    /**
     * Configura la ventana principal.
     */
    private void initStage() {
        stage.setHeight(HEIGHT);
        stage.setWidth(WIDTH);
        stage.setResizable(false);  // No se puede redimenzionar
        stage.setTitle("Pong by dani-tk");
        stage.setScene(scene);  // Se asigna la escena principal
    }

}
