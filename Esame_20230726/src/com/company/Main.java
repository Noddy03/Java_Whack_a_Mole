package com.company;


import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Main extends Application {

    private static final int NUM_COL = 4;
    private static final int NUM_ROW = 4;
    private static final int SPACING = 10;
    private static final int COLPI_INIZIALI = 15;
    private static final int NUM_TALPE_PUNTI = 5;
    private static final int NUM_TALPE_OTHER = 2;

    private List<Talpa> talpe = new ArrayList<>();
    private Text txtPunteggio = new Text();

    Button btnShowAll = new Button("Scopri tutto!");

    private Random rand = new Random();
    private int punteggio;
    int colpi = COLPI_INIZIALI;
    private Text txtColpi = new Text();
    private boolean gameover;

    private Talpa currentTalpa;
    private boolean powerUp = false;

    public static void main(String[] args) {
        launch(args);
    }


    @Override
    public void start(Stage primarystage) throws Exception {

        for (int i = 0; i < NUM_TALPE_PUNTI; i++) {
            talpe.add(new TalpaPunti(this, true));
            talpe.add(new TalpaPunti(this, false));
        }

        for (int i = 0; i < NUM_TALPE_OTHER; i++) {
            talpe.add(new TalpaColpi(this));
            talpe.add(new TalpaPerdi(this));
            talpe.add(new TalpaScopri(this));
        }

        Collections.shuffle(talpe);

        GridPane grid = new GridPane();
        for (int col = 0; col < NUM_COL; col++)
            for (int row = 0; row < NUM_ROW; row++) {
                grid.add(talpe.get((col + row * NUM_ROW)), col, row);
            }

        this.colpi += 1; // questo colpo è usato nella prossima riga per inizializzare il gioco

        newTurn();
        setPunteggio(0);

        btnShowAll.setDisable(true);


        HBox top = new HBox(txtColpi, txtPunteggio);
        top.setPadding(new Insets(SPACING));
        top.setSpacing(SPACING);
        top.setAlignment(Pos.CENTER);

        HBox bottom = new HBox(btnShowAll);
        bottom.setPadding(new Insets(SPACING));
        bottom.setAlignment(Pos.CENTER);

        VBox root = new VBox(top, grid, bottom);

        Scene scene = new Scene(root);

        btnShowAll.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(!gameover) {
                    powerUp = true;
                    scopriTutto();
                    btnShowAll.setDisable(true);
                }
            }
        });

        scene.setOnMouseMoved(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(powerUp && ! gameover) {
                    powerUp = false;

                    for (Talpa t : talpe) {
                        if (t != currentTalpa)
                            t.setNascosta(true);
                    }
                }
            }
        });


        primarystage.setTitle("Acchiappa la talpa!");
        primarystage.setScene(scene);
        primarystage.show();
    }

    public void newTurn() {

        if (--colpi == 0) {
            setGameover();
        } else {
            for (Talpa t : talpe)
                t.setNascosta(true);
            currentTalpa = talpe.get(rand.nextInt(talpe.size()));
            currentTalpa.setNascosta(false);
            txtColpi.setText("Colpi: " + colpi);
        }
    }

    void setGameover() {
        gameover = true;
        txtColpi.setText("GAME OVER!");
        scopriTutto();
        for (Talpa t : talpe) {
            t.disabled = true;
        }
    }

    void scopriTutto() {
        for (Talpa t : talpe) {
            t.setNascosta(false);
        }
    }

    public void colpita(int extraPoint) {
        setPunteggio(punteggio + extraPoint);
        newTurn();
    }

    private void setPunteggio(int i) {
        punteggio = i;
        txtPunteggio.setText("Punteggio: " + punteggio);
    }

    public void enablePowerUp() {
        btnShowAll.setDisable(false);
        newTurn();
    }
}
