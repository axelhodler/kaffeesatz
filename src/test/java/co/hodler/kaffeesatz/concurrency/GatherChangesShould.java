package co.hodler.kaffeesatz.concurrency;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.internal.util.collections.Sets;
import org.mockito.runners.MockitoJUnitRunner;

import co.hodler.kaffeesatz.actions.ProvideChangesBetweenTwoCommits;
import co.hodler.kaffeesatz.model.ChangedFile;
import co.hodler.kaffeesatz.model.CommitHash;
import co.hodler.kaffeesatz.model.LinkedCommitHashPair;
import co.hodler.kaffeesatz.ui.TrackProgress;

@RunWith(MockitoJUnitRunner.class)
public class GatherChangesShould {

  @Mock
  TrackProgress trackProgress;
  @Mock
  ProvideChangesBetweenTwoCommits provideChanges;
  private List<ChangedFile> changedFiles;
  private GatherChanges gatherChanges;

  @Before
  public void initialise() {
    changedFiles = new ArrayList<>();
    gatherChanges = new GatherChanges(
        Sets.newSet(firstPair(), secondPair()), provideChanges, trackProgress,
        changedFiles);
  }

  @Test
  public void gather_changes() {
    given(provideChanges.fetchChangesBetween(firstPair()))
        .willReturn(Sets.newSet(new ChangedFile("README.md")));

    gatherChanges.run();

    assertThat(changedFiles, hasItem(new ChangedFile("README.md")));
  }

  @Test
  public void track_progress() {
    gatherChanges.run();

    verify(trackProgress, times(2)).track();
  }

  private LinkedCommitHashPair firstPair() {
    return new LinkedCommitHashPair(
        new CommitHash("1b937db69b4f7edb519b051ef8319de56de6f627"),
        new CommitHash("2e121305db8fa36f1dbc6083e628b245e20ef4c4"));
  }

  private LinkedCommitHashPair secondPair() {
    return new LinkedCommitHashPair(
        new CommitHash("2e121305db8fa36f1dbc6083e628b245e20ef4c4"),
        new CommitHash("5633c849c06f6063fdca5dec5054950e403447b8"));
  }
}
