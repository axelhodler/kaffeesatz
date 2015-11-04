package co.hodler.kaffeesatz.actions;

import java.util.LinkedHashSet;
import java.util.Set;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.revwalk.RevCommit;

import co.hodler.model.CommitHash;

public class ProvideGitLogHashes implements ProvideLog {

  private Git git;

  public ProvideGitLogHashes(Git git) {
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
