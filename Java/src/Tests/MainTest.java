package Tests;

import java.awt.event.MouseEvent;

import Main.Answer;
import View.MainView;
import acm.program.GraphicsProgram;

@SuppressWarnings("serial")
public class MainTest extends GraphicsProgram {
  MainView mv;

  public void init() {
    mv = new MainView(this, 3);
    addMouseListeners();
  }

  public void run() {
    Answer a = new Answer("Marcel Davis", "Hallo, Mein Name ist Marcel Davis vong 1&1");
    a.setZuordnung(1, 3);
    mv.add(a);
    Answer b = new Answer("Dieter Dietrich", "HALLÍCHEM MOTHERFUCKER");
    b.setZuordnung(2, 3);
    mv.add(b);
    mv.showPanel(false, 2);
    mv.showPanel(false, 3);
    mv.showPanel(false, 0);
  }
  
  @Override
  public void mouseClicked(MouseEvent e) {
    mv.mouseClicked(e);
  }
}
