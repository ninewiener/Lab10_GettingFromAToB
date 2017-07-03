/**
 * Created by Janine on 21.06.17.
 */
public class Edge {
  //end und anfangsknoten
  private Vertice v;
  private Vertice w;
  private int kosten;

  public Edge(Vertice v, Vertice w, int kosten) {
    this.v = v;
    this.w = w;
    this.kosten = kosten;
  }

  public Vertice getV() {
    return v;
  }

  public void setV(Vertice v) {
    this.v = v;
  }

  public Vertice getW() {
    return w;
  }

  public void setW(Vertice w) {
    this.w = w;
  }

  public int getWeight() {
    return kosten;
  }

  public void setCost(int kosten) {
    this.kosten = kosten;
  }
}
