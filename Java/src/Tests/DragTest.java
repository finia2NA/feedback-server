package Tests;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import acm.graphics.GOval;
import acm.program.GraphicsProgram;

@SuppressWarnings("serial")
public class DragTest extends GraphicsProgram {
  GOval activeElement;

  public void init() {
    GOval g = new GOval(100, 100);
    g.setFilled(true);
    g.setFillColor(Color.black);
    add(g);
    activeElement = g;
    addMouseListeners();
    addKeyListeners();
  }

  public void run() {

  }

  @Override
  public void mouseDragged(MouseEvent e) {
    GOval g = (GOval) getGCanvas().getElementAt(e.getX(), e.getY());
    if (g == null)
      return;
    double xMidOffset = 1.0 / 2.0 * g.getBounds().getWidth();
    double yMidOffset = 1.0 / 2.0 * g.getBounds().getHeight();

    g.setLocation(e.getX() - xMidOffset, e.getY() - yMidOffset);
  }

  @Override
  public void keyTyped(KeyEvent e) {
    println("here!");
    if(e.getKeyChar()=='q') {
      activeElement.setSize(2*activeElement.getSize().getWidth(), 2*activeElement.getSize().getHeight());
    }
    if(e.getKeyChar()=='e') {
      activeElement.setSize(0.5*activeElement.getSize().getWidth(), 0.5*activeElement.getSize().getHeight());
    }
     
  }
}
