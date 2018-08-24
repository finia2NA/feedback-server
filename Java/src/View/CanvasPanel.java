package View;

import java.awt.event.MouseEvent;

import Main.Answer;

@SuppressWarnings("serial")
public class CanvasPanel extends Panel {
  public CanvasPanel() {
    super();
    content.move(10, 0);
    add(content);
  }

  @Override
  public void add(Answer answer) {
    // TODO Auto-generated method stub
    SpeechBubble sb = new SpeechBubble(answer.name, answer.message);
    // TODO: spacing am Anfang
    SpeechBubbles.add(sb);
    content.add(sb);
  }

  public void drag(MouseEvent e) {
    // TODO Auto-generated method stub

  }

}
