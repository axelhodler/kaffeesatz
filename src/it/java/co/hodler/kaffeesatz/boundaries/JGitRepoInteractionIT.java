package co.hodler.kaffeesatz.boundaries;

import co.hodler.kaffeesatz.model.ChangedFile;
import co.hodler.kaffeesatz.model.CommitHash;
import co.hodler.kaffeesatz.model.LinkedCommitHashPair;
import org.junit.Before;
import org.junit.Test;

import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsCollectionContaining.hasItem;

public class JGitRepoInteractionIT {

  JGitRepoInteraction gitRepoInteraction;

  @Before
  public void initialize() {
    gitRepoInteraction = new JGitRepoInteraction();
    gitRepoInteraction.initFunctionality(System.getenv("REPO_DIR").toString());
  }

  @Test
  public void canCountCommitAmount() throws Exception {
    int commitCountOnWritingThisTest = 67;
    assertThat(gitRepoInteraction.provideCommitCount(),
            greaterThanOrEqualTo(commitCountOnWritingThisTest));
  }

  @Test
  public void canGetChangesBetweenTwoGitCommits() throws Exception {
    Set<ChangedFile> changedFiles = gitRepoInteraction.provideChangesBetween(
            new LinkedCommitHashPair(
                    new CommitHash("1ab34bcc4b4e891577bdcd8254cccc6742955f51"),
                    new CommitHash("1e38e41b8fbeed7682ef8099b78a9cd05bbf0b89")));

    assertThat(changedFiles, hasItem(new ChangedFile("README.md")));
  }

  @Test
  public void canReadCommits() throws Exception {
    Set<CommitHash> hashes = gitRepoInteraction.provideOrderedLogOfCommitHashes();

    assertThat(secondCommitIn(hashes),
            is(new CommitHash("42fd264755b05be73971fca4b55764115990a2e0")));
    assertThat(initialCommitIn(hashes),
            is(new CommitHash("e90998dc42756404b8f2b10508f3b150bc8c5d8e")));
  }

  private CommitHash initialCommitIn(Set<CommitHash> hashes) {
    return hashes.stream().skip(hashes.size() - 1).findFirst().get();
  }

  private CommitHash secondCommitIn(Set<CommitHash> hashes) {
    return hashes.stream().skip(hashes.size() - 2).findFirst().get();
  }
}
