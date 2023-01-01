package com.monopoly.monopolyprojetoo;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class FenetreAcheterTerrain {
    private PartieMonopoly partieM;
    private Stage stage;
    private HBox root;
    private Label l_Texte;
    private Button b_Oui;
    private Button b_Non;

    public FenetreAcheterTerrain(PartieMonopoly partieM) {

        this.partieM = partieM;

        this.stage = new Stage();
        this.stage.setTitle("Acheter ce terrain ?");
        this.stage.initOwner(partieM.getStage());
        this.stage.initModality(Modality.APPLICATION_MODAL);

        stage.setOnHiding(new EvtQuitter());
    }

    private void initRoot() {
        root.setPadding(new Insets(10,10,10,10));
        root.setSpacing(10);
        root.setStyle("-fx-background-color: #CDE6D0; ");

        Image i_terrain;

        switch(partieM.getPartie().getPM().getCaseActive().getNom()) {
            case "Gare Montparnasse": i_terrain = new Image("file:src/main/java/Jeu/Images/gare.jpg"); break;
            case "Gare de Lyon": i_terrain = new Image("file:src/main/java/Jeu/Images/gare.jpg"); break;
            case "Gare du Nord": i_terrain = new Image("file:src/main/java/Jeu/Images/gare.jpg"); break;
            case "Gare Saint-Lazare": i_terrain = new Image("file:src/main/java/Jeu/Images/gare.jpg"); break;
            case "Compagnie des eaux": i_terrain = new Image("file:src/main/java/Jeu/Images/eau.jpg"); break;
            case "Compagnie d'électricité": i_terrain = new Image("file:src/main/java/Jeu/Images/gare.jpg"); break;
            default: {
                String couleur = partieM.getPartie().getPM().getCaseActive().getCouleur();
                //i_terrain = new Image("images/m_"+couleur+".jpg");
                i_terrain = new Image("file:src/main/java/Jeu/Images/m_bleu.jpg");
            }; break;
        }

        ImageView iv_terrain = new ImageView(i_terrain);
        root.getChildren().add(iv_terrain);

        VBox aside = new VBox();
        aside.setSpacing(15);
        root.getChildren().add(aside);

        l_Texte = new Label("Voulez vous acheter " + partieM.getPartie().getPM().getCaseActive().getNom() + " pour " + partieM.getPartie().getPM().getCaseActive().getPrix() + "$ ?");
        aside.getChildren().add(l_Texte);

        HBox buttons_horiz = new HBox();
        buttons_horiz.setSpacing(10);

        b_Oui = new Button("Oui");
        b_Oui.setOnAction(new EvtOui());
        buttons_horiz.getChildren().add(b_Oui);

        b_Non = new Button("Non");
        b_Non.setOnAction(new EvtNon());
        buttons_horiz.getChildren().add(b_Non);

        aside.getChildren().add(buttons_horiz);

        root.addEventHandler(KeyEvent.KEY_PRESSED, ev -> {
            if (ev.getCode() == KeyCode.ENTER) {
                if(b_Oui.isFocused())
                    b_Oui.fire();
                else
                    b_Non.fire();
                ev.consume();
            }
        });
    }

    public void afficherFenetre() {
        root = new HBox();
        initRoot();

        Scene scene = new Scene(root,450,130);
        stage.setScene(scene);
        stage.show();
    }

    public Stage getStage() {
        return stage;
    }

    private class EvtOui implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            partieM.getPartie().getPM().getCaseActive().setReponseQuestion(true);
            stage.close();
            event.consume();
        }
    }
    private class EvtNon implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            stage.close();
            event.consume();
        }
    }
    private class EvtQuitter implements EventHandler<WindowEvent> {
        @Override
        public void handle(WindowEvent event) {
            partieM.getPartie().reprendrePartie();
            event.consume();
        }
    }
}

