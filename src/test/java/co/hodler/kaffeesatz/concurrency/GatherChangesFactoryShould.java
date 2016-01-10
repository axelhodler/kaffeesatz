package co.hodler.kaffeesatz.concurrency;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import co.hodler.kaffeesatz.boundaries.GitRepoInteractions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import co.hodler.kaffeesatz.model.ChangedFile;
import co.hodler.kaffeesatz.model.LinkedCommitHashPair;
import co.hodler.kaffeesatz.ui.TrackProgress;

@RunWith(MockitoJUnitRunner.class)
public class GatherChangesFactoryShould {

  @Mock
  GitRepoInteractions gitRepoInteractions;
  @Mock
  TrackProgress trackProgress;

  @Test
  public void create_gatherchanges_runnable() {
    Set<LinkedCommitHashPair> commitPairs = new HashSet<>();
    List<ChangedFile> changedFiles = new ArrayList<>();
    GatherChangesFactory gatherChangesFactory = new GatherChangesFactory();

    GatherChanges gatherChangesRunnable = gatherChangesFactory
        .createGatherChanges(commitPairs,
                gitRepoInteractions, trackProgress, changedFiles);

    GatherChanges expectedGatherChangesRunnable = 
        new GatherChanges(commitPairs, gitRepoInteractions,
            trackProgress, changedFiles);
    assertThat(gatherChangesRunnable, is(expectedGatherChangesRunnable));
  }
}
