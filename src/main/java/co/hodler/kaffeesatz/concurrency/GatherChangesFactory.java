package co.hodler.kaffeesatz.concurrency;

import java.util.List;
import java.util.Set;

import co.hodler.kaffeesatz.TrackProgress;
import co.hodler.kaffeesatz.actions.ProvideChangesBetweenTwoCommits;
import co.hodler.kaffeesatz.model.ChangedFile;
import co.hodler.kaffeesatz.model.LinkedCommitHashPair;

public class GatherChangesFactory {

  public GatherChanges createThreadToGatherChanges(Set<LinkedCommitHashPair> commitPairs,
      ProvideChangesBetweenTwoCommits changesBetweenTwoCommitsProvider,
      TrackProgress trackProgress, List<ChangedFile> changedFiles) {
    return new GatherChanges(commitPairs,
        changesBetweenTwoCommitsProvider, trackProgress, changedFiles);
  }
}
