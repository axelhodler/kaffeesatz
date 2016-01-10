package co.hodler.kaffeesatz.di;

import co.hodler.kaffeesatz.actions.CommitCount;
import co.hodler.kaffeesatz.actions.FetchChangedFiles;
import co.hodler.kaffeesatz.actions.FindLinkedCommitPairs;
import co.hodler.kaffeesatz.actions.ProvideChangesBetweenTwoCommits;
import co.hodler.kaffeesatz.actions.git.GitFetchChangedFiles;
import co.hodler.kaffeesatz.actions.git.GitFindLinkedCommitPairs;
import co.hodler.kaffeesatz.actions.git.JGitCommitCount;
import co.hodler.kaffeesatz.actions.git.JGitProvideChangesBetweenTwoCommits;
import co.hodler.kaffeesatz.boundaries.GitRepoInteractions;
import co.hodler.kaffeesatz.boundaries.JGitRepoInteraction;
import co.hodler.kaffeesatz.ui.DisplayProgressBar;
import co.hodler.kaffeesatz.ui.TerminalDisplayProgressBar;
import com.google.inject.AbstractModule;
import org.eclipse.jgit.api.Git;

public class KaffeesatzModule extends AbstractModule {

  private String gitRepoPath;
  private Git git;

  public KaffeesatzModule(Git git) {
    this.git = git;
  }

  public KaffeesatzModule(String gitRepoPath) {
    this.gitRepoPath = gitRepoPath;
  }

  @Override
  protected void configure() {
    JGitRepoInteraction gitRepoInteraction = new JGitRepoInteraction();
    gitRepoInteraction.initFunctionality(gitRepoPath);
    bind(GitRepoInteractions.class).toInstance(gitRepoInteraction);

    bind(Git.class).toInstance(git);

    bind(FindLinkedCommitPairs.class).to(GitFindLinkedCommitPairs.class);

    bind(CommitCount.class).to(JGitCommitCount.class);

    bind(ProvideChangesBetweenTwoCommits.class).to(JGitProvideChangesBetweenTwoCommits.class);

    bind(DisplayProgressBar.class).to(TerminalDisplayProgressBar.class);

    bind(FetchChangedFiles.class).to(GitFetchChangedFiles.class);
  }

}
