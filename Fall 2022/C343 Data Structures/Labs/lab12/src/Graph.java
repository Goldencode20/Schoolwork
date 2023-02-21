import java.util.*;

/*
Big fat note --- Java built-iin PriorityQueue does not update values.
 */

public class Graph {
    private List<Node> nodes;
    private Hashtable<Node, List<Edge>> neighbors;


    public Graph(List<Node> nodes, Hashtable<Node, List<Edge>> neighbors) {
        this.nodes = nodes;
        this.neighbors = neighbors;
    }

    public List<Edge> getNeighborsOf(Node n) {
        return this.neighbors.get(n);
    }

    public void clearVisits() {
        for (Node n : nodes) {
            n.setUnvisited();
        }
    }

    /*
    // TODO: Shortest Paths w/ Priority Queue :D
    We set the values of the Nodes to be the cost of the shortest path ^_^
     */
    public void shortestPath(Node s){

        Comparator<Edge> c = Comparator.comparing(Edge::getWeight);

        if (s.getValue() == Integer.MAX_VALUE) {
            s.setValue(0);
        }

        PriorityQueue<Edge> queue = new PriorityQueue<>(c);
        if (getNeighborsOf(s) != null) {
            for (Edge e : getNeighborsOf(s)) {
                queue.add(e);
            }
        }
        s.setVisited();

        while (!queue.isEmpty()){
            Edge e = queue.remove();
            if (!e.getDestination().isVisited()) {
                e.setWeight(e.getWeight() + s.getValue());
                e.getDestination().setValue(e.getWeight());
                shortestPath(e.getDestination());
            }
        }
    }

    /*
    // TODO: Prims Algorithm MST w/ Priority Queue :D
    The "tree" contains the edges we included :) ^_^
     */
    public Set<Edge> primsMST(Node s){
        Set<Edge> mst = new HashSet<>();
        ArrayList<Edge> available = new ArrayList<>();
        available.addAll(getNeighborsOf(s));
        s.setVisited();
        while (!available.isEmpty()) {
            Edge minEdge = available.get(0);
            for (Edge e : available) {
                if (minEdge.getWeight() > e.getWeight()) {
                    minEdge = e;
                }
            }
            available.remove(minEdge);
            minEdge.getDestination().setVisited();
            mst.add(minEdge);
            List<Edge> temp = getNeighborsOf(minEdge.getDestination());
            if (temp != null) {
                for (Edge e : temp) {
                    if (!e.getDestination().isVisited()){
                        available.add(e);
                    }
                }
            }
        }
        return mst;
    }
}