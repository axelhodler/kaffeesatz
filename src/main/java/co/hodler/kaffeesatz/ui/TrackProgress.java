package co.hodler.kaffeesatz.ui;

import co.hodler.kaffeesatz.boundaries.GitRepoInteractions;

import javax.inject.Inject;

public class TrackProgress {

  private DisplayProgressBar displayProgressBar;
  private int commitAmount;
  private int commitCounter;

  @Inject
  public TrackProgress(DisplayProgressBar displayProgressBar, GitRepoInteractions gitRepoInteractions) {
    this.displayProgressBar = displayProgressBar;
    this.commitAmount = gitRepoInteractions.provideCommitCount();
  }

  public void track() {
    if (trackingHasBegun())
      displayProgressBar.withPercentageDone(0);
    commitCounter = timesTracked() + 1;

    if (commitCounter == commitAmount)
      displayProgressBar.withPercentageDone(1);
    else if (percentageReached(0.1))
      displayProgressBar.withPercentageDone(0.1);
    else if (percentageReached(0.2))
      displayProgressBar.withPercentageDone(0.2);
    else if (percentageReached(0.3))
      displayProgressBar.withPercentageDone(0.3);
    else if (percentageReached(0.4))
      displayProgressBar.withPercentageDone(0.4);
    else if (percentageReached(0.5))
      displayProgressBar.withPercentageDone(0.5);
    else if (percentageReached(0.6))
      displayProgressBar.withPercentageDone(0.6);
    else if (percentageReached(0.7))
      displayProgressBar.withPercentageDone(0.7);
    else if (percentageReached(0.8))
      displayProgressBar.withPercentageDone(0.8);
    else if (percentageReached(0.9))
      displayProgressBar.withPercentageDone(0.9);
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
