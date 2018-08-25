package View;

import Main.Answer;
import acm.graphics.GPoint;

/**
 * A class to display SpeechBubbles in a scrollable list.
 *
 */
@SuppressWarnings("serial")
public class ListPanel extends Panel {
  private final static double DISTANCE_BETWEEN_SBS = 10;
  private final static GPoint base = new GPoint(10, 0);

  public ListPanel() {
    super();
    content.move(10, 0);
    add(content);
  }

  public ListPanel(boolean DEBUG) {
    super();
    this.DEBUG = DEBUG;
    content.move(10, 0);
    add(content);
  }

  public void scroll(double howMuch) {
    content.move(0, howMuch);
  }

  public void resetScroll() {
    content.setLocation(base);
  }

  public void add(Answer answer) {
    SpeechBubble sb = new SpeechBubble(answer.name, answer.message);
    if (!SpeechBubbles.isEmpty()) {
      // in this case we need to move the Speechbubble down the screen into its
      // correct position.
      SpeechBubble lastSB = SpeechBubbles.get(SpeechBubbles.size() - 1);
      sb.setLocation(0, lastSB.getY() + lastSB.getHeight() + DISTANCE_BETWEEN_SBS);
    }
    SpeechBubbles.add(sb);
    content.add(sb);
  }
}
