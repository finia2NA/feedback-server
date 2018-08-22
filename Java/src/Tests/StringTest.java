package Tests;

import java.util.ArrayList;

import GraphicsElements.SpeechBubble;
import acm.graphics.GLabel;

public class StringTest {
  public static void main(String[] args) {
    test1();
  }

  private static void test1() {
    String data = DataSet1.getLongText();
    String[] words = data.split("\\s+");
    GLabel space = new GLabel(" ");
    space.setFont(SpeechBubble.FONT + "-*-" + SpeechBubble.MESSAGEFONTSIZE);
    double spaceWidth = space.getSize().getWidth();
    ArrayList<String> re = SpeechBubble.getLines(words, spaceWidth);
    int vier = 5;

  }

  private static void test2() {
    String satz = "Fischers Fritz fischt frische Fische";
    String[] words = satz.split("\\s+");
    GLabel a = new GLabel(satz);
    double length1 = a.getSize().getWidth();
    System.out.println(length1);
    double length2 = 0.0;
    for (String word : words) {
      GLabel wordLabel = new GLabel(word);
      length2 += wordLabel.getSize().getWidth();
    }
    length2 += (words.length - 1) * (new GLabel(" ").getSize().getWidth());
    System.out.println(length2);
  }
}
