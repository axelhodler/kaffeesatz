package co.hodler.kaffeesatz;

import java.util.Map;

import org.eclipse.jgit.api.Git;

import com.google.inject.Guice;
import com.google.inject.Injector;

import co.hodler.kaffeesatz.actions.git.JGitRepo;
import co.hodler.kaffeesatz.di.KaffeesatzModule;

public class Main {

  public static void main(String[] args) throws Exception {
    JGitRepo gitRepo = new JGitRepo();
    Git git = gitRepo.byFilePath(args[0]);

    Injector injector = Guice.createInjector(new KaffeesatzModule(git));
    FileChangeChart fileChangeChart = injector.getInstance(FileChangeChart.class);

    Map<String, Integer> changedFilesChart = fileChangeChart.createTop(30);
    changedFilesChart.keySet().stream().forEach(
        key -> System.out.printf("%d | %s\n", changedFilesChart.get(key), key));
  }
}
