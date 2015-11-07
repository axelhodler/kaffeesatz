package co.hodler.kaffeesatz.actions.git;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import co.hodler.kaffeesatz.actions.FetchChangedFiles;
import co.hodler.kaffeesatz.actions.ProvideChangesBetweenTwoCommits;
import co.hodler.kaffeesatz.actions.FindLinkedCommitPairs;

public class GitFetchChangedFiles implements FetchChangedFiles {

  private FindLinkedCommitPairs commitPairProvider;
  private ProvideChangesBetweenTwoCommits changesBetweenTwoCommitsProvider;

  public GitFetchChangedFiles(FindLinkedCommitPairs commitPairProvider,
      ProvideChangesBetweenTwoCommits changesBetweenTwoCommitsProvider) {
    this.commitPairProvider = commitPairProvider;
    this.changesBetweenTwoCommitsProvider = changesBetweenTwoCommitsProvider;
  }

  @Override
  public List<String> fetchChangedFiles() {
    List<String> changedFiles = new ArrayList<>();

    commitPairProvider.providePairs().stream()
        .collect(Collectors.toList())
        .forEach(commitPair -> changedFiles.addAll(changesBetweenTwoCommitsProvider.fetchChangesBetween(commitPair)));

    return changedFiles;
  }

}
