package Cases;

import com.monopoly.monopolyprojetoo.PartieMonopoly;
import abstractClasses.Case;
import Jeu.JoueurMonopoly;
import Jeu.PlateauMonopoly;


public class Case_depart extends Case{

    public Case_depart() {
        super("Depart", 0);
    }

    public void actionCase(JoueurMonopoly joueur, PlateauMonopoly plateau, PartieMonopoly partieM) {

        joueur.ajouterArgent(200);
        if(partieM!=null) partieM.afficherMsg(joueur.getNom() + " s'arrête sur la case départ et reçoit $200 !");
    }

    public void fenetreAction(PartieMonopoly partieM) { partieM.getPartie().reprendrePartie(); }
    
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
        return "CaseDepart ["+super.toString()+"]";
    }
    
    
}
