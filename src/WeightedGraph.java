import java.util.ArrayList;

/**
 * Created by Janine on 21.06.17.
 */
public class WeightedGraph implements GraphInterface {

    private ArrayList<Knoten> knoten;
    private ArrayList<Kanten> kanten;
    private Kanten[] path;

    public void addKnotenAndKanten(){
        Knoten a = new Knoten ("a");
        Knoten b = new Knoten ("b");
        Kanten ab = new Kanten (a, b, 10);
        kanten.add(ab);
    }
}
