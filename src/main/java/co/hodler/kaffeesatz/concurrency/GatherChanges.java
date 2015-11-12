package co.hodler.kaffeesatz.concurrency;

import java.util.List;
import java.util.Set;

import co.hodler.kaffeesatz.TrackProgress;
import co.hodler.kaffeesatz.actions.ProvideChangesBetweenTwoCommits;
import co.hodler.kaffeesatz.model.LinkedCommitHashPair;

public class GatherChanges implements Runnable {

  private Set<LinkedCommitHashPair> commitPairs;
  private ProvideChangesBetweenTwoCommits changesBetweenTwoCommitsProvider;
  private TrackProgress trackProgress;
  private List<String> changedFiles;

  public GatherChanges(Set<LinkedCommitHashPair> commitPairs,
      ProvideChangesBetweenTwoCommits changesBetweenTwoCommitsProvider,
      TrackProgress trackProgress, List<String> changedFiles) {
    this.commitPairs = commitPairs;
    this.changesBetweenTwoCommitsProvider = changesBetweenTwoCommitsProvider;
    this.trackProgress = trackProgress;
    this.changedFiles = changedFiles;
  }

  @Override
  public void run() {
    commitPairs.forEach(commitPair -> {
      changedFiles.addAll(
          changesBetweenTwoCommitsProvider.fetchChangesBetween(commitPair));
      trackProgress.track();
    });
  }

}
