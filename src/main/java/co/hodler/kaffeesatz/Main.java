package co.hodler.kaffeesatz;

import java.util.Map;

import org.eclipse.jgit.api.Git;

import co.hodler.kaffeesatz.actions.git.GitCommitCount;
import co.hodler.kaffeesatz.actions.git.GitFetchChangedFiles;
import co.hodler.kaffeesatz.actions.git.GitFindLinkedCommitPairs;
import co.hodler.kaffeesatz.actions.git.GitProvideChangesBetweenTwoCommits;
import co.hodler.kaffeesatz.actions.git.GitProvideLogHashes;
import co.hodler.kaffeesatz.actions.git.GitRepo;

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
        findAllCommitPairs(git),
        findAllChangesBetweenCommits(git),
        trackProgressOnTerminal(git));
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
