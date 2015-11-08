package co.hodler.kaffeesatz.actions.git;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.BDDMockito.given;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import co.hodler.kaffeesatz.actions.FetchChangedFiles;
import co.hodler.kaffeesatz.actions.FindLinkedCommitPairs;
import co.hodler.kaffeesatz.actions.ProvideChangesBetweenTwoCommits;
import co.hodler.model.CommitHash;
import co.hodler.model.LinkedCommitHashPair;

@RunWith(MockitoJUnitRunner.class)
public class GitFetchChangedFilesShould {

  @Mock
  FindLinkedCommitPairs commitPairProvider;
  @Mock
  ProvideChangesBetweenTwoCommits changesForPairProvider;

  private FetchChangedFiles changedFiles;

  @Before
  public void setUp() {
    changedFiles = new GitFetchChangedFiles(commitPairProvider,
        changesForPairProvider, null);
  }

  @Test
  public void provideAllChangedFiles() {
    willHaveTwoChangedFiles(oneCommitPair());

    List<String> filesChanged = changedFiles.fetchChangedFiles();

    assertThat(filesChanged, hasItem(".gitignore"));
    assertThat(filesChanged, hasItem("/src/main/java/App.java"));
  }

  private void willHaveTwoChangedFiles(LinkedCommitHashPair pair) {
    Set<String> changes = new HashSet<>();
    changes.add(".gitignore");
    changes.add("/src/main/java/App.java");
    given(changesForPairProvider.fetchChangesBetween(pair)).willReturn(changes);
  }

  private LinkedCommitHashPair oneCommitPair() {
    LinkedCommitHashPair pair = new LinkedCommitHashPair(
        new CommitHash("ef3cc2dc4c04be7c87af619ad1d7cc1d6f11bb26"),
        new CommitHash("63c29e3323b5cec97a6214a22110fa8d7dae660b"));
    Set<LinkedCommitHashPair> pairs = new HashSet<>();
    pairs.add(pair);
    given(commitPairProvider.providePairs()).willReturn(pairs);
    return pair;
  }
}
