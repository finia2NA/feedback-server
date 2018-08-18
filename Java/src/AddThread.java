import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class AddThread extends Thread {
  private Path path;
  @SuppressWarnings("unused")
  private Boolean stopScan;
  private ArrayList<Answer> AnswerQueue;

  public void init(ArrayList<Answer> AnswerQueue, Path path, Boolean stopScan) {
    this.path = path;
    this.AnswerQueue = AnswerQueue;
    this.stopScan = stopScan;
  }

  public void run() {
    try {
      TimeUnit.MILLISECONDS.wait(30);
    } catch (InterruptedException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    try {
      FileReader fr = new FileReader(path.toFile());
      BufferedReader bfr = new BufferedReader(fr);
      String name = bfr.readLine();
      String message = "";
      String line;
      while ((line = bfr.readLine()) != null) {
        message += "\n" + line;
      }
      if (name.equals("finia"))
        adminIntervention(message);
      else
        AnswerQueue.add(new Answer(name, message));
      bfr.close();

    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private void adminIntervention(String message) {
    if (message.equals("stopScan"))
      stopScan = true;
  }

}
