package Tests;

import View.Panel;
import java.awt.event.MouseEvent;

import Main.Answer;
import View.CanvasPanel;
import View.SpeechBubble;
import acm.graphics.GCompound;
import acm.graphics.GObject;
import acm.program.GraphicsProgram;

@SuppressWarnings("serial")
public class CanvasTest extends GraphicsProgram {
  Panel p;
  private final static boolean DEBUG = true;

  @Override
  public void init() {
    addMouseListeners();
  }

  public void run() {
    CanvasPanel cp = new CanvasPanel();
    add(cp);
    p = cp;
    cp.add(new Answer("Finite", "Das ist ein Test"));
    cp.add(new Answer("George", "Hallo, ich bin George!"));
  }

  @Override
  public void mouseDragged(MouseEvent e) {
    getSBat(e.getX(), e.getY());
    GObject clicked = getSBat(e.getX(), e.getY());
    if (clicked == null)
      return;
    // else
    // p.setSBPosition(clicked,e.getX(),e.getY());
  }

  /**
   * returns the {@link SpeechBubble} at a given Position or {@code null} if there
   * is no {@link SpeechBubble} at that Position.
   * 
   * @param x
   * @param y
   * @return
   */
  public SpeechBubble getSBat(double x, double y) {
    GObject clicked = getElementAt(x, y);
    GCompound compound = null;
    if (clicked instanceof GCompound) {
      compound = (GCompound) clicked;
    }

    while (clicked instanceof GCompound && !(clicked instanceof SpeechBubble)) {
      x -= clicked.getX();
      y -= clicked.getY();
      clicked = ((GCompound) clicked).getElementAt(x, y);
    }
    if (DEBUG)
      if (!(clicked == null))
        println(clicked.getClass());
    if (clicked instanceof SpeechBubble)
      return (SpeechBubble) clicked;
    else
      return null;
  }
}
