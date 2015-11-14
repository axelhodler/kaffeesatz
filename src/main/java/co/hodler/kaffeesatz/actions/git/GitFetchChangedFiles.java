package co.hodler.kaffeesatz.actions.git;

import java.util.ArrayList;
import java.util.List;

import co.hodler.kaffeesatz.TrackProgress;
import co.hodler.kaffeesatz.actions.FetchChangedFiles;
import co.hodler.kaffeesatz.actions.FindLinkedCommitPairs;
import co.hodler.kaffeesatz.actions.ProvideChangesBetweenTwoCommits;

public class GitFetchChangedFiles implements FetchChangedFiles {

  private FindLinkedCommitPairs commitPairProvider;
  private ProvideChangesBetweenTwoCommits changesBetweenTwoCommitsProvider;
  private TrackProgress trackProgress;

  public GitFetchChangedFiles(FindLinkedCommitPairs commitPairProvider,
      ProvideChangesBetweenTwoCommits changesBetweenTwoCommitsProvider,
      TrackProgress trackProgress) {
    this.commitPairProvider = commitPairProvider;
    this.changesBetweenTwoCommitsProvider = changesBetweenTwoCommitsProvider;
    this.trackProgress = trackProgress;
  }

  @Override
  public List<String> fetchChangedFiles() {
    List<String> changedFiles = new ArrayList<>();
    commitPairProvider.providePairs().stream()
        .forEach(commitPair -> {
          changedFiles.addAll(changesBetweenTwoCommitsProvider.fetchChangesBetween(commitPair));
          trackProgress.track();
        });

    return changedFiles;
  }
}
