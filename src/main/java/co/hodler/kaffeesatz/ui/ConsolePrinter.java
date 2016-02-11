package co.hodler.kaffeesatz.ui;

public class ConsolePrinter implements Printer {
  @Override
  public void display(String toPrint) {
    System.out.print(toPrint + "\r");
  }
}
