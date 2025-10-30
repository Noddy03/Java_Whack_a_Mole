package com.company;

import javafx.scene.paint.Color;

public class TalpaColpi extends TalpaPunti {

    public TalpaColpi(Main app) {
        super(app, false);
        this.color = Color.BLUEVIOLET;
    }

    @Override
    public void hit() {
        super.hit();
        this.app.colpi += 2;
    }
}
