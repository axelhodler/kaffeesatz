package co.hodler.kaffeesatz.actions;

import java.util.Set;

import co.hodler.kaffeesatz.model.LinkedCommitHashPair;

public interface FindLinkedCommitPairs {

  Set<LinkedCommitHashPair> providePairs();
}
