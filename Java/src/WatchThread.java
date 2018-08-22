
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

public class WatchThread extends Thread {
  private boolean DEBUG = true;
  private Path path;
  ArrayList<Answer> AnswerQueue;
  AtomicBoolean stop = new AtomicBoolean(false);

  /**
   * 
   * @param path
   * @param AnswerQueue
   * @param DEBUG
   */
  public void init(Path path, ArrayList<Answer> AnswerQueue, boolean DEBUG) {
    this.AnswerQueue = AnswerQueue;
    this.path = path;
    this.DEBUG = DEBUG;
  }

  public void run() {
    if (path == null)
      throw new NullPointerException("Have you run init before run?");
    try {
      WatchService watchService = FileSystems.getDefault().newWatchService();

      path.register(watchService, StandardWatchEventKinds.ENTRY_CREATE, StandardWatchEventKinds.ENTRY_DELETE,
          StandardWatchEventKinds.ENTRY_MODIFY);

      WatchKey key;
      // (key = watchService.take()) != null
      while (!stop.get()) {
        key = watchService.take();
        for (WatchEvent<?> event : key.pollEvents()) {
          if (DEBUG && event.kind() == StandardWatchEventKinds.ENTRY_CREATE)
            System.out.println("File Creation recognized: " + event.context() + ".");
          if (event.kind().equals(StandardWatchEventKinds.ENTRY_CREATE)) {
            processAnswer((Path) event.context());
          }
        }
        key.reset();
      }

    } catch (Exception e) {
      e.printStackTrace();
    }
    if (DEBUG)
      System.out.println("exited scan mode!");
  }

  private void processAnswer(Path context) {
    ProcessThread adder = new ProcessThread();
    adder.init(AnswerQueue, context, DEBUG, stop);
    adder.start();
  }
}
