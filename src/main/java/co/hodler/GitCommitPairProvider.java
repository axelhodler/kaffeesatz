package co.hodler;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

import co.hodler.model.CommitHash;
import co.hodler.model.LinkedCommitHashPair;

public class GitCommitPairProvider implements CommitPairProvider {

  private ProvideLog provideLog;

  public GitCommitPairProvider(ProvideLog provideLog) {
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
