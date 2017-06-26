import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Janine on 21.06.17.
 */
public class WeightedGraph implements GraphInterface {

  private ArrayList<Vertice> vertices = new ArrayList<>();
  private ArrayList<Edge> edges = new ArrayList<>();
  //private Edge[] path = new Edge[edges.size()];
  private Stack<Edge> path = new Stack<>();
  private String rawData = "1\t2,1\t8,2\n" +
          "2\t1,1\t3,1\n" +
          "3\t2,1\t4,1\n" +
          "4\t3,1\t5,1\n" +
          "5\t4,1\t6,1\n" +
          "6\t5,1\t7,1\n" +
          "7\t6,1\t8,1\n" +
          "8\t7,1\t1,2";

  public static void main(String[] args) {
    System.out.println("hello world");
    WeightedGraph graph = new WeightedGraph();
    graph.processRawData();
    graph.printVertices();
    graph.printEdges();
    //graph.findShortestPath(graph.getVertice(1), graph.getVertice(5), 0);
    graph.depthFirstSearch(graph, graph.getVertice(3), graph.getVertice(5), 0);
  }

  private static int countLines(String str) {
    String[] lines = str.split("\r\n|\r|\n");
    return lines.length;
  }

  /* public void findShortestPath(Vertice a, Vertice b, int pathLength) {
    int i = 0;
    //int pathLength = 0;
    ArrayList<Edge> list = a.getListWithEdges();

    System.out.println(list);
    for (Edge edge : list) {
      Vertice w = edge.getW();
      i++;

      if (w == b) {
        pathLength += i;
      } else {
        //a = w;
        findShortestPath(a, w, pathLength);
      }
    }
    System.out.println("Shortest Path Length: " + pathLength);
  } */

  public void depthFirstSearch(WeightedGraph graph, Vertice v, Vertice end, int steps) {
    v.setDiscovered();
    if (v == end) {
      System.out.println("found path, took " + steps + " steps : Path " + path);
      for (Edge e : path) {
        System.out.print(e.getV().getId() + " → ");
      }
      System.out.print(end.getId());
    }
    for (Edge edge : v.getListWithEdges()) {
      Vertice w = edge.getW();
      if (!w.isDiscovered()) {
        path.add(edge);
        graph.depthFirstSearch(graph, w, end, steps + 1);
      }
    }
  }

  public void processRawData() {
    createVertices();

    String regex = "(\\d)\\s(\\d),(\\d)\\s(\\d),(\\d)";
    Pattern pattern = Pattern.compile(regex);

    Scanner scanner = new Scanner(rawData);
    while (scanner.hasNextLine()) {
      String line = scanner.nextLine();
      if (line.matches(regex)) {
        Matcher matcher = pattern.matcher(line);
        while (matcher.find()) {
          int id = Integer.parseInt(matcher.group(1));
          Vertice vertice = getVertice(id);
          int v = Integer.parseInt(matcher.group(2));
          int vWeight = Integer.parseInt(matcher.group(3));
          int w = Integer.parseInt(matcher.group(4));
          int wWeight = Integer.parseInt(matcher.group(5));
          Edge edge1 = new Edge(vertice, getVertice(v), vWeight);
          edges.add(edge1);
          Edge edge2 = new Edge(vertice, getVertice(w), wWeight);
          edges.add(edge2);
          vertice.addEdge(edge1);
          vertice.addEdge(edge2);
          System.out.println("1: " + matcher.group(1));
          System.out.println("v: " + matcher.group(2) + " → weight: " + matcher.group(3));
          System.out.println("w: " + matcher.group(4) + " → weight: " + matcher.group(5));
        }
      }
    }
    scanner.close();
  }

  private void createVertices() {
    int totalVertices = countLines(rawData);
    for (int i = 1; i <= totalVertices; i++) {
      Vertice node = new Vertice(i, String.valueOf(i));
      this.vertices.add(node);
    }
  }

  private Vertice getVertice(int searchId) {
    for (Vertice node : vertices) {
      if (node.getId() == searchId) {
        return node;
      }
    }
    return null;
  }

  private void printVertices() {
    for (Vertice node : vertices) {
      System.out.println(node.getId());
    }
  }

  private void printEdges() {
    for (Edge edge : edges) {
      System.out.println("" + edge.getV().getId() + " → " + edge.getW().getId() + " (cost: " + edge.getCost() + ")");
    }
  }
}

