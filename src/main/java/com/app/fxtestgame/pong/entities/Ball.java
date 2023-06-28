package com.app.fxtestgame.pong.entities;

import com.app.fxtestgame.pong.PongGame;
import com.app.fxtestgame.pong.control.GameController;
import javafx.scene.control.Label;

import java.util.Random;

public class Ball extends Label {
    private final int SIZE; // Tamaño de la pelota.
    private boolean hasContactX; // Booleano controlador de los contactos con otros elementos (Jugadores, Bots) en el eje horizontal.
    private double ballSpeed = 2; // Velocidad de movimiento.
    private double increment = 0.2; // Valor de incrementacion a la velocidad de movimiento por cada toque.
    private int directionX = 1; // Variable controladora de la direccion horizontal.
    private int directionY = 1; // Variable controladora de la direccion vertical.
    private double ballAngle = 25.00; // Angulo de la pelota.
    private final int outsideRange = 50; // Valor arbitrario para la deteccion de la salida de pantalla de la pelota.

    /**
     * El constructor establece el contacto, el tamaño y el estilo de la pelota.
     */
    public Ball() {
        hasContactX = false;
        SIZE = 15;
        initStyle();
    }

    /**
     * El estilo se añade con notacion de JavaFX en CSS.
     * Tambien se añade su posicion inicial (centro de la pantalla).
     */
    private void initStyle() {
        this.setStyle("-fx-background-color: white;" +
                "-fx-border-color:          black;" +
                "-fx-border-width:          2;" +
                "-fx-background-radius: " + SIZE + ";" +
                "-fx-border-radius:     " + SIZE + ";" +
                "-fx-max-height:        " + SIZE + ";" +
                "-fx-min-height:        " + SIZE + ";" +
                "-fx-pref-height:       " + SIZE + ";" +
                "-fx-max-width:         " + SIZE + ";" +
                "-fx-min-width:         " + SIZE + ";" +
                "-fx-pref-width:        " + SIZE + ";"
        );

        this.setLayoutX(PongGame.WIDTH/2 - (double) SIZE/2);
        this.setLayoutY(PongGame.HEIGHT/2 - (double) SIZE /2);
    }

    /**
     * Este metodo se ejecuta en el bucle principal del juego y sirve para renderizar
     * tanto la pelota como su logica de colisiones.
     * @param player Es el jugador en pantalla.
     * @param bot Es el bot en pantalla.
     * @param pointsLabel Es la etiqueta de puntos.
     */
    public void render(Player player, Bot bot, Label pointsLabel) {

        switchDirectionX(hasContactX);    // En caso de contacto con alguna entidad, cambia la direccion.

        obsCollision(player);   // Observa si hay alguna colision con el jugador
        obsCollision(bot);  // Observa si hay alguna colision con el bot.

        if (this.getLayoutY() <= 0 || this.getLayoutY() >= PongGame.HEIGHT - SIZE*3.5 ) // Por si se choca arriba o abajo.
            directionY = -directionY;   // Se invierte la direccion vertical para generar el efecto rebote.

        boolean leftMax  = this.getLayoutX() <= -outsideRange;  // Si se sale por la izquierda de la pantalla.
        boolean rightMax = this.getLayoutX() >= (PongGame.WIDTH - this.getWidth()) + outsideRange; // Si se sale por la derecha.

        // Control de puntos.
        if (leftMax)             GameController.points[0]++;
        else if (rightMax)       GameController.points[1]++;

        // Actualiza la etiqueta de puntuacion y resetea la pelota (posicion y velocidad).
        if (leftMax || rightMax){
            pointsLabel.setText(GameController.points[0] + "   |   " + GameController.points[1]);
            resetBall();
        }

        ballMoveX();    // Mueve la pelota en el eje horizontal
        ballMoveY();    // Mueve la pelota en el eje vertical.
    }

    /**
     * Este metodo reestablece los parametros de la pelota luego de algun evento (en este caso solo salor de la pantalla).
     */
    private void resetBall() {
        this.setLayoutX(PongGame.WIDTH/2 - (double) SIZE/2);
        this.setLayoutY(PongGame.HEIGHT/2 - (double) SIZE /2);
        ballSpeed = 2;
        increment = 0.3;
    }

    /**
     * Invierte la direccion horizontal en base al contacto con alguna entidad.
     * @param hasContact Si tuvo o no contacto/colision.
     */
    private void switchDirectionX(boolean hasContact) {
        if (hasContact) directionX = -1;
        else directionX = 1;
    }

    /**
     * Este metodo mueve la pelota horizontalmente en funcion del angulo y la velocidad.
     */
    private void ballMoveX() {
        double speedX = Math.cos(Math.toRadians(ballAngle)) * ballSpeed;
        this.setLayoutX(this.getLayoutX() + speedX * directionX);
    }

    /**
     * Este metodo mueve la pelota verticalmente en funcion del angulo y la velocidad.
     */
    private void ballMoveY() {
        double speedY = Math.sin(Math.toRadians(ballAngle)) * ballSpeed;
        this.setLayoutY(this.getLayoutY() + speedY * directionY);
    }

    /**
     * Este metodo observa colisiones con entidades (jugador, bot).
     * Al tener contacto con alguna, invierte su angulo, direcciones,
     * contacto en el eje horizontal incrementa la velocidad.
     * @param entity Es la entidad a detectar.
     */
    private void obsCollision(Label entity){
        if (collision(entity)) {
            ballAngle  *= -1;
            directionY *= -1;
            directionX *= -1;
            ballSpeed += increment;
            increment += 0.01;
            hasContactX = !hasContactX;
        }
    }

    /**
     * Este metodo detecta colisiones con entidades.
     * @param entity Es la entidad a detectar.
     * @return Devuelve si hubo o no contacto.
     */
    public boolean collision(Label entity) {

        int[] playerRangeX = setEntityDispositionOnScreen(entity);
        int[] playerRangeY = {(int) entity.getLayoutY(), (int) (entity.getLayoutY() + entity.getHeight())};

        return collisionRange(this.getLayoutX() ,playerRangeX[0], playerRangeX[1]) && collisionRange(this.getLayoutY(),playerRangeY[0], playerRangeY[1]);
    }

    /**
     * Este metodo calcula el rango de colision de la entidad en funcion de su posicion en pantalla.
     * (Esta varia dependiendo de a que lado este).
     * @param entity Es la entidad a calcular.
     * @return Devuelve un arreglo de enteros con 2 posiciones, las cuales son el rango.
     */
    private int[] setEntityDispositionOnScreen(Label entity) {
        if (entity.getLayoutX() < PongGame.WIDTH / 2)
            return new int[]{(int) entity.getLayoutX(), (int) (entity.getLayoutX() + entity.getWidth())};
        else
            return  new int[]{(int) (entity.getLayoutX() - entity.getWidth()), (int) entity.getLayoutX()};
    }

    /**
     * Este metodo sirve para calcular rangos.
     * @param obs Es el numero a observar.
     * @param min Es el numero minimo en el rango.
     * @param max Es el numero maximo en el rango.
     * @return Devuelve si el numero a observar entra en el rango.
     */
    private boolean collisionRange(double obs, int min, double max) {
        return obs > min && obs <= max;
    }

    /**
     * @return Devuelve la direccion horizontal (lo usa el bot).
     */
    public int getDirectionX() {
        return directionX;
    }
}
