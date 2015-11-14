package co.hodler.kaffeesatz.concurrency;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import co.hodler.kaffeesatz.TrackProgress;
import co.hodler.kaffeesatz.actions.ProvideChangesBetweenTwoCommits;
import co.hodler.kaffeesatz.model.LinkedCommitHashPair;

@RunWith(MockitoJUnitRunner.class)
public class GatherChangesFactoryShould {

  @Mock
  ProvideChangesBetweenTwoCommits provideChangesBetweenTwoCommits;
  @Mock
  TrackProgress trackProgress;

  @Test
  public void create_gatherchanges_runnable() {
    Set<LinkedCommitHashPair> commitPairs = new HashSet<>();
    List<String> changedFiles = new ArrayList<>();
    GatherChangesFactory gatherChangesFactory = new GatherChangesFactory();

    GatherChanges gatherChangesRunnable = gatherChangesFactory
        .createThreadToGatherChanges(commitPairs,
            provideChangesBetweenTwoCommits, trackProgress, changedFiles);

    GatherChanges expectedGatherChangesRunnable = 
        new GatherChanges(commitPairs, provideChangesBetweenTwoCommits,
            trackProgress, changedFiles);
    assertThat(gatherChangesRunnable, is(expectedGatherChangesRunnable));
  }
}
