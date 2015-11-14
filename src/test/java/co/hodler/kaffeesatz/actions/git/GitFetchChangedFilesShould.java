package co.hodler.kaffeesatz.actions.git;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.Arrays;
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
import co.hodler.kaffeesatz.model.ChangedFile;
import co.hodler.kaffeesatz.model.CommitHash;
import co.hodler.kaffeesatz.model.LinkedCommitHashPair;
import co.hodler.kaffeesatz.ui.TrackProgress;

@RunWith(MockitoJUnitRunner.class)
public class GitFetchChangedFilesShould {

  @Mock
  FindLinkedCommitPairs commitPairProvider;
  @Mock
  ProvideChangesBetweenTwoCommits changesForPairProvider;
  @Mock
  TrackProgress trackProgress;

  private FetchChangedFiles changedFiles;

  @Before
  public void initialize() {
    changedFiles = new GitFetchChangedFiles(commitPairProvider,
        changesForPairProvider, trackProgress);
  }

  @Test
  public void provide_all_changed_files() {
    logHasFollowingCommitPairs(firstCommitPair());
    willHaveTwoChangedFiles(firstCommitPair());

    List<ChangedFile> filesChanged = changedFiles.fetchChangedFiles();

    assertThat(filesChanged, hasItem(new ChangedFile(".gitignore")));
    assertThat(filesChanged, hasItem(new ChangedFile("/src/main/java/App.java")));
  }

  @Test
  public void track_progress_for_every_pair_using_one_pair() {
    logHasFollowingCommitPairs(firstCommitPair());
    willHaveTwoChangedFiles(firstCommitPair());

    changedFiles.fetchChangedFiles();

    verify(trackProgress).track();
  }

  @Test
  public void track_pogress_for_every_pair_using_two_pairs() {
    logHasFollowingCommitPairs(firstCommitPair(), secondCommitPair());
    willHaveTwoChangedFiles(firstCommitPair(), secondCommitPair());

    changedFiles.fetchChangedFiles();

    verify(trackProgress, times(2)).track();
  }

  private void willHaveTwoChangedFiles(LinkedCommitHashPair... pairs) {
    Set<ChangedFile> changes = new HashSet<>();
    changes.add(new ChangedFile(".gitignore"));
    changes.add(new ChangedFile("/src/main/java/App.java"));
    Arrays.asList(pairs).stream()
      .forEach(pair -> given(changesForPairProvider.fetchChangesBetween(pair)).willReturn(changes));
  }

  private void logHasFollowingCommitPairs(LinkedCommitHashPair... pairs) {
    Set<LinkedCommitHashPair> allPairs = new HashSet<>();
    Arrays.asList(pairs).stream()
      .forEach(pair -> allPairs.add(pair));
    given(commitPairProvider.providePairs()).willReturn(allPairs);
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
