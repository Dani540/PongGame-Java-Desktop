/**
 * Esta clase es la clase contenedora del juego.
 *
 * @author dani-tk
 */

package com.app.fxtestgame.pong;

import com.app.fxtestgame.pong.control.GameController;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class PongGame {
    private final Stage stage;
    public static final double HEIGHT = 400; // Alto de la pantalla
    public static final double WIDTH = 600; // Ancho de la pantalla
    private final AnchorPane anchorPane; // Panel contenedor de todos los elementos
    private final GameController gameController; // Objeto controlador de la logica y visual del juego

    /**
     * El constructor define el stage(ventana principal) segun los valores antes mencionados
     * Establece que no pueda redimensionarse, establece el titulo de la ventana, instancia un
     * AnchorPane para panel principal, inicializa este panel, instancia el controlador del juego
     * con la dependencia del panel, y establece la escena principal obteniendola del controlador del juego.
     * @param stage Es la ventana principal
     */

    public PongGame(Stage stage) {


        stage.setHeight(HEIGHT);    // Alto de la ventana
        stage.setWidth(WIDTH);  // Ancho de la ventana
        stage.setResizable(false); // No se puede redimenzionar
        stage.setTitle("Pong By dani-tk"); // Titulo de la ventana

        this.stage = stage; // Dependencia de ventana principal.

        anchorPane = new AnchorPane(); // Panel principal
        initPane();

        gameController = new GameController(anchorPane);

        stage.setScene( gameController.getScene() ); // Se establece la scena obteniendose del controlador del juego,
                                                     // esto luego fue muy contraproducente, asi que probablemente lo
                                                     // lo cambie en el futuro junto con la mejora de la IA del bot.

    }

    /**
     * Inicializa el panel principal, asignandole un color de fondo y sus respectivas medidas.
     */
    private void initPane() {
        anchorPane.setStyle(
                "-fx-background-color: lightcyan;" +
                "-fx-max-height: " + HEIGHT + ";" +
                "-fx-min-height: " + HEIGHT + ";" +
                "-fx-pref-height: " + HEIGHT + ";" +
                "-fx-max-width: " + WIDTH + ";" +
                "-fx-min-widtht: " + WIDTH + ";" +
                "-fx-pref-width: " + WIDTH + ";"
        );
    }

    /**
     *  Establece el control de la pause y del movimiento del jugador
     *  (al ser entradas de teclado, tienen que ser detectadas una vez la "scene" o escena principal de
     *  nuestra ventana (contenedora del panel), sea creada y establecida.).
     *  Muestra la pantalla principal e inicia el juego.
     */
    public void startGame(){
        stage.show();
        gameController.setPauseControl(stage.getScene()); // Control de las pausas por teclado
        gameController.setPlayerMovement(); // Control del movimiento del personaje por teclado y mouse.
        gameController.start();
    }
}
