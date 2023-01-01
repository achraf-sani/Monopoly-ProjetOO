package Cases;

import com.monopoly.monopolyprojetoo.PartieMonopoly;
import abstractClasses.Case;
import Jeu.JoueurMonopoly;
import Jeu.PlateauMonopoly;



public class Case_parc_gratuit extends Case{
    public Case_parc_gratuit() {
        super("Parc Gratuit", 0);
    }

    public void actionCase(JoueurMonopoly joueur, PlateauMonopoly plateau, PartieMonopoly partieM) {


        if(partieM!=null)
            partieM.afficherMsg(joueur.getNom() + " ramasse " + this.getPrix() + "€ du Parc Gratuit !");
        joueur.ajouterArgent(this.getPrix());
        this.setPrix(0);
    }
    

    @Override

    public void fenetreAction(PartieMonopoly partieM) {
        partieM.getPartie().reprendrePartie();
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
    public boolean getReponseQuestion() {
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
