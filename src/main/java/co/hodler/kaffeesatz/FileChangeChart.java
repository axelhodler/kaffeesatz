package co.hodler.kaffeesatz;

import co.hodler.kaffeesatz.actions.FetchChangedFiles;
import co.hodler.kaffeesatz.model.ChangedFile;

import javax.inject.Inject;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class FileChangeChart {

  private FetchChangedFiles changedFiles;

  @Inject
  public FileChangeChart(FetchChangedFiles changedFiles) {
    this.changedFiles = changedFiles;
  }

  public Map<String, Integer> create() {
    List<ChangedFile> filesWithChanges = changedFiles.fetchChangedFiles();
    Set<ChangedFile> uniqueFiles = new HashSet<>(filesWithChanges);

    Map<String, Integer> changesWithAmount = uniqueFiles.stream()
            .collect(Collectors.toMap(file -> file.value(),
                    file -> Collections.frequency(filesWithChanges, file)));

    return changesWithAmount.entrySet().stream()
        .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
        .collect(toLinkedHashMap());
  }

  public Map<String, Integer> createTop(int amount) {
    return create().entrySet()
            .stream()
            .limit(amount)
            .collect(toLinkedHashMap());
  }

  private Collector<Map.Entry<String, Integer>, ?, LinkedHashMap<String, Integer>> toLinkedHashMap() {
    return Collectors.toMap(
            entry -> entry.getKey(),
            entry -> entry.getValue(),
            (v1, v2) -> { throw new RuntimeException();},
            LinkedHashMap::new);
  }
}
