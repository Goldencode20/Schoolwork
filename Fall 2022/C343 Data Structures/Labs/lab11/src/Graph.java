import java.util.*;

public class Graph {
    private List<Node> nodes;
    private Hashtable<Node, List<Node>> neighbors;

    public Graph(List<Node> nodes, Hashtable<Node, List<Node>> neighbors){
        this.nodes = nodes;
        this.neighbors = neighbors;
    }

    public void clearVisits(){
        for(Node n: nodes){
            n.setUnvisited();
        }
    }


    // TODO
    // queue - peek, remove, size, add
    public List<Node> BFS(Node start){
        List<Node> order = new ArrayList<>();
        Queue<Node> queue = new LinkedList<>() { };
        start.setVisited();
        queue.add(start);
        while(!queue.isEmpty()){
            Node n = queue.remove();
            for(Node temp : neighbors.get(n)){
                if(!temp.isVisited()){
                    queue.add(temp);
                    temp.setVisited();
                }
            }
            order.add(n);
        }
        return order;
    }


    // TODO
    // stack - pop, push, poll ?
    public List<Node> DFS(Node start){
        List<Node> order = new ArrayList<>();
        Stack<Node> stack = new Stack<>();
        start.setVisited();
        stack.add(start);
        while(!stack.isEmpty()){
            Node n = stack.peek();
            for(Node temp : neighbors.get(n)){
                if(!temp.isVisited()){
                    stack.add(temp);
                    temp.setVisited();
                }
            }
            if(n == stack.peek()){
                order.add(stack.pop());
            }
        }
        return order;
    }


}