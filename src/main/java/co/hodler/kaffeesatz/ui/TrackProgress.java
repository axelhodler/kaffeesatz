package co.hodler.kaffeesatz.ui;

import co.hodler.kaffeesatz.boundaries.GitRepoInteractions;
import co.hodler.kaffeesatz.model.Progress;

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
      displayProgressBar.withPercentageDone(new Progress(0));
    commitCounter = timesTracked() + 1;

    if (commitCounter == commitAmount)
      displayProgressBar.withPercentageDone(new Progress(100));
    else if (percentageReached(0.1))
      displayProgressBar.withPercentageDone(new Progress(10));
    else if (percentageReached(0.2))
      displayProgressBar.withPercentageDone(new Progress(20));
    else if (percentageReached(0.3))
      displayProgressBar.withPercentageDone(new Progress(30));
    else if (percentageReached(0.4))
      displayProgressBar.withPercentageDone(new Progress(40));
    else if (percentageReached(0.5))
      displayProgressBar.withPercentageDone(new Progress(50));
    else if (percentageReached(0.6))
      displayProgressBar.withPercentageDone(new Progress(60));
    else if (percentageReached(0.7))
      displayProgressBar.withPercentageDone(new Progress(70));
    else if (percentageReached(0.8))
      displayProgressBar.withPercentageDone(new Progress(80));
    else if (percentageReached(0.9))
      displayProgressBar.withPercentageDone(new Progress(90));
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
