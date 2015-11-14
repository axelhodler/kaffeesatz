package co.hodler.kaffeesatz.concurrency;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
        .collect(Collectors.groupingBy(pair -> nextKey(amount)));

    return toSet(amount, groups);
  }

  private List<Set<LinkedCommitHashPair>> toSet(int amount,
      Map<Object, List<LinkedCommitHashPair>> groups) {
    List<Set<LinkedCommitHashPair>> splitPairs = new ArrayList<>();
    IntStream.rangeClosed(1, amount)
      .forEach(key -> {
        Set<LinkedCommitHashPair> pairSet = new HashSet<>();
        pairSet.addAll(groups.get(key));
        splitPairs.add(pairSet);
      });
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
