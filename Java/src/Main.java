import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.WatchService;
import java.util.ArrayList;

import acm.program.GraphicsProgram;

@SuppressWarnings("serial")
public class Main extends GraphicsProgram {
  final static boolean DEBUG = true;

  final static Path path = Paths.get("O:\\Studium\\Kompetenz\\feedback-server\\Node\\data");
  WatchService watchService;

  ArrayList<Answer> AnswerQueue;

  Boolean stopScan = false;

  public void init() {
    AnswerQueue = new ArrayList<Answer>();
    try {
      println("Expecting data in: " + path);

      AnswerQueue = new ArrayList<Answer>();
      printIP();
    } catch (Exception e) {
      e.printStackTrace();
    }
    // readLine("Press Enter to start");
  }

  public void run() {
    WatchThread watcher = new WatchThread();
    watcher.init(path, AnswerQueue, stopScan);
    watcher.start();

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

  public String toString() {
    return "'" + name + "' wrote: \n" + message;

  }

}