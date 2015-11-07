package co.hodler.kaffeesatz;

import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

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

    verify(displayProgressBar).thrityPercentDone();
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
