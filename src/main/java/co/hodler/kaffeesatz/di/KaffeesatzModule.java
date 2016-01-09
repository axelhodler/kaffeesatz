package co.hodler.kaffeesatz.di;

import org.eclipse.jgit.api.Git;

import com.google.inject.AbstractModule;

import co.hodler.kaffeesatz.actions.CommitCount;
import co.hodler.kaffeesatz.actions.FetchChangedFiles;
import co.hodler.kaffeesatz.actions.FindLinkedCommitPairs;
import co.hodler.kaffeesatz.actions.ProvideChangesBetweenTwoCommits;
import co.hodler.kaffeesatz.actions.ProvideLog;
import co.hodler.kaffeesatz.actions.git.JGitCommitCount;
import co.hodler.kaffeesatz.actions.git.GitFetchChangedFiles;
import co.hodler.kaffeesatz.actions.git.GitFindLinkedCommitPairs;
import co.hodler.kaffeesatz.actions.git.JGitProvideChangesBetweenTwoCommits;
import co.hodler.kaffeesatz.actions.git.JGitProvideLogHashes;
import co.hodler.kaffeesatz.ui.DisplayProgressBar;
import co.hodler.kaffeesatz.ui.TerminalDisplayProgressBar;

public class KaffeesatzModule extends AbstractModule {

  private Git git;

  public KaffeesatzModule(Git git) {
    this.git = git;
  }

  @Override
  protected void configure() {
    bind(Git.class).toInstance(git);

    bind(ProvideLog.class).to(JGitProvideLogHashes.class);
    bind(FindLinkedCommitPairs.class).to(GitFindLinkedCommitPairs.class);

    bind(CommitCount.class).to(JGitCommitCount.class);

    bind(ProvideChangesBetweenTwoCommits.class).to(JGitProvideChangesBetweenTwoCommits.class);

    bind(DisplayProgressBar.class).to(TerminalDisplayProgressBar.class);

    bind(FetchChangedFiles.class).to(GitFetchChangedFiles.class);
  }

}
