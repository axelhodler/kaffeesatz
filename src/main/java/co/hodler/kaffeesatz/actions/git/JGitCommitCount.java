package co.hodler.kaffeesatz.actions.git;

import java.util.stream.StreamSupport;

import javax.inject.Inject;

import org.eclipse.jgit.api.Git;

import co.hodler.kaffeesatz.actions.CommitCount;

public class JGitCommitCount implements CommitCount {

  private Git git;

  @Inject
  public JGitCommitCount(Git git) {
    this.git = git;
  }

  @Override
  public int value() {
    try {
      return (int) StreamSupport.stream(git.log().all().call().spliterator(), false).count();
    } catch (Exception e) {
      throw new RuntimeException("Could not get commit count", e);
    }
  }
  
}
