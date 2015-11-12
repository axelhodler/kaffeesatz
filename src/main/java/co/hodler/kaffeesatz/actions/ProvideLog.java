package co.hodler.kaffeesatz.actions;

import java.util.Set;

import co.hodler.kaffeesatz.model.CommitHash;

public interface ProvideLog {

  Set<CommitHash> provide();

}
