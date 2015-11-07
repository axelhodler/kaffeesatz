package co.hodler.kaffeesatz;

public class TrackProgress {

  private DisplayProgressBar displayProgressBar;
  private int commitAmount;

  public TrackProgress(DisplayProgressBar displayProgressBar, int commitAmount) {
    this.displayProgressBar = displayProgressBar;
    this.commitAmount = commitAmount;
  }

  public void track() {
    displayProgressBar.begin();
  }
}
