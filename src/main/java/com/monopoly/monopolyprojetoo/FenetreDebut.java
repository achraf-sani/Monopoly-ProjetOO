package com.monopoly.monopolyprojetoo;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.control.Button;

import java.util.ArrayList;
import java.util.Stack;

public class FenetreDebut {
    public PartieMonopoly partieM;
    public Stage stage;
    public Button b_Start;
    public ImageView i_Plateau;
    public AnchorPane root;


    public FenetreDebut(PartieMonopoly partieM) {
        this.partieM = partieM;


        this.stage = new Stage();
        this.stage.setTitle("Monopoly - Projet OO --- SANI Achraf & Ahmed DAOUDI");
        this.stage.initOwner(partieM.getStage());
        this.stage.initModality(Modality.APPLICATION_MODAL);
        root = new AnchorPane();
        // Layer 1 - Image
        i_Plateau = new ImageView("file:src/main/java/Jeu/Images/plateauDebut.jpg");
        root.getChildren().add(i_Plateau);
        Label label = new Label(" Monopoly - Projet OO");
        label.setAlignment(Pos.CENTER);
        label.setFont(new Font("Arial", 24));
        root.getChildren().add(label);

        Label labelS = new Label("Achraf SANI");
        labelS.setAlignment(Pos.CENTER);
        labelS.setFont(new Font("Arial", 18));
        root.getChildren().add(labelS);

        Label labelD = new Label("Ahmed DAOUDI");
        labelD.setAlignment(Pos.CENTER);
        labelD.setFont(new Font("Arial", 18));
        root.getChildren().add(labelD);

        // Button
        b_Start = new Button(" Commencer le jeu ");
        b_Start.setOnAction(new EvtValider());
        b_Start.setDefaultButton(true);
        b_Start.setOnAction(new EvtValider());
        b_Start.setPrefWidth(200);
        root.getChildren().add(b_Start);

        // Pos. button
        AnchorPane.setBottomAnchor(b_Start, 110.0);
        AnchorPane.setLeftAnchor(b_Start, 225.0);

        // Pos. image
        //AnchorPane.setTopAnchor(i_Plateau, 10.0);
        //AnchorPane.setBottomAnchor(i_Plateau, 10.0);
        //AnchorPane.setRightAnchor(i_Plateau, 10.0);
        //AnchorPane.setLeftAnchor(i_Plateau, 10.0);

        // Pos. label
        AnchorPane.setRightAnchor(label, 210.0);
        AnchorPane.setTopAnchor(label, 300.0);

        AnchorPane.setRightAnchor(labelS, 210.0);
        AnchorPane.setTopAnchor(labelS, 340.0);
        AnchorPane.setRightAnchor(labelD, 210.0);
        AnchorPane.setTopAnchor(labelD, 365.0);

        Scene scene = new Scene(root);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public Stage getStage() {
        return this.stage;
    }

    private class EvtValider implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent event) {
            ArrayList<String> listeJoueurs = new ArrayList<String>();
            listeJoueurs.add("Achraf");
            listeJoueurs.add("Ahmed");
            if(listeJoueurs.size()>=2) {
                partieM.setPartie(listeJoueurs.size(), listeJoueurs);
                partieM.getStage().show();
                stage.close();
            }
            event.consume();
        }
    }
}
