package co.hodler.kaffeesatz;

public class TrackProgress {

  private TerminalDisplayProgressBar displayProgressBar;
  private int commitAmount;
  private int commitCounter;

  public TrackProgress(TerminalDisplayProgressBar displayProgressBar, int commitAmount) {
    this.displayProgressBar = displayProgressBar;
    this.commitAmount = commitAmount;
    this.commitCounter = 0;
  }

  public void track() {
    if (commitCounter == 0)
      displayProgressBar.begin();
    else if (commitCounter == commitAmount)
      displayProgressBar.full();
    else
      displayProgressBar.tenPercentDone();
    commitCounter++;
  }
}
