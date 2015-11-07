package co.hodler.kaffeesatz.actions.git;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import co.hodler.kaffeesatz.actions.ChangedFiles;
import co.hodler.kaffeesatz.actions.ChangesBetweenTwoCommitsProvider;
import co.hodler.kaffeesatz.actions.CommitPairProvider;

public class GitChangedFiles implements ChangedFiles {

  private CommitPairProvider commitPairProvider;
  private ChangesBetweenTwoCommitsProvider changesBetweenTwoCommitsProvider;

  public GitChangedFiles(CommitPairProvider commitPairProvider,
      ChangesBetweenTwoCommitsProvider changesBetweenTwoCommitsProvider) {
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
