package co.hodler;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.io.File;
import java.util.Set;

import org.eclipse.jgit.api.Git;
import org.junit.Test;

import co.hodler.git.ProvideGitLogHashes;
import co.hodler.model.CommitHash;

public class ProvideGitLogIT {

  @Test
  public void canReadCommits() throws Exception {
    File gitWorkDir = new File(System.getenv("REPO_DIR").toString());
    Git git = Git.open(gitWorkDir);

    ProvideGitLogHashes provideGitLogHashes = new ProvideGitLogHashes(git);
    Set<CommitHash> hashes = provideGitLogHashes.provide();

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
