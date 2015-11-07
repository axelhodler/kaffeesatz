package co.hodler.kaffeesatz;

public class TrackProgress {

  private DisplayProgressBar displayProgressBar;
  private int commitAmount;
  private int commitCounter;

  public TrackProgress(DisplayProgressBar displayProgressBar, int commitAmount) {
    this.displayProgressBar = displayProgressBar;
    this.commitAmount = commitAmount;
    this.commitCounter = 0;
  }

  public void track() {
    if (commitCounter == 0)
      displayProgressBar.begin();
    commitCounter++;
    if (commitCounter == 5)
      displayProgressBar.thrityPercentDone();
    else if (commitCounter == commitAmount)
      displayProgressBar.full();
    else if (commitCounter == commitAmount*0.2)
      displayProgressBar.twentyPercentDone();
    else if (commitCounter == 1)
      displayProgressBar.tenPercentDone();
  }
}
