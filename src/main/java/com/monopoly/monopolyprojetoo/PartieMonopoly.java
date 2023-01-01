package com.monopoly.monopolyprojetoo;

import Cases.Case_terrain;
import Jeu.Partie;
import abstractClasses.Case;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.File;
import java.util.ArrayList;
import java.util.Random;

import Jeu.JoueurMonopoly;
import Jeu.Partie;
import Jeu.PlateauMonopoly;
import javafx.util.Duration;


public class PartieMonopoly {

    Random random = new Random();

    @FXML
    private ImageView dice1Image;
    @FXML
    private ImageView dice2Image;


    public Stage stage;
    public StackPane root;

    private Label l_ParcGratuit = new Label("0�");
    private Label l_Message = new Label("");

    public ArrayList<Label> l_joueurs = new ArrayList<Label>();
    private ArrayList <Label> l_ListeTerrains = new ArrayList <Label>();
    public ArrayList<Circle> l_pions = new ArrayList<Circle>();

    private ArrayList<Label> l_Logs = new ArrayList<Label>();
    private ArrayList<Image> imageDes = new ArrayList<Image>();
    private ArrayList<ImageView> Des = new ArrayList<ImageView>();
    private Button tourSuivant = new Button("Tour suivant");
    public Random rand = new Random();
    public Color[] Couleurs = new Color[] {Color.RED, Color.BLUE};

    public FenetreDebut fd = new FenetreDebut(this);
    private FenetreAcheterTerrain fat = new FenetreAcheterTerrain(this);

    private Partie partie;

    public PartieMonopoly(Stage primaryStage){
        this.stage = primaryStage;

        root = new StackPane();
        //root.setOnMouseClicked(new EvtClicRoot());
        initRoot();

        Scene scene = new Scene(root, 655, 655);
        stage.setScene(scene);
        stage.setTitle("Monopoly - Projet OO");


        fd.getStage().show();
    }

    private void initRoot() {
        root.setStyle("-fx-background-image: url('file:src/main/java/Jeu/Images/plateau.png'); -fx-background-repeat: no-repeat");
        root.setAlignment(Pos.TOP_LEFT);

        for(int i=1; i<7; i++)
            imageDes.add(new Image("file:src/main/java/Jeu/dice/dice"+i+".png"));

        Des.add(new ImageView());
        Des.add(new ImageView());
        Des.get(0).setTranslateX(200);
        Des.get(0).setTranslateY(360);
        Des.get(1).setTranslateX(337);
        Des.get(1).setTranslateY(360);
        root.getChildren().add(Des.get(0));
        root.getChildren().add(Des.get(1));

        l_ParcGratuit.setTranslateX(3);
        l_ParcGratuit.setTranslateY(68);
        root.getChildren().add(l_ParcGratuit);

        l_Message.setFont(Font.font("Consolas", 14));
        l_Message.setTranslateX(95);
        l_Message.setTranslateY(480);
        l_Message.setMaxWidth(470);
        root.getChildren().add(l_Message);

        tourSuivant.setPrefWidth(100);
        tourSuivant.setTranslateX(277.5);
        tourSuivant.setTranslateY(533);
        tourSuivant.setOnAction(new EvtTourSuivant());
        tourSuivant.setDefaultButton(true);
        if(!partie.START)
            root.getChildren().add(tourSuivant);
    }

