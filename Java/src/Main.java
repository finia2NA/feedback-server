import java.awt.event.MouseEvent;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import acm.program.GraphicsProgram;

@SuppressWarnings("serial")
public class Main extends GraphicsProgram {
  final static boolean DEBUG = true;
  final static Path path = Paths.get("O:\\Studium\\Kompetenz\\feedback-server\\Node\\data");

  int phase = 0;
  ArrayList<Answer> AnswerQueue;
  ArrayList<ArrayList<Answer>> zuordnungen;

  WatchThread watcher;

  public void init() {
    AnswerQueue = new ArrayList<Answer>();
    try {
      println("Expecting data in: " + path);

      AnswerQueue = new ArrayList<Answer>();
      printIP();
      addMouseListeners();
      zuordnungen = new ArrayList<ArrayList<Answer>>();
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
    int processed = 0;
    while (true) {
      if (processed < AnswerQueue.size()) {
        processAnswer(processed++);
      }
      if (phase > 0)
        break;

    }

  }

  private void processAnswer(int i) {
    // TODO Auto-generated method stub

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

  }
}
