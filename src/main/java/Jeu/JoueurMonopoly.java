package Jeu;

import abstractClasses.Case;
import abstractClasses.Joueur;

import java.util.ArrayList;

public class JoueurMonopoly extends Joueur {
    private int argent=1500;
    private int nbrGares=0;
    private int nbrServices=0;
    private boolean estEnFaillite=false;
    private ArrayList<Case> terrains = new ArrayList<Case>();
    private ArrayList<String> couleurs = new ArrayList<String>();
    public JoueurMonopoly(String nom, int id, int argent) {
        super(nom, id);
        this.argent = argent;
    }


    public int getNbrGares() { return nbrGares; }
    public int getNbrServices() { return nbrServices; }
    public boolean getEstEnFaillite() { return this.estEnFaillite; }
    public ArrayList<Case> getTerrains() { return this.terrains; }
    public ArrayList<String> getCouleurs() {
        couleurs.clear();
        int bleu=0;
        int vert=0;
        int marron=0;
        int turquois=0;
        int rose=0;
        int orange=0;
        int rouge=0;
        int jaune=0;

        /*
        for (Case t: this.getTerrains()){
            if(t.getCouleur() == "bleu") bleu+=1;
            if(t.getCouleur() == "vert") vert+=1;
            if(t.getCouleur() == "marron") marron+=1;
            if(t.getCouleur() == "turquois") turquois+=1;
            if(t.getCouleur() == "rose") rose+=1;
            if(t.getCouleur() == "orange") orange+=1;
            if(t.getCouleur() == "rouge") rouge+=1;
            if(t.getCouleur() == "jaune") jaune+=1;
        }
        */

        // Couleur en possession si tous les terrains de la même couleur sont achetés
        if(bleu==2) couleurs.add("bleu");
        if(vert==3) couleurs.add("vert");
        if(marron==2) couleurs.add("marron");
        if(turquois==3) couleurs.add("turquois");
        if(rose==3) couleurs.add("rose");
        if(orange==3) couleurs.add("orange");
        if(rouge==3) couleurs.add("rouge");
        if(jaune==3) couleurs.add("jaune");

        return this.couleurs;
    }

    public int getArgent(){ return this.argent; }
    public void ajouterArgent(int somme) { this.argent += somme; }
    public void retirerArgent(int somme) {
        this.argent = this.argent - somme;
        if(this.argent<=0){
            this.argent = 0;
            this.setEstEnFaillite(true);
        }
    }

    public void setEstEnFaillite(boolean etat){
        this.estEnFaillite = etat;
        //clearMarqueurs();
        this.terrains.clear();
    }

    public void setNbrGares(int nbrG) { this.nbrGares = nbrG; }
    public void setNbrServices(int nbrS) { this.nbrServices = nbrS; }
    public void ajouterTerrain(Case terrain) {
        this.terrains.add(terrain);
    }
}
