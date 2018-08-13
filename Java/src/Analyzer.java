import acm.program.GraphicsProgram;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class Analyzer extends GraphicsProgram {

  public void init() {
    DatagramSocket socket;
    try {
      socket = new DatagramSocket();
      socket.connect(InetAddress.getByName("8.8.8.8"), 10002);
      String ip = socket.getLocalAddress().getHostAddress();
      println("Local IP is " + ip);
    } catch (SocketException | UnknownHostException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    readLine("Press Enter to start ")
  }
}
