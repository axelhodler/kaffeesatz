package co.hodler.kaffeesatz.concurrency;

import java.util.List;
import java.util.Set;

import co.hodler.kaffeesatz.boundaries.GitRepoInteractions;
import co.hodler.kaffeesatz.model.ChangedFile;
import co.hodler.kaffeesatz.model.LinkedCommitHashPair;
import co.hodler.kaffeesatz.ui.TrackProgress;

public class GatherChanges implements Runnable {

  private Set<LinkedCommitHashPair> commitPairs;
  private GitRepoInteractions gitRepoInteractions;
  private TrackProgress trackProgress;
  private List<ChangedFile> changedFiles;

  public GatherChanges(Set<LinkedCommitHashPair> commitPairs,
                       GitRepoInteractions gitRepoInteractions,
      TrackProgress trackProgress, List<ChangedFile> changedFiles) {
    this.commitPairs = commitPairs;
    this.gitRepoInteractions = gitRepoInteractions;
    this.trackProgress = trackProgress;
    this.changedFiles = changedFiles;
  }

  @Override
  public void run() {
    commitPairs.forEach(commitPair -> {
      changedFiles.addAll(
              gitRepoInteractions.provideChangesBetween(commitPair));
      trackProgress.track();
    });
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    GatherChanges that = (GatherChanges) o;

    if (commitPairs != null ? !commitPairs.equals(that.commitPairs) : that.commitPairs != null) return false;
    if (gitRepoInteractions != null ? !gitRepoInteractions.equals(that.gitRepoInteractions) : that.gitRepoInteractions != null)
      return false;
    if (trackProgress != null ? !trackProgress.equals(that.trackProgress) : that.trackProgress != null) return false;
    return !(changedFiles != null ? !changedFiles.equals(that.changedFiles) : that.changedFiles != null);
  }

  @Override
  public int hashCode() {
    int result = commitPairs != null ? commitPairs.hashCode() : 0;
    result = 31 * result + (gitRepoInteractions != null ? gitRepoInteractions.hashCode() : 0);
    result = 31 * result + (trackProgress != null ? trackProgress.hashCode() : 0);
    result = 31 * result + (changedFiles != null ? changedFiles.hashCode() : 0);
    return result;
  }
}
