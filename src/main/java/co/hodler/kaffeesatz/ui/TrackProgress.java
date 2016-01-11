package co.hodler.kaffeesatz.ui;

import co.hodler.kaffeesatz.model.CommitCount;
import co.hodler.kaffeesatz.model.Progress;

import javax.inject.Inject;

public class TrackProgress {

  private DisplayProgressBar displayProgressBar;
  private CommitCount commitAmount;
  private int commitCounter;

  @Inject
  public TrackProgress(DisplayProgressBar displayProgressBar, CommitCount commitCount) {
    this.displayProgressBar = displayProgressBar;
    this.commitAmount = commitCount;
  }

  public void track() {
    if (trackingHasBegun())
      displayProgressBar.withPercentageDone(new Progress(0));
    commitCounter = timesTracked() + 1;

    if (commitCounter == commitAmount.intValue())
      displayProgressBar.withPercentageDone(new Progress(100));
    else if (percentageReached(new Progress(10)))
      displayProgressBar.withPercentageDone(new Progress(10));
    else if (percentageReached(new Progress(20)))
      displayProgressBar.withPercentageDone(new Progress(20));
    else if (percentageReached(new Progress(30)))
      displayProgressBar.withPercentageDone(new Progress(30));
    else if (percentageReached(new Progress(40)))
      displayProgressBar.withPercentageDone(new Progress(40));
    else if (percentageReached(new Progress(50)))
      displayProgressBar.withPercentageDone(new Progress(50));
    else if (percentageReached(new Progress(60)))
      displayProgressBar.withPercentageDone(new Progress(60));
    else if (percentageReached(new Progress(70)))
      displayProgressBar.withPercentageDone(new Progress(70));
    else if (percentageReached(new Progress(80)))
      displayProgressBar.withPercentageDone(new Progress(80));
    else if (percentageReached(new Progress(90)))
      displayProgressBar.withPercentageDone(new Progress(90));
  }

  private boolean trackingHasBegun() {
    commitCounter = timesTracked();
    return commitCounter == 0;
  }

  protected int timesTracked() {
    return commitCounter;
  }

  private boolean percentageReached(Progress progress) {
    return commitCounter == Math.round(commitAmount.intValue()*(((double)progress.intValue())/100));
  }
}
