package co.hodler.kaffeesatz.ui;

import co.hodler.kaffeesatz.model.CommitCount;
import co.hodler.kaffeesatz.model.Progress;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class TrackProgressShould {

  @Mock
  DisplayProgressBar displayProgressBar;

  private TestableTrackProgress trackProgress;

  @Test
  public void triggerBeginningDisplayOnFirstCall() {
    trackProgress = new TestableTrackProgress(displayProgressBar, new CommitCount(0));

    trackProgress.track();

    verify(displayProgressBar).withPercentageDone(new Progress(0));
  }

  @Test
  public void triggerTenPercentMarkAs10PercentOfTheCommitsIsReached() {
    trackProgress = new TestableTrackProgress(displayProgressBar, new CommitCount(10));

    trackProgress.track();

    verify(displayProgressBar).withPercentageDone(new Progress(10));
  }

  @Test
  public void thrityPercentMarkIsReachedOnSixthCommitOfTwenty() {
    trackProgress = new TestableTrackProgress(displayProgressBar, new CommitCount(20));
    trackProgress.timesTracked = 5;

    trackProgress.track();

    verify(displayProgressBar).withPercentageDone(new Progress(30));
  }

  @Test
  public void triggerFullProgressMarkAsTheLastCommitIsReached() {
    trackProgress = new TestableTrackProgress(displayProgressBar, new CommitCount(1));

    trackProgress.track();

    verify(displayProgressBar).withPercentageDone(new Progress(100));
  }

  @Test
  public void triggerTwentyPercentAsTwentyPercentOfCommitsIsReached() {
    trackProgress = new TestableTrackProgress(displayProgressBar, new CommitCount(10));
    trackProgress.timesTracked = 1;

    trackProgress.track();

    verify(displayProgressBar).withPercentageDone(new Progress(20));
  }

  @Test
  public void tenPercentIsTwoCommitsOutOf20() {
    trackProgress = new TestableTrackProgress(displayProgressBar, new CommitCount(20));
    trackProgress.timesTracked = 1;

    trackProgress.track();

    verify(displayProgressBar).withPercentageDone(new Progress(10));
  }

  @Test
  public void thirtyPercentOfTenCommitsIsThreeCommits() {
    trackProgress = new TestableTrackProgress(displayProgressBar, new CommitCount(10));
    trackProgress.timesTracked = 2;

    trackProgress.track();

    verify(displayProgressBar).withPercentageDone(new Progress(30));
  }

  @Test
  public void thirtyPercentOfFifteenCommitsIsFiveCommits() {
    trackProgress = new TestableTrackProgress(displayProgressBar, new CommitCount(15));
    trackProgress.timesTracked = 4;

    trackProgress.track();

    verify(displayProgressBar).withPercentageDone(new Progress(30));
  }

  @Test
  public void twentyPercentOfSeventeenCommitsIsThreeCommits() {
    trackProgress = new TestableTrackProgress(displayProgressBar, new CommitCount(17));
    trackProgress.timesTracked = 2;

    trackProgress.track();

    verify(displayProgressBar).withPercentageDone(new Progress(20));
  }

  @Test
  public void fourtyPercentOfTenCommitsIsFourCommits() {
    trackProgress = new TestableTrackProgress(displayProgressBar, new CommitCount(10));
    trackProgress.timesTracked = 3;

    trackProgress.track();

    verify(displayProgressBar).withPercentageDone(new Progress(40));
  }

  @Test
  public void fiftyPercentOfTenCommitsIsFiveCommits() {
    trackProgress = new TestableTrackProgress(displayProgressBar, new CommitCount(10));
    trackProgress.timesTracked = 4;

    trackProgress.track();

    verify(displayProgressBar).withPercentageDone(new Progress(50));
  }

  @Test
  public void sixtyPercentOfTenCommitsIsSixCommits() {
    trackProgress = new TestableTrackProgress(displayProgressBar, new CommitCount(10));
    trackProgress.timesTracked = 5;

    trackProgress.track();

    verify(displayProgressBar).withPercentageDone(new Progress(60));
  }

  @Test
  public void seventyPercentOfTenCommitsIsSevenCommits() {
    trackProgress = new TestableTrackProgress(displayProgressBar, new CommitCount(10));
    trackProgress.timesTracked = 6;

    trackProgress.track();

    verify(displayProgressBar).withPercentageDone(new Progress(70));
  }

  @Test
  public void eightyPercentOfTenCommitsIsSevenCommits() {
    trackProgress = new TestableTrackProgress(displayProgressBar, new CommitCount(10));
    trackProgress.timesTracked = 7;

    trackProgress.track();

    verify(displayProgressBar).withPercentageDone(new Progress(80));
  }

  @Test
  public void ninetyPercentOfTenCommitsIsSevenCommits() {
    trackProgress = new TestableTrackProgress(displayProgressBar, new CommitCount(10));
    trackProgress.timesTracked = 8;

    trackProgress.track();

    verify(displayProgressBar).withPercentageDone(new Progress(90));
  }

  @Test
  public void tenPercentOfFifteenIsTwoCommits() {
    trackProgress = new TestableTrackProgress(displayProgressBar, new CommitCount(15));
    trackProgress.timesTracked = 1;

    trackProgress.track();

    verify(displayProgressBar).withPercentageDone(new Progress(10));
  }

  class TestableTrackProgress extends TrackProgress {

    public int timesTracked = 0;

    public TestableTrackProgress(DisplayProgressBar displayProgressBar,
        CommitCount commitCount) {
      super(displayProgressBar, commitCount);
    }

    @Override
    protected int timesTracked() {
      return timesTracked;
    }
  }
}
