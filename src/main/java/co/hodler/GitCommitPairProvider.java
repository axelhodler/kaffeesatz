package co.hodler;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

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

    String currentLowerHash;
    for (String commitHash : commitLog) {
      if (commitLogIter.hasNext()) {
        currentLowerHash = commitLogIter.next().toString();
        pairs.add(new CommitPair(commitHash, currentLowerHash));
      }
    }

    return pairs;
  }

}
