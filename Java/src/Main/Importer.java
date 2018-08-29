package Main;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Importer {
  private Importer() {

  }

  /**
   * Returns the Answers saved in a specific location or {@code null} if there are
   * no Answers there.
   * 
   * @param path
   * @param DEBUG
   * @return
   */
  public static Answer[] importFile(String path, boolean DEBUG) {
    if (DEBUG)
      System.out.println("ProcessThread started");
    FileReader fr;
    try {
      fr = new FileReader(path);
    } catch (FileNotFoundException e) {
      return null;
    }

    try {
      BufferedReader bfr = new BufferedReader(fr);
      // reaad all lines into memory
      String content;
      content = bfr.readLine();
      String line;
      while ((line = bfr.readLine()) != null) {
        // for the comment feature!
        if (!(line.trim().toCharArray()[0] == '#'))
          content += "\n" + line;
      }
      bfr.close();
      // convert all the messages to answers and return them
      String[] answers = content.split("@newAnswer");
      Answer[] re = new Answer[answers.length];
      for (int i = 0; i < answers.length; i++) {
        String atm = answers[i];
        String[] areas = atm.split("@separator");
        String name = areas[0];
        String message = areas[1];
        Answer answer = new Answer(name, message);
        String[] zuString = areas[2].split("\\s+");
        int[] zuordnungen = new int[zuString.length];
        for (int j = 0; j < zuString.length; j++) {
          zuordnungen[j] = Integer.parseInt(zuString[j]);
        }
        answer.setZuordnung(zuordnungen);
        re[i] = answer;
      }
      return re;

    } catch (IOException e) {
      System.err.println("error WHILE reading the file: " + path);
      if (DEBUG)
        e.printStackTrace();
      return null;
    }
  }
}
