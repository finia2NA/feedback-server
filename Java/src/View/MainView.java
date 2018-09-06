package View;

import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;

import javax.swing.JButton;

import Main.Answer;
import acm.program.GraphicsProgram;
import acm.program.Program;

public class MainView {
  GraphicsProgram top;
  ListPanel[] ListPanels;
  boolean MLPs = false;
  boolean DEBUG = false;
  private ScrollingPanel activePanel;
  private ListPanel focus = null;
  private static int nMultiListpanels = 2;

  public MainView(GraphicsProgram top, int numberOfAreas) {
    commonCore(top, numberOfAreas);
  }

  public MainView(GraphicsProgram top, boolean MLPs, int numberOfAreas) {
    this.MLPs = MLPs;
    commonCore(top, numberOfAreas);
  }

  public MainView(GraphicsProgram top, boolean MLPs, int numberOfAreas, boolean DEBUG) {
    this.MLPs = MLPs;
    this.DEBUG = DEBUG;
    commonCore(top, numberOfAreas);
  }

  public MainView(GraphicsProgram top, int numberOfAreas, boolean DEBUG) {
    this.DEBUG = DEBUG;
    commonCore(top, numberOfAreas);
  }

  private void commonCore(GraphicsProgram top, int numberOfAreas) {
    this.top = top;

    // +1 weil die liste 0 alle antworten zeigt.
    ListPanels = new ListPanel[numberOfAreas + 1];
    for (int i = 0; i < numberOfAreas + 1; i++) {
      ListPanels[i] = new ListPanel(DEBUG);
      top.add(ListPanels[i]);
      ListPanels[i].setVisible(false);
      top.add(new JButton("" + i), Program.EAST);
    }

    ListPanels[0].setVisible(true);
    activePanel = ListPanels[0];
  }

  public void add(Answer a) {
    int[] toAdds = a.getZuordnungen();
    if (toAdds == null) {
      System.err.println("Please init ZUORDNUNGEN b4 adding to MainView");
      return;
    }
    if (toAdds.length == 0)
      return;

    ListPanels[0].add(a);

    for (int i : toAdds) {
      ListPanels[i].add(a);

    }
  }

  public void showPanel(int panel) {
    assert panel <= ListPanels.length;
    for (ListPanel lp : ListPanels)
      lp.setVisible(false);

    ListPanels[panel].setVisible(true);
    activePanel = ListPanels[panel];
  }

  public void mouseClicked(MouseEvent e) {
    if (focus == null) {
      SpeechBubble activeSP = activePanel.getSBat(e.getX(), e.getY());
      if (activeSP == null)
        return;
      activePanel.setVisible(false);
      focus = new ListPanel(DEBUG);
      focus.add(new Answer(activeSP.name, activeSP.message));
      top.add(focus);
    } else {
      top.remove(focus);
      focus = null;
      activePanel.setVisible(true);
    }
  }

  public void actionPerformed(ActionEvent e) {
    String label = e.getActionCommand();
    int identifier = Integer.parseInt(label);
    showPanel(identifier);
  }

}
