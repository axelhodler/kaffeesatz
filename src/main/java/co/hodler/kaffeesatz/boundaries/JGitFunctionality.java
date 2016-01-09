package co.hodler.kaffeesatz.boundaries;

import co.hodler.kaffeesatz.model.ChangedFile;
import co.hodler.kaffeesatz.model.CommitHash;
import co.hodler.kaffeesatz.model.LinkedCommitHashPair;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.diff.DiffEntry;
import org.eclipse.jgit.lib.ObjectId;
import org.eclipse.jgit.lib.ObjectReader;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.treewalk.CanonicalTreeParser;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.StreamSupport;

public class JGitFunctionality implements GitFunctionality {

  private Git gitRepo;

  @Override
  public void initFunctionality(String path) {
    File gitWorkDir = new File(path);
    try {
      this.gitRepo = Git.open(gitWorkDir);
    } catch (IOException e) {
      throw new RuntimeException("Could not find provided git repo", e);
    }
  }

  @Override
  public Set<CommitHash> provideOrderedLogOfCommitHashes() {
    try {
      Iterable<RevCommit> commits = gitRepo.log().all().call();
      Set<CommitHash> gitLogHashes = new LinkedHashSet<>();
      for (RevCommit commit : commits) {
        gitLogHashes.add(new CommitHash(commit.getId().name()));
      }
      return gitLogHashes;
    } catch (Exception e) {
      throw new RuntimeException("Could not get the git log", e);
    }
  }

  @Override
  public Set<ChangedFile> provideChangesBetween(LinkedCommitHashPair commitPair) {
    try {
      Repository repo = gitRepo.getRepository();
      ObjectId head = repo.resolve(commitPair.getUpperCommitHashValue() + "^{tree}");
      ObjectId old = repo.resolve(commitPair.getLowerCommitHashValue() + "^{tree}");

      ObjectReader reader = repo.newObjectReader();
      CanonicalTreeParser oldTreeIter = new CanonicalTreeParser();
      oldTreeIter.reset(reader, old);
      CanonicalTreeParser newTreeIter = new CanonicalTreeParser();
      newTreeIter.reset(reader, head);
      List<DiffEntry> diffs= gitRepo.diff()
              .setNewTree(newTreeIter)
              .setOldTree(oldTreeIter)
              .call();

      Set<ChangedFile> fileChanges = new HashSet<>();
      diffs.stream().forEach(diff -> fileChanges.add(new ChangedFile(diff.getNewPath())));
      return fileChanges;
    } catch (Exception e) {
      throw new RuntimeException("Could not fetch changes between two commits", e);
    }
  }

  @Override
  public int provideCommitCount() {
    try {
      return (int) StreamSupport.stream(gitRepo.log().all().call().spliterator(), false).count();
    } catch (Exception e) {
      throw new RuntimeException("Could not get commit count", e);
    }
  }

}
