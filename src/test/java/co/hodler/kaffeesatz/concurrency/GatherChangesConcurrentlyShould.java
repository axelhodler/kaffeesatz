package co.hodler.kaffeesatz.concurrency;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Before;
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
  @Mock
  private GatherChangesConcurrently gatherChangesConcurrently;

  @Before
  public void initialize() {
    gatherChangesConcurrently = new GatherChangesConcurrently(
        provideChangesBetweenTwoCommits, gatherChangesThreadFactory,
        gatherChangesFactory, trackProgress);
  }

  @Test
  public void create_gatherchanges() {
    List<Set<LinkedCommitHashPair>> groupsOfCommitPairs = new ArrayList<>();
    groupsOfCommitPairs.add(new HashSet<>());

    gatherChangesConcurrently.gather(groupsOfCommitPairs);

    verify(gatherChangesFactory).createGatherChanges(new HashSet<>(),
        provideChangesBetweenTwoCommits, trackProgress, new ArrayList<>());
  }

  @Test
  public void create_as_much_gather_changes_as_groups_of_commit_pairs() {
    List<Set<LinkedCommitHashPair>> groupsOfCommitPairs = new ArrayList<>();
    groupsOfCommitPairs.add(new HashSet<>());
    groupsOfCommitPairs.add(new HashSet<>());

    gatherChangesConcurrently.gather(groupsOfCommitPairs);

    verify(gatherChangesFactory, times(2)).createGatherChanges(new HashSet<>(),
        provideChangesBetweenTwoCommits, trackProgress, new ArrayList<>());
  }

  @Test
  public void create_as_much_threads_as_groups_of_commit_pairs() {
    given(gatherChangesFactory.createGatherChanges(new HashSet<>(),
        provideChangesBetweenTwoCommits, trackProgress, new ArrayList<>())).willReturn(
            gatherChangesObject());
    List<Set<LinkedCommitHashPair>> groupsOfCommitPairs = new ArrayList<>();
    groupsOfCommitPairs.add(new HashSet<>());
    groupsOfCommitPairs.add(new HashSet<>());

    gatherChangesConcurrently.gather(groupsOfCommitPairs);

    verify(gatherChangesThreadFactory, times(2)).createThreadTo(gatherChangesObject());
  }

  private GatherChanges gatherChangesObject() {
    return new GatherChanges(new HashSet<>(), provideChangesBetweenTwoCommits,
        trackProgress, new ArrayList<>());
  }
}
