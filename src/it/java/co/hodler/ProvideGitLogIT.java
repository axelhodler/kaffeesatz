package co.hodler;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.io.File;
import java.util.Iterator;
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

    Iterator<CommitHash> commitLogIterator = hashes.iterator();
    assertThat(commitLogIterator.next(), is(new CommitHash("42cee89dc8e12feda94b1f1aa84d6c57ba8f633a")));
    assertThat(commitLogIterator.next(), is(new CommitHash("358808125d4f9292dcc105e264177e6837bf8809")));
  }
}
