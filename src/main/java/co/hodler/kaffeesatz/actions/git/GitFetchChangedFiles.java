package co.hodler.kaffeesatz.actions.git;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import co.hodler.kaffeesatz.DisplayProgressBar;
import co.hodler.kaffeesatz.actions.FetchChangedFiles;
import co.hodler.kaffeesatz.actions.ProvideChangesBetweenTwoCommits;
import co.hodler.kaffeesatz.actions.FindLinkedCommitPairs;

public class GitFetchChangedFiles implements FetchChangedFiles {

  private FindLinkedCommitPairs commitPairProvider;
  private ProvideChangesBetweenTwoCommits changesBetweenTwoCommitsProvider;
  private DisplayProgressBar displayProgressBar;

  public GitFetchChangedFiles(FindLinkedCommitPairs commitPairProvider,
      ProvideChangesBetweenTwoCommits changesBetweenTwoCommitsProvider,
      DisplayProgressBar displayProgressBar) {
    this.commitPairProvider = commitPairProvider;
    this.changesBetweenTwoCommitsProvider = changesBetweenTwoCommitsProvider;
    this.displayProgressBar = displayProgressBar;
  }

  @Override
  public List<String> fetchChangedFiles() {
    List<String> changedFiles = new ArrayList<>();
    displayProgressBar.display();
    commitPairProvider.providePairs().stream()
        .collect(Collectors.toList())
        .forEach(commitPair -> changedFiles.addAll(changesBetweenTwoCommitsProvider.fetchChangesBetween(commitPair)));

    return changedFiles;
  }

}
