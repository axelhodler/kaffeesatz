package co.hodler;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

import java.util.LinkedHashSet;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import co.hodler.model.CommitPair;

@RunWith(MockitoJUnitRunner.class)
public class GitCommitPairProviderShould {

  @Mock
  ProvideLog provideLog;

  @Test
  public void pairLinkedCommitIdsFromLog() {
    Set<String> commitLog = new LinkedHashSet<>();
    commitLog.add("79972e25c2891d6187b633a4735982ee1a210f90");
    commitLog.add("f9a67ea400a4d33b3cf9914bab5e240c708be755");
    commitLog.add("5c82c9cabf18bed0f32877208b070f3c9a4bcce0");
    given(provideLog.provide()).willReturn(commitLog);
    CommitPairProvider provider = new GitCommitPairProvider(provideLog);

    Set<CommitPair> pairs = provider.providePairs();

    verify(provideLog).provide();
    assertThat(pairs,
        hasItem(new CommitPair("79972e25c2891d6187b633a4735982ee1a210f90",
            "f9a67ea400a4d33b3cf9914bab5e240c708be755")));
    assertThat(pairs,
        hasItem(new CommitPair("f9a67ea400a4d33b3cf9914bab5e240c708be755",
            "5c82c9cabf18bed0f32877208b070f3c9a4bcce0")));
  }

  @Test
  public void beAbleToHandleDifferentLogs() {
    Set<String> commitLog = new LinkedHashSet<>();
    commitLog.add("9a287ecdcbcb777b98a720ad01514afff506103c");
    commitLog.add("ec840c1bfca417cb2b71d3ab01e1f4c46a17612b");
    commitLog.add("d866ef6f405be734e15db6ad97845e6ebcc7673d");
    given(provideLog.provide()).willReturn(commitLog);
    CommitPairProvider provider = new GitCommitPairProvider(provideLog);

    Set<CommitPair> pairs = provider.providePairs();

    assertThat(pairs,
        hasItem(new CommitPair("9a287ecdcbcb777b98a720ad01514afff506103c",
            "ec840c1bfca417cb2b71d3ab01e1f4c46a17612b")));
    assertThat(pairs,
        hasItem(new CommitPair("ec840c1bfca417cb2b71d3ab01e1f4c46a17612b",
            "d866ef6f405be734e15db6ad97845e6ebcc7673d")));
  }
}
