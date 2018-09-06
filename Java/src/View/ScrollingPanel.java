package View;

import java.util.ArrayList;

import Main.Answer;
import acm.graphics.GCompound;
import acm.graphics.GObject;

@SuppressWarnings("serial")
public abstract class ScrollingPanel extends GCompound {
  GCompound content = new GCompound();
  ArrayList<SpeechBubble> SpeechBubbles = new ArrayList<SpeechBubble>();
  boolean abstractDEBUG = false;
  boolean DEBUG = false;

  public abstract void add(Answer answer);

  public abstract void scroll(double amount);

  public abstract void resetScroll();

  public SpeechBubble getSBat(double x, double y) {
    GObject clicked = getElementAt(x, y);

    while (clicked instanceof GCompound && !(clicked instanceof SpeechBubble)) {
      x -= clicked.getX();
      y -= clicked.getY();
      clicked = ((GCompound) clicked).getElementAt(x, y);
    }
    if (abstractDEBUG)
      if (!(clicked == null))
        System.out.println(clicked.getClass());
    if (clicked instanceof SpeechBubble)
      return (SpeechBubble) clicked;
    else
      return null;
  }
}
