package co.hodler;

import java.util.Set;

import co.hodler.model.LinkedCommitHashPair;

public interface ChangesBetweenTwoCommitsProvider {

  Set<String> fetchChangesBetween(LinkedCommitHashPair commitPair);
}
