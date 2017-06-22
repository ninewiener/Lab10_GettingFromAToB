import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by Janine on 21.06.17.
 */
public class Vertice {
  private int id;
  private String name;
  private ArrayList<Edge> edges = new ArrayList<>();

  public Vertice(int id, String name) {
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

  public ArrayList<Edge> getListWithEdges(){
    return edges;
  }

}
