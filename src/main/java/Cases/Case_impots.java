package Cases;
import com.monopoly.monopolyprojetoo.PartieMonopoly;

import abstractClasses.Case;
import Jeu.JoueurMonopoly;
import Jeu.PlateauMonopoly;



public class Case_impots extends Case{
    public Case_impots(String nom, int valeur) {
        super(nom, valeur);
    }


    public void actionCase(JoueurMonopoly joueur, PlateauMonopoly plateau, PartieMonopoly partieM) {

        if(partieM != null)
            partieM.afficherMsg(joueur.getNom() + " dépose " + this.getPrix() + "€ au Parc Gratuit.");

        joueur.retirerArgent(this.getPrix());

        int nouveauMontantParcGratuit = plateau.getCase(20).getPrix() + this.getPrix();
        plateau.getCase(20).setPrix(nouveauMontantParcGratuit);
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

}
