package Tests;

import java.util.ArrayList;

import View.SpeechBubble;
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
    for(String s : re)
      System.out.println(s);
  }
}
