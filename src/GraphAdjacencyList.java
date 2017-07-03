import java.util.*;

public class GraphAdjacencyList {
  /* Makes use of Map collection to store the adjacency list for each vertex. */
  private Map<Integer, List<Integer>> adjacencyList;

  /*
   * Initializes the map to with size equal to number of vertices in a graph
   * Maps each vertex to a given List Object
   */
  public GraphAdjacencyList(int totalVertices) {
    adjacencyList = new HashMap<>();
    for (int i = 1; i <= totalVertices; i++) {
      adjacencyList.put(i, new LinkedList<>());
    }
  }

  /*
   * Main Function reads the number of vertices and edges in a graph.
   * then creates a Adjacency List for the graph and prints it
   */
  public static void main(String[] args) {
    int source, destination;
    int number_of_edges, number_of_vertices;
    int count = 1;
    Scanner sc = new Scanner(System.in);
    try {
             /* Read the number of vertices and edges in graph */
      System.out.println("Enter the number of vertices and edges in graph");
      number_of_vertices = sc.nextInt();
      number_of_edges = sc.nextInt();
      GraphAdjacencyList adjacencyList = new GraphAdjacencyList(number_of_vertices);

             /* Reads the edges present in the graph */
      System.out.println("Enter the edges in graph Format : <source index> <destination index>");
      while (count <= number_of_edges) {
        source = sc.nextInt();
        destination = sc.nextInt();
        adjacencyList.setEdge(source, destination, 1);
        count++;
      }

             /* Prints the adjacency List representing the graph. */
      System.out.println("the given Adjacency List for the graph \n");
      for (int i = 1; i <= number_of_vertices; i++) {
        System.out.print(i + "→");
        List<Integer> edgeList = adjacencyList.getEdge(i);
        for (int j = 1; ; j++) {
          if (j != edgeList.size()) {
            System.out.print(edgeList.get(j - 1) + "→");
          } else {
            System.out.print(edgeList.get(j - 1));
            break;
          }
        }
        System.out.println();
      }
    } catch (InputMismatchException inputMismatch) {
      System.out.println("Error in Input Format. \nFormat : <source index> <destination index>");
    }
    sc.close();
  }

  /* Adds nodes in the Adjacency list for the corresponding vertex */
  public boolean setEdge(int source, int destination, int weight) {
    if (source > adjacencyList.size() || destination > adjacencyList.size()) {
      System.out.println("the vertex entered in not present");
      return false;
    }
    if (source == destination) {
      System.out.println("self loop");
      return false;
    }
    List<Integer> slist = adjacencyList.get(source);
    slist.add(destination);
    List<Integer> dlist = adjacencyList.get(destination);
    dlist.add(source);
    return true;
  }

  /* Returns the List containing the vertex joining the source vertex */
  public List<Integer> getEdge(int source) {
    if (source > adjacencyList.size()) {
      System.out.println("the vertex entered is not present");
      return null;
    }
    return adjacencyList.get(source);
  }

  public String toString() {
    String result = "";
    for (int i = 1; i <= adjacencyList.size(); i++) {
      result += (i + "→");
      List<Integer> edgeList = getEdge(i);
      result += edgeList;
      /* for (int j = 1; ; j++) {
        if (j != edgeList.size()) {
          result += (edgeList.get(j - 1) + "→");
        } else {
          result += (edgeList.get(j - 1));
          break;
        }
      } */
      result += "\n";
    }
    return result;
  }
}