    public void deplacerPion(JoueurMonopoly joueur){

        double x, y;
        int pos = joueur.getPosition();
        TranslateTransition tt = new TranslateTransition(Duration.millis(500), getPionActif());

        if(joueur.getEstEnFaillite()) {
            x = 103;
            y = 538;
        }
        else if(pos == 0) {
            x = 604;
            y = 604;
        }
        else if(pos == 10) {
            if(joueur.getId() == 0 || joueur.getId() == 1){
                x = 16;
                y = 644;
            }
            else /* idJoueur == 2 ou 3*/ {
                x = 48;
                y = 628;
            }
        }
        else if(pos == 20) {
            x = 30;
            y = 34;
        }
        else if(pos == 30) {
            x = 604;
            y = 34;
        }
        else if(pos > 0 && pos < 10) {
            x = 537 - ((pos-1) * 54);
            y = 620;
        }
        else if(pos > 10 && pos < 20) {
            x = 30;
            y = 538 - ((pos-11) * 54);
        }
        else if(pos > 20 && pos < 30) {
            x = 104 + ((pos-21) * 54);
            y = 30;
        }
        else if(pos > 30 && pos < 40) {
            x = 612;
            y = 106 + ((pos-31) * 54);
        }
        else {
            x = -50;
            y = -50;
        }

        switch(joueur.getId()) {
            case 0: x-=8; y-=8; break;
            case 1: x+=8; y-=8; break;
            case 2: x-=8; y+=8; break;
            case 3: x+=8; y+=8; break;
            default: break;
        }

        tt.setToX(x);
        tt.setToY(y);
        tt.play();
    }

    public void setPartie(int nbJoueurs, ArrayList<String> nomsDesJoueurs) {

        partie = new Partie(nbJoueurs, nomsDesJoueurs, this);

        for(int i=0; i<nbJoueurs; i++) {
            Label l_nomJoueur = new Label(partie.getPM().getJoueur(i).getNom());
            l_nomJoueur.setTextFill(Couleurs[i]);
            l_nomJoueur.setTranslateX(95+i*120);
            l_nomJoueur.setTranslateY(100);
            root.getChildren().add(l_nomJoueur);

            l_joueurs.add(new Label(""+partie.getPM().getJoueur(i).getArgent()+"$"));
            l_joueurs.get(i).setTranslateX(95+i*120);
            l_joueurs.get(i).setTranslateY(120);
            l_joueurs.get(i).setFont(Font.font("Arial", 15));
            root.getChildren().add(l_joueurs.get(i));

            l_ListeTerrains.add(new Label("\n"));
            l_ListeTerrains.get(i).setTranslateX(95+i*120);
            l_ListeTerrains.get(i).setTranslateY(140);
            l_ListeTerrains.get(i).setMaxWidth(110);
            root.getChildren().add(l_ListeTerrains.get(i));

            l_pions.add(new Circle(9));
            l_pions.get(i).setFill(Couleurs[i]);
            if(i<2) {
                l_pions.get(i).setTranslateX(598 + i*15);
                l_pions.get(i).setTranslateY(605);
            }
            else {
                l_pions.get(i).setTranslateX(598 + (i-2)*15);
                l_pions.get(i).setTranslateY(620);
            }
            root.getChildren().add(l_pions.get(i));
        }

        refreshLabels(partie.getPM());
        partie.commencerPartie();
    }

    public Stage getStage() { return this.stage; }
    public StackPane getRoot() { return this.root; }
    public Circle getPionActif() { return l_pions.get(partie.getPM().getJoueurCourantID());}

    public void refreshLabels(PlateauMonopoly pm) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                // l_ParcGratuit.setText(""+pm.getCase(20).getPrix()+"$");

