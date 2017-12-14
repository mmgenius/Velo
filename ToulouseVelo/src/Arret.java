
public class Arret {
	private final String id;
    private final Sommet source;
    private final Sommet destination;
    private final int taille;

    public Arret(String id, Sommet source, Sommet destination, int taille) {
        this.id = id;
        this.source = source;
        this.destination = destination;
        this.taille = taille;
    }

    public String getId() {
        return id;
    }
    public Sommet getDestination() {
        return destination;
    }

    public Sommet getSource() {
        return source;
    }
    public int gettaille() {
        return taille;
    }

    @Override
    public String toString() {
        return source + " " + destination;
    }


}
