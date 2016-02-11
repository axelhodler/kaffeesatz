package co.hodler.kaffeesatz.di;

import co.hodler.kaffeesatz.actions.FetchChangedFiles;
import co.hodler.kaffeesatz.actions.FindLinkedCommitPairs;
import co.hodler.kaffeesatz.actions.git.GitFetchChangedFiles;
import co.hodler.kaffeesatz.actions.git.GitFindLinkedCommitPairs;
import co.hodler.kaffeesatz.boundaries.GitRepoInteractions;
import co.hodler.kaffeesatz.boundaries.JGitRepoInteraction;
import co.hodler.kaffeesatz.model.CommitCount;
import co.hodler.kaffeesatz.ui.ConsolePrinter;
import co.hodler.kaffeesatz.ui.DisplayProgressBar;
import co.hodler.kaffeesatz.ui.Printer;
import co.hodler.kaffeesatz.ui.TerminalDisplayProgressBar;
import com.google.inject.AbstractModule;

public class KaffeesatzModule extends AbstractModule {

  private String gitRepoPath;

  public KaffeesatzModule(String gitRepoPath) {
    this.gitRepoPath = gitRepoPath;
  }

  @Override
  protected void configure() {
    JGitRepoInteraction gitRepoInteraction = new JGitRepoInteraction();
    gitRepoInteraction.initFunctionality(gitRepoPath);
    bind(GitRepoInteractions.class).toInstance(gitRepoInteraction);
    bind(CommitCount.class).toInstance(new CommitCount(gitRepoInteraction.provideCommitCount()));

    bind(FindLinkedCommitPairs.class).to(GitFindLinkedCommitPairs.class);

    bind(Printer.class).to(ConsolePrinter.class);
    bind(DisplayProgressBar.class).to(TerminalDisplayProgressBar.class);

    bind(FetchChangedFiles.class).to(GitFetchChangedFiles.class);
  }

}
