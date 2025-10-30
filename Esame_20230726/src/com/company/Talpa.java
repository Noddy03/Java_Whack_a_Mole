package com.company;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public abstract class Talpa extends StackPane {

    public static final double RADIUS = 30.0;
    public static final double SIDE = 75;
    public static final Color HIDE_COLOR = Color.DARKGRAY;

    final Main app;
    Circle circle = new Circle(RADIUS);
    Color color;

    boolean nascosta;
    boolean disabled = false;
    protected double speed;

    public Talpa(Main app, Color color) {
        this.color = color;
        this.app = app;
        circle.setFill(HIDE_COLOR);

        this.getChildren().addAll(new Rectangle(SIDE,SIDE,Color.GREEN), circle);

        setNascosta(true);

        this.circle.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (!disabled && !nascosta) {
                    if (Math.random() < speed) {
                        app.newTurn();
                    }
                }
            }
        });

        this.circle.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (!disabled && !nascosta) {
                    setNascosta(true);
                    hit();
                }
            }
        });
    }

    public abstract void hit();

    public void setNascosta(boolean nascosta) {
        this.nascosta = nascosta;
        if (nascosta) {
            circle.setFill(HIDE_COLOR);
        } else {
            circle.setFill(color);
        }
    }
}
