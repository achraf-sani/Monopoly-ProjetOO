package Cases;
import com.monopoly.monopolyprojetoo.PartieMonopoly;
import io.Console;
import abstractClasses.Case;
import jeu.JoueurMonopoly;
import jeu.PlateauMonopoly;



public class Case_impots extends Case{
    public CaseImpots(String nom, int valeur) {
        super(nom, valeur);
    }


    public void actionCase(JoueurMonopoly joueur, PlateauMonopoly plateau, PartieMonopoly fp) {

        Console es = new Console();

        es.println(" > " + joueur.getNom() + " dépose " + this.getPrix() + "€ au Parc Gratuit.");
        if(fp != null)
            fp.afficherMessage(joueur.getNom() + " dépose " + this.getPrix() + "€ au Parc Gratuit.");

        joueur.retirerArgent(this.getPrix());

        int nouveauMontantParcGratuit = plateau.getCase(20).getPrix() + this.getPrix();
        plateau.getCase(20).setPrix(nouveauMontantParcGratuit);
    }
    

    @Override
    
    public void fenetreAction(FenetrePrincipale fp) {
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

}
