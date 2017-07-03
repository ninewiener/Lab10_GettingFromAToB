import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class GraphFileReader {
  private String file;
  private String rawData = "";
  private BufferedReader br;

  public GraphFileReader(String filename) {
    this.file = filename;
  }

  String read() {
    try {
      br = new BufferedReader(new FileReader(file));
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
    return rawData;
  }
}
