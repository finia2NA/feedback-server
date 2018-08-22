package Main;
public class Answer {
  public String name;
  public String message;

  public Answer(String name, String message) {
    this.name = name;
    this.message = message;
  }

  public Answer(String name) {
    this.name = name;
  }

  public String toString() {
    return "'" + name + "' wrote: \n" + message;

  }
}