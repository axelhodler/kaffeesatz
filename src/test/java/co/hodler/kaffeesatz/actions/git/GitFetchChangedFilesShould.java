package co.hodler.kaffeesatz.actions.git;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.BDDMockito.given;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.internal.util.collections.Sets;
import org.mockito.runners.MockitoJUnitRunner;

import co.hodler.kaffeesatz.actions.FetchChangedFiles;
import co.hodler.kaffeesatz.concurrency.GatherChangesConcurrently;
import co.hodler.kaffeesatz.concurrency.SplitPairsSetIntoEqualParts;
import co.hodler.kaffeesatz.model.ChangedFile;
import co.hodler.kaffeesatz.model.CommitHash;
import co.hodler.kaffeesatz.model.LinkedCommitHashPair;

@RunWith(MockitoJUnitRunner.class)
public class GitFetchChangedFilesShould {

  @Mock
  SplitPairsSetIntoEqualParts logSplitter;
  @Mock
  GatherChangesConcurrently gatherChanges;

  private FetchChangedFiles changedFiles;

  @Before
  public void initialize() {
    changedFiles = new GitFetchChangedFiles(logSplitter, gatherChanges);
  }

  @Test
  public void provide_all_changed_files() {
    given(logSplitter.splitInto(4)).willReturn(groupsOfChanges());
    List<ChangedFile> changes = new ArrayList<>();
    changes.add(new ChangedFile(".gitignore"));
    changes.add(new ChangedFile("/src/main/java/App.java"));
    given(gatherChanges.gather(groupsOfChanges())).willReturn(changes);

    List<ChangedFile> filesChanged = changedFiles.fetchChangedFiles();

    assertThat(filesChanged, hasItem(new ChangedFile(".gitignore")));
    assertThat(filesChanged, hasItem(new ChangedFile("/src/main/java/App.java")));
  }

  private List<Set<LinkedCommitHashPair>> groupsOfChanges() {
    List<Set<LinkedCommitHashPair>> groupsOfChanges = new ArrayList<>();
    groupsOfChanges.add(Sets.newSet(firstCommitPair()));
    groupsOfChanges.add(Sets.newSet(secondCommitPair()));
    return groupsOfChanges;
  }

  private LinkedCommitHashPair firstCommitPair() {
    LinkedCommitHashPair pair = new LinkedCommitHashPair(
        new CommitHash("ef3cc2dc4c04be7c87af619ad1d7cc1d6f11bb26"),
        new CommitHash("63c29e3323b5cec97a6214a22110fa8d7dae660b"));
    return pair;
  }

  private LinkedCommitHashPair secondCommitPair() {
    LinkedCommitHashPair pair = new LinkedCommitHashPair(
        new CommitHash("ef3cc2dc4c04be7c87af619ad1d7cc1d6ffoobar"),
        new CommitHash("ef3cc2dc4c04be7c87af619ad1d7cc1d6f11bb26"));
    return pair;
  }
}
