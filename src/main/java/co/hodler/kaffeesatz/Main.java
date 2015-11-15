package co.hodler.kaffeesatz;

import java.util.Map;

import org.eclipse.jgit.api.Git;

import com.google.inject.Guice;
import com.google.inject.Injector;

import co.hodler.kaffeesatz.actions.CommitCount;
import co.hodler.kaffeesatz.actions.ProvideChangesBetweenTwoCommits;
import co.hodler.kaffeesatz.actions.git.GitFetchChangedFiles;
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
  private static CommitCount commitCounter;
  private static ProvideChangesBetweenTwoCommits provideChangesBetweenTwoCommits;

  public static void main(String[] args) throws Exception {
    GitRepo gitRepo = new GitRepo();
    Git git = gitRepo.byFilePath(args[0]);
    Injector injector = Guice.createInjector(new KaffeesatzModule(git));
    logSplitter = injector.getInstance(SplitPairsSetIntoEqualParts.class);
    commitCounter = injector.getInstance(CommitCount.class);
    provideChangesBetweenTwoCommits = injector.getInstance(ProvideChangesBetweenTwoCommits.class);

    FileChangeChart fileChangeChart =
        new FileChangeChart(findAllChangedFiles(git));

    Map<String, Integer> changedFilesChart = fileChangeChart.createTop(30);
    changedFilesChart.keySet().stream().forEach(
        key -> System.out.printf("%d | %s\n", changedFilesChart.get(key), key));
  }

  private static GitFetchChangedFiles findAllChangedFiles(Git git) {
    return new GitFetchChangedFiles(
        logSplitter,
        new GatherChangesConcurrently(provideChangesBetweenTwoCommits,
            new GatherChangesThreadFactory(),
            new GatherChangesFactory(),
            trackProgressOnTerminal(git)));
  }

  private static TrackProgress trackProgressOnTerminal(Git git) {
    return new TrackProgress(new TerminalDisplayProgressBar(), commitCounter);
  }
}
