package co.hodler.kaffeesatz.actions.git;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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

    if (parts == 2) {
      splitLogHashes.add(logHashes.stream().limit(2).collect(Collectors.toSet()));
      splitLogHashes.add(logHashes.stream().skip(1).collect(Collectors.toSet()));
    } else {
      splitLogHashes.add(logHashes.stream().limit(2).collect(Collectors.toSet()));
      splitLogHashes.add(logHashes.stream().skip(1).collect(Collectors.toSet()));
      splitLogHashes.add(logHashes.stream().skip(2).collect(Collectors.toSet()));
    }

    return splitLogHashes;
  }

}
