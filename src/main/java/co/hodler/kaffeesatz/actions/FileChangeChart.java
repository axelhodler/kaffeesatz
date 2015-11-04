package co.hodler.kaffeesatz.actions;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class FileChangeChart {

  private ChangedFiles changedFiles;

  public FileChangeChart(ChangedFiles changedFiles) {
    this.changedFiles = changedFiles;
  }

  public Map<String, Integer> create() {
    Map<String, Integer> changesWithAmount = new HashMap<>();
    List<String> filesWithChanges = changedFiles.fetchChangedFiles();

    Set<String> uniqueFiles = new HashSet<>(filesWithChanges);

    for (String key : uniqueFiles) {
      changesWithAmount.put(key, Collections.frequency(filesWithChanges, key));
    }

    Map<String, Integer> changesSortedByAmount = new LinkedHashMap<>();

    changesWithAmount.entrySet().stream()
        .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
        .forEach(entry -> changesSortedByAmount.put(entry.getKey(), entry.getValue()));

    return changesSortedByAmount;
  }

}
