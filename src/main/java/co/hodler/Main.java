package co.hodler;

import java.io.File;
import java.util.Map;

import org.eclipse.jgit.api.Git;

import co.hodler.git.GitChangedFiles;
import co.hodler.git.GitCommitPairProvider;
import co.hodler.git.ProvideChangesBetweenTwoGitCommits;
import co.hodler.git.ProvideGitLogHashes;

public class Main {

  public static void main(String[] args) throws Exception {
    File gitWorkDir = new File(args[0]);
    Git git = Git.open(gitWorkDir);

    FileChangeChart fileChangeChart = new FileChangeChart(new GitChangedFiles(
        new GitCommitPairProvider(new ProvideGitLogHashes(git)),
        new ProvideChangesBetweenTwoGitCommits(git)));

    Map<String, Integer> changedFilesChart = fileChangeChart.create();
    changedFilesChart.keySet().stream().forEach(
        key -> System.out.println(key + " | " + changedFilesChart.get(key)));
  }
}
