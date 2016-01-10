package co.hodler.kaffeesatz.ui;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

import co.hodler.kaffeesatz.boundaries.GitRepoInteractions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class TrackProgressShould {

  @Mock
  DisplayProgressBar displayProgressBar;
  @Mock
  GitRepoInteractions gitRepoInteractions;

  private TestableTrackProgress trackProgress;

  @Test
  public void triggerBeginningDisplayOnFirstCall() {
    given(gitRepoInteractions.provideCommitCount()).willReturn(0);
    trackProgress = new TestableTrackProgress(displayProgressBar, gitRepoInteractions);

    trackProgress.track();

    verify(displayProgressBar).begin();
  }

  @Test
  public void triggerTenPercentMarkAs10PercentOfTheCommitsIsReached() {
    given(gitRepoInteractions.provideCommitCount()).willReturn(10);
    trackProgress = new TestableTrackProgress(displayProgressBar, gitRepoInteractions);

    trackProgress.track();

    verify(displayProgressBar).tenPercentDone();
  }

  @Test
  public void thrityPercentMarkIsReachedOnSixthCommitOfTwenty() {
    given(gitRepoInteractions.provideCommitCount()).willReturn(20);
    trackProgress = new TestableTrackProgress(displayProgressBar, gitRepoInteractions);
    trackProgress.timesTracked = 5;

    trackProgress.track();

    verify(displayProgressBar).thirtyPercentDone();
  }

  @Test
  public void triggerFullProgressMarkAsTheLastCommitIsReached() {
    given(gitRepoInteractions.provideCommitCount()).willReturn(1);
    trackProgress = new TestableTrackProgress(displayProgressBar, gitRepoInteractions);

    trackProgress.track();

    verify(displayProgressBar).full();
  }

  @Test
  public void triggerTwentyPercentAsTwentyPercentOfCommitsIsReached() {
    given(gitRepoInteractions.provideCommitCount()).willReturn(10);
    trackProgress = new TestableTrackProgress(displayProgressBar, gitRepoInteractions);
    trackProgress.timesTracked = 1;

    trackProgress.track();

    verify(displayProgressBar).twentyPercentDone();
  }

  @Test
  public void tenPercentIsTwoCommitsOutOf20() {
    given(gitRepoInteractions.provideCommitCount()).willReturn(20);
    trackProgress = new TestableTrackProgress(displayProgressBar, gitRepoInteractions);
    trackProgress.timesTracked = 1;

    trackProgress.track();

    verify(displayProgressBar).tenPercentDone();
  }

  @Test
  public void thirtyPercentOfTenCommitsIsThreeCommits() {
    given(gitRepoInteractions.provideCommitCount()).willReturn(10);
    trackProgress = new TestableTrackProgress(displayProgressBar, gitRepoInteractions);
    trackProgress.timesTracked = 2;

    trackProgress.track();

    verify(displayProgressBar).thirtyPercentDone();
  }

  @Test
  public void thirtyPercentOfFifteenCommitsIsFiveCommits() {
    given(gitRepoInteractions.provideCommitCount()).willReturn(15);
    trackProgress = new TestableTrackProgress(displayProgressBar, gitRepoInteractions);
    trackProgress.timesTracked = 4;

    trackProgress.track();

    verify(displayProgressBar).thirtyPercentDone();
  }

  @Test
  public void twentyPercentOfSeventeenCommitsIsThreeCommits() {
    given(gitRepoInteractions.provideCommitCount()).willReturn(17);
    trackProgress = new TestableTrackProgress(displayProgressBar, gitRepoInteractions);
    trackProgress.timesTracked = 2;

    trackProgress.track();

    verify(displayProgressBar).twentyPercentDone();
  }

  @Test
  public void fourtyPercentOfTenCommitsIsFourCommits() {
    given(gitRepoInteractions.provideCommitCount()).willReturn(10);
    trackProgress = new TestableTrackProgress(displayProgressBar, gitRepoInteractions);
    trackProgress.timesTracked = 3;

    trackProgress.track();

    verify(displayProgressBar).fourtyPercentDone();
  }

  @Test
  public void fiftyPercentOfTenCommitsIsFiveCommits() {
    given(gitRepoInteractions.provideCommitCount()).willReturn(10);
    trackProgress = new TestableTrackProgress(displayProgressBar, gitRepoInteractions);
    trackProgress.timesTracked = 4;

    trackProgress.track();

    verify(displayProgressBar).fiftyPercentDone();
  }

  @Test
  public void sixtyPercentOfTenCommitsIsSixCommits() {
    given(gitRepoInteractions.provideCommitCount()).willReturn(10);
    trackProgress = new TestableTrackProgress(displayProgressBar, gitRepoInteractions);
    trackProgress.timesTracked = 5;

    trackProgress.track();

    verify(displayProgressBar).sixtyPercentDone();
  }

  @Test
  public void seventyPercentOfTenCommitsIsSevenCommits() {
    given(gitRepoInteractions.provideCommitCount()).willReturn(10);
    trackProgress = new TestableTrackProgress(displayProgressBar, gitRepoInteractions);
    trackProgress.timesTracked = 6;

    trackProgress.track();

    verify(displayProgressBar).seventyPercentDone();
  }

  @Test
  public void eightyPercentOfTenCommitsIsSevenCommits() {
    given(gitRepoInteractions.provideCommitCount()).willReturn(10);
    trackProgress = new TestableTrackProgress(displayProgressBar, gitRepoInteractions);
    trackProgress.timesTracked = 7;

    trackProgress.track();

    verify(displayProgressBar).eightyPercentDone();
  }

  @Test
  public void ninetyPercentOfTenCommitsIsSevenCommits() {
    given(gitRepoInteractions.provideCommitCount()).willReturn(10);
    trackProgress = new TestableTrackProgress(displayProgressBar, gitRepoInteractions);
    trackProgress.timesTracked = 8;

    trackProgress.track();

    verify(displayProgressBar).ninetyPercentDone();
  }

  @Test
  public void tenPercentOfFifteenIsTwoCommits() {
    given(gitRepoInteractions.provideCommitCount()).willReturn(15);
    trackProgress = new TestableTrackProgress(displayProgressBar, gitRepoInteractions);
    trackProgress.timesTracked = 1;

    trackProgress.track();

    verify(displayProgressBar).tenPercentDone();
  }

  class TestableTrackProgress extends TrackProgress {

    public int timesTracked = 0;

    public TestableTrackProgress(DisplayProgressBar displayProgressBar,
        GitRepoInteractions gitRepoInteractions) {
      super(displayProgressBar, gitRepoInteractions);
    }

    @Override
    protected int timesTracked() {
      return timesTracked;
    }
  }
}
