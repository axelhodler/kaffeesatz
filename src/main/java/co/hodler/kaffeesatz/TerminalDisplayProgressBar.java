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
    System.out.print("==>       \r");
  }

  @Override
  public void thirtyPercentDone() {
    System.out.print("===>      \r");
  }

  @Override
  public void fourtyPercentDone() {
    System.out.print("====>     \r");
  }

  @Override
  public void fiftyPercentDone() {
    System.out.print("=====>    \r");
  }

  @Override
  public void sixtyPercentDone() {
    System.out.print("======>   \r");
  }

  @Override
  public void seventyPercentDone() {
    System.out.print("=======>  \r");
  }

  @Override
  public void eightyPercentDone() {
    System.out.print("========> \r");
  }

  @Override
  public void ninetyPercentDone() {
    System.out.print("=========>\r");
  }

}
