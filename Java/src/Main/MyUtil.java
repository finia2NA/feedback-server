package Main;

public class MyUtil {
  public static boolean isIntegerString(String s) {
    String[] parts = s.split("\\s+");
    for (String p : parts) {
      try {
        Integer.parseInt(p);
      } catch (NumberFormatException e) {
        return false;
      }
    }
    return true;
  }
}
