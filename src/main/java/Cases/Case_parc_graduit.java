package Cases;

import com.monopoly.monopolyprojetoo.PartieMonopoly;
import io.Console;
import abstractClasses.Case;
import jeu.JoueurMonopoly;
import jeu.PlateauMonopoly;



public class Case_parc_graduit extends Case{
    public CaseParcGratuit() {
        super("Parc Gratuit", 0);
    }

    public void actionCase(JoueurMonopoly joueur, PlateauMonopoly plateau, PartieMonopoly fp) {

        Console es = new Console();

        es.println(" > " + joueur.getNom() + " ramasse " + this.getPrix() + "€ du Parc Gratuit !");
        if(fp!=null)
            fp.afficherMessage(joueur.getNom() + " ramasse " + this.getPrix() + "€ du Parc Gratuit !");
        joueur.ajouterArgent(this.getPrix());
        this.setPrix(0);
    }
    

    @Override

    public void fenetreAction(PartieMonopoly fp) {
        fp.getPartie().reprendrePartie();
    }


    @Override
    public JoueurMonopoly getProprietaire() {
        return null;
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
    public int getPrixMaison() {
        return 0;
    }

    @Override
    public int getNbMaison() {
        return 0;
    }

    @Override
    public boolean getReponseQuestion() {
        return false;
    }

    @Override
    public boolean getPeutMettreMaison() {
        return false;
    }

    @Override
    public void setProprietaire(JoueurMonopoly j) {}

    @Override
    public void setReponseQuestion(boolean b) {}

    @Override
    public String toString() {
        return "CaseParcGratuit [" + super.toString() + "]";
    }

}
