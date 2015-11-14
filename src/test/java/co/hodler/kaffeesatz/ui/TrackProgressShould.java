package co.hodler.kaffeesatz.ui;

import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import co.hodler.kaffeesatz.ui.DisplayProgressBar;
import co.hodler.kaffeesatz.ui.TrackProgress;

@RunWith(MockitoJUnitRunner.class)
public class TrackProgressShould {

  @Mock
  DisplayProgressBar displayProgressBar;

  private TestableTrackProgress trackProgress;

  @Test
  public void triggerBeginningDisplayOnFirstCall() {
    trackProgress = new TestableTrackProgress(displayProgressBar, 0);

    trackProgress.track();

    verify(displayProgressBar).begin();
  }

  @Test
  public void triggerTenPercentMarkAs10PercentOfTheCommitsIsReached() {
    trackProgress = new TestableTrackProgress(displayProgressBar, 10);

    trackProgress.track();

    verify(displayProgressBar).tenPercentDone();
  }

  @Test
  public void thrityPercentMarkIsReachedOnSixthCommitOfTwenty() {
    trackProgress = new TestableTrackProgress(displayProgressBar, 20);
    trackProgress.timesTracked = 5;

    trackProgress.track();

    verify(displayProgressBar).thirtyPercentDone();
  }

  @Test
  public void triggerFullProgressMarkAsTheLastCommitIsReached() {
    trackProgress = new TestableTrackProgress(displayProgressBar, 1);

    trackProgress.track();

    verify(displayProgressBar).full();
  }

  @Test
  public void triggerTwentyPercentAsTwentyPercentOfCommitsIsReached() {
    trackProgress = new TestableTrackProgress(displayProgressBar, 10);
    trackProgress.timesTracked = 1;

    trackProgress.track();

    verify(displayProgressBar).twentyPercentDone();
  }

  @Test
  public void tenPercentIsTwoCommitsOutOf20() {
    trackProgress = new TestableTrackProgress(displayProgressBar, 20);
    trackProgress.timesTracked = 1;

    trackProgress.track();

    verify(displayProgressBar).tenPercentDone();
  }

  @Test
  public void thirtyPercentOfTenCommitsIsThreeCommits() {
    trackProgress = new TestableTrackProgress(displayProgressBar, 10);
    trackProgress.timesTracked = 2;

    trackProgress.track();

    verify(displayProgressBar).thirtyPercentDone();
  }

  @Test
  public void thirtyPercentOfFifteenCommitsIsFiveCommits() {
    trackProgress = new TestableTrackProgress(displayProgressBar, 15);
    trackProgress.timesTracked = 4;

    trackProgress.track();

    verify(displayProgressBar).thirtyPercentDone();
  }

  @Test
  public void twentyPercentOfSeventeenCommitsIsThreeCommits() {
    trackProgress = new TestableTrackProgress(displayProgressBar, 17);
    trackProgress.timesTracked = 2;

    trackProgress.track();

    verify(displayProgressBar).twentyPercentDone();
  }

  @Test
  public void fourtyPercentOfTenCommitsIsFourCommits() {
    trackProgress = new TestableTrackProgress(displayProgressBar, 10);
    trackProgress.timesTracked = 3;

    trackProgress.track();

    verify(displayProgressBar).fourtyPercentDone();
  }

  @Test
  public void fiftyPercentOfTenCommitsIsFiveCommits() {
    trackProgress = new TestableTrackProgress(displayProgressBar, 10);
    trackProgress.timesTracked = 4;

    trackProgress.track();

    verify(displayProgressBar).fiftyPercentDone();
  }

  @Test
  public void sixtyPercentOfTenCommitsIsSixCommits() {
    trackProgress = new TestableTrackProgress(displayProgressBar, 10);
    trackProgress.timesTracked = 5;

    trackProgress.track();

    verify(displayProgressBar).sixtyPercentDone();
  }

  @Test
  public void seventyPercentOfTenCommitsIsSevenCommits() {
    trackProgress = new TestableTrackProgress(displayProgressBar, 10);
    trackProgress.timesTracked = 6;

    trackProgress.track();

    verify(displayProgressBar).seventyPercentDone();
  }

  @Test
  public void eightyPercentOfTenCommitsIsSevenCommits() {
    trackProgress = new TestableTrackProgress(displayProgressBar, 10);
    trackProgress.timesTracked = 7;

    trackProgress.track();

    verify(displayProgressBar).eightyPercentDone();
  }

  @Test
  public void ninetyPercentOfTenCommitsIsSevenCommits() {
    trackProgress = new TestableTrackProgress(displayProgressBar, 10);
    trackProgress.timesTracked = 8;

    trackProgress.track();

    verify(displayProgressBar).ninetyPercentDone();
  }

  @Test
  public void tenPercentOfFifteenIsTwoCommits() {
    trackProgress = new TestableTrackProgress(displayProgressBar, 15);
    trackProgress.timesTracked = 1;

    trackProgress.track();

    verify(displayProgressBar).tenPercentDone();
  }

  class TestableTrackProgress extends TrackProgress {

    public int timesTracked = 0;

    public TestableTrackProgress(DisplayProgressBar displayProgressBar,
        int commitAmount) {
      super(displayProgressBar, commitAmount);
    }

    @Override
    protected int timesTracked() {
      return timesTracked;
    }
  }
}
