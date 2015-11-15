package co.hodler.kaffeesatz;

import java.util.Map;

import org.eclipse.jgit.api.Git;

import com.google.inject.Guice;
import com.google.inject.Injector;

import co.hodler.kaffeesatz.actions.git.GitCommitCount;
import co.hodler.kaffeesatz.actions.git.GitFetchChangedFiles;
import co.hodler.kaffeesatz.actions.git.GitProvideChangesBetweenTwoCommits;
import co.hodler.kaffeesatz.actions.git.GitRepo;
import co.hodler.kaffeesatz.concurrency.GatherChangesConcurrently;
import co.hodler.kaffeesatz.concurrency.GatherChangesFactory;
import co.hodler.kaffeesatz.concurrency.GatherChangesThreadFactory;
import co.hodler.kaffeesatz.concurrency.SplitPairsSetIntoEqualParts;
import co.hodler.kaffeesatz.di.KaffeesatzModule;
import co.hodler.kaffeesatz.ui.TerminalDisplayProgressBar;
import co.hodler.kaffeesatz.ui.TrackProgress;

public class Main {

  private static SplitPairsSetIntoEqualParts logSplitter;

  public static void main(String[] args) throws Exception {
    GitRepo gitRepo = new GitRepo();
    Git git = gitRepo.byFilePath(args[0]);
    Injector injector = Guice.createInjector(new KaffeesatzModule(git));
    logSplitter = injector.getInstance(SplitPairsSetIntoEqualParts.class);

    FileChangeChart fileChangeChart =
        new FileChangeChart(findAllChangedFiles(git));

    Map<String, Integer> changedFilesChart = fileChangeChart.createTop(30);
    changedFilesChart.keySet().stream().forEach(
        key -> System.out.printf("%d | %s\n", changedFilesChart.get(key), key));
  }

  private static GitFetchChangedFiles findAllChangedFiles(Git git) {
    return new GitFetchChangedFiles(
        logSplitter,
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
}
