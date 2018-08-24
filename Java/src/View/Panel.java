package View;

import java.awt.event.MouseEvent;
import java.util.ArrayList;

import Main.Answer;
import acm.graphics.GCompound;

@SuppressWarnings("serial")
public abstract class Panel extends GCompound {
  GCompound content = new GCompound();
  ArrayList<SpeechBubble> SpeechBubbles = new ArrayList<SpeechBubble>();

  public abstract void add(Answer answer);

  public SpeechBubble getSPat(MouseEvent e) {
    // TODO implement
    return null;
  }
}
