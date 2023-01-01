package abstractClasses;

import Jeu.JoueurMonopoly;
import Jeu.PlateauMonopoly;
import com.monopoly.monopolyprojetoo.PartieMonopoly;
import javafx.scene.shape.Polygon;

import java.util.ArrayList;

public abstract class Case {
    private String nom;
    private int id=0;
    private int valeur=0;

    private Polygon marqueur = new Polygon();

    // constructor
    public Case(String nom, int valeur) {
        this.nom = nom;
        this.valeur = valeur;
    }


    public String getNom() { return nom; }
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getPrix() { return valeur; }
    public void setPrix(int valeur) { this.valeur = valeur; }
    public Polygon getMarqueur() { return this.marqueur; }

    public void setMarqueur(Polygon r) { this.marqueur = r; }


    public abstract JoueurMonopoly getProprietaire();

    public abstract String getCouleur();

    public abstract int getLoyer();

    public abstract boolean getReponseQuestion();


    public abstract void setProprietaire(JoueurMonopoly j);

    public abstract void setReponseQuestion(boolean b);

    public abstract void fenetreAction(PartieMonopoly partieM);


    public abstract void actionCase(JoueurMonopoly joueur, PlateauMonopoly plateau, PartieMonopoly partieM);


    @Override
    public String toString() {
        return "Case [nom=" + nom + ", id=" + id + ", valeur=" + valeur + "]";
    }
}
