package co.hodler.kaffeesatz.concurrency;

import java.util.List;
import java.util.Set;

import co.hodler.kaffeesatz.boundaries.GitRepoInteractions;
import co.hodler.kaffeesatz.model.ChangedFile;
import co.hodler.kaffeesatz.model.LinkedCommitHashPair;
import co.hodler.kaffeesatz.ui.TrackProgress;

public class GatherChangesFactory {

  public GatherChanges createGatherChanges(Set<LinkedCommitHashPair> commitPairs,
      GitRepoInteractions gitRepoInteractions,
      TrackProgress trackProgress, List<ChangedFile> changedFiles) {
    return new GatherChanges(commitPairs,
        gitRepoInteractions, trackProgress, changedFiles);
  }
}
