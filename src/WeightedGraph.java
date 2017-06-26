import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WeightedGraph implements GraphInterface {
  private static final String FILE = "graph1.txt";
  private ArrayList<Vertice> vertices = new ArrayList<>();
  private ArrayList<Edge> edges = new ArrayList<>();
  private Stack<Edge> path = new Stack<>();
  private String rawData = "";
  private BufferedReader br;

  public static void main(String[] args) {
    System.out.println("hello world");
    WeightedGraph graph = new WeightedGraph();
    graph.readFile();
    graph.processRawData();
    graph.printVertices();
    graph.printEdges();
    graph.depthFirstSearch(graph, graph.getVertice(3), graph.getVertice(5));
  }

  private void readFile() {
    try {
      br = new BufferedReader(new FileReader(FILE));
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    String currentLine;
    try {
      while ((currentLine = br.readLine()) != null) {
        rawData += currentLine + "\n";
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    System.out.println(rawData);
  }

  private static int countLines(String str) {
    String[] lines = str.split("\r\n|\r|\n");
    return lines.length;
  }

  public void depthFirstSearch(WeightedGraph graph, Vertice v, Vertice end) {
    v.setDiscovered();
    if (v == end) {
      System.out.println("found path, took " + path.size() + " steps : Path " + path);
      for (Edge e : path) {
        System.out.print(e.getV().getId() + " → ");
      }
      System.out.print(end.getId());
    }
    for (Edge edge : v.getListWithEdges()) {
      Vertice w = edge.getW();
      if (!w.isDiscovered()) {
        path.add(edge);
        graph.depthFirstSearch(graph, w, end);
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
          if (vertice != null) {
            vertice.addEdge(edge1);
            vertice.addEdge(edge2);
          }
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

