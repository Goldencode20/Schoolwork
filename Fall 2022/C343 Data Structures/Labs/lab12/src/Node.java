public class Node implements Comparable<Node> {

    private String name;
    private boolean visited;
    private int value;
    private Edge assocEdge; // for MST

    public Node(String name){
        this.name = name;
        this.visited = false;
        this.value = Integer.MAX_VALUE;
        this.assocEdge = null;
    }

    public void setValue(int value){
        this.value = value;
    }

    public int getValue(){
        return this.value;
    }

    public void setAssocEdge(Edge assocEdge) {
        this.assocEdge = assocEdge;
    }

    public Edge getAssocEdge(){
        return this.assocEdge;
    }

    public String getName(){
        return this.name;
    }

    public boolean isVisited(){
        return this.visited;
    }

    public void setUnvisited(){
        this.visited = false;
    }

    public void setVisited(){
        this.visited = true;
    }

    public String toString(){
        return this.name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node n = (Node) o;
        return n.getValue() == this.value &&
                this.name.equals(n.name);
    }

    @Override
    public int compareTo(Node o) {
        return Integer.compare(this.value, o.getValue());
    }
}