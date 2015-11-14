package co.hodler.kaffeesatz.concurrency;

import java.util.ArrayList;
import java.util.Collections;
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
        splitPairs.add(addGroupsToSet(groups, key));
      });
    return splitPairs;
  }

  private Set<LinkedCommitHashPair> addGroupsToSet(
      Map<Object, List<LinkedCommitHashPair>> groups, int key) {
    Set<LinkedCommitHashPair> pairSet = new HashSet<>();
    if (groups.get(key) != null)
      pairSet.addAll(groups.get(key));
    else
      pairSet.addAll(Collections.emptySet());
    return pairSet;
  }

  private int nextKey(int amount) {
    if (counter == amount)
      counter = 1;
    else
      counter++;
    return counter;
  }

}
