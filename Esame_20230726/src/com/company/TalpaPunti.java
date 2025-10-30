package com.company;

import javafx.scene.paint.Color;

public class TalpaPunti extends Talpa {

    protected int points;

    public TalpaPunti(Main app, boolean slow) {
        super(app, Color.ORANGE);
        if (slow) {
            this.points = 50;
            this.speed = 0.2;
            this.color = Color.CYAN;
        } else {
            this.points = 100;
            this.speed = 0.5;
        }
    }

    @Override
    public void hit() {
        this.app.colpita(points);
    }
}
