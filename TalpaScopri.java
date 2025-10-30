package com.company;

import javafx.scene.paint.Color;

public class TalpaScopri extends Talpa {

    public TalpaScopri(Main app) {
        super(app, Color.YELLOW);
        this.speed = 0.7;
    }

    @Override
    public void hit() {
        this.app.enablePowerUp();
    }
}
