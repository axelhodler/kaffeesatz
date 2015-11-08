package co.hodler;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;

import java.io.File;

import org.eclipse.jgit.api.Git;
import org.junit.Test;

import co.hodler.kaffeesatz.actions.git.GitCommitCount;

public class GitCommitCountIT {

  private final int COMMIT_COUNT_WHEN_WRITING_THIS_TEST = 67;

  @Test
  public void canReadCommits() throws Exception {
    File gitWorkDir = new File(System.getenv("REPO_DIR").toString());
    Git git = Git.open(gitWorkDir);

    GitCommitCount commitCount = new GitCommitCount(git);

    assertThat(commitCount.value(), greaterThanOrEqualTo(COMMIT_COUNT_WHEN_WRITING_THIS_TEST));
  }

}
