package Cases;

public class Case_depart extends Case{

    public CaseDepart() {
        super("Depart", 0);
    }

    /**
     * Ajoute l'argent du passage sur la case
     * @see Case
     */
    public void actionCase(JoueurMonopoly joueur, PlateauMonopoly plateau, FenetrePrincipale fp) {

        Console es = new Console();

        joueur.ajouterArgent(200);
        es.println(" > " + joueur.getNom() + " s'arrête sur la case départ: il reçoit 2000€ supplémentaire !");
        if(fp!=null) fp.afficherMessage(joueur.getNom() + " s'arrête sur la case départ et reçoit 4000€ !");
    }
    
    
}
