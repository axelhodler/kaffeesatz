package co.hodler.kaffeesatz.concurrency;

import java.util.List;
import java.util.Set;

import co.hodler.kaffeesatz.actions.ProvideChangesBetweenTwoCommits;
import co.hodler.kaffeesatz.model.ChangedFile;
import co.hodler.kaffeesatz.model.LinkedCommitHashPair;
import co.hodler.kaffeesatz.ui.TrackProgress;

public class GatherChanges implements Runnable {

  private Set<LinkedCommitHashPair> commitPairs;
  private ProvideChangesBetweenTwoCommits changesBetweenTwoCommitsProvider;
  private TrackProgress trackProgress;
  private List<ChangedFile> changedFiles;

  public GatherChanges(Set<LinkedCommitHashPair> commitPairs,
      ProvideChangesBetweenTwoCommits changesBetweenTwoCommitsProvider,
      TrackProgress trackProgress, List<ChangedFile> changedFiles) {
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

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result
        + ((changedFiles == null) ? 0 : changedFiles.hashCode());
    result = prime * result + ((changesBetweenTwoCommitsProvider == null) ? 0
        : changesBetweenTwoCommitsProvider.hashCode());
    result = prime * result
        + ((commitPairs == null) ? 0 : commitPairs.hashCode());
    result = prime * result
        + ((trackProgress == null) ? 0 : trackProgress.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    GatherChanges other = (GatherChanges) obj;
    if (changedFiles == null) {
      if (other.changedFiles != null)
        return false;
    } else if (!changedFiles.equals(other.changedFiles))
      return false;
    if (changesBetweenTwoCommitsProvider == null) {
      if (other.changesBetweenTwoCommitsProvider != null)
        return false;
    } else if (!changesBetweenTwoCommitsProvider
        .equals(other.changesBetweenTwoCommitsProvider))
      return false;
    if (commitPairs == null) {
      if (other.commitPairs != null)
        return false;
    } else if (!commitPairs.equals(other.commitPairs))
      return false;
    if (trackProgress == null) {
      if (other.trackProgress != null)
        return false;
    } else if (!trackProgress.equals(other.trackProgress))
      return false;
    return true;
  }

}
