package co.hodler.kaffeesatz.actions.git;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.inject.Inject;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.revwalk.RevCommit;

import co.hodler.kaffeesatz.actions.ProvideLog;
import co.hodler.kaffeesatz.model.CommitHash;

public class GitProvideLogHashes implements ProvideLog {

  private Git git;

  @Inject
  public GitProvideLogHashes(Git git) {
    this.git = git;
  }

  @Override
  public Set<CommitHash> provide() {
    try {
      Iterable<RevCommit> commits = git.log().all().call();
      Set<CommitHash> gitLogHashes = new LinkedHashSet<>();
      for (RevCommit commit : commits) {
        gitLogHashes.add(new CommitHash(commit.getId().name()));
      }
      return gitLogHashes;
    } catch (Exception e) {
      throw new RuntimeException("Could not get the git log", e);
    }
  }

}
