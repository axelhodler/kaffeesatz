package co.hodler.kaffeesatz.actions.git;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.inject.Inject;

import co.hodler.kaffeesatz.actions.FindLinkedCommitPairs;
import co.hodler.kaffeesatz.boundaries.GitRepoInteractions;
import co.hodler.kaffeesatz.model.CommitHash;
import co.hodler.kaffeesatz.model.LinkedCommitHashPair;

public class GitFindLinkedCommitPairs implements FindLinkedCommitPairs {

  private GitRepoInteractions gitRepoInteractions;

  @Inject
  public GitFindLinkedCommitPairs(GitRepoInteractions gitRepoInteractions) {
    this.gitRepoInteractions = gitRepoInteractions;
  }

  @Override
  public Set<LinkedCommitHashPair> providePairs() {
    Set<CommitHash> commitLog = gitRepoInteractions.provideOrderedLogOfCommitHashes();

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
