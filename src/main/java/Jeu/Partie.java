package Jeu;

import abstractClasses.Case;
import com.monopoly.monopolyprojetoo.PartieMonopoly;

import javafx.concurrent.Service;
import javafx.concurrent.Task;

import java.util.ArrayList;

public class Partie {
    private PlateauMonopoly pm;

    private PartieMonopoly partieM;

    public boolean pausePartie = false;
    public final static long FPS = 1000;
    public final static boolean START = false;

    public Partie(int nbrJoueurs, ArrayList<String> nomsJoueurs, PartieMonopoly partieM){
        this.pm = new PlateauMonopoly(nbrJoueurs);
        this.partieM = partieM;

        for (int i=0; i<nbrJoueurs; i++){
            pm.getJoueur(i).setNom(nomsJoueurs.get(i));
        }
    }

    public void pausePartie() { this.pausePartie = true; }
    public void reprendrePartie() { this.pausePartie = false; }
    public boolean getPausePartie() { return this.pausePartie; }

    public PlateauMonopoly getPM() {
        return this.pm;
    }

    public void commencerPartie() {
        final Service<Void> partieS = new Service<Void>() {
            @Override
            protected Task<Void> createTask() {
                return new Task<Void>() {
                    @Override
                    protected Void call() throws Exception {
                        JoueurMonopoly joueur;
                        int started;
                        Case _case;

                        while(!pm.finPartie() && pm.getNbrTours() <= 100) {
                            joueur = pm.getJoueurActif();

                            //partieM.afficherMsg()
                            if (!joueur.getEstEnFaillite()) {
                                Thread.sleep(FPS);
                                started = pm.dice.lancerDes()[0] + pm.dice.lancerDes()[1];
                                //partieM.afficherDes(pm);
                                pm.deplacerJoueur(joueur, started);
                                partieM.deplacerPion(joueur);

                                // _case = pm.getCase(joueur.getPosition());

                                Thread.sleep(FPS);

                                pausePartie = true;
                                pausePartie = false;
                                while(pausePartie && !START) { Thread.sleep(400); }

                                // Fenetre Action;
                            }

                            Thread.sleep(400);
                            partieM.deplacerPion(joueur);
                            // partieM.refreshLabels(pm);

                            // pausePartie ;
                            pausePartie = !joueur.getEstEnFaillite();
                            while(pausePartie && !START) { Thread.sleep(200); }
                            // partieM.effacerDes();
                            pm.setJoueurSuivant();
                        }

                        // partieM.afficherGagnant();

                        return null;
                    }
                };
            }
        };
        partieS.start();
    }
}
