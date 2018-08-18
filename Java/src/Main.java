import java.awt.event.MouseEvent;
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

  int phase = 0;
  ArrayList<Answer> AnswerQueue;

  WatchThread watcher;

  public void init() {
    AnswerQueue = new ArrayList<Answer>();
    try {
      println("Expecting data in: " + path);

      AnswerQueue = new ArrayList<Answer>();
      printIP();
      addMouseListeners();
    } catch (Exception e) {
      e.printStackTrace();
    }
    // readLine("Press Enter to start");
  }

  public void run() {
    WatchThread watcher = new WatchThread();
    this.watcher = watcher;
    watcher.init(path, AnswerQueue, DEBUG);
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

  public void mouseClicked(MouseEvent e) {
    if (phase == 0) {
      watcher.stop();
      phase++;
    }

  }
}
