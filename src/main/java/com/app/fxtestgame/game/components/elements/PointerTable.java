package com.app.fxtestgame.game.components.elements;

import com.app.fxtestgame.game.PongGame;
import com.app.fxtestgame.game.enums.Side;
import com.app.fxtestgame.game.repo.StylesRepo;
import javafx.scene.control.Label;


public class PointerTable extends Label implements Element {

    private final double HEIGHT, WIDTH;
    private final int [] points;
    private final Pause pause;
    private String statement;

    public PointerTable(Pause pause){
        this.pause = pause;
        HEIGHT = 30;
        WIDTH = HEIGHT * 2.5;
        points = new int[2];
        initStyle();
        refresh();
        this.setLayoutX(PongGame.WIDTH/2 - WIDTH /2);
        this.setLayoutY(HEIGHT);
    }
    private void initStyle() {
        this.setStyle(StylesRepo.getLabelStyle(WIDTH, HEIGHT));
    }
    @Override
    public void refresh() {
        this.setVisible(!pause.getPause());
        this.setText( statement = points[0] + "    |   " + points[1]);
    }
    public void addPoint(Side side){
        if (side.equals(Side.LEFT)) points[0]++;
        else points[1]++;
        refresh();
    }
    public String getStatement() {
        return statement;
    }
}
