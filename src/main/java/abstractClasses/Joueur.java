package abstractClasses;

public abstract class Joueur {
    private String nom;
    private int id;
    private int position=0;

    public Joueur(String nom, int id){
        this.nom = nom;
        this.id= id;
    }

    public String getNom() { return this.nom; }
    public int getId() { return this.id; }
    public int getPosition() { return this.position; }

    public void setNom(String nom) { this.nom = nom; }
    public void setId(int id) { this.id = id; }
    public void setPosition(int pos) { this.position = pos; }

    public String toString() { return "Joueur : nom=" + this.nom + ", id=" + this.id + ", position=" + this.position + ".";}

}
