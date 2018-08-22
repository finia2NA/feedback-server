package Tests;
import GraphicsElements.SpeechBubble;
import acm.program.GraphicsProgram;

public class GraphicsTester extends GraphicsProgram {
  public void run() {
    SpeechBubble sb = new SpeechBubble("finite", "hi there das hier ist ein test. er kann beliebig lang sein, es wird dann aber entsprechend irgendwann zu problemen kommen. das hier war ein zeilenumbruch.");
    sb.setLocation(10, 10);
    add(sb);
    println(sb.getSize());
  }
}
