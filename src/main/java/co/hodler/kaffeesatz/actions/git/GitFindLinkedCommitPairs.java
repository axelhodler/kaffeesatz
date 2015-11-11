package co.hodler.kaffeesatz.actions.git;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

import co.hodler.kaffeesatz.actions.FindLinkedCommitPairs;
import co.hodler.kaffeesatz.actions.ProvideLog;
import co.hodler.kaffesatz.model.CommitHash;
import co.hodler.kaffesatz.model.LinkedCommitHashPair;

public class GitFindLinkedCommitPairs implements FindLinkedCommitPairs {

  private ProvideLog provideLog;

  public GitFindLinkedCommitPairs(ProvideLog provideLog) {
    this.provideLog = provideLog;
  }

  @Override
  public Set<LinkedCommitHashPair> providePairs() {
    Set<CommitHash> commitLog = provideLog.provide();

    Iterator<CommitHash> commitLogIter = commitLog.iterator();
    commitLogIter.next();

    Set<LinkedCommitHashPair> pairs = new LinkedHashSet<>();

    commitLog.stream().forEach(commitHash -> {
      if (commitLogIter.hasNext())
        pairs.add(new LinkedCommitHashPair(commitHash, commitLogIter.next()));
    });

    return pairs;
  }

}
