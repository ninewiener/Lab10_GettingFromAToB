/**
 * Created by Janine on 21.06.17.
 */
public class Edge {

  //end und anfangsknoten
  private Node v;
  private Node w;
  private int kosten;

  public Edge(Node v, Node w, int kosten) {
    this.v = v;
    this.w = w;
    this.kosten = kosten;
  }

  public Node getV() {
    return v;
  }

  public void setV(Node v) {
    this.v = v;
  }

  public Node getW() {
    return w;
  }

  public void setW(Node w) {
    this.w = w;
  }

  public int getCost() {
    return kosten;
  }

  public void setCost(int kosten) {
    this.kosten = kosten;
  }


}
