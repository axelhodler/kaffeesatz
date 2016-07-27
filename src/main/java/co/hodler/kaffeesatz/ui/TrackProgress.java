package co.hodler.kaffeesatz.ui;

import co.hodler.kaffeesatz.model.CommitCount;
import co.hodler.kaffeesatz.model.Progress;

import javax.inject.Inject;

public class TrackProgress {

  private Progress currentProgress = new Progress(0);
  private DisplayProgressBar displayProgressBar;
  private CommitCount commitAmount;
  private int commitCounter;

  @Inject
  public TrackProgress(DisplayProgressBar displayProgressBar, CommitCount commitCount) {
    this.displayProgressBar = displayProgressBar;
    this.commitAmount = commitCount;
  }

  public void track() {
    if (trackingHasBegun()) {
      displayProgressBar.withPercentageDone(new Progress(currentProgress.intValue()));
      currentProgress.increaseByTen();
    }
    commitCounter += 1;

    if (lastCommit())
      displayProgressBar.withPercentageDone(new Progress(100));

    if (currentProgress.areNextTenPercentReached(commitCounter, commitAmount)) {
      displayProgressBar.withPercentageDone(new Progress(currentProgress.intValue()));
      currentProgress.increaseByTen();
    }
  }

  private boolean lastCommit() {
    return commitCounter == commitAmount.intValue();
  }

  private boolean trackingHasBegun() {
    return commitCounter == 0;
  }
}
