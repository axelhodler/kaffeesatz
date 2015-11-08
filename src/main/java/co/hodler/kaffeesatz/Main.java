package co.hodler.kaffeesatz;

import java.io.File;
import java.util.Map;

import org.eclipse.jgit.api.Git;

import co.hodler.kaffeesatz.actions.git.GitFetchChangedFiles;
import co.hodler.kaffeesatz.actions.git.GitFindLinkedCommitPairs;
import co.hodler.kaffeesatz.actions.git.GitProvideChangesBetweenTwoCommits;
import co.hodler.kaffeesatz.actions.git.GitProvideLogHashes;

public class Main {

  public static void main(String[] args) throws Exception {
    File gitWorkDir = new File(args[0]);
    Git git = Git.open(gitWorkDir);

    FileChangeChart fileChangeChart = new FileChangeChart(new GitFetchChangedFiles(
        new GitFindLinkedCommitPairs(new GitProvideLogHashes(git)),
        new GitProvideChangesBetweenTwoCommits(git), new TrackProgress(new TerminalDisplayProgressBar(), 0)));

    Map<String, Integer> changedFilesChart = fileChangeChart.create();
    changedFilesChart.keySet().stream().forEach(
        key -> System.out.printf("%d | %s\n", changedFilesChart.get(key), key));
  }
}
