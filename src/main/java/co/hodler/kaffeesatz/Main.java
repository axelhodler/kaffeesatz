package co.hodler.kaffeesatz;

import java.util.Map;

import org.eclipse.jgit.api.Git;

import co.hodler.kaffeesatz.actions.git.GitCommitCount;
import co.hodler.kaffeesatz.actions.git.GitFetchChangedFiles;
import co.hodler.kaffeesatz.actions.git.GitFindLinkedCommitPairs;
import co.hodler.kaffeesatz.actions.git.GitProvideChangesBetweenTwoCommits;
import co.hodler.kaffeesatz.actions.git.GitProvideLogHashes;
import co.hodler.kaffeesatz.actions.git.GitRepo;
import co.hodler.kaffeesatz.concurrency.GatherChangesConcurrently;
import co.hodler.kaffeesatz.concurrency.GatherChangesFactory;
import co.hodler.kaffeesatz.concurrency.GatherChangesThreadFactory;
import co.hodler.kaffeesatz.concurrency.SplitPairsSetIntoEqualParts;
import co.hodler.kaffeesatz.ui.TerminalDisplayProgressBar;
import co.hodler.kaffeesatz.ui.TrackProgress;

public class Main {

  public static void main(String[] args) throws Exception {
    GitRepo gitRepo = new GitRepo();

    FileChangeChart fileChangeChart =
        new FileChangeChart(findAllChangedFiles(gitRepo.byFilePath(args[0])));

    Map<String, Integer> changedFilesChart = fileChangeChart.createTop(30);
    changedFilesChart.keySet().stream().forEach(
        key -> System.out.printf("%d | %s\n", changedFilesChart.get(key), key));
  }

  private static GitFetchChangedFiles findAllChangedFiles(Git git) {
    return new GitFetchChangedFiles(
        new SplitPairsSetIntoEqualParts(findAllCommitPairs(git)),
        new GatherChangesConcurrently(findAllChangesBetweenCommits(git),
            new GatherChangesThreadFactory(),
            new GatherChangesFactory(),
            trackProgressOnTerminal(git)));
  }

  private static TrackProgress trackProgressOnTerminal(Git git) {
    GitCommitCount gitCommitCount = new GitCommitCount(git);
    return new TrackProgress(new TerminalDisplayProgressBar(), gitCommitCount.value());
  }

  private static GitProvideChangesBetweenTwoCommits findAllChangesBetweenCommits(
      Git git) {
    return new GitProvideChangesBetweenTwoCommits(git);
  }

  private static GitFindLinkedCommitPairs findAllCommitPairs(Git git) {
    return new GitFindLinkedCommitPairs(new GitProvideLogHashes(git));
  }
}
