package co.hodler;

import java.util.Set;

import co.hodler.model.CommitPair;

public interface CommitPairProvider {

  Set<CommitPair> providePairs();
}
