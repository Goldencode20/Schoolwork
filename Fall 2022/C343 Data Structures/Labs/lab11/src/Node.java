public class Node {

    private String name;
    private boolean visited;

    public Node(String name){
        this.name = name;
        this.visited = false;
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

}