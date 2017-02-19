package co.hodler.kaffeesatz.ui;

import co.hodler.kaffeesatz.model.Progress;

public class TerminalDisplayProgressBar implements DisplayProgressBar {

  private Printer printer;

  public TerminalDisplayProgressBar(Printer printer) {
    this.printer = printer;
  }

  @Override
  public void full() {
    printer.display("|==========|");
  }

  @Override
  public void withPercentageDone(final Progress progress) {
    printer.display(createProgressBarWithEqualSignAmountOf(progress.intValue()/10));
  }

  private String createProgressBarWithEqualSignAmountOf(int amountOfEqualSigns) {
    return "|" + repeatEqualSignTimes(amountOfEqualSigns)
        .concat(">")
        .concat(repeatSpacesTimes(9 - amountOfEqualSigns))
        .concat("|");
  }

  private String repeatEqualSignTimes(int amountOfEqualSigns) {
    return new String(new char[amountOfEqualSigns]).replace("\0", "=");
  }

  private String repeatSpacesTimes(int amountOfSpaces) {
    return new String(new char[amountOfSpaces]).replace("\0", " ");
  }
}
