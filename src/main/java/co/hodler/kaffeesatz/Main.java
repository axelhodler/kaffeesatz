package co.hodler.kaffeesatz;

import java.util.Map;

import org.eclipse.jgit.api.Git;

import com.google.inject.Guice;
import com.google.inject.Injector;

import co.hodler.kaffeesatz.actions.git.GitFetchChangedFiles;
import co.hodler.kaffeesatz.actions.git.GitRepo;
import co.hodler.kaffeesatz.concurrency.GatherChangesConcurrently;
import co.hodler.kaffeesatz.concurrency.SplitPairsSetIntoEqualParts;
import co.hodler.kaffeesatz.di.KaffeesatzModule;

public class Main {

  private static SplitPairsSetIntoEqualParts logSplitter;
  private static GatherChangesConcurrently gatherChangesConcurrently;

  public static void main(String[] args) throws Exception {
    GitRepo gitRepo = new GitRepo();
    Git git = gitRepo.byFilePath(args[0]);
    Injector injector = Guice.createInjector(new KaffeesatzModule(git));
    logSplitter = injector.getInstance(SplitPairsSetIntoEqualParts.class);
    gatherChangesConcurrently = injector.getInstance(GatherChangesConcurrently.class);

    FileChangeChart fileChangeChart =
        new FileChangeChart(findAllChangedFiles(git));

    Map<String, Integer> changedFilesChart = fileChangeChart.createTop(30);
    changedFilesChart.keySet().stream().forEach(
        key -> System.out.printf("%d | %s\n", changedFilesChart.get(key), key));
  }

  private static GitFetchChangedFiles findAllChangedFiles(Git git) {
    return new GitFetchChangedFiles(
        logSplitter,
        gatherChangesConcurrently);
  }
}
