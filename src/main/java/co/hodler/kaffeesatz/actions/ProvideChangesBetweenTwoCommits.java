package co.hodler.kaffeesatz.actions;

import java.util.Set;

import co.hodler.kaffeesatz.model.ChangedFile;
import co.hodler.kaffeesatz.model.LinkedCommitHashPair;

public interface ProvideChangesBetweenTwoCommits {

  Set<ChangedFile> fetchChangesBetween(LinkedCommitHashPair commitPair);
}
