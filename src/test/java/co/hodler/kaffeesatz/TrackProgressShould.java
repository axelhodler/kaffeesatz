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
}
