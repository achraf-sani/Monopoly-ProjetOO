package Cases;

import com.monopoly.monopolyprojetoo.PartieMonopoly;
import io.Console;
import abstractClasses.Case;
import jeu.JoueurMonopoly;
import jeu.PlateauMonopoly;


public class Case_depart extends Case{

    public CaseDepart() {
        super("Depart", 0);
    }

    public void actionCase(JoueurMonopoly joueur, PlateauMonopoly plateau, PartieMonopoly fp) {

        Console es = new Console();

        joueur.ajouterArgent(200);
        es.println(" > " + joueur.getNom() + " s'arrête sur la case départ: il reçoit 2000€ supplémentaire !");
        if(fp!=null) fp.afficherMessage(joueur.getNom() + " s'arrête sur la case départ et reçoit 4000€ !");
    }

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
        return "CaseDepart ["+super.toString()+"]";
    }
    
    
}
