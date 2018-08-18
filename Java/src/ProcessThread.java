import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.file.Path;
import java.util.ArrayList;

public class ProcessThread extends Thread {
  private Path path;
  @SuppressWarnings("unused")
  private WatchThread parent;
  private ArrayList<Answer> AnswerQueue;
  private boolean DEBUG;

  public void init(ArrayList<Answer> AnswerQueue, Path path, boolean DEBUG) {
    this.path = path;
    this.AnswerQueue = AnswerQueue;
    this.DEBUG = DEBUG;
  }

  public void run() {
    // if (DEBUG)
    System.out.println("ProcessThread started");
    try {
      Thread.sleep(50);
    } catch (InterruptedException e1) {
      e1.printStackTrace();
    }
    try {
      FileReader fr = new FileReader("O:\\Studium\\Kompetenz\\feedback-server\\Node\\data\\" + path.toString());
      BufferedReader bfr = new BufferedReader(fr);
      String name = bfr.readLine();
      String message = bfr.readLine();
      String line;
      while ((line = bfr.readLine()) != null) {
        message += "\n" + line;
      }

      AnswerQueue.add(new Answer(name, message));
      bfr.close();

    } catch (Exception e) {
      e.printStackTrace();
    }
  }

}
