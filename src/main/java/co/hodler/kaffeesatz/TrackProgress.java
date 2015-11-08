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
    if (trackingHasBegun())
      displayProgressBar.begin();
    commitCounter = timesTracked() + 1;

    if (commitCounter == commitAmount)
      displayProgressBar.full();
    else if (percentageReached(0.1))
      displayProgressBar.tenPercentDone();
    else if (percentageReached(0.2))
      displayProgressBar.twentyPercentDone();
    else if (percentageReached(0.3))
      displayProgressBar.thirtyPercentDone();
    else if (percentageReached(0.4))
      displayProgressBar.fourtyPercentDone();
    else if (percentageReached(0.5))
      displayProgressBar.fiftyPercentDone();
    else if (percentageReached(0.6))
      displayProgressBar.sixtyPercentDone();
    else if (percentageReached(0.7))
      displayProgressBar.seventyPercentDone();
    else if (percentageReached(0.8))
      displayProgressBar.eightyPercentDone();
    else if (percentageReached(0.9))
      displayProgressBar.ninetyPercentDone();
  }

  private boolean trackingHasBegun() {
    commitCounter = timesTracked();
    return commitCounter == 0;
  }

  protected int timesTracked() {
    return commitCounter;
  }

  private boolean percentageReached(double percentage) {
    return commitCounter == Math.round(commitAmount*percentage);
  }
}
