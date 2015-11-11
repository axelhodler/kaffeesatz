package co.hodler.kaffeesatz.actions.git;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import co.hodler.kaffeesatz.DistributeParts;
import co.hodler.kaffeesatz.actions.ProvideLog;
import co.hodler.model.CommitHash;

public class SplitLogIntoEqualParts {

  private ProvideLog provideLog;

  public SplitLogIntoEqualParts(ProvideLog provideLog) {
    this.provideLog = provideLog;
  }

  public List<Set<CommitHash>> splitInto(int desiredParts) {
    Set<CommitHash> logHashes = provideLog.provide();

    return splitLog(logHashes, calculateLimit(desiredParts, logHashes), desiredParts);
  }

  private int calculateLimit(int desiredParts, Set<CommitHash> logHashes) {
    DistributeParts distributeParts = new DistributeParts();

    return distributeParts.distribute(logHashes.size(), desiredParts)[0];
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
