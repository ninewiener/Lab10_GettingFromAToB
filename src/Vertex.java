import java.util.ArrayList;

/**
 * Created by Janine on 21.06.17.
 */
public class Vertex {
  private int id;
  private String name;
  private ArrayList<Edge> edges = new ArrayList<>();
  private boolean discovered = false;

  public Vertex(int id, String name) {
    this.id = id;
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public int getId() {
    return id;
  }

    /*public void setName(String name) {
        this.name = name;
    }*/

  public ArrayList<Edge> getListWithEdges() {
    return edges;
  }

  public void addEdge(Edge e) {
    edges.add(e);
  }

  public void setDiscovered() {
    discovered = true;
  }

  public boolean isDiscovered() {
    return discovered;
  }

}
