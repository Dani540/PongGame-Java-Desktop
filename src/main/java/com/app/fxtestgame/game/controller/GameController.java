/**
 * Clase controladora del juego, se usa para enlazar los
 * componentes con el renderizado e iniciar este mismo.
 */

package com.app.fxtestgame.game.controller;

import com.app.fxtestgame.game.components.Component;
import com.app.fxtestgame.game.components.elements.Pause;
import com.app.fxtestgame.game.components.elements.PointerTable;
import com.app.fxtestgame.game.components.entities.Ball;
import com.app.fxtestgame.game.components.entities.Bot;
import com.app.fxtestgame.game.components.entities.Playable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.util.List;

public class GameController {
    private final Stage stage;  // Es la ventana principal
    private final Render render;    // Es el modelo renderizador del juego.
    private final Ball ball;
    private final Bot bot;
    private final Playable player;
    private final Pause pause;
    private final PointerTable pointerTable;
    private List<Component> components; // Lista de componentes, planee usarla para almacenar
                                        // todos los componentes y añadirlos de forma mas modular
                                        // a los sitios donde se requiriese, pero de momento no esta bien implementado.

    /**
     * El controlador asigna los componentes principales, e instancia los componentes del juego y los inicializa.
     * @param stage Es la ventana principal.
     * @param scene Es la escena principal.
     * @param anchorPane Es el panel principal.
     */
    public GameController(Stage stage, Scene scene, AnchorPane anchorPane) {
        this.stage = stage;

        pause        = new Pause();
        pointerTable = new PointerTable(pause);
        player       = new Playable(scene);
        bot          = new Bot();
        ball         = new Ball(pointerTable, player, bot);

        initComponents();

        anchorPane.getChildren().addAll(components.stream().map(n -> (Label) n).toList()); // Se añaden todos los componentes al panel principal.

        render = new Render(pause, pointerTable, player, bot, ball);
        render.setPauseControl(anchorPane); // El control de las pausas se debe hacer luego de haber instanciado el renderizador.
    }

    /**
     * Inicializa los componentes del juego y los añade a la lista de componentes.
     */
    private void initComponents() {
        bot.setBall(ball);
        bot.setVisible(!pause.getPause());
        ball.setVisible(!pause.getPause());
        player.setVisible(!pause.getPause());

        components = List.of(bot, ball, player, pause, pointerTable);
    }

    /**
     * Muesta la ventana principal y comienza a renderizar el juego (bucle principal).
     */
    public void start() {
        stage.show();
        render.renderer();
    }
}
