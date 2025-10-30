package com.company;

import javafx.scene.paint.Color;

public class TalpaPerdi extends Talpa {

    public static final double SPEED = 0.1;

    public TalpaPerdi(Main app) {
        super(app, Color.RED);
        this.speed = SPEED;
    }

    public void hit() {
        this.app.setGameover();
    }
}
