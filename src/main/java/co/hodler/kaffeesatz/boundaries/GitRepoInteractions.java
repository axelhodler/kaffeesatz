package co.hodler.kaffeesatz.boundaries;

import co.hodler.kaffeesatz.model.ChangedFile;
import co.hodler.kaffeesatz.model.CommitHash;
import co.hodler.kaffeesatz.model.LinkedCommitHashPair;

import java.util.Set;

public interface GitRepoInteractions {
  void initFunctionality(String path);

  Set<CommitHash> provideOrderedLogOfCommitHashes();

  Set<ChangedFile> provideChangesBetween(LinkedCommitHashPair commitPair);

  int provideCommitCount();
}
