import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Janine on 21.06.17.
 */
public class WeightedGraph implements GraphInterface {

  private ArrayList<Node> nodes = new ArrayList<>();
  private ArrayList<Edge> edges = new ArrayList<>();
  private Edge[] path = new Edge[edges.size()];

  public static void main(String[] args) {
    System.out.println("hello world");
    WeightedGraph wg = new WeightedGraph();
    wg.processRawData();
    wg.printNodes();
    wg.printEdges();
  }

  private String rawData = "1\t2,1\t8,2\n" +
          "2\t1,1\t3,1\n" +
          "3\t2,1\t4,1\n" +
          "4\t3,1\t5,1\n" +
          "5\t4,1\t6,1\n" +
          "6\t5,1\t7,1\n" +
          "7\t6,1\t8,1\n" +
          "8\t7,1\t1,2";

  public void processRawData() {
    createNodes();

    String regex = "(\\d)\\s(\\d),(\\d)\\s(\\d),(\\d)";
    Pattern pattern = Pattern.compile(regex);

    Scanner scanner = new Scanner(rawData);
    while (scanner.hasNextLine()) {
      String line = scanner.nextLine();
      if (line.matches(regex)) {
        Matcher matcher = pattern.matcher(line);
        while (matcher.find()) {
          int id = Integer.parseInt(matcher.group(1));
          Node node = getNode(id);
          int v = Integer.parseInt(matcher.group(2));
          int vWeight = Integer.parseInt(matcher.group(3));
          int w = Integer.parseInt(matcher.group(4));
          int wWeight = Integer.parseInt(matcher.group(5));
          Edge kante1 = new Edge(node, getNode(v), vWeight);
          edges.add(kante1);
          Edge kante2 = new Edge(node, getNode(w), wWeight);
          edges.add(kante2);
          System.out.println("1: " + matcher.group(1));
          System.out.println("v: " + matcher.group(2) + " → weight: " + matcher.group(3));
          System.out.println("w: " + matcher.group(4) + " → weight: " + matcher.group(5));
        }
      }
    }
    scanner.close();
  }

  private void createNodes() {
    int totalNodes = countLines(rawData);
    for (int i = 1; i <= totalNodes; i++) {
      Node node = new Node(i, String.valueOf(i));
      this.nodes.add(node);
    }
  }

  private static int countLines(String str) {
    String[] lines = str.split("\r\n|\r|\n");
    return lines.length;
  }

  private Node getNode(int searchId) {
    for (Node node : nodes) {
      if (node.getId() == searchId) {
        return node;
      }
    }
    return null;
  }

  private void printNodes() {
    for (Node node : nodes) {
      System.out.println(node.getId());
    }
  }

  private void printEdges() {
    for (Edge edge : edges) {
      System.out.println("" + edge.getV().getId() + " → " + edge.getW().getId() + " (cost: " + edge.getCost() + ")");
    }
  }
}
