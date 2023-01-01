package Cases;

import Jeu.JoueurMonopoly;
import Jeu.PlateauMonopoly;
import abstractClasses.Case;
import com.monopoly.monopolyprojetoo.PartieMonopoly;

import java.util.Random;

public class Case_gare extends Case {
    private JoueurMonopoly proprietaire;
    private boolean reponseQuestion = false;

    /**
     * Indique le nom et ajoute le prix d'une gare
     * @param nom String
     */
    public Case_gare(String nom) {
        super(nom, 200);
    }

    @Override
    public void actionCase(JoueurMonopoly joueur, PlateauMonopoly plateau, PartieMonopoly partieM) {
        if(this.getProprietaire() == null) {
            if(getReponseQuestion()) {
                if(acheterTerrain(joueur, partieM))
                    partieM.setMarqueurProprietaire(joueur, this);
            }
            else {
                partieM.afficherMsg(joueur.getNom() + " décide de ne pas acheter cette gare.");
            }
        }
        else if(this.getProprietaire() != joueur)
            payerLoyer(joueur, partieM);

        else {
            partieM.afficherMsg(joueur.getNom() + " est dans sa propre gare.");
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
            joueur.setNbrGares(joueur.getNbrGares() + 1);

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
        if(partieM!=null) partieM.afficherMsg(joueur.getNom() + " paye un loyer de " + getLoyer() + "$ à " + beneficiaire);

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
    public JoueurMonopoly getProprietaire() { return proprietaire; }

    @Override
    public String getCouleur() { return null; }

    @Override
    public int getLoyer() { return proprietaire != null ? 50 * this.getProprietaire().getNbrGares() : 0; }


    @Override
    public boolean getReponseQuestion() { return reponseQuestion; }


    @Override
    public void setProprietaire(JoueurMonopoly j) { this.proprietaire = j; }

    @Override
    public void setReponseQuestion(boolean b) { this.reponseQuestion = b; }

    @Override
    public String toString() {
        return "CaseGare [" + super.toString() + ", proprietaire=" + (proprietaire==null?"null":proprietaire.getNom()) + "]";
    }


}
