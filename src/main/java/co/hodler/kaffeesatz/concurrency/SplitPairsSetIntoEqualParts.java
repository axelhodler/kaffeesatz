package co.hodler.kaffeesatz.concurrency;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.inject.Inject;

import co.hodler.kaffeesatz.actions.FindLinkedCommitPairs;
import co.hodler.kaffeesatz.model.LinkedCommitHashPair;

public class SplitPairsSetIntoEqualParts {

  private FindLinkedCommitPairs findLinkedCommitPairs;
  private int counter = 1;

  @Inject
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
    return IntStream.rangeClosed(1, amount)
      .mapToObj(key -> addGroupsToSet(groups, key))
      .collect(Collectors.toList());
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
