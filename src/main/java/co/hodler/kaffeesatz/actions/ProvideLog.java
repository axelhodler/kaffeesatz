package co.hodler.kaffeesatz.actions;

import java.util.Set;

import co.hodler.kaffesatz.model.CommitHash;

public interface ProvideLog {

  Set<CommitHash> provide();

}
