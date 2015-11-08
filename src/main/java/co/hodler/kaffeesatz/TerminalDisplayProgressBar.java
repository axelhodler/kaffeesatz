package co.hodler.kaffeesatz;

public class TerminalDisplayProgressBar implements DisplayProgressBar {

  @Override
  public void begin() {
    System.out.print(">         \r");
  }

  @Override
  public void full() {
    System.out.print("==========\r");
  }

  @Override
  public void tenPercentDone() {
    System.out.print("=>        \r");
  }

  @Override
  public void twentyPercentDone() {

  }

  @Override
  public void thirtyPercentDone() {

  }

  @Override
  public void fourtyPercentDone() {

  }

  @Override
  public void fiftyPercentDone() {

  }

  @Override
  public void sixtyPercentDone() {

  }

  @Override
  public void seventyPercentDone() {

  }

  @Override
  public void eightyPercentDone() {

  }

  @Override
  public void ninetyPercentDone() {

  }

}
