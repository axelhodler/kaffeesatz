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
    if (timesTracked() == 0)
      displayProgressBar.begin();
    ++commitCounter;
    if (timesTracked() == 6)
      displayProgressBar.thrityPercentDone();
    else if (timesTracked() == commitAmount)
      displayProgressBar.full();
    else if (timesTracked() == commitAmount*0.2)
      displayProgressBar.twentyPercentDone();
    else if (timesTracked() == 1)
      displayProgressBar.tenPercentDone();
  }

  protected int timesTracked() {
    return commitCounter;
  }
}
