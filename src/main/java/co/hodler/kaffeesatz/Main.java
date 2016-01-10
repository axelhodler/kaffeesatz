package co.hodler.kaffeesatz;

import java.util.Map;

import org.eclipse.jgit.api.Git;

import com.google.inject.Guice;
import com.google.inject.Injector;

import co.hodler.kaffeesatz.di.KaffeesatzModule;

public class Main {

  public static void main(String[] args) throws Exception {
    String gitRepoPath = args[0];

    Injector injector = Guice.createInjector(new KaffeesatzModule(gitRepoPath));
    FileChangeChart fileChangeChart = injector.getInstance(FileChangeChart.class);

    Map<String, Integer> changedFilesChart = fileChangeChart.createTop(30);
    changedFilesChart.keySet().stream().forEach(
        key -> System.out.printf("%d | %s\n", changedFilesChart.get(key), key));
  }
}
