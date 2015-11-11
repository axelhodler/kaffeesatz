package co.hodler.kaffeesatz.actions;

import java.util.Set;

import co.hodler.kaffesatz.model.LinkedCommitHashPair;

public interface ProvideChangesBetweenTwoCommits {

  Set<String> fetchChangesBetween(LinkedCommitHashPair commitPair);
}
