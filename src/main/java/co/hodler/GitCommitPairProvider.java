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
    Set<String> commitLog = provideLog.provide();

    Iterator<String> commitLogIter = commitLog.iterator();
    commitLogIter.next();

    Set<LinkedCommitHashPair> pairs = new LinkedHashSet<>();

    commitLog.stream().forEach(commitHash -> {
      if (commitLogIter.hasNext())
        pairs.add(new LinkedCommitHashPair(new CommitHash(commitHash), new CommitHash(commitLogIter.next().toString())));
    });

    return pairs;
  }

}
