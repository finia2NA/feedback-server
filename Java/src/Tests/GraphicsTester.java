package Tests;
import View.SpeechBubble;
import acm.program.GraphicsProgram;

@SuppressWarnings("serial")
public class GraphicsTester extends GraphicsProgram {
  public void run() {
    SpeechBubble sb = new SpeechBubble("finite", "hi there das hier ist ein test. er kann beliebig lang sein,"
        + " wenn alles gut geht gibt es dann einen Zeilenumbruch damit die Sprechblase kompakt bleibt."
        + " So wie ich das geschrieben habe habe ich Probleme mit sehr langen Wörtern, wie z.b."
//        + " Eierschalensollbruchstellenverursacherproduktionsfirma."
        + " Innerhalb von denen habe ich leider keinen Zeilenumbruch, aber ich denke das wird nicht eintreten."
        + " Und selbst wenn, dann ist die sprechblase ein bisschen größer, aber sonst passiert da nicht viel.");
    sb.setLocation(10, 10);
    add(sb);
    println(sb.getSize());
  }
}
