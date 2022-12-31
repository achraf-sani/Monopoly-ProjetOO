package com.monopoly.monopolyprojetoo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.util.Random;

public class Dés {

    Random random = new Random();

    @FXML
    private ImageView dice1Image;
    @FXML
    private ImageView dice2Image;
    @FXML
    private Button rollButton;

    private int dé1;
    private int dé2;

    public int getDé1() { return dé1; }
    public int getDé2() { return dé2; }

    public int getDés(){
        return (this.dé1 + this.dé2);
    }
    public int lancerDes() {
        this.dé1 = 1+this.random.nextInt(6);
        this.dé2 = 1+this.random.nextInt(6);

        return getDés();
    }

    @FXML
    void roll(ActionEvent event) {

        rollButton.setDisable(true);

        Thread thread = new Thread(){
            public void run(){
                System.out.println("Thread Running");
                try {
                    for (int i = 0; i < 15; i++) {
                        dé1 = (random.nextInt(6)+1);
                        dé2 = (random.nextInt(6)+1);
                        File file1 = new File("src/main/resources/com/monopoly/monopolyprojetoo/dice/dice" + dé1 + ".png");
                        File file2 = new File("src/main/resources/com/monopoly/monopolyprojetoo/dice/dice" + dé2 + ".png");
                        dice1Image.setImage(new Image(file1.toURI().toString()));
                        dice2Image.setImage(new Image(file2.toURI().toString()));
                        Thread.sleep(50);
                    }
                    rollButton.setDisable(false);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        thread.start();
    }
}