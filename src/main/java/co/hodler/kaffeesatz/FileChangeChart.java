package co.hodler.kaffeesatz;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.inject.Inject;

import co.hodler.kaffeesatz.actions.FetchChangedFiles;
import co.hodler.kaffeesatz.model.ChangedFile;

public class FileChangeChart {

  private FetchChangedFiles changedFiles;

  @Inject
  public FileChangeChart(FetchChangedFiles changedFiles) {
    this.changedFiles = changedFiles;
  }

  public Map<String, Integer> create() {
    Map<String, Integer> changesWithAmount = new HashMap<>();
    List<ChangedFile> filesWithChanges = changedFiles.fetchChangedFiles();

    Set<ChangedFile> uniqueFiles = new HashSet<>(filesWithChanges);

    for (ChangedFile key : uniqueFiles) {
      changesWithAmount.put(key.value(), Collections.frequency(filesWithChanges, key));
    }

    Map<String, Integer> changesSortedByAmount = new LinkedHashMap<>();

    changesWithAmount.entrySet().stream()
        .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
        .forEach(entry -> changesSortedByAmount.put(entry.getKey(), entry.getValue()));

    return changesSortedByAmount;
  }

  public Map<String, Integer> createTop(int amount) {
    return create().entrySet()
            .stream()
            .limit(amount)
            .collect(Collectors.toMap(
                    entry -> entry.getKey(),
                    entry -> entry.getValue(),
                    (v1, v2) -> { throw new RuntimeException(); },
                    LinkedHashMap::new));
  }

}
