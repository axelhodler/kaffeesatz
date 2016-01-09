package co.hodler.kaffeesatz.actions.git;

import java.io.File;
import java.io.IOException;

import org.eclipse.jgit.api.Git;

public class JGitRepo {

  public Git byFilePath(String path) {
    File gitWorkDir = new File(path);
    try {
      return Git.open(gitWorkDir);
    } catch (IOException e) {
      throw new RuntimeException("Could not find provided git repo", e);
    }
  }
}
