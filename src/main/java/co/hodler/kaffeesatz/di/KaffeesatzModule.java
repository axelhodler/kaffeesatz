package co.hodler.kaffeesatz.di;

import org.eclipse.jgit.api.Git;

import com.google.inject.AbstractModule;

import co.hodler.kaffeesatz.actions.FindLinkedCommitPairs;
import co.hodler.kaffeesatz.actions.ProvideLog;
import co.hodler.kaffeesatz.actions.git.GitFindLinkedCommitPairs;
import co.hodler.kaffeesatz.actions.git.GitProvideLogHashes;

public class KaffeesatzModule extends AbstractModule {

  private Git git;

  public KaffeesatzModule(Git git) {
    this.git = git;
  }

  @Override
  protected void configure() {
    bind(Git.class).toInstance(git);

    bind(ProvideLog.class).to(GitProvideLogHashes.class);
    bind(FindLinkedCommitPairs.class).to(GitFindLinkedCommitPairs.class);
  }

}
