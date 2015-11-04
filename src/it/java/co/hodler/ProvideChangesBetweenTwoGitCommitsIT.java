package co.hodler;

import java.io.File;
import java.util.Set;

import org.eclipse.jgit.api.Git;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;

import co.hodler.kaffeesatz.actions.ProvideChangesBetweenTwoGitCommits;
import co.hodler.model.CommitHash;
import co.hodler.model.LinkedCommitHashPair;

public class ProvideChangesBetweenTwoGitCommitsIT {

  @Test
  public void canGetChangesBetweenTwoGitCommits() throws Exception {
    File gitWorkDir = new File(System.getenv("REPO_DIR").toString());
    Git git = Git.open(gitWorkDir);

    ProvideChangesBetweenTwoGitCommits provideChanges = new ProvideChangesBetweenTwoGitCommits(git);
    Set<String> changedFiles = provideChanges.fetchChangesBetween(
        new LinkedCommitHashPair(
            new CommitHash("1ab34bcc4b4e891577bdcd8254cccc6742955f51"),
            new CommitHash("1e38e41b8fbeed7682ef8099b78a9cd05bbf0b89")));

    assertThat(changedFiles, hasItem("README.md"));
  }
}
