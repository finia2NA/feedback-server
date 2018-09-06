package View;

import Main.Answer;

@SuppressWarnings("serial")
public class MultiListPanel extends ScrollingPanel {
  ListPanel[] lps;
  int nextUp = 0;

  public MultiListPanel(int howMany) {
    lps = new ListPanel[howMany];
    for (int i = 0; i < lps.length; i++) {
      lps[i] = new ListPanel();
      lps[i].move(0, i * SpeechBubble.MAXWIDTH);
    }
  }

  @Override
  public void add(Answer answer) {
    lps[nextUp++ % lps.length].add(answer);
  }

  @Override
  public void scroll(double amount) {
    for (int i = 0; i < lps.length; i++) {
      lps[i].scroll(amount);
    }

  }

  @Override
  public void resetScroll() {
    for (int i = 0; i < lps.length; i++) {
      lps[i].resetScroll();
    }

  }

}
