package co.hodler.kaffeesatz.actions;

import java.util.Set;

import co.hodler.model.LinkedCommitHashPair;

public interface CommitPairProvider {

  Set<LinkedCommitHashPair> providePairs();
}
