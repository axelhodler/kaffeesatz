package co.hodler.kaffeesatz.ui;

import co.hodler.kaffeesatz.model.Progress;

import javax.inject.Inject;

public class TerminalDisplayProgressBar implements DisplayProgressBar {

  private Printer printer;

  @Inject
  public TerminalDisplayProgressBar(Printer printer) {
    this.printer = printer;
  }

  @Override
  public void full() {
    printer.print("|==========|\r");
  }

  @Override
  public void withPercentageDone(final Progress progress) {
    printer.print(createProgressBarWithEqualSignAmountOf(progress.intValue()/10));
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
}
