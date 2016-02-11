package co.hodler.kaffeesatz.ui;

public class ConsolePrinter implements Printer {
  @Override
  public void print(String toPrint) {
    System.out.print(toPrint);
  }
}