                for (int i=0; i<pm.getNbrJoueurs(); i++){
                    l_joueurs.get(i).setText(""+pm.getJoueur(i).getArgent()+"$.");
                    // String listeTerrains
                }
            }
        });
    }
    public void afficherMsg(String msg) {

        Platform.runLater(new Runnable() {
            @Override public void run() {

                l_Message.setTextFill(Couleurs[getPartie().getPM().getJoueurCourantID()]);
                l_Message.setText(msg);
            }
        });
    }
    public Partie getPartie() { return this.partie; }

    public void afficherDes(PlateauMonopoly pm) {
        Platform.runLater(new Runnable() {
            @Override public void run() {

                effacerDes();

                Des.get(0).setImage(imageDes.get(pm.dice.getDé1()-1));
                Des.get(1).setImage(imageDes.get(pm.dice.getDé2()-1));

            }
        });
    }
    public void effacerDes() {
        Des.get(0).setImage(null);
        Des.get(1).setImage(null);
    }

    public void afficherFenetreAchatTerrain() {
        Platform.runLater(new Runnable() {
            @Override public void run() { fat.afficherFenetre(); }
        });
    }

    public void afficherGagnant(PlateauMonopoly pm) {

        Platform.runLater(new Runnable() {
            @Override public void run() {

                Label vainqueur = new Label("Le vainqueur est "+pm.estGagnant().getNom()+" !");
                vainqueur.setTextFill(l_pions.get(pm.estGagnant().getId()).getFill());
                vainqueur.setFont(Font.font("Arial", 26));
                vainqueur.setTranslateX(145);
                vainqueur.setTranslateY(525);

                root.getChildren().add(vainqueur);

                root.getChildren().remove(tourSuivant);

            }
        });
    }

    public void setMarqueurProprietaire(JoueurMonopoly joueur, Case _case) {

        Platform.runLater(new Runnable() {
            @Override public void run() {

                _case.getMarqueur().setFill(getPionActif().getFill());

                double x = 100, y = 100;
                int pos = joueur.getPosition();

                if(_case.getMarqueur().getPoints().isEmpty())
                    root.getChildren().add(_case.getMarqueur());

                if(pos > 0 && pos < 10) {
                    if(_case.getMarqueur().getPoints().isEmpty())
                        _case.getMarqueur().getPoints().addAll(new Double[] {0.,0.,0.,12.,12.,12.});
                    x = 517 - ((pos-1) * 54);
                    y = 642;
                }
                else if(pos > 10 && pos < 20) {
                    if(_case.getMarqueur().getPoints().isEmpty())
                        _case.getMarqueur().getPoints().addAll(new Double[] {0.,12.,12.,12.,12.,0.});
                    x = 51;
                    y = 558 - ((pos-11) * 54);
                }
                else if(pos > 20 && pos < 30) {
                    if(_case.getMarqueur().getPoints().isEmpty())
                        _case.getMarqueur().getPoints().addAll(new Double[] {0.,0.,0.,12.,12.,12.});
                    x = 85 + ((pos-21) * 54);
                    y = 51;
                }
                else if(pos > 30 && pos < 40) {
                    if(_case.getMarqueur().getPoints().isEmpty())
                        _case.getMarqueur().getPoints().addAll(new Double[] {0.,0.,12.,0.,0.,12.});
                    x = 592;
                    y = 85 + ((pos-31) * 54);
                }

                if(pos == 15 || pos == 12)
                    x+=21;
                else if(pos == 25 || pos == 28)
                    y+=21;
                else if(pos == 35)
                    x-=21;

                _case.getMarqueur().setTranslateX(x);
                _case.getMarqueur().setTranslateY(y);
            }
        });
    }

    private class EvtTourSuivant implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            partie.reprendrePartie();
        }
    }

    /*
    private class EvtClicRoot implements EventHandler<MouseEvent> {

        @Override
        public void handle(MouseEvent event) {

            int pos = -1;

            if(event.getSceneX() < 84) {
                if(event.getSceneY() < 84)
                    pos = 20;
                else if(event.getSceneY() < 570)
                    pos = 19 - (int)((event.getSceneY()-84)/54);
                else
                    pos = 10;
            }
            else if(event.getSceneX() < 570) {
                if(event.getSceneY() < 84)
                    pos = 21 + (int)((event.getSceneX()-84)/54);
                else if(event.getSceneY() >= 570)
                    pos = 9 - (int)((event.getSceneX()-84)/54);
            }
            else {
                if(event.getSceneY() < 84)
                    pos = 30;
                else if(event.getSceneY() < 570)
                    pos = 31 + (int)((event.getSceneY()-84)/54);
                else
                    pos = 0;
            }

            ArrayList<Integer> CasesInterdites = new ArrayList<Integer>();
            for(int i=0; i<40; i++) {
                CasesInterdites.add(i);
            }
            CasesInterdites.add(-1);
            for(Case t:getPartie().getPM().getJoueurActif().getListeTerrains()) {
                CasesInterdites.remove((Object)(t.getId()));
            }

            if(!CasesInterdites.contains(pos)) {
                fact_ter.afficherFenetre(pos);
            }
        }
    }
    */
}
