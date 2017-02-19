package co.hodler.kaffeesatz;

import co.hodler.kaffeesatz.actions.git.GitFetchChangedFiles;
import co.hodler.kaffeesatz.actions.git.GitFindLinkedCommitPairs;
import co.hodler.kaffeesatz.boundaries.JGitRepoInteraction;
import co.hodler.kaffeesatz.concurrency.GatherChangesConcurrently;
import co.hodler.kaffeesatz.concurrency.GatherChangesFactory;
import co.hodler.kaffeesatz.concurrency.GatherChangesThreadFactory;
import co.hodler.kaffeesatz.concurrency.SplitPairsSetIntoEqualParts;
import co.hodler.kaffeesatz.model.CommitCount;
import co.hodler.kaffeesatz.ui.ConsolePrinter;
import co.hodler.kaffeesatz.ui.TerminalDisplayProgressBar;
import co.hodler.kaffeesatz.ui.TrackProgress;
import org.eclipse.jgit.api.Git;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class Main {

  public static void main(String[] args) throws Exception {
    Git git;
    try {
      git = Git.open(new File(args[0]));
    } catch (IOException e) {
      throw new RuntimeException("Could not find provided git repo", e);
    }
    JGitRepoInteraction gitRepoInteraction = new JGitRepoInteraction(git);

    FileChangeChart fileChangeChart = new FileChangeChart(
      new GitFetchChangedFiles(
        new SplitPairsSetIntoEqualParts(
          new GitFindLinkedCommitPairs(gitRepoInteraction)
        ), new GatherChangesConcurrently(gitRepoInteraction,
        new GatherChangesThreadFactory(),
        new GatherChangesFactory(),
        new TrackProgress(
          new TerminalDisplayProgressBar(
            new ConsolePrinter()),
          new CommitCount(gitRepoInteraction.provideCommitCount()))
      )));

    Map<String, Integer> changedFilesChart = fileChangeChart.createTop(30);
    changedFilesChart.keySet().stream().forEach(
      key -> System.out.printf("%d | %s\n", changedFilesChart.get(key), key));
  }
}
