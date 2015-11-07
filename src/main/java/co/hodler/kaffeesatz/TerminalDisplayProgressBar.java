package co.hodler.kaffeesatz;

public class TerminalDisplayProgressBar {

  public void display() {
    System.out.println("progress");
  }

  public void begin() {
    System.out.print(">         \r");
  }

  public void full() {
    System.out.print("==========\r");
  }

  public void tenPercentDone() {
    System.out.print("=>        \r");
  }

}
