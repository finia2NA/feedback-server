package WatchSystem;

import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.file.Path;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

import Main.Answer;

public class ProcessThread extends Thread {
  private Path path;
  @SuppressWarnings("unused")
  private WatchThread parent;
  CopyOnWriteArrayList<Answer> AnswerList;
  private boolean DEBUG;
  AtomicBoolean stop;
  // TODO: maybe move these up to main?
  final static String DATALOCATION = "O:\\Studium\\Kompetenz\\feedback-server\\Node\\data\\";
  // TODO: fill
  final static String BACKUPLOCATION = "";

  public void init(CopyOnWriteArrayList<Answer> AnswerList, Path path, boolean DEBUG, AtomicBoolean stop) {
    this.path = path;
    this.AnswerList = AnswerList;
    this.DEBUG = DEBUG;
    this.stop = stop;
  }

  // TODO backup with zuordnung
  // synchronized to prevent simultanious access to backup file
  public void run() {
    if (DEBUG)
      System.out.println("ProcessThread started");
    try {
      Thread.sleep(50);
    } catch (InterruptedException e1) {
      e1.printStackTrace();
    }
    try {
      FileReader fr = new FileReader(DATALOCATION + path.toString());
      BufferedReader bfr = new BufferedReader(fr);
      String name = bfr.readLine();
      String message = bfr.readLine();
      String line;
      while ((line = bfr.readLine()) != null) {
        message += "\n" + line;
      }
      if (name.equals("finia2na")) {
        if (DEBUG)
          System.out.println("stop detected");
        stop.set(true);
      } else {
        Answer a = new Answer(name, message);
        write(a);
        AnswerList.add(a);
      }
      bfr.close();

    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private synchronized void write(Answer answer) {
    String toAdd = "@newAnswer" + answer.name + "@separator" + answer.message + "@seperator";
    //TODO add the part where it actually writes
  }
}
