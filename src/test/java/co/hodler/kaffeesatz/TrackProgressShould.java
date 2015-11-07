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

  @Test
  public void triggerBeginningDisplayOnFirstCall() {
    TrackProgress trackProgress = new TrackProgress(displayProgressBar, 0);

    trackProgress.track();

    verify(displayProgressBar).begin();
  }

  @Test
  public void triggerTenPercentMarkAs10PercentOfTheCommitsIsReached() {
    TrackProgress trackProgress = new TrackProgress(displayProgressBar, 10);

    trackProgress.track();
    trackProgress.track();

    verify(displayProgressBar).tenPercentDone();
  }

  @Test
  public void triggerFullProgressMarkAsTheLastCommitIsReached() {
    TrackProgress trackProgress = new TrackProgress(displayProgressBar, 1);

    trackProgress.track();
    trackProgress.track();

    verify(displayProgressBar).full();
  }
}
