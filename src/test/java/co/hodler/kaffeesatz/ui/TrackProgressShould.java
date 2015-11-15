package co.hodler.kaffeesatz.ui;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import co.hodler.kaffeesatz.actions.CommitCount;

@RunWith(MockitoJUnitRunner.class)
public class TrackProgressShould {

  @Mock
  DisplayProgressBar displayProgressBar;
  @Mock
  CommitCount commitCount;

  private TestableTrackProgress trackProgress;

  @Test
  public void triggerBeginningDisplayOnFirstCall() {
    given(commitCount.value()).willReturn(0);
    trackProgress = new TestableTrackProgress(displayProgressBar, commitCount);

    trackProgress.track();

    verify(displayProgressBar).begin();
  }

  @Test
  public void triggerTenPercentMarkAs10PercentOfTheCommitsIsReached() {
    given(commitCount.value()).willReturn(10);
    trackProgress = new TestableTrackProgress(displayProgressBar, commitCount);

    trackProgress.track();

    verify(displayProgressBar).tenPercentDone();
  }

  @Test
  public void thrityPercentMarkIsReachedOnSixthCommitOfTwenty() {
    given(commitCount.value()).willReturn(20);
    trackProgress = new TestableTrackProgress(displayProgressBar, commitCount);
    trackProgress.timesTracked = 5;

    trackProgress.track();

    verify(displayProgressBar).thirtyPercentDone();
  }

  @Test
  public void triggerFullProgressMarkAsTheLastCommitIsReached() {
    given(commitCount.value()).willReturn(1);
    trackProgress = new TestableTrackProgress(displayProgressBar, commitCount);

    trackProgress.track();

    verify(displayProgressBar).full();
  }

  @Test
  public void triggerTwentyPercentAsTwentyPercentOfCommitsIsReached() {
    given(commitCount.value()).willReturn(10);
    trackProgress = new TestableTrackProgress(displayProgressBar, commitCount);
    trackProgress.timesTracked = 1;

    trackProgress.track();

    verify(displayProgressBar).twentyPercentDone();
  }

  @Test
  public void tenPercentIsTwoCommitsOutOf20() {
    given(commitCount.value()).willReturn(20);
    trackProgress = new TestableTrackProgress(displayProgressBar, commitCount);
    trackProgress.timesTracked = 1;

    trackProgress.track();

    verify(displayProgressBar).tenPercentDone();
  }

  @Test
  public void thirtyPercentOfTenCommitsIsThreeCommits() {
    given(commitCount.value()).willReturn(10);
    trackProgress = new TestableTrackProgress(displayProgressBar, commitCount);
    trackProgress.timesTracked = 2;

    trackProgress.track();

    verify(displayProgressBar).thirtyPercentDone();
  }

  @Test
  public void thirtyPercentOfFifteenCommitsIsFiveCommits() {
    given(commitCount.value()).willReturn(15);
    trackProgress = new TestableTrackProgress(displayProgressBar, commitCount);
    trackProgress.timesTracked = 4;

    trackProgress.track();

    verify(displayProgressBar).thirtyPercentDone();
  }

  @Test
  public void twentyPercentOfSeventeenCommitsIsThreeCommits() {
    given(commitCount.value()).willReturn(17);
    trackProgress = new TestableTrackProgress(displayProgressBar, commitCount);
    trackProgress.timesTracked = 2;

    trackProgress.track();

    verify(displayProgressBar).twentyPercentDone();
  }

  @Test
  public void fourtyPercentOfTenCommitsIsFourCommits() {
    given(commitCount.value()).willReturn(10);
    trackProgress = new TestableTrackProgress(displayProgressBar, commitCount);
    trackProgress.timesTracked = 3;

    trackProgress.track();

    verify(displayProgressBar).fourtyPercentDone();
  }

  @Test
  public void fiftyPercentOfTenCommitsIsFiveCommits() {
    given(commitCount.value()).willReturn(10);
    trackProgress = new TestableTrackProgress(displayProgressBar, commitCount);
    trackProgress.timesTracked = 4;

    trackProgress.track();

    verify(displayProgressBar).fiftyPercentDone();
  }

  @Test
  public void sixtyPercentOfTenCommitsIsSixCommits() {
    given(commitCount.value()).willReturn(10);
    trackProgress = new TestableTrackProgress(displayProgressBar, commitCount);
    trackProgress.timesTracked = 5;

    trackProgress.track();

    verify(displayProgressBar).sixtyPercentDone();
  }

  @Test
  public void seventyPercentOfTenCommitsIsSevenCommits() {
    given(commitCount.value()).willReturn(10);
    trackProgress = new TestableTrackProgress(displayProgressBar, commitCount);
    trackProgress.timesTracked = 6;

    trackProgress.track();

    verify(displayProgressBar).seventyPercentDone();
  }

  @Test
  public void eightyPercentOfTenCommitsIsSevenCommits() {
    given(commitCount.value()).willReturn(10);
    trackProgress = new TestableTrackProgress(displayProgressBar, commitCount);
    trackProgress.timesTracked = 7;

    trackProgress.track();

    verify(displayProgressBar).eightyPercentDone();
  }

  @Test
  public void ninetyPercentOfTenCommitsIsSevenCommits() {
    given(commitCount.value()).willReturn(10);
    trackProgress = new TestableTrackProgress(displayProgressBar, commitCount);
    trackProgress.timesTracked = 8;

    trackProgress.track();

    verify(displayProgressBar).ninetyPercentDone();
  }

  @Test
  public void tenPercentOfFifteenIsTwoCommits() {
    given(commitCount.value()).willReturn(15);
    trackProgress = new TestableTrackProgress(displayProgressBar, commitCount);
    trackProgress.timesTracked = 1;

    trackProgress.track();

    verify(displayProgressBar).tenPercentDone();
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
