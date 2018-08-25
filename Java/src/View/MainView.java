package View;

import java.awt.event.MouseEvent;

import Main.Answer;
import acm.program.GraphicsProgram;

public class MainView {
  GraphicsProgram top;
  ListPanel[] ListPanels;
  CanvasPanel[] CanvasPanels;
  final static boolean useCPs = false;
  boolean DEBUG = false;
  private Panel activePanel;
  private ListPanel focus = null;

  public MainView(GraphicsProgram top, int numberOfAreas) {
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
    }
    if (useCPs) {
      CanvasPanels = new CanvasPanel[numberOfAreas + 1];
      for (int i = 0; i < numberOfAreas + 1; i++) {
        CanvasPanels[i] = new CanvasPanel(DEBUG);
        top.add(CanvasPanels[i]);
        CanvasPanels[i].setVisible(false);
      }
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
    if (useCPs)
      CanvasPanels[0].add(a);
    for (int i : toAdds) {
      ListPanels[i].add(a);
      if (useCPs)
        CanvasPanels[i].add(a);
    }
  }

  public void showPanel(boolean canvas, int panel) {
    assert !(canvas && !useCPs);
    assert panel <= ListPanels.length;
    for (ListPanel lp : ListPanels)
      lp.setVisible(false);
    if (useCPs)
      for (CanvasPanel cp : CanvasPanels)
        cp.setVisible(false);

    (canvas ? CanvasPanels[panel] : ListPanels[panel]).setVisible(true);
  }

  public void mouseDragged(MouseEvent e) {
    if (activePanel instanceof CanvasPanel)
      ((CanvasPanel) activePanel).drag(e);
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

}
