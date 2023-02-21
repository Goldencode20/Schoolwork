import java.sql.Array;
import java.util.*;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

 public class ShortestPathTest {
     @Test
     public void sampleGraph(){
         // nodes
         Node a = new Node("a");
         Node b = new Node("b");
         Node c = new Node("c");
         List<Node> nodes = List.of(a,b,c);

         // edges
         Edge ab = new Edge(a, b);
         Edge bc = new Edge(b, c);
         Hashtable<Node, List<Edge>> neighbors = new Hashtable<>();
         neighbors.put(a, new ArrayList<>(Arrays.asList(ab)));
         neighbors.put(b, new ArrayList<>(Arrays.asList(bc)));

         // put together for a graph voila
         Graph g = new Graph(nodes, neighbors);
         // a -> b -> c : u will need to set weights yada yada
     }

     @Test
     public void shortestPath(){
         // nodes
         Node a = new Node("a");
         Node b = new Node("b");
         Node c = new Node("c");
         Node d = new Node("d");
         List<Node> nodes = List.of(a,b,c,d);

         // edges
         Edge ab = new Edge(a, b, 3);
         Edge bc = new Edge(b, c, 2);
         Edge ad = new Edge (a, d,1);
         Edge cd = new Edge (c, d, 4);
         Hashtable<Node, List<Edge>> neighbors = new Hashtable<>();
         neighbors.put(a, new ArrayList<>(Arrays.asList(ab)));
         neighbors.put(b, new ArrayList<>(Arrays.asList(bc)));
         neighbors.put(c, new ArrayList<>(Arrays.asList(cd)));
         neighbors.get(a).add(ad);


         Graph g = new Graph(nodes, neighbors);
         g.shortestPath(a);
         for (Node n : nodes){
             System.out.println(n + " " + n.getValue());
         }
     }

     @Test
     public void primsMST(){
         // nodes
         Node a = new Node("a");
         Node b = new Node("b");
         Node c = new Node("c");
         Node d = new Node("d");
         List<Node> nodes = List.of(a,b,c,d);

         // edges
         Edge ab = new Edge(a, b, 3);
         Edge bc = new Edge(b, c, 2);
         Edge ad = new Edge (a, d,1);
         Edge cd = new Edge (c, d, 4);
         Hashtable<Node, List<Edge>> neighbors = new Hashtable<>();
         neighbors.put(a, new ArrayList<>(Arrays.asList(ab)));
         neighbors.put(b, new ArrayList<>(Arrays.asList(bc)));
         neighbors.put(c, new ArrayList<>(Arrays.asList(cd)));
         neighbors.get(a).add(ad);


         Graph g = new Graph(nodes, neighbors);
         System.out.println(g.primsMST(a));
     }

}
