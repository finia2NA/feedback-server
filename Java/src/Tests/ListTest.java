package Tests;

import Main.Answer;
import View.ListPanel;
import acm.program.GraphicsProgram;

@SuppressWarnings("serial")
public class ListTest extends GraphicsProgram {
  public void run() {
    ListPanel lp = new ListPanel();
    add(lp);
    lp.add(new Answer("Finite","Das ist ein Test"));
    lp.add(new Answer("George","Hallo, ich bin George!"));
    lp.scroll(50);
  }
}
