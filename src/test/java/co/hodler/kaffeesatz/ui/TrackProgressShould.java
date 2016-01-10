package co.hodler.kaffeesatz.ui;

import co.hodler.kaffeesatz.boundaries.GitRepoInteractions;
import co.hodler.kaffeesatz.model.Progress;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

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

    verify(displayProgressBar).withPercentageDone(new Progress(0));
  }

  @Test
  public void triggerTenPercentMarkAs10PercentOfTheCommitsIsReached() {
    given(gitRepoInteractions.provideCommitCount()).willReturn(10);
    trackProgress = new TestableTrackProgress(displayProgressBar, gitRepoInteractions);

    trackProgress.track();

    verify(displayProgressBar).withPercentageDone(new Progress(10));
  }

  @Test
  public void thrityPercentMarkIsReachedOnSixthCommitOfTwenty() {
    given(gitRepoInteractions.provideCommitCount()).willReturn(20);
    trackProgress = new TestableTrackProgress(displayProgressBar, gitRepoInteractions);
    trackProgress.timesTracked = 5;

    trackProgress.track();

    verify(displayProgressBar).withPercentageDone(new Progress(30));
  }

  @Test
  public void triggerFullProgressMarkAsTheLastCommitIsReached() {
    given(gitRepoInteractions.provideCommitCount()).willReturn(1);
    trackProgress = new TestableTrackProgress(displayProgressBar, gitRepoInteractions);

    trackProgress.track();

    verify(displayProgressBar).withPercentageDone(new Progress(100));
  }

  @Test
  public void triggerTwentyPercentAsTwentyPercentOfCommitsIsReached() {
    given(gitRepoInteractions.provideCommitCount()).willReturn(10);
    trackProgress = new TestableTrackProgress(displayProgressBar, gitRepoInteractions);
    trackProgress.timesTracked = 1;

    trackProgress.track();

    verify(displayProgressBar).withPercentageDone(new Progress(20));
  }

  @Test
  public void tenPercentIsTwoCommitsOutOf20() {
    given(gitRepoInteractions.provideCommitCount()).willReturn(20);
    trackProgress = new TestableTrackProgress(displayProgressBar, gitRepoInteractions);
    trackProgress.timesTracked = 1;

    trackProgress.track();

    verify(displayProgressBar).withPercentageDone(new Progress(10));
  }

  @Test
  public void thirtyPercentOfTenCommitsIsThreeCommits() {
    given(gitRepoInteractions.provideCommitCount()).willReturn(10);
    trackProgress = new TestableTrackProgress(displayProgressBar, gitRepoInteractions);
    trackProgress.timesTracked = 2;

    trackProgress.track();

    verify(displayProgressBar).withPercentageDone(new Progress(30));
  }

  @Test
  public void thirtyPercentOfFifteenCommitsIsFiveCommits() {
    given(gitRepoInteractions.provideCommitCount()).willReturn(15);
    trackProgress = new TestableTrackProgress(displayProgressBar, gitRepoInteractions);
    trackProgress.timesTracked = 4;

    trackProgress.track();

    verify(displayProgressBar).withPercentageDone(new Progress(30));
  }

  @Test
  public void twentyPercentOfSeventeenCommitsIsThreeCommits() {
    given(gitRepoInteractions.provideCommitCount()).willReturn(17);
    trackProgress = new TestableTrackProgress(displayProgressBar, gitRepoInteractions);
    trackProgress.timesTracked = 2;

    trackProgress.track();

    verify(displayProgressBar).withPercentageDone(new Progress(20));
  }

  @Test
  public void fourtyPercentOfTenCommitsIsFourCommits() {
    given(gitRepoInteractions.provideCommitCount()).willReturn(10);
    trackProgress = new TestableTrackProgress(displayProgressBar, gitRepoInteractions);
    trackProgress.timesTracked = 3;

    trackProgress.track();

    verify(displayProgressBar).withPercentageDone(new Progress(40));
  }

  @Test
  public void fiftyPercentOfTenCommitsIsFiveCommits() {
    given(gitRepoInteractions.provideCommitCount()).willReturn(10);
    trackProgress = new TestableTrackProgress(displayProgressBar, gitRepoInteractions);
    trackProgress.timesTracked = 4;

    trackProgress.track();

    verify(displayProgressBar).withPercentageDone(new Progress(50));
  }

  @Test
  public void sixtyPercentOfTenCommitsIsSixCommits() {
    given(gitRepoInteractions.provideCommitCount()).willReturn(10);
    trackProgress = new TestableTrackProgress(displayProgressBar, gitRepoInteractions);
    trackProgress.timesTracked = 5;

    trackProgress.track();

    verify(displayProgressBar).withPercentageDone(new Progress(60));
  }

  @Test
  public void seventyPercentOfTenCommitsIsSevenCommits() {
    given(gitRepoInteractions.provideCommitCount()).willReturn(10);
    trackProgress = new TestableTrackProgress(displayProgressBar, gitRepoInteractions);
    trackProgress.timesTracked = 6;

    trackProgress.track();

    verify(displayProgressBar).withPercentageDone(new Progress(70));
  }

  @Test
  public void eightyPercentOfTenCommitsIsSevenCommits() {
    given(gitRepoInteractions.provideCommitCount()).willReturn(10);
    trackProgress = new TestableTrackProgress(displayProgressBar, gitRepoInteractions);
    trackProgress.timesTracked = 7;

    trackProgress.track();

    verify(displayProgressBar).withPercentageDone(new Progress(80));
  }

  @Test
  public void ninetyPercentOfTenCommitsIsSevenCommits() {
    given(gitRepoInteractions.provideCommitCount()).willReturn(10);
    trackProgress = new TestableTrackProgress(displayProgressBar, gitRepoInteractions);
    trackProgress.timesTracked = 8;

    trackProgress.track();

    verify(displayProgressBar).withPercentageDone(new Progress(90));
  }

  @Test
  public void tenPercentOfFifteenIsTwoCommits() {
    given(gitRepoInteractions.provideCommitCount()).willReturn(15);
    trackProgress = new TestableTrackProgress(displayProgressBar, gitRepoInteractions);
    trackProgress.timesTracked = 1;

    trackProgress.track();

    verify(displayProgressBar).withPercentageDone(new Progress(10));
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
