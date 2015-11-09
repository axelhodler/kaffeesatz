package co.hodler.kaffeesatz.actions.git;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.BDDMockito.given;

import java.util.List;
import java.util.Set;

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

  @Test
  public void splitLogIntoTwo() {
    Set<CommitHash> commitLogHashes = Sets.newSet(
        new CommitHash("2e121305db8fa36f1dbc6083e628b245e20ef4c4"),
        new CommitHash("5633c849c06f6063fdca5dec5054950e403447b8"),
        new CommitHash("1b937db69b4f7edb519b051ef8319de56de6f627"));
    given(provideLog.provide()).willReturn(commitLogHashes);
    SplitLogIntoEqualParts splitLog = new SplitLogIntoEqualParts(provideLog);

    List<Set<CommitHash>> logParts = splitLog.splitInto(2);

    assertThat(logParts.get(0), hasItem(new CommitHash("2e121305db8fa36f1dbc6083e628b245e20ef4c4")));
    assertThat(logParts.get(0), hasItem(new CommitHash("5633c849c06f6063fdca5dec5054950e403447b8")));
    assertThat(logParts.get(1), hasItem(new CommitHash("5633c849c06f6063fdca5dec5054950e403447b8")));
    assertThat(logParts.get(1), hasItem(new CommitHash("1b937db69b4f7edb519b051ef8319de56de6f627")));
  }

  @Test
  public void splitLogIntoThree() {
    Set<CommitHash> commitLogHashes = Sets.newSet(
        new CommitHash("1e121305db8fa36f1dbc6083e628b245e20ef4c1"),
        new CommitHash("2633c849c06f6063fdca5dec5054950e403447b2"),
        new CommitHash("3633c849c06f6063fdca5dec5054950e403447b3"),
        new CommitHash("4633c849c06f6063fdca5dec5054950e403447b4"));
    given(provideLog.provide()).willReturn(commitLogHashes);
    SplitLogIntoEqualParts splitLog = new SplitLogIntoEqualParts(provideLog);

    List<Set<CommitHash>> logParts = splitLog.splitInto(3);

    assertThat(logParts.get(0), hasItem(new CommitHash("1e121305db8fa36f1dbc6083e628b245e20ef4c1")));
    assertThat(logParts.get(0), hasItem(new CommitHash("2633c849c06f6063fdca5dec5054950e403447b2")));
    assertThat(logParts.get(1), hasItem(new CommitHash("2633c849c06f6063fdca5dec5054950e403447b2")));
    assertThat(logParts.get(1), hasItem(new CommitHash("3633c849c06f6063fdca5dec5054950e403447b3")));
    assertThat(logParts.get(2), hasItem(new CommitHash("3633c849c06f6063fdca5dec5054950e403447b3")));
    assertThat(logParts.get(2), hasItem(new CommitHash("4633c849c06f6063fdca5dec5054950e403447b4")));
  }
}
