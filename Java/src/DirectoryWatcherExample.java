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

import acm.program.GraphicsProgram;

@SuppressWarnings("serial")
public class DirectoryWatcherExample extends GraphicsProgram {

  final static Path path = Paths.get("O:\\Studium\\Kompetenz\\feedback-server\\Node\\data");
  WatchService watchService;

  public void init() {
    try {
      watchService = FileSystems.getDefault().newWatchService();
      path.register(watchService, StandardWatchEventKinds.ENTRY_CREATE, StandardWatchEventKinds.ENTRY_DELETE,
          StandardWatchEventKinds.ENTRY_MODIFY);
      println("Expecting data in: " + path);
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
          System.out.println("Event kind:" + event.kind() + ". File affected: " + event.context() + ".");
        }
        key.reset();
      }
    } catch (

    Exception e) {

    }
  }

  private void printIP() throws SocketException, UnknownHostException {
    DatagramSocket socket;
    socket = new DatagramSocket();
    socket.connect(InetAddress.getByName("8.8.8.8"), 10002);
    String ip = socket.getLocalAddress().getHostAddress();
    println("Local IP is " + ip);
  }

}