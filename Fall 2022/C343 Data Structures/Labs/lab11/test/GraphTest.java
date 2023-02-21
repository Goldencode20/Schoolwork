import org.junit.Test;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import static org.junit.Assert.assertEquals;

public class GraphTest {
    // TODO: BFS Test
    @Test
    public void BFS(){
        List<Node> nodes1 = new ArrayList<>();
        Hashtable<Node, List<Node>> neighbors = new Hashtable<>();
        Node n0 = new Node("0");
        Node n1 = new Node("1");
        Node n2 = new Node("2");
        Node n3 = new Node("3");
        nodes1.add(n0);
        nodes1.add(n1);
        nodes1.add(n2);
        nodes1.add(n3);
        List<Node> node0connection = new ArrayList<>();
        List<Node> node1connection = new ArrayList<>();
        List<Node> node2connection = new ArrayList<>();
        List<Node> node3connection = new ArrayList<>();
        node0connection.add(n1);
        node0connection.add(n2);
        node1connection.add(n2);
        node2connection.add(n0);
        node2connection.add(n3);
        node3connection.add(n3);
        neighbors.put(n0, node0connection);
        neighbors.put(n1, node1connection);
        neighbors.put(n2, node2connection);
        neighbors.put(n3, node3connection);
        Graph graph1 = new Graph(nodes1, neighbors);

        List<Node> equals = new ArrayList<>();

        equals.add(n0);
        equals.add(n1);
        equals.add(n2);
        equals.add(n3);
        assertEquals(equals, graph1.BFS(n0));

        equals.clear();
        graph1.clearVisits();
        equals.add(n1);
        equals.add(n2);
        equals.add(n0);
        equals.add(n3);
        assertEquals(equals, graph1.BFS(n1));

        equals.clear();
        graph1.clearVisits();
        equals.add(n2);
        equals.add(n0);
        equals.add(n3);
        equals.add(n1);
        assertEquals(equals, graph1.BFS(n2));

        equals.clear();
        graph1.clearVisits();
        equals.add(n3);
        assertEquals(equals, graph1.BFS(n3));
    }

    // TODO: DFS Test
    @Test
    public void DFS(){
        List<Node> nodes1 = new ArrayList<>();
        Hashtable<Node, List<Node>> neighbors = new Hashtable<>();
        Node n0 = new Node("0");
        Node n1 = new Node("1");
        Node n2 = new Node("2");
        Node n3 = new Node("3");
        nodes1.add(n0);
        nodes1.add(n1);
        nodes1.add(n2);
        nodes1.add(n3);
        List<Node> node0connection = new ArrayList<>();
        List<Node> node1connection = new ArrayList<>();
        List<Node> node2connection = new ArrayList<>();
        List<Node> node3connection = new ArrayList<>();
        node0connection.add(n1);
        node0connection.add(n2);
        node1connection.add(n2);
        node2connection.add(n0);
        node2connection.add(n3);
        node3connection.add(n3);
        neighbors.put(n0, node0connection);
        neighbors.put(n1, node1connection);
        neighbors.put(n2, node2connection);
        neighbors.put(n3, node3connection);
        Graph graph1 = new Graph(nodes1, neighbors);

        List<Node> equals = new ArrayList<>();

        equals.add(n3);
        equals.add(n2);
        equals.add(n1);
        equals.add(n0);
        assertEquals(equals, graph1.DFS(n0));

        equals.clear();
        graph1.clearVisits();
        equals.add(n3);
        equals.add(n0);
        equals.add(n2);
        equals.add(n1);
        assertEquals(equals, graph1.DFS(n1));

        equals.clear();
        graph1.clearVisits();
        equals.add(n3);
        equals.add(n1);
        equals.add(n0);
        equals.add(n2);
        assertEquals(equals, graph1.DFS(n2));

        equals.clear();
        graph1.clearVisits();
        equals.add(n3);
        assertEquals(equals, graph1.DFS(n3));
    }

}