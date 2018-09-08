package Main;

public class Answer {
  public String name;
  public String message;
  private int[] zuordnungen;

  /**
   * @return the zuordnungen
   */

  public Answer(String name, String message) {
    this.name = name;
    this.message = message;
  }

  // public Answer(String name, String message, int[] zuordnungen) {
  // super();
  // this.name = name;
  // this.message = message;
  // this.zuordnungen = zuordnungen;
  // }

  public Answer(String name, String message, int... zuordnungen) {
    super();
    this.name = name;
    this.message = message;
    this.zuordnungen = zuordnungen;
  }

  public Answer(String name) {
    this.name = name;
  }

  public String toString() {
    return "'" + name + "' wrote: \n" + message;
  }

  public void setZuordnungen(int[] zuordnugen) {
    this.zuordnungen = zuordnugen;
  }

  public void setZuordnung(int... zuordnungen) {
    this.zuordnungen = zuordnungen;
  }

  public int[] getZuordnungen() {
    return zuordnungen;
  }
}