package co.hodler.kaffeesatz.actions;

import java.util.Set;

import co.hodler.model.CommitHash;

public interface ProvideLog {

  Set<CommitHash> provide();

}
