package co.hodler.kaffeesatz.ui;

public class TerminalDisplayProgressBar implements DisplayProgressBar {

  @Override
  public void full() {
    print("|==========|\r");
  }

  @Override
  public void withPercentageDone(double percentage) {
    print(createProgressBarWithEqualSignAmountOf((int) (percentage * 10 + 0.5)));
  }

  private String createProgressBarWithEqualSignAmountOf(int amountOfEqualSigns) {
    return "|" + repeatEqualSignTimes(amountOfEqualSigns)
        .concat(">")
        .concat(repeatSpacesTimes(9 - amountOfEqualSigns))
        .concat("|\r");
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
