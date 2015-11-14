package co.hodler.kaffeesatz.concurrency;

import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import co.hodler.kaffeesatz.actions.ProvideChangesBetweenTwoCommits;
import co.hodler.kaffeesatz.model.LinkedCommitHashPair;
import co.hodler.kaffeesatz.ui.TrackProgress;

@RunWith(MockitoJUnitRunner.class)
public class GatherChangesConcurrentlyShould {

  @Mock
  TrackProgress trackProgress;
  @Mock
  ProvideChangesBetweenTwoCommits provideChangesBetweenTwoCommits;
  @Mock
  GatherChangesFactory gatherChangesFactory;
  @Mock
  GatherChangesThreadFactory gatherChangesThreadFactory;

  private GatherChangesConcurrently gatherChangesConcurrently;

  @Test
  public void create_threads_to_gather_changes() {
    gatherChangesConcurrently = new GatherChangesConcurrently(
        provideChangesBetweenTwoCommits, gatherChangesThreadFactory,
        gatherChangesFactory, trackProgress);
    List<Set<LinkedCommitHashPair>> groupsOfCommitPairs = new ArrayList<>();
    groupsOfCommitPairs.add(new HashSet<>());

    gatherChangesConcurrently.gather(groupsOfCommitPairs);

    verify(gatherChangesFactory).createGatherChanges(new HashSet<>(),
        provideChangesBetweenTwoCommits, trackProgress, new ArrayList<>());
  }
}
