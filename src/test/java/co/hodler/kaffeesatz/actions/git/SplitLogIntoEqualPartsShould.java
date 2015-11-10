package co.hodler.kaffeesatz.actions.git;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.BDDMockito.given;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.internal.util.collections.Sets;
import org.mockito.runners.MockitoJUnitRunner;

import co.hodler.kaffeesatz.actions.ProvideLog;
import co.hodler.model.CommitHash;

@RunWith(MockitoJUnitRunner.class)
public class SplitLogIntoEqualPartsShould {

  @Mock
  ProvideLog provideLog;

  private SplitLogIntoEqualParts splitLog;

  @Before
  public void setUp() {
    splitLog = new SplitLogIntoEqualParts(provideLog);
  }

  @Test
  public void splitLogIntoTwo() {
    Set<CommitHash> commitLogHashes = Sets.newSet(
        new CommitHash("2e121305db8fa36f1dbc6083e628b245e20ef4c4"),
        new CommitHash("5633c849c06f6063fdca5dec5054950e403447b8"),
        new CommitHash("1b937db69b4f7edb519b051ef8319de56de6f627"));
    given(provideLog.provide()).willReturn(commitLogHashes);

    List<Set<CommitHash>> logParts = splitLog.splitInto(2);

    assertThat(first(logParts), hasCommitHash("2e121305db8fa36f1dbc6083e628b245e20ef4c4"));
    assertThat(first(logParts), hasCommitHash("5633c849c06f6063fdca5dec5054950e403447b8"));
    assertThat(second(logParts), hasCommitHash("5633c849c06f6063fdca5dec5054950e403447b8"));
    assertThat(second(logParts), hasCommitHash("1b937db69b4f7edb519b051ef8319de56de6f627"));
  }

  @Test
  public void splitLogIntoThree() {
    given(provideLog.provide()).willReturn(fourCommits());

    List<Set<CommitHash>> logParts = splitLog.splitInto(3);

    assertThat(first(logParts), hasCommitHash("1e121305db8fa36f1dbc6083e628b245e20ef4c1"));
    assertThat(first(logParts), hasCommitHash("2633c849c06f6063fdca5dec5054950e403447b2"));
    assertThat(second(logParts), hasCommitHash("2633c849c06f6063fdca5dec5054950e403447b2"));
    assertThat(second(logParts), hasCommitHash("3633c849c06f6063fdca5dec5054950e403447b3"));
    assertThat(third(logParts), hasCommitHash("3633c849c06f6063fdca5dec5054950e403447b3"));
    assertThat(third(logParts), hasCommitHash("4633c849c06f6063fdca5dec5054950e403447b4"));
  }

  @Test
  public void splitFourCommitsIntoTwoPieces() {
    given(provideLog.provide()).willReturn(fourCommits());

    List<Set<CommitHash>> logParts = splitLog.splitInto(2);

    assertThat(first(logParts), hasCommitHash("1e121305db8fa36f1dbc6083e628b245e20ef4c1"));
    assertThat(first(logParts), hasCommitHash("2633c849c06f6063fdca5dec5054950e403447b2"));
    assertThat(first(logParts), hasCommitHash("3633c849c06f6063fdca5dec5054950e403447b3"));
    assertThat(second(logParts), hasCommitHash("3633c849c06f6063fdca5dec5054950e403447b3"));
    assertThat(second(logParts), hasCommitHash("4633c849c06f6063fdca5dec5054950e403447b4"));
  }

  @Test
  public void splitFiveCommitsIntoFourPieces() {
    given(provideLog.provide()).willReturn(fiveCommits());

    List<Set<CommitHash>> logParts = splitLog.splitInto(4);

    assertThat(first(logParts), hasCommitHash("1e121305db8fa36f1dbc6083e628b245e20ef4c1"));
    assertThat(first(logParts), hasCommitHash("2633c849c06f6063fdca5dec5054950e403447b2"));
    assertThat(second(logParts), hasCommitHash("2633c849c06f6063fdca5dec5054950e403447b2"));
    assertThat(second(logParts), hasCommitHash("3633c849c06f6063fdca5dec5054950e403447b3"));
    assertThat(third(logParts), hasCommitHash("3633c849c06f6063fdca5dec5054950e403447b3"));
    assertThat(third(logParts), hasCommitHash("4633c849c06f6063fdca5dec5054950e403447b4"));
    assertThat(fourth(logParts), hasCommitHash("4633c849c06f6063fdca5dec5054950e403447b4"));
    assertThat(fourth(logParts), hasCommitHash("5633c849c06f6063fdca5dec5054950e403447b4"));
  }

  private Set<CommitHash> fourCommits() {
    Set<CommitHash> commitLogHashes = Sets.newSet(
        new CommitHash("1e121305db8fa36f1dbc6083e628b245e20ef4c1"),
        new CommitHash("2633c849c06f6063fdca5dec5054950e403447b2"),
        new CommitHash("3633c849c06f6063fdca5dec5054950e403447b3"),
        new CommitHash("4633c849c06f6063fdca5dec5054950e403447b4"));
    return commitLogHashes;
  }

  private Set<CommitHash> fiveCommits() {
    Set<CommitHash> commitLogHashes = new LinkedHashSet<>();
    commitLogHashes.addAll(fourCommits());
    commitLogHashes.add(new CommitHash("5633c849c06f6063fdca5dec5054950e403447b4"));
    return commitLogHashes;
  }

  private Matcher<Iterable<? super CommitHash>> hasCommitHash(String commitHashValue) {
    return hasItem(new CommitHash(commitHashValue));
  }

  private Set<CommitHash> fourth(List<Set<CommitHash>> logParts) {
    return logParts.get(3);
  }

  private Set<CommitHash> third(List<Set<CommitHash>> logParts) {
    return logParts.get(2);
  }

  private Set<CommitHash> second(List<Set<CommitHash>> logParts) {
    return logParts.get(1);
  }

  private Set<CommitHash> first(List<Set<CommitHash>> logParts) {
    return logParts.get(0);
  }

}
