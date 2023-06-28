/**
 * Esta clase es la clase controladora del juego.
 */

package com.app.fxtestgame.pong.control;

import com.app.fxtestgame.pong.PongGame;
import com.app.fxtestgame.pong.entities.Ball;
import com.app.fxtestgame.pong.entities.Bot;
import com.app.fxtestgame.pong.entities.Player;
import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;

public class GameController {
    public static final int[] points = new int[2];  // Son los puntos de cada jugador, haré una mejor implementacion de esto a futuro.
    private final AnchorPane anchorPane; // Panel principal
    private final Player playerOne; // Jugador 1, tambien hare un modo de 2 jugadores, cuando cree el menu principal.
    private final Bot bot; // Bot para jugar
    private final Ball ball; // Pelota en pantalla
    private final Label pauseLabel; // Etiqueta de pausa (visual)
    private final Label pointsLabel; // Etiqueta de puntos (visual)
    private boolean isPause; // Booleano controlador de la pausa del juego

    /**
     * El constructor define todos los objetos antes mencionados, establece la pausa en verdadero,
     * e inicializa todos los componentes del juego.
     * @param anchorPane Es el panel principal, se asigna a la variable de clase que controla este.
     */
    public GameController(AnchorPane anchorPane) {
        this.anchorPane = anchorPane;

        isPause = true;

        pauseLabel      = new Label();
        pointsLabel     = new Label(points[0] + "   |   " + points[1]);
        playerOne       = new Player(anchorPane);
        bot             = new Bot();
        ball            = new Ball();

        initComponents();
    }

    /**
     * Primero se inicializan los Labels (Etiquetas visuales), luego los objetos en pantalla se
     * establecen como invisibles al estar la pausa activada, y se añaden todos los componentes
     * al panel principal.
     */
    private void initComponents() {

        initPauseLabel();
        initPointsLabel();

        playerOne.setVisible(!isPause);
        ball.setVisible(!isPause);
        bot.setVisible(!isPause);

        anchorPane.getChildren().add(pauseLabel);
        anchorPane.getChildren().add(pointsLabel);
        anchorPane.getChildren().add(bot);
        anchorPane.getChildren().add(playerOne);
        anchorPane.getChildren().add(ball);

    }

    /**
     * Para inicializar la etiqueta de puntos, asigno su alto y ancho (en funcion de su alto),
     * su visibilidad depende de la pausa, y su estilo se asigna mediante CSS con notacion de JavaFX.
     * Su ubicacion se situa en el centro superior de la pantalla
     */
    private void initPointsLabel() {
        double height = 20;
        double width = height*2.5;

        pointsLabel.setVisible(!isPause);

        pointsLabel.setStyle(
                "-fx-background-color: white;" +
                        "-fx-border-color: black;" +
                        "-fx-border-width: 2;" +
                        "-fx-font-family: 'Lato Black';" +
                        "-fx-font-size: 12;" +
                        "-fx-text-alignment: center;" +
                        "-fx-alignment: center;" +
                        "-fx-max-width: " + width + ";" +
                        "-fx-min-width: " + width + ";" +
                        "-fx-pref-width: " + width + ";" +
                        "-fx-max-height: " + height + ";" +
                        "-fx-min-height: " + height + ";" +
                        "-fx-pref-height: " + height + ";"
        );

        pointsLabel.setLayoutX(PongGame.WIDTH/2 - width/2);
        pointsLabel.setLayoutY(20);
    }

    /**
     * Para inicializar la etiqueta de pausa, asigno su alto y ancho (en funcion de su alto),
     * su visibilidad depende de la pausa propia (booleano), y su estilo se asigna mediante
     * CSS con notacion de JavaFX. Su ubicacion se situa en el centro de la pantalla
     */
    private void initPauseLabel() {

        double height = 60;
        double width = height*2.5;

        pauseLabel.setText("""
                PAUSE!
                Move: Up, Down, W or S
                Pause: Enter or Space""");

        pauseLabel.setVisible(isPause);

        pauseLabel.setStyle(
                "-fx-background-color: white;" +
                "-fx-border-color: black;" +
                "-fx-border-width: 2;" +
                "-fx-font-family: 'Lato Black';" +
                "-fx-font-size: 12;" +
                "-fx-text-alignment: center;" +
                "-fx-alignment: center;" +
                "-fx-max-width: " + width + ";" +
                "-fx-min-width: " + width + ";" +
                "-fx-pref-width: " + width + ";" +
                "-fx-max-height: " + height + ";" +
                "-fx-min-height: " + height + ";" +
                "-fx-pref-height: " + height + ";"
        );

        pauseLabel.setLayoutX(PongGame.WIDTH/2 - width/2);
        pauseLabel.setLayoutY(PongGame.HEIGHT/2 - height);
    }

    /**
     * Este metodo contiene el bucle principal del juego, en el renderizo todos los elementos
     * necesarios para el correcto funcionamiento (pausa, jugadores, bot, pelota), en funcion
     * de la pausa.
     */
    public void start() {
        AnimationTimer animationTimer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                if (!isPause) {
                    playerOne.update();
                    ball.render(playerOne, bot, pointsLabel);
                    bot.render(ball);
                }
            }
        };
        animationTimer.start();
    }

    /**
     * Este metodo contiene la logica de la pausa. Cuando se presionan los botones para esta,
     * se invierte el booleano de la pausa y se actualiza la visibilidad de todos los
     * componentes asociados a esta.
     * @param scene Es la escena desde la que se debe detectar el pulso de tecla.
     */
    public void setPauseControl(Scene scene) {
        scene.setOnKeyPressed(event ->{
            if (event.getCode().equals(KeyCode.ENTER) || event.getCode().equals(KeyCode.SPACE)){
                isPause = !isPause;
                pauseLabel.setVisible(isPause);

                pointsLabel.setVisible(!isPause);

                playerOne.setVisible(!isPause);
                ball.setVisible(!isPause);
                bot.setVisible(!isPause);
            }
        });
    }

    /**
     * Este metodo asigna el movimiento por teclado y mouse al jugador en pantalla.
     */
    public void setPlayerMovement() {
        playerOne.setMovement(anchorPane);
    }

    /**
     * Crea una escena con el panel principal.
     * @return Devuelve esta escena.
     */
    public Scene getScene() {
        return new Scene(anchorPane);
    }
}
