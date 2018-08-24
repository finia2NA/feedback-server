package Tests;

import Main.Answer;
import View.MainView;
import acm.program.GraphicsProgram;

@SuppressWarnings("serial")
public class MainTest extends GraphicsProgram {
  MainView mv;

  public void init() {
    mv = new MainView(this, 3);
  }

  public void run() {
    Answer a = new Answer("Marcel Davis", "Hallo, Mein Name ist Marcel Davis vong 1&1");
    a.setZuordnung(1,3);
    mv.add(a);
    mv.showPanel(false, 2);
    mv.showPanel(false, 3);
    mv.showPanel(false, 0);
  }
}
