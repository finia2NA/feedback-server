import java.io.BufferedReader;
import java.io.FileReader;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import acm.program.GraphicsProgram;

@SuppressWarnings("serial")
public class Main extends GraphicsProgram {
  final static boolean DEBUG = true;

  final static Path path = Paths.get("O:\\Studium\\Kompetenz\\feedback-server\\Node\\data");
  WatchService watchService;

  ArrayList<Answer> AnswerQueue;

  boolean stopScan = false;

  public void init() {
    AnswerQueue = new ArrayList<Answer>();
    try {
      watchService = FileSystems.getDefault().newWatchService();
      path.register(watchService, StandardWatchEventKinds.ENTRY_CREATE, StandardWatchEventKinds.ENTRY_DELETE,
          StandardWatchEventKinds.ENTRY_MODIFY);
      println("Expecting data in: " + path);
      AnswerQueue = new ArrayList<Answer>();
      printIP();
    } catch (Exception e) {
    }
    // readLine("Press Enter to start");
  }

  public void run() {
    try {
      WatchService watchService = FileSystems.getDefault().newWatchService();

      path.register(watchService, StandardWatchEventKinds.ENTRY_CREATE, StandardWatchEventKinds.ENTRY_DELETE,
          StandardWatchEventKinds.ENTRY_MODIFY);

      WatchKey key;
      while ((key = watchService.take()) != null) {
        for (WatchEvent<?> event : key.pollEvents()) {
          if (DEBUG)
            System.out.println("Event:" + event.kind() + ". File affected: " + event.context() + ".");
          if (event.kind().equals(StandardWatchEventKinds.ENTRY_CREATE)) {
            addAnswerToQueue((Path) event.context());
          }
        }
        key.reset();
        if (stopScan)
          break;
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    if (DEBUG)
      println("exited scan mode!");
  }

  private void printIP() throws SocketException, UnknownHostException {
    DatagramSocket socket;
    socket = new DatagramSocket();
    socket.connect(InetAddress.getByName("8.8.8.8"), 10002);
    String ip = socket.getLocalAddress().getHostAddress();
    println("Local IP is " + ip);
    socket.close();
  }

  void addAnswerToQueue(Path context) {
    try {
      TimeUnit.MILLISECONDS.wait(30);
    } catch (InterruptedException e1) {
      // TODO Auto-generated catch block
      e1.printStackTrace();
    }
    try {
      FileReader fr = new FileReader(context.toFile());
      BufferedReader bfr = new BufferedReader(fr);
      String name = bfr.readLine();
      String message = "";
      String line;
      while ((line = bfr.readLine()) != null) {
        message += line;
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

class Answer {
  public String name;
  public String message;

  public Answer(String name, String message) {
    this.name = name;
    this.message = message;
  }

  public Answer(String name) {
    this.name = name;
  }

}