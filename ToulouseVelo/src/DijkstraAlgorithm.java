import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class DijkstraAlgorithm {
	
	private final List<Sommet> nodes;
    private final List<Arret> Arrets;
    private Set<Sommet> settledNodes;
    private Set<Sommet> unSettledNodes;
    private Map<Sommet, Sommet> predecessors;
    private Map<Sommet, Integer> distance;

    public DijkstraAlgorithm(Graph graph) {
        // create a copy of the array so that we can operate on this array
        this.nodes = new ArrayList<Sommet>(graph.getSommet());
        this.Arrets = new ArrayList<Arret>(graph.getArrets());
    }

    public void execute(Sommet source) {
        settledNodes = new HashSet<Sommet>();
        unSettledNodes = new HashSet<Sommet>();
        distance = new HashMap<Sommet, Integer>();
        predecessors = new HashMap<Sommet, Sommet>();
        distance.put(source, 0);
        unSettledNodes.add(source);
        while (unSettledNodes.size() > 0) {
            Sommet node = getMinimum(unSettledNodes);
            settledNodes.add(node);
            unSettledNodes.remove(node);
            findMinimalDistances(node);
        }
    }

    private void findMinimalDistances(Sommet node) {
        List<Sommet> adjacentNodes = getNeighbors(node);
        for (Sommet target : adjacentNodes) {
            if (getShortestDistance(target) > getShortestDistance(node)
                    + getDistance(node, target)) {
                distance.put(target, getShortestDistance(node)
                        + getDistance(node, target));
                predecessors.put(target, node);
                unSettledNodes.add(target);
            }
        }

    }

    private int getDistance(Sommet node, Sommet target) {
        for (Arret Arret : Arrets) {
            if (Arret.getSource().equals(node)
                    && Arret.getDestination().equals(target)) {
                return Arret.gettaille();
            }
        }
        throw new RuntimeException("Should not happen");
    }

    private List<Sommet> getNeighbors(Sommet node) {
        List<Sommet> neighbors = new ArrayList<Sommet>();
        for (Arret Arret : Arrets) {
            if (Arret.getSource().equals(node)
                    && !isSettled(Arret.getDestination())) {
                neighbors.add(Arret.getDestination());
            }
        }
        return neighbors;
    }

    private Sommet getMinimum(Set<Sommet> Sommetes) {
        Sommet minimum = null;
        for (Sommet Sommet : Sommetes) {
            if (minimum == null) {
                minimum = Sommet;
            } else {
                if (getShortestDistance(Sommet) < getShortestDistance(minimum)) {
                    minimum = Sommet;
                }
            }
        }
        return minimum;
    }

    private boolean isSettled(Sommet Sommet) {
        return settledNodes.contains(Sommet);
    }

    private int getShortestDistance(Sommet destination) {
        Integer d = distance.get(destination);
        if (d == null) {
            return Integer.MAX_VALUE;
        } else {
            return d;
        }
    }

    /*
     * This method returns the path from the source to the selected target and
     * NULL if no path exists
     */
    public LinkedList<Sommet> getPath(Sommet target) {
        LinkedList<Sommet> path = new LinkedList<Sommet>();
        Sommet step = target;
        // check if a path exists
        if (predecessors.get(step) == null) {
            return null;
        }
        path.add(step);
        while (predecessors.get(step) != null) {
            step = predecessors.get(step);
            path.add(step);
        }
        // Put it into the correct order
        Collections.reverse(path);
        return path;
    }

}
