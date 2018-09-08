package View;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;

import acm.graphics.GCompound;
import acm.graphics.GLabel;
import acm.graphics.GRoundRect;

//TODO: Iwie funktioniert die MESSAGEFONTSIZE nicht. investigate.
@SuppressWarnings("serial")
public class SpeechBubble extends GCompound {
  String name;
  String message;
  Color color = Color.lightGray;
  final static int NAMEFONTSIZE = 50;
  public final static int MESSAGEFONTSIZE = 35;
  public final static String FONT = "*";
  // Entscheidet über Zeilenumbruch
  final static double MAXWIDTH = 800;

  final static boolean DEBUG = true;

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
    nameLabel.setFont(FONT + "-*-" + NAMEFONTSIZE);
    nameLabel.setLocation(5, nameLabel.getSize().getHeight());
    GCompound messageCompound = generateMessageCompound(message);
    add(nameLabel);
    messageCompound.setLocation(nameLabel.getX() + 5, nameLabel.getY() + 30);
    add(messageCompound);
    GRoundRect outline = new GRoundRect(this.getSize().getWidth() + 10, this.getSize().getHeight());
    outline.setLocation(nameLabel.getX() - 5, nameLabel.getY() - nameLabel.getHeight() + 10);
    outline.setFilled(true);
    outline.setColor(color);
    add(outline);
    outline.sendToBack();
  }

  private static GCompound generateMessageCompound(String message) {
    GCompound messageCompound = new GCompound();
    // if the message itself was below MAXWIDTH we can just return a compound with a
    // single GLabel containing the message.
    {
      GLabel firstAttempt = new GLabel(message);
      firstAttempt.setFont(FONT + "-*-" + MESSAGEFONTSIZE);
      if (firstAttempt.getSize().getWidth() <= MAXWIDTH) {
        messageCompound.add(firstAttempt);
        return messageCompound;
      }
    }
    // Words need to be seperated by spaces, else i'll get into problems.
    message.replace("\n", " ");
    /*
     * if we're here the message is longer than 1 line. To have the best wordwrap,
     * we should break between words. So, we have to split the original String into
     * words first. The best way to do that is with a RegEx stolen from
     * StackOverflow.
     */
    String[] words = message.split("\\s+");
    // Now we can generate the lines so that they will be narrower than MAXWIDTH.
    GLabel space = new GLabel(" ");
    space.setFont(FONT + "-*-" + MESSAGEFONTSIZE);
    double spaceWidth = space.getSize().getWidth();
    ArrayList<String> lines = getLines(words, spaceWidth);

    double messageOffset = space.getSize().getHeight();
    for (int i = 0; i < lines.size(); i++) {
      String line = lines.get(i);
      GLabel lineLabel = new GLabel(line);
      lineLabel.setFont(FONT + "-*-" + MESSAGEFONTSIZE);
      lineLabel.move(0, i * messageOffset);
      messageCompound.add(lineLabel);
    }

    return messageCompound;
  }

  /**
   *
   * @param words
   * @param spaceWidth
   * @return Lines so that every line is <MAXWIDTH.
   */
  public static ArrayList<String> getLines(String[] words, double spaceWidth) {
    ArrayList<Integer> breaks = new ArrayList<Integer>();

    double currentLineWidth = 0;
    for (int i = 0; i < words.length; i++) {
      String word = words[i];
      GLabel wordLabel = new GLabel(word);
      wordLabel.setFont(FONT + "-*-" + MESSAGEFONTSIZE);
      double wordWidth = wordLabel.getSize().getWidth();
      if (wordWidth > MAXWIDTH && DEBUG) {
        System.err.println("warning: single word longer than MAXWIDTH: will fuck shit up");
      }
      if (currentLineWidth + spaceWidth + wordWidth <= MAXWIDTH) {
        // in this case the new word fits into our current line
        currentLineWidth += spaceWidth + wordWidth;
      } else {
        // in this case we need to add a break at this word and start a new line.
        breaks.add(i);
        currentLineWidth = wordWidth;
      }
    }
    // Let's not forget to add the last line.
    breaks.add(words.length);

    // Generate the lines from the words and breakpoints.
    ArrayList<String> re = new ArrayList<String>();
    int lastBreak = 0;
    for (int i = 0; i < breaks.size(); i++) {
      int currentBreak = breaks.get(i);
      String[] sub = Arrays.copyOfRange(words, lastBreak, currentBreak);
      String atm = "";

      for (String word : sub) {
        atm += word + " ";
      }
      atm = atm.trim();
      re.add(atm);

      lastBreak = currentBreak;
    }
    return re;
  }
}
