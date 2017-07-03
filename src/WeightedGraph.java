import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WeightedGraph implements GraphInterface {

  private static final boolean DEBUG = false;
  private ArrayList<Vertex> vertices = new ArrayList<>();
  private ArrayList<Edge> edges = new ArrayList<>();
  //private Vertex[][] graph = new Vertex[][]{};
  private GraphAdjacencyList adjacencyList;
  private int[][] adjacencyMatrix;
  private Stack<Edge> path = new Stack<>();
  private String rawData = "";
  private BufferedReader br;

  public WeightedGraph() {

  }

  public static void main(String[] args) {
    WeightedGraph graph = new WeightedGraph();
    GraphFileReader fileReader = new GraphFileReader("graph1.txt");
    graph.rawData = fileReader.read();
    graph.processRawData();
    // graph.printVertices();
    graph.initAdjacencyList();
    graph.initAdjacencyMatrix();
    graph.printEdges();
    graph.depthFirstSearch(graph.getVertice(3), graph.getVertice(5));
    System.out.println("\n" + graph.adjacencyList.isConnected(graph.vertices.get(1), graph.vertices.get(2)));
  }

  private void initAdjacencyMatrix() {
    adjacencyMatrix = new int[vertices.size()][vertices.size()];
    // init with zeros
    for (int i = 0; i < vertices.size(); i++) {
      for (int j = 0; j < vertices.size(); j++) {
        adjacencyMatrix[i][j] = 0;
      }
    }

    // put in actual weights for existing edges
    for (Edge edge : edges) {
      adjacencyMatrix[edge.getV().getId()][edge.getW().getId()] = edge.getWeight();
    }
  }

  private void initAdjacencyList() {
    adjacencyList = new GraphAdjacencyList(vertices.size());
    for (Edge edge : edges) {
      adjacencyList.setEdge(edge);
    }
    System.out.println("ADJACENCY LIST:\n" + adjacencyList.toString());
  }

  private static int countLines(String str) {
    String[] lines = str.split("\r\n|\r|\n");
    return lines.length;
  }

  public void depthFirstSearch(Vertex start, Vertex end) {
    start.setDiscovered();
    if (start == end) {
      System.out.println("found path, took " + path.size() + " steps : Path " + path);
      for (Edge e : path) {
        System.out.print(e.getV().getId() + " → ");
      }
      System.out.print(end.getId());
    } else {
      for (Edge edge : start.getListWithEdges()) {
        Vertex w = edge.getW();
        if (!w.isDiscovered()) {
          path.add(edge);
          this.depthFirstSearch(w, end);
        }
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
          Vertex vertex = getVertice(id);
          int v = Integer.parseInt(matcher.group(2));
          int vWeight = Integer.parseInt(matcher.group(3));
          int w = Integer.parseInt(matcher.group(4));
          int wWeight = Integer.parseInt(matcher.group(5));
          Edge edge1 = new Edge(vertex, getVertice(v), vWeight);
          edges.add(edge1);
          Edge edge2 = new Edge(vertex, getVertice(w), wWeight);
          edges.add(edge2);
          if (vertex != null) {
            vertex.addEdge(edge1);
            vertex.addEdge(edge2);
          }
          if (DEBUG) {
            System.out.println(matcher.group(1));
            System.out.println("v: " + matcher.group(2) + " → weight: " + matcher.group(3));
            System.out.println("w: " + matcher.group(4) + " → weight: " + matcher.group(5));
            System.out.println();
          }
        }
      }
    }
    scanner.close();
  }

  private void createVertices() {
    int totalVertices = countLines(rawData);
    for (int i = 1; i <= totalVertices; i++) {
      Vertex node = new Vertex(i, String.valueOf(i));
      this.vertices.add(node);
    }
  }

  private Vertex getVertice(int searchId) {
    for (Vertex node : vertices) {
      if (node.getId() == searchId) {
        return node;
      }
    }
    return null;
  }

  private void printVertices() {
    for (Vertex node : vertices) {
      System.out.println(node.getId());
    }
  }

  private void printEdges() {
    for (Edge edge : edges) {
      System.out.println("" + edge.getV().getId() + " → " + edge.getW().getId() + " (cost: " + edge.getWeight() + ")");
    }
  }
}

