import java.awt.Color;

import acm.graphics.GCompound;
import acm.graphics.GLabel;
import acm.graphics.GRoundRect;

public class SpeechBubble extends GCompound {
  String name;
  String message;
  Color color = Color.lightGray;

  public SpeechBubble(String name, String message) {
    this.name = name;
    this.message = message;
    initGraphics();
  }

  public SpeechBubble(String name, String message, Color color) {
    this.name = name;
    this.message = message;
    this.color = color;
    initGraphics();
  }

  private void initGraphics() {
    GLabel nameLabel = new GLabel(name);
    nameLabel.setFont("*-*-30");
    nameLabel.setLocation(0, nameLabel.getSize().getHeight());
    GLabel messageLabel = new GLabel(message);
    messageLabel.setFont("*-*-20");
    messageLabel.setLocation(nameLabel.getX(), nameLabel.getY() + 20);
    add(nameLabel);
    add(messageLabel);
    GRoundRect outline = new GRoundRect(getSize().getWidth() + 0, getSize().getHeight());
    outline.setLocation(this.getBounds().getLocation());
    outline.move(-5, 0);
    outline.setFilled(true);
    outline.setFillColor(color);
    add(outline);
    outline.sendToBack();
  }

  private static GCompound generateMessageCompound(String message) {
    GCompound messageCompound = new GCompound();
    return messageCompound;
  }

}
