package Jeu;

import abstractClasses.Case;
import com.monopoly.monopolyprojetoo.Dés;
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

                            partieM.afficherMsg("C'est au tour de " + joueur.getNom() + " de jouer (possède " + joueur.getArgent() + "$).");
                            if (!joueur.getEstEnFaillite()) {
                                Thread.sleep(FPS);

                                for (int i =0; i<15; i++ ) {
                                    started = pm.dice.lancerDes()[0] + pm.dice.lancerDes()[1];
                                    partieM.afficherDes(pm);
                                    Thread.sleep(50);
                                }
                                started = pm.dice.lancerDes()[0] + pm.dice.lancerDes()[1];
                                partieM.afficherDes(pm);
                                pm.deplacerJoueur(joueur, started);
                                partieM.deplacerPion(joueur);

                                _case = pm.getCase(joueur.getPosition());
                                if (!(_case==null)) {
                                    System.out.println(_case);
                                    Thread.sleep(FPS);

                                    pausePartie = true;
                                    _case.fenetreAction(partieM);


                                    while (pausePartie && !START) {
                                        Thread.sleep(200);
                                    }
                                    // Fenetre Action;
                                    _case.actionCase(joueur, pm, partieM);
                                }

                            }

                            Thread.sleep(400);
                            partieM.deplacerPion(joueur);
                            partieM.refreshLabels(pm);

                            // pausePartie ;
                            pausePartie = !joueur.getEstEnFaillite();
                            while(pausePartie && !START) { Thread.sleep(100); }
                            partieM.effacerDes();
                            pm.setJoueurSuivant();
                        }

                        partieM.afficherGagnant(pm);

                        return null;
                    }
                };
            }
        };
        partieS.start();
    }
}
