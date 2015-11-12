package co.hodler.kaffeesatz.concurrency;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import co.hodler.kaffeesatz.actions.FindLinkedCommitPairs;
import co.hodler.kaffeesatz.model.LinkedCommitHashPair;

public class SplitPairsSetIntoEqualParts {

  private FindLinkedCommitPairs findLinkedCommitPairs;
  private int counter = 1;

  public SplitPairsSetIntoEqualParts(FindLinkedCommitPairs findLinkedCommitPairs) {
    this.findLinkedCommitPairs = findLinkedCommitPairs;
  }

  public List<Set<LinkedCommitHashPair>> splitIntoTwo() {
    Map<Object, List<LinkedCommitHashPair>> groups =
        findLinkedCommitPairs.providePairs().stream()
        .collect(Collectors.groupingBy(pair -> nextKey()));

    List<Set<LinkedCommitHashPair>> splitPairs = new ArrayList<>();
    Set<LinkedCommitHashPair> firstSet = new HashSet<>();
    firstSet.addAll(groups.get(0));
    Set<LinkedCommitHashPair> secondSet = new HashSet<>();
    secondSet.addAll(groups.get(1));

    splitPairs.add(firstSet);
    splitPairs.add(secondSet);

    return splitPairs;
  }

  private int nextKey() {
    if (counter == 1)
      counter = 0;
    else
      counter = 1;
    return counter;
  }

}
