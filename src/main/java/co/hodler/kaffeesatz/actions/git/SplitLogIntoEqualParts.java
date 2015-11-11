package co.hodler.kaffeesatz.actions.git;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import co.hodler.kaffeesatz.DistributeParts;
import co.hodler.kaffeesatz.PartSizes;
import co.hodler.kaffeesatz.actions.ProvideLog;
import co.hodler.kaffesatz.model.CommitHash;

public class SplitLogIntoEqualParts {

  private ProvideLog provideLog;

  public SplitLogIntoEqualParts(ProvideLog provideLog) {
    this.provideLog = provideLog;
  }

  public List<Set<CommitHash>> splitInto(int desiredParts) {
    Set<CommitHash> logHashes = provideLog.provide();

    return splitLog(logHashes, desiredParts);
  }

  private List<Set<CommitHash>> splitLog(Set<CommitHash> logHashes, int splits) {
    DistributeParts distributeParts = new DistributeParts();

    PartSizes sizes = distributeParts.distributeSizes(logHashes.size(), splits);

    List<Set<CommitHash>> splitLogHashes = new ArrayList<>();
    splitLogHashes.add(logHashes.stream().limit(sizes.sizeOfFirstPart()).collect(Collectors.toSet()));
    IntStream.range(0, splits - 1)
              .forEach(counter ->
                splitLogHashes.add(logHashes.stream()
                  .skip(sizes.sizeOfFirstPart() + counter * sizes.sizeOfEveryOtherPart() - counter - 1)
                  .limit(sizes.sizeOfEveryOtherPart())
                  .collect(Collectors.toSet())));
    return splitLogHashes;
  }

}
