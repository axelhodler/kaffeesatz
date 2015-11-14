package co.hodler.kaffeesatz.actions.git;

import java.util.List;

import co.hodler.kaffeesatz.actions.FetchChangedFiles;
import co.hodler.kaffeesatz.concurrency.GatherChangesConcurrently;
import co.hodler.kaffeesatz.concurrency.SplitPairsSetIntoEqualParts;
import co.hodler.kaffeesatz.model.ChangedFile;

public class GitFetchChangedFiles implements FetchChangedFiles {

  private SplitPairsSetIntoEqualParts logSplitter;
  private GatherChangesConcurrently gatherChangesConcurrently;

  public GitFetchChangedFiles(SplitPairsSetIntoEqualParts logSplitter,
      GatherChangesConcurrently gatherChangesConcurrently) {
    this.logSplitter = logSplitter;
    this.gatherChangesConcurrently = gatherChangesConcurrently;
  }

  @Override
  public List<ChangedFile> fetchChangedFiles() {
    return gatherChangesConcurrently.gather(logSplitter.splitInto(4));
  }
}
