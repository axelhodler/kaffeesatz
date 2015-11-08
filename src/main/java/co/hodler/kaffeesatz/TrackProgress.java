package co.hodler.kaffeesatz;

public class TrackProgress {

  private DisplayProgressBar displayProgressBar;
  private int commitAmount;
  private int commitCounter;

  public TrackProgress(DisplayProgressBar displayProgressBar, int commitAmount) {
    this.displayProgressBar = displayProgressBar;
    this.commitAmount = commitAmount;
  }

  public void track() {
    if (commitCounter == 0)
      displayProgressBar.begin();
    commitCounter = timesTracked() + 1;
    if (commitCounter == commitAmount*0.3)
      displayProgressBar.thrityPercentDone();
    else if (commitCounter == commitAmount)
      displayProgressBar.full();
    else if (commitCounter == commitAmount*0.2)
      displayProgressBar.twentyPercentDone();
    else if (commitCounter == commitAmount*0.1)
      displayProgressBar.tenPercentDone();
  }

  protected int timesTracked() {
    return commitCounter;
  }
}
