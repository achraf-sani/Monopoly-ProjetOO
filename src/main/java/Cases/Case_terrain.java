package Cases;

import java.util.Random;

import Jeu.Partie;
import com.monopoly.monopolyprojetoo.PartieMonopoly;
import abstractClasses.Case;
import Jeu.JoueurMonopoly;
import Jeu.PlateauMonopoly;


public class Case_terrain extends Case{
    private JoueurMonopoly proprietaire;
    private String couleur;
    private boolean reponseQuestion = false;

    public Case_terrain(String nom, int valeur, String couleur) {
        super(nom, valeur);
        this.couleur = couleur;
    }

    @SuppressWarnings({ "unused", "static-access" })
    public void actionCase(JoueurMonopoly joueur, PlateauMonopoly plateau, PartieMonopoly partieM) {

        if(this.getProprietaire() == null) {
            if(getReponseQuestion()) {
                if(acheterTerrain(joueur, partieM))
                    partieM.setMarqueurProprietaire(joueur, this);
            }
            else {
                partieM.afficherMsg(joueur.getNom() + " décide de ne pas acheter ce terrain.");
            }
        }

        else if(this.getProprietaire() != joueur)
            payerLoyer(joueur, partieM);

        else {
            partieM.afficherMsg(joueur.getNom() + " est sur son terrain acheté");
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
            if(partieM!=null) partieM.afficherMsg(joueur.getNom() + " achète " + this.getNom() + " pour " + this.getPrix() + "$");
            return true;
        }
    }

    public void payerLoyer(JoueurMonopoly joueur, PartieMonopoly partieM) {
        String beneficiaire = "la Banque";

        joueur.retirerArgent(getLoyer());
        if(!this.getProprietaire().getEstEnFaillite()) {
            this.getProprietaire().ajouterArgent(getLoyer());
            beneficiaire = this.getProprietaire().getNom();
        }
        System.out.println(" > " + joueur.getNom() + " paye un loyer de " + getLoyer() + "$ à " + beneficiaire);
        if(partieM!=null) partieM.afficherMsg(joueur.getNom() + " paye un loyer de " + getLoyer() + "$ à " + beneficiaire);
    }


    @SuppressWarnings("static-access")
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
    public int getLoyer() {
        return this.getPrix();
    }

    @Override
    public String getCouleur() {
        return couleur;
    }



    @Override
    public JoueurMonopoly getProprietaire() { return proprietaire; }

    @Override
    public boolean getReponseQuestion() { return reponseQuestion; }

    @Override
    public void setProprietaire(JoueurMonopoly j) { this.proprietaire = j; }

    @Override
    public void setReponseQuestion(boolean b) {
        this.reponseQuestion = b;
    }

    @Override
    public String toString() {
        return "CaseTerrain ["+ super.toString() +", proprietaire=" + (proprietaire==null?"null":proprietaire.getNom()) + ", couleur=" + couleur + ", loyer=" + this.getPrix()
                + "]";
    }
}
