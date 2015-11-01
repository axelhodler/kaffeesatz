package co.hodler;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

import java.util.LinkedHashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import co.hodler.model.CommitHash;
import co.hodler.model.LinkedCommitHashPair;

@RunWith(MockitoJUnitRunner.class)
public class GitCommitPairProviderShould {

  @Mock
  ProvideLog provideLog;
  private CommitPairProvider provider;

  @Before
  public void initialize() {
    provider = new GitCommitPairProvider(provideLog);
  }

  @Test
  public void pairLinkedCommitIdsFromLog() {
    Set<CommitHash> commitLog = new LinkedHashSet<>();
    commitLog.add(new CommitHash("79972e25c2891d6187b633a4735982ee1a210f90"));
    commitLog.add(new CommitHash("f9a67ea400a4d33b3cf9914bab5e240c708be755"));
    commitLog.add(new CommitHash("5c82c9cabf18bed0f32877208b070f3c9a4bcce0"));
    given(provideLog.provide()).willReturn(commitLog);

    Set<LinkedCommitHashPair> pairs = provider.providePairs();

    verify(provideLog).provide();
    assertThat(pairs,
        hasItem(new LinkedCommitHashPair(new CommitHash("79972e25c2891d6187b633a4735982ee1a210f90"),
            new CommitHash("f9a67ea400a4d33b3cf9914bab5e240c708be755"))));
    assertThat(pairs,
        hasItem(new LinkedCommitHashPair(new CommitHash("f9a67ea400a4d33b3cf9914bab5e240c708be755"),
            new CommitHash("5c82c9cabf18bed0f32877208b070f3c9a4bcce0"))));
  }

  @Test
  public void beAbleToHandleDifferentLogs() {
    Set<CommitHash> commitLog = new LinkedHashSet<>();
    commitLog.add(new CommitHash("9a287ecdcbcb777b98a720ad01514afff506103c"));
    commitLog.add(new CommitHash("ec840c1bfca417cb2b71d3ab01e1f4c46a17612b"));
    commitLog.add(new CommitHash("d866ef6f405be734e15db6ad97845e6ebcc7673d"));
    given(provideLog.provide()).willReturn(commitLog);

    Set<LinkedCommitHashPair> pairs = provider.providePairs();

    assertThat(pairs,
        hasItem(new LinkedCommitHashPair(new CommitHash("9a287ecdcbcb777b98a720ad01514afff506103c"),
            new CommitHash("ec840c1bfca417cb2b71d3ab01e1f4c46a17612b"))));
    assertThat(pairs,
        hasItem(new LinkedCommitHashPair(new CommitHash("ec840c1bfca417cb2b71d3ab01e1f4c46a17612b"),
            new CommitHash("d866ef6f405be734e15db6ad97845e6ebcc7673d"))));
  }
}
