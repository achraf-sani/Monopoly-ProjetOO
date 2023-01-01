package Jeu;

import Cases.*;
import abstractClasses.Case;
import abstractClasses.Joueur;
import abstractClasses.Plateau;

import java.util.ArrayList;
import java.util.Arrays;

public class PlateauMonopoly extends Plateau {
    private ArrayList<JoueurMonopoly> joueurs = new ArrayList<JoueurMonopoly>();
    public PlateauMonopoly(int nbrJoueurs) {
        super(nbrJoueurs, 40);

        for (int i=1; i<=this.getNbrJoueurs(); i++) {
            this.joueurs.add(new JoueurMonopoly("Joueur"+i, i, 1500));
        }

        /* INITIALISATION DES CASES*/
        setCase(0, new Case_depart());
        //setCase(2, new CaseCommunaute());
        setCase(4, new Case_impots("Impots sur le revenu", 200));
        setCase(5, new Case_gare("Gare Montparnasse"));
        //setCase(7, new CaseChance());
        //setCase(10, new CasePrison());
        setCase(12, new Case_service_public("Compagnie d'électricité"));
        setCase(15, new Case_gare("Gare de Lyon"));
        //setCase(17, new CaseCommunaute());
        setCase(20, new Case_parc_gratuit());
        //setCase(22, new CaseChance());
        setCase(25, new Case_gare("Gare du Nord"));
        setCase(28, new Case_service_public("Compagnie des eaux"));
        //setCase(30, new CaseAllerPrison());
        //setCase(33, new CaseCommunaute());
        setCase(35, new Case_gare("Gare Saint-Lazare"));
        //setCase(36, new CaseChance());
        setCase(38, new Case_impots("Taxe de Luxe", 100));

        /* INITIALISATION DES TERRAINS */
        setCase(1, new Case_terrain("Boulevard de Belleville", 60, "brun"));
        setCase(3, new Case_terrain("Rue Lecourbe", 60,"brun"));

        setCase(6, new Case_terrain("Rue de Vaugirard", 100,"turquoise"));
        setCase(8, new Case_terrain("Rue de Courcelles", 100, "turquoise"));
        setCase(9, new Case_terrain("Avenue de la République", 120, "turquoise"));

        setCase(11, new Case_terrain("Boulevard la Villette", 140,"mauve"));
        setCase(13, new Case_terrain("Avenue de Neuilly", 140,"mauve"));
        setCase(14, new Case_terrain("Rue du Paradis", 160,"mauve"));

        setCase(16, new Case_terrain("Avenue Mozart", 180,"orange"));
        setCase(18, new Case_terrain("Boulevard Saint-Victorien", 180, "orange"));
        setCase(19, new Case_terrain("Place Pigalle", 200, "orange"));

        setCase(21, new Case_terrain("Avenue Matignon", 220,"rouge"));
        setCase(23, new Case_terrain("Boulevard Malesherbes", 220, "rouge"));
        setCase(24, new Case_terrain("Avenue Henri-Martin", 240,"rouge"));

        setCase(26, new Case_terrain("Faubourg Saint-Honoré", 260,"jaune"));
        setCase(27, new Case_terrain("Place de la Bourse", 260,"jaune"));
        setCase(29, new Case_terrain("Rue La Fayette", 280,"jaune"));

        setCase(31, new Case_terrain("Avenue de Breuteuil", 300,"vert"));
        setCase(32, new Case_terrain("Avenue Foch", 300,"vert"));
        setCase(34, new Case_terrain("Boulevard des Capucines", 320, "vert"));

        setCase(37, new Case_terrain("Avenue des Champs-élysées", 350, "bleu"));
        setCase(39, new Case_terrain("Rue de la Paix", 400,"bleu"));

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

    public Case getCaseActive() {
        return getCase(getJoueurActif().getPosition());
    }

    @Override
    public boolean finPartie() {
        int nbrJoueursRestant = 0;
        for (JoueurMonopoly j:joueurs) if (!j.getEstEnFaillite()) nbrJoueursRestant++;
        
        return (nbrJoueursRestant <=1);
    }


}
