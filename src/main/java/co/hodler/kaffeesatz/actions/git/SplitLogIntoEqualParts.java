package co.hodler.kaffeesatz.actions.git;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import co.hodler.kaffeesatz.actions.ProvideLog;
import co.hodler.model.CommitHash;

public class SplitLogIntoEqualParts {

  private ProvideLog provideLog;

  public SplitLogIntoEqualParts(ProvideLog provideLog) {
    this.provideLog = provideLog;
  }

  public List<Set<CommitHash>> splitInto(int parts) {
    Set<CommitHash> logHashes = provideLog.provide();
    List<Set<CommitHash>> splitLogHashes = new ArrayList<>();

    if (parts == 2 && logHashes.size() == 3) {
      splitLogHashes.add(logHashes.stream().limit(2).collect(Collectors.toSet()));
      splitLogHashes.add(logHashes.stream().skip(1).collect(Collectors.toSet()));
    } else if (parts == 2 && logHashes.size() == 4) {
      splitLogHashes.add(logHashes.stream().limit(3).collect(Collectors.toSet()));
      splitLogHashes.add(logHashes.stream().skip(2).collect(Collectors.toSet()));
    } else if (parts == 4) {
      splitLogHashes.add(logHashes.stream().limit(2).collect(Collectors.toSet()));
      splitLogHashes.add(logHashes.stream().skip(1).collect(Collectors.toSet()));
      splitLogHashes.add(logHashes.stream().skip(2).collect(Collectors.toSet()));
      splitLogHashes.add(logHashes.stream().skip(3).collect(Collectors.toSet()));
    } else {
      splitLogHashes = splitLog(logHashes, 2, 3);
    }

    return splitLogHashes;
  }

  private List<Set<CommitHash>> splitLog(Set<CommitHash> logHashes, int limit, int splits) {
    List<Set<CommitHash>> splitLogHashes = new ArrayList<>();
    splitLogHashes.add(logHashes.stream().limit(limit).collect(Collectors.toSet()));
    IntStream.range(1, splits)
              .forEach(amountToSkip -> splitLogHashes.add(logHashes.stream()
                                                                    .skip(amountToSkip)
                                                                    .collect(Collectors.toSet())));
    return splitLogHashes;
  }

}
