import java.util.List;
public class Graph {
	
	private final List<Sommet> vertexes;
    private final List<Arret> Arrets;

    public Graph(List<Sommet> vertexes, List<Arret> Arrets) {
        this.vertexes = vertexes;
        this.Arrets = Arrets;
    }

    public List<Sommet> getSommet() {
        return vertexes;
    }

    public List<Arret> getArrets() {
        return Arrets;
    }

}
	