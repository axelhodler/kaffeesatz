package co.hodler.kaffeesatz.actions.git;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.diff.DiffEntry;
import org.eclipse.jgit.lib.ObjectId;
import org.eclipse.jgit.lib.ObjectReader;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.treewalk.CanonicalTreeParser;

import co.hodler.kaffeesatz.actions.ProvideChangesBetweenTwoCommits;
import co.hodler.kaffeesatz.model.ChangedFile;
import co.hodler.kaffeesatz.model.LinkedCommitHashPair;

public class GitProvideChangesBetweenTwoCommits implements ProvideChangesBetweenTwoCommits {

  private Git git;

  @Inject
  public GitProvideChangesBetweenTwoCommits(Git git) {
    this.git = git;
  }

  @Override
  public Set<ChangedFile> fetchChangesBetween(LinkedCommitHashPair commitPair) {
    try {
      Repository repo = git.getRepository();
      ObjectId head = repo.resolve(commitPair.getUpperCommitHashValue() + "^{tree}");
      ObjectId old = repo.resolve(commitPair.getLowerCommitHashValue() + "^{tree}");

      ObjectReader reader = repo.newObjectReader();
      CanonicalTreeParser oldTreeIter = new CanonicalTreeParser();
      oldTreeIter.reset(reader, old);
      CanonicalTreeParser newTreeIter = new CanonicalTreeParser();
      newTreeIter.reset(reader, head);
      List<DiffEntry> diffs= git.diff()
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

}
