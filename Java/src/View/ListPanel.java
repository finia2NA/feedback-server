package View;

import java.util.ArrayList;

import Main.Answer;
import acm.graphics.GCompound;

/**
 * A class to display SpeechBubbles in a scrollable list.
 *
 */
@SuppressWarnings("serial")
public class ListPanel extends GCompound {
  GCompound content = new GCompound();
  ArrayList<SpeechBubble> SpeechBubbles = new ArrayList<SpeechBubble>();
  private final static double DISTANCE_BETWEEN_SBS = 10;

  public ListPanel() {
    super();
    content.move(10, 0);
    add(content);
  }

  public void scroll(double howMuch) {
    content.move(0, howMuch);
  }

  public void resetScroll() {
    content.setLocation(0, 0);
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
