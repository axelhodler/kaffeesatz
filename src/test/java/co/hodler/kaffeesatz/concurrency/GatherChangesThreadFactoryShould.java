package co.hodler.kaffeesatz.concurrency;

import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class GatherChangesThreadFactoryShould {

  @Mock
  GatherChanges gatherChanges;

  @Test
  public void create_thread_gathering_changes() throws InterruptedException {
    GatherChangesThreadFactory distributeGathering = new GatherChangesThreadFactory();
    GatherChangesThread thread = distributeGathering.createThreadTo(gatherChanges);

    thread.startGathering();
    thread.waitToFinish();

    verify(gatherChanges).run();
  }
}
