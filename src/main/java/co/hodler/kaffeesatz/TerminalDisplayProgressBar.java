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
    print(createProgressBarWithEqualSignAmountOf(1));
  }

  @Override
  public void twentyPercentDone() {
    print(createProgressBarWithEqualSignAmountOf(2));
  }

  @Override
  public void thirtyPercentDone() {
    print(createProgressBarWithEqualSignAmountOf(3));
  }

  @Override
  public void fourtyPercentDone() {
    print(createProgressBarWithEqualSignAmountOf(4));
  }

  @Override
  public void fiftyPercentDone() {
    print(createProgressBarWithEqualSignAmountOf(5));
  }

  @Override
  public void sixtyPercentDone() {
    print(createProgressBarWithEqualSignAmountOf(6));
  }

  @Override
  public void seventyPercentDone() {
    print(createProgressBarWithEqualSignAmountOf(7));
  }

  @Override
  public void eightyPercentDone() {
    print(createProgressBarWithEqualSignAmountOf(8));
  }

  @Override
  public void ninetyPercentDone() {
    print(createProgressBarWithEqualSignAmountOf(9));
  }

  private String createProgressBarWithEqualSignAmountOf(int amountOfEqualSigns) {
    return repeatEqualSignTimes(amountOfEqualSigns)
        .concat(">")
        .concat(repeatSpacesTimes(9 - amountOfEqualSigns))
        .concat("\r");
  }

  private String repeatEqualSignTimes(int amountOfEqualSigns) {
    return new String(new char[amountOfEqualSigns]).replace("\0", "=");
  }

  private String repeatSpacesTimes(int amountOfSpaces) {
    return new String(new char[amountOfSpaces]).replace("\0", " ");
  }

  private void print(String toPrint) {
    System.out.print(toPrint);
  }
}
