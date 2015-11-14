package co.hodler.kaffeesatz.actions.git;

import java.util.ArrayList;
import java.util.List;

import co.hodler.kaffeesatz.actions.FetchChangedFiles;
import co.hodler.kaffeesatz.actions.FindLinkedCommitPairs;
import co.hodler.kaffeesatz.actions.ProvideChangesBetweenTwoCommits;
import co.hodler.kaffeesatz.model.ChangedFile;
import co.hodler.kaffeesatz.ui.TrackProgress;

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
  public List<ChangedFile> fetchChangedFiles() {
    List<ChangedFile> changedFiles = new ArrayList<>();
    commitPairProvider.providePairs().stream()
        .forEach(commitPair -> {
          changedFiles.addAll(changesBetweenTwoCommitsProvider.fetchChangesBetween(commitPair));
          trackProgress.track();
        });

    return changedFiles;
  }
}
