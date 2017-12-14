import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class TestDijkstraAlgorithm {
	
	private List<Sommet> nodes;
    private List<Arret> Arrets;

    @Test
    public void testExcute() throws IOException, JSONException {
        nodes = new ArrayList<Sommet>();
        Arrets = new ArrayList<Arret>();
        List<JSONObject> etatVelo = new EtatVeloByURL().readJsonFromUrl();
        for(int i=0; i<etatVelo.size(); i++){
        	String num = etatVelo.get(i).getString("number"); 
        	String nom = etatVelo.get(i).getString("name");
        	JSONObject position = new JSONObject(""+etatVelo.get(i).get("position"));
        	Position post = new Position(position.getDouble("lng"),position.getDouble("lat"));
        	String adresse = etatVelo.get(i).getString("address");
        	String status = etatVelo.get(i).getString("status");
        	int nVelo = etatVelo.get(i).getInt("bike_stands");
        	int nbMarch = etatVelo.get(nVelo).getInt("available_bikes");
        	int nbArr = etatVelo.get(i).getInt("available_bike_stands");
        	Station uneStation = new Station("Node_"+i+"_"+num, "Node_"+i+"_"+nom, adresse, post, status, nVelo, nbMarch, nbArr);
            nodes.add(uneStation);
        }

        addLane("Arret_0", 0, 4, 4);
        addLane("Arret_1", 0, 1, 15);
        addLane("Arret_2", 2, 1, 3);
        addLane("Arret_3", 2, 3, 2);
        addLane("Arret_4", 3, 1, 3);
        addLane("Arret_5", 3, 0, 10);
        addLane("Arret_6", 4, 2, 7);
        addLane("Arret_7", 4, 3, 5);
        

        // Lets check from location Loc_1 to Loc_10
        Graph graph = new Graph(nodes, Arrets);
        DijkstraAlgorithm dijkstra = new DijkstraAlgorithm(graph);
        dijkstra.execute(nodes.get(0));
        LinkedList<Sommet> path = dijkstra.getPath(nodes.get(1));

        assertNotNull(path);
        assertTrue(path.size() > 0);

        for (Sommet Sommet : path) {
            System.out.println(Sommet);
        }

    }

    private void addLane(String laneId, int sourceLocNo, int destLocNo,
            int duration) {
        Arret lane = new Arret(laneId,nodes.get(sourceLocNo), nodes.get(destLocNo), duration );
        Arrets.add(lane);
    }
	
	

}
