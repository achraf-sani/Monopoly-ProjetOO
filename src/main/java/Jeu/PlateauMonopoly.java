package Jeu;

import abstractClasses.Joueur;
import abstractClasses.Plateau;

import java.util.ArrayList;

public class PlateauMonopoly extends Plateau {
    private ArrayList<JoueurMonopoly> joueurs = new ArrayList<JoueurMonopoly>();
    public PlateauMonopoly(int nbrJoueurs) {
        super(nbrJoueurs, 40);

        for (int i=1; i<=this.getNbrJoueurs(); i++) {
            this.joueurs.add(new JoueurMonopoly("Joueur"+i, i, 1500));
        }
    }

    public void deplacerJoueur(JoueurMonopoly joueur, int nbrCases) {
        int pos;
        // si le joueur passe par la case Départ ( >= 40 )
        if((joueur.getPosition() + nbrCases) >= getNbrCases()){
            pos = (joueur.getPosition() + nbrCases) % getNbrCases();
            joueur.ajouterArgent(200);
        } else {
            pos = joueur.getPosition() + nbrCases;
        }

        if (!joueur.getEstEnFaillite()) joueur.setPosition(pos);
    }

    public JoueurMonopoly getJoueur(int i) { return this.joueurs.get(i); }

    public JoueurMonopoly getJoueurActif() {
        return this.joueurs.get(getJoueurCourantID());
    }

    @Override
    public Joueur estGagnant() {
        int restant = 0;
        for (int i=0; i<joueurs.size(); i++){
            if (getJoueur(i).getArgent() > getJoueur(restant).getArgent()) { restant = i; }
        }
        return getJoueur(restant);
    }

    @Override
    public boolean finPartie() {
        int nbrJoueursRestant = 0;
        for (JoueurMonopoly j:joueurs) if (!j.getEstEnFaillite()) nbrJoueursRestant++;
        
        return (nbrJoueursRestant <=1);
    }
}
