package co.hodler;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

import co.hodler.model.CommitHash;
import co.hodler.model.CommitPair;

public class GitCommitPairProvider implements CommitPairProvider {

  private ProvideLog provideLog;

  public GitCommitPairProvider(ProvideLog provideLog) {
    this.provideLog = provideLog;
  }

  @Override
  public Set<CommitPair> providePairs() {
    Set<String> commitLog = provideLog.provide();

    Iterator<String> commitLogIter = commitLog.iterator();
    commitLogIter.next();

    Set<CommitPair> pairs = new LinkedHashSet<>();

    commitLog.stream().forEach(commitHash -> {
      if (commitLogIter.hasNext())
        pairs.add(new CommitPair(new CommitHash(commitHash), new CommitHash(commitLogIter.next().toString())));
    });

    return pairs;
  }

}
