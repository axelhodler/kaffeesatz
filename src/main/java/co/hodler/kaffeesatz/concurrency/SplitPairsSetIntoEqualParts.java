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

  public List<Set<LinkedCommitHashPair>> splitInto(int amount) {
    this.counter = amount;
    Map<Object, List<LinkedCommitHashPair>> groups =
        findLinkedCommitPairs.providePairs().stream()
        .collect(Collectors.groupingBy(pair -> nextKey(2)));

    List<Set<LinkedCommitHashPair>> splitPairs = new ArrayList<>();
    Set<LinkedCommitHashPair> firstSet = new HashSet<>();
    firstSet.addAll(groups.get(amount - 1));
    Set<LinkedCommitHashPair> secondSet = new HashSet<>();
    secondSet.addAll(groups.get(amount));

    splitPairs.add(firstSet);
    splitPairs.add(secondSet);

    return splitPairs;
  }

  private int nextKey(int amount) {
    if (counter == amount)
      counter = 1;
    else
      counter++;
    return counter;
  }

}
