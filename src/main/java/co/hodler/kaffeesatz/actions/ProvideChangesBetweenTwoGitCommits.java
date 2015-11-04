package co.hodler.kaffeesatz.actions;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.diff.DiffEntry;
import org.eclipse.jgit.errors.AmbiguousObjectException;
import org.eclipse.jgit.errors.IncorrectObjectTypeException;
import org.eclipse.jgit.errors.RevisionSyntaxException;
import org.eclipse.jgit.lib.ObjectId;
import org.eclipse.jgit.lib.ObjectReader;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.treewalk.CanonicalTreeParser;

import co.hodler.model.LinkedCommitHashPair;

public class ProvideChangesBetweenTwoGitCommits implements ChangesBetweenTwoCommitsProvider {

  private Git git;

  public ProvideChangesBetweenTwoGitCommits(Git git) {
    this.git = git;
  }

  @Override
  public Set<String> fetchChangesBetween(LinkedCommitHashPair commitPair) {
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

      Set<String> fileChanges = new HashSet<>();
      diffs.stream().forEach(diff -> fileChanges.add(diff.getNewPath()));
      return fileChanges;
    } catch (Exception e) {
      throw new RuntimeException("Could not fetch changes between two commits", e);
    }
  }

}
