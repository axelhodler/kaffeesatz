package co.hodler;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.BDDMockito.given;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import co.hodler.model.CommitPair;

@RunWith(MockitoJUnitRunner.class)
public class GitChangedFilesShould {

  @Mock
  CommitPairProvider commitPairProvider;
  @Mock
  ChangesBetweenTwoCommitsProvider changesForPairProvider;

  @Test
  public void provideAllChangedFiles() {
    CommitPair pair = new CommitPair("ef3cc2dc4c04be7c87af619ad1d7cc1d6f11bb26",
        "63c29e3323b5cec97a6214a22110fa8d7dae660b");
    Set<CommitPair> pairs = new HashSet<>();
    pairs.add(pair);
    given(commitPairProvider.providePairs()).willReturn(pairs);
    Set<String> changes = new HashSet<>();
    changes.add(".gitignore");
    changes.add("/src/main/java/App.java");
    given(changesForPairProvider.fetchChangesBetween(pair)).willReturn(changes);
    ChangedFiles changedFiles = new GitChangedFiles(commitPairProvider, changesForPairProvider);

    List<String> filesChanged = changedFiles.fetchChangedFiles();

    assertThat(filesChanged, hasItem(".gitignore"));
    assertThat(filesChanged, hasItem("/src/main/java/App.java"));
  }
}
