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

  private TrackProgress trackProgress;

  @Test
  public void triggerBeginningDisplayOnFirstCall() {
    trackProgress = new TrackProgress(displayProgressBar, new CommitCount(0));

    trackProgress.track();

    verify(displayProgressBar).withPercentageDone(new Progress(0));
  }

  @Test
  public void triggerTenPercentMarkAs10PercentOfTheCommitsIsReached() {
    trackProgress = new TrackProgress(displayProgressBar, new CommitCount(10));

    trackProgress.track();

    verify(displayProgressBar).withPercentageDone(new Progress(10));
  }

  @Test
  public void thirtyPercentMarkIsReachedOnSixthCommitOfTwenty() {
    trackProgress = new TrackProgress(displayProgressBar, new CommitCount(20));

    trackProgress.track();
    trackProgress.track();
    trackProgress.track();
    trackProgress.track();
    trackProgress.track();
    trackProgress.track();

    verify(displayProgressBar).withPercentageDone(new Progress(30));
  }

  @Test
  public void triggerFullProgressMarkAsTheLastCommitIsReached() {
    trackProgress = new TrackProgress(displayProgressBar, new CommitCount(1));

    trackProgress.track();

    verify(displayProgressBar).withPercentageDone(new Progress(100));
  }

  @Test
  public void triggerTwentyPercentAsTwentyPercentOfCommitsIsReached() {
    trackProgress = new TrackProgress(displayProgressBar, new CommitCount(10));

    trackProgress.track();
    trackProgress.track();

    verify(displayProgressBar).withPercentageDone(new Progress(20));
  }

  @Test
  public void tenPercentIsTwoCommitsOutOf20() {
    trackProgress = new TrackProgress(displayProgressBar, new CommitCount(20));

    trackProgress.track();
    trackProgress.track();

    verify(displayProgressBar).withPercentageDone(new Progress(10));
  }

  @Test
  public void thirtyPercentOfTenCommitsIsThreeCommits() {
    trackProgress = new TrackProgress(displayProgressBar, new CommitCount(10));

    trackProgress.track();
    trackProgress.track();
    trackProgress.track();

    verify(displayProgressBar).withPercentageDone(new Progress(30));
  }

  @Test
  public void thirtyPercentOfFifteenCommitsIsFiveCommits() {
    trackProgress = new TrackProgress(displayProgressBar, new CommitCount(15));

    trackProgress.track();
    trackProgress.track();
    trackProgress.track();
    trackProgress.track();
    trackProgress.track();

    verify(displayProgressBar).withPercentageDone(new Progress(30));
  }

  @Test
  public void twentyPercentOfSeventeenCommitsIsThreeCommits() {
    trackProgress = new TrackProgress(displayProgressBar, new CommitCount(17));

    trackProgress.track();
    trackProgress.track();
    trackProgress.track();

    verify(displayProgressBar).withPercentageDone(new Progress(20));
  }

  @Test
  public void fourtyPercentOfTenCommitsIsFourCommits() {
    trackProgress = new TrackProgress(displayProgressBar, new CommitCount(10));

    trackProgress.track();
    trackProgress.track();
    trackProgress.track();
    trackProgress.track();

    verify(displayProgressBar).withPercentageDone(new Progress(40));
  }

  @Test
  public void fiftyPercentOfTenCommitsIsFiveCommits() {
    trackProgress = new TrackProgress(displayProgressBar, new CommitCount(10));

    trackProgress.track();
    trackProgress.track();
    trackProgress.track();
    trackProgress.track();
    trackProgress.track();

    verify(displayProgressBar).withPercentageDone(new Progress(50));
  }

  @Test
  public void sixtyPercentOfTenCommitsIsSixCommits() {
    trackProgress = new TrackProgress(displayProgressBar, new CommitCount(10));

    trackProgress.track();
    trackProgress.track();
    trackProgress.track();
    trackProgress.track();
    trackProgress.track();
    trackProgress.track();

    verify(displayProgressBar).withPercentageDone(new Progress(60));
  }

  @Test
  public void seventyPercentOfTenCommitsIsSevenCommits() {
    trackProgress = new TrackProgress(displayProgressBar, new CommitCount(10));

    trackProgress.track();
    trackProgress.track();
    trackProgress.track();
    trackProgress.track();
    trackProgress.track();
    trackProgress.track();
    trackProgress.track();

    verify(displayProgressBar).withPercentageDone(new Progress(70));
  }

  @Test
  public void eightyPercentOfTenCommitsIsSevenCommits() {
    trackProgress = new TrackProgress(displayProgressBar, new CommitCount(10));

    trackProgress.track();
    trackProgress.track();
    trackProgress.track();
    trackProgress.track();
    trackProgress.track();
    trackProgress.track();
    trackProgress.track();
    trackProgress.track();

    verify(displayProgressBar).withPercentageDone(new Progress(80));
  }

  @Test
  public void ninetyPercentOfTenCommitsIsSevenCommits() {
    trackProgress = new TrackProgress(displayProgressBar, new CommitCount(10));

    trackProgress.track();
    trackProgress.track();
    trackProgress.track();
    trackProgress.track();
    trackProgress.track();
    trackProgress.track();
    trackProgress.track();
    trackProgress.track();
    trackProgress.track();

    verify(displayProgressBar).withPercentageDone(new Progress(90));
  }

  @Test
  public void tenPercentOfFifteenIsTwoCommits() {
    trackProgress = new TrackProgress(displayProgressBar, new CommitCount(15));

    trackProgress.track();
    trackProgress.track();

    verify(displayProgressBar).withPercentageDone(new Progress(10));
  }
}
