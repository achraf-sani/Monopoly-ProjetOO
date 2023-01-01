package abstractClasses;

import abstractClasses.Case;
import com.monopoly.monopolyprojetoo.Dés;

import java.util.ArrayList;

public abstract class Plateau {
    public int nbrJoueurs = 2;
    public int nbrCases;
    public int nbrTours =1;
    public int joueurCourantID=0;
    public ArrayList<Case> cases = new ArrayList<Case>();

    public Dés dice = new Dés();

    public int getNbrCases() { return this.nbrCases; }
    public int getNbrJoueurs() { return this.nbrJoueurs; }
    public int getNbrTours() { return this.nbrTours; }
    public int getJoueurCourantID() { return this.joueurCourantID; }

    public Plateau(int nbrJoueurs, int nbrCases){
        this.nbrJoueurs = nbrJoueurs;
        this.nbrCases = nbrCases;
        for (int i=0; i<nbrCases; i++) { cases.add(null); }
    }

    public Case getCase(int i) { return this.cases.get(i); }
    public void setCase(int i, Case _case) {
        this.cases.set(i, _case);
        //cases.get(i).setID(i);
    }




    public void setJoueurSuivant() {
        this.joueurCourantID++;
        if(this.joueurCourantID >= this.nbrJoueurs) {
            this.joueurCourantID = 0;
            this.nbrTours++;
        }
    }


    public abstract boolean finPartie();
    public abstract Joueur estGagnant();

}
