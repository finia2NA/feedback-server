package Tests;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import Main.Answer;
import View.MainView;
import acm.program.GraphicsProgram;

@SuppressWarnings("serial")
public class MainTest extends GraphicsProgram {
  MainView mv;

  public void init() {
    mv = new MainView(this, true, 3);
    addMouseListeners();
    addActionListeners();
  }

  public void run() {
    Answer a = new Answer("Marcel Davis", "Hallo, Mein Name ist Marcel Davis vong 1&1");
    a.setZuordnung(1, 3);
    mv.add(a);
    Answer b = new Answer("Dieter Dietrich", "HALLÖCHEN MOTHERFUCKER");
    b.setZuordnung(2, 3);
    mv.add(b);
    mv.showPanel(2);
    mv.showPanel(3);
    mv.showPanel(0);
  }

  @Override
  public void mouseClicked(MouseEvent e) {
    mv.mouseClicked(e);
  }

  @Override
  public void keyTyped(KeyEvent e) {

  }

  @Override
  public void actionPerformed(ActionEvent e) {
    mv.actionPerformed(e);
  }
}
