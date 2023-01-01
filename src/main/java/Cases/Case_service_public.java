package Cases;

import com.monopoly.monopolyprojetoo.PartieMonopoly;
import abstractClasses.Case;
import Jeu.JoueurMonopoly;
import Jeu.PlateauMonopoly;

import java.util.Random;


public class Case_service_public extends Case{

    private JoueurMonopoly proprietaire;
    private boolean reponseQuestion = false;

   
    public Case_service_public(String nom) {
        super(nom, 150);
    }

    @Override
    public void actionCase(JoueurMonopoly joueur, PlateauMonopoly plateau, PartieMonopoly partieM) {

        if(this.getProprietaire() == null) {
            if(getReponseQuestion()) {
                if(acheterTerrain(joueur, partieM))
                    partieM.setMarqueurProprietaire(joueur, this);
            }
            else {
                partieM.afficherMsg(joueur.getNom() + " décide de ne pas acheter cette compagnie.");
            }
        }

        else if(this.getProprietaire() != joueur)
            payerLoyer(joueur, plateau, partieM);

        else {
            if(partieM!=null) partieM.afficherMsg("Le propriétaire est en prison. " + joueur.getNom() + " ne paye pas de loyer.");
        }
    }


    public boolean acheterTerrain(JoueurMonopoly joueur, PartieMonopoly partieM) {
        if((joueur.getArgent() - this.getPrix()) <= 0) {
            System.out.println("Vous n'avez pas assez d'argent!");
            return false;
        }
        else {
            setProprietaire(joueur);
            joueur.ajouterTerrain(this);
            joueur.retirerArgent(this.getPrix());
            joueur.setNbrServices(joueur.getNbrServices() + 1);

            if(partieM!=null) partieM.afficherMsg(joueur.getNom() + " achète " + this.getNom() + " pour " + this.getPrix() + "€");
            return true;
        }
    }

    public void payerLoyer(JoueurMonopoly joueur, PlateauMonopoly pm, PartieMonopoly partieM) {
        String beneficiaire = "la Banque";

        int loyer = pm.dice.lancerDes()[0] + pm.dice.lancerDes()[1];
        if(partieM!=null) {
            partieM.effacerDes();
            partieM.afficherDes(pm);
        }

        if(this.getProprietaire().getNbrServices() == 2) loyer*=10;
        else loyer*=4;

        joueur.retirerArgent(loyer);

        if(!this.getProprietaire().getEstEnFaillite()) {
            this.getProprietaire().ajouterArgent(loyer);
            beneficiaire = this.getProprietaire().getNom();
        }
        System.out.println(" > " + joueur.getNom() + " paye un loyer de " + loyer + "€ à " + beneficiaire);
        if(partieM!=null) partieM.afficherMsg(joueur.getNom() + " paye un loyer de " + loyer + "€ à " + beneficiaire);

    }

    @SuppressWarnings("static-access")
    @Override

    public void fenetreAction(PartieMonopoly partieM) {

        if(partieM.getPartie().START) {
            Random rand = new Random();
            if(rand.nextBoolean())
                reponseQuestion = true;
            partieM.getPartie().reprendrePartie();
        }
        else if(this.getProprietaire() == null)
            partieM.afficherFenetreAchatTerrain();
        else
            partieM.getPartie().reprendrePartie();
    }
    

    @Override
    public JoueurMonopoly getProprietaire() {
        return proprietaire;
    }

    @Override
    public String getCouleur() {
        return null;
    }

    @Override
    public int getLoyer() {
        return 0;
    }

    @Override
    public boolean getReponseQuestion() {
        return reponseQuestion;
    }


    @Override
    public void setProprietaire(JoueurMonopoly j) {
        this.proprietaire = j;
    }

    @Override
    public void setReponseQuestion(boolean b) {
        this.reponseQuestion = b;
    }

    @Override
    public String toString() {
        return "CaseServicePublic [" + super.toString() + ", proprietaire=" + (proprietaire==null?"null":proprietaire.getNom()) + "]";
    }

}
