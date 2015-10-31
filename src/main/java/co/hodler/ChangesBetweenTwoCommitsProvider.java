package co.hodler;

import java.util.Set;

import co.hodler.model.CommitPair;

public interface ChangesBetweenTwoCommitsProvider {

  Set<String> fetchChangesBetween(CommitPair commitPair);
}
