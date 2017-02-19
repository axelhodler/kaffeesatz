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

import java.util.Map;

public class Main {

  public static void main(String[] args) throws Exception {
    JGitRepoInteraction gitRepoInteraction = new JGitRepoInteraction();
    gitRepoInteraction.initFunctionality(args[0]);

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
