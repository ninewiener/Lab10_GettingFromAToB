/**
 * Created by Janine on 21.06.17.
 */
public class Edge {
  //end und anfangsknoten
  private Vertex v;
  private Vertex w;
  private int kosten;

  public Edge(Vertex v, Vertex w, int kosten) {
    this.v = v;
    this.w = w;
    this.kosten = kosten;
  }

  public Vertex getV() {
    return v;
  }

  public void setV(Vertex v) {
    this.v = v;
  }

  public Vertex getW() {
    return w;
  }

  public void setW(Vertex w) {
    this.w = w;
  }

  public int getWeight() {
    return kosten;
  }

  public void setCost(int kosten) {
    this.kosten = kosten;
  }
}
