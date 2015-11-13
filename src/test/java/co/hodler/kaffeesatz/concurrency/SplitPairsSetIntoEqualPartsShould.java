package co.hodler.kaffeesatz.concurrency;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.BDDMockito.given;

import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.internal.util.collections.Sets;
import org.mockito.runners.MockitoJUnitRunner;

import co.hodler.kaffeesatz.actions.FindLinkedCommitPairs;
import co.hodler.kaffeesatz.model.CommitHash;
import co.hodler.kaffeesatz.model.LinkedCommitHashPair;

@RunWith(MockitoJUnitRunner.class)
public class SplitPairsSetIntoEqualPartsShould {

  @Mock
  FindLinkedCommitPairs findLinkedCommitPairs;

  private SplitPairsSetIntoEqualParts splitter;

  @Before
  public void initialise() {
    splitter = new SplitPairsSetIntoEqualParts(findLinkedCommitPairs);
  }

  @Test
  public void split_two_pairs_into_one_each() {
    given(findLinkedCommitPairs.providePairs()).willReturn(
        Sets.newSet(firstPair(), secondPair()));

    List<Set<LinkedCommitHashPair>> splitPairs = splitter.splitInto(2);

    assertThat(splitPairs.get(0), hasItem(firstPair()));
    assertThat(splitPairs.get(1), hasItem(secondPair()));
  }

  @Test
  public void split_three_pairs_into_two_and_one() {
    given(findLinkedCommitPairs.providePairs()).willReturn(
        Sets.newSet(firstPair(), secondPair(), thirdPair()));

    List<Set<LinkedCommitHashPair>> splitPairs = splitter.splitInto(2);

    assertThat(splitPairs.get(0), hasItem(firstPair()));
    assertThat(splitPairs.get(0), hasItem(thirdPair()));
    assertThat(splitPairs.get(1), hasItem(secondPair()));
  }

  private LinkedCommitHashPair firstPair() {
    return new LinkedCommitHashPair(
        new CommitHash("1b937db69b4f7edb519b051ef8319de56de6f627"),
        new CommitHash("2e121305db8fa36f1dbc6083e628b245e20ef4c4"));
  }

  private LinkedCommitHashPair secondPair() {
    return new LinkedCommitHashPair(
        new CommitHash("2e121305db8fa36f1dbc6083e628b245e20ef4c4"),
        new CommitHash("5633c849c06f6063fdca5dec5054950e403447b8"));
  }

  private LinkedCommitHashPair thirdPair() {
    return new LinkedCommitHashPair(
        new CommitHash("5633c849c06f6063fdca5dec5054950e403447b8"),
        new CommitHash("6633c849c06f6063fdca5dec5054950e403447b8"));
  }
}
