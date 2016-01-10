package co.hodler.kaffeesatz.concurrency;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;

import javax.inject.Inject;

import co.hodler.kaffeesatz.boundaries.GitRepoInteractions;
import co.hodler.kaffeesatz.model.ChangedFile;
import co.hodler.kaffeesatz.model.LinkedCommitHashPair;
import co.hodler.kaffeesatz.ui.TrackProgress;

public class GatherChangesConcurrently {

  private GatherChangesThreadFactory gatherChangesThreadFactory;
  private GatherChangesFactory gatherChangesFactory;
  private TrackProgress trackProgress;
  private GitRepoInteractions gitRepoInteractions;

  @Inject
  public GatherChangesConcurrently(
          GitRepoInteractions provideChangesBetweenTwoCommits,
      GatherChangesThreadFactory gatherChangesThreadFactory,
      GatherChangesFactory gatherChangesFactory, TrackProgress trackProgress) {
    this.gitRepoInteractions = provideChangesBetweenTwoCommits;
    this.gatherChangesThreadFactory = gatherChangesThreadFactory;
    this.gatherChangesFactory = gatherChangesFactory;
    this.trackProgress = trackProgress;
  }

  public List<ChangedFile> gather(List<Set<LinkedCommitHashPair>> groupsOfCommitPairs) {
    List<List<ChangedFile>> allGroups = new ArrayList<>();
    IntStream.range(0, groupsOfCommitPairs.size()).forEach(counter -> {
      List<ChangedFile> groupOfChangedFiles = createListToHoldChangedFiles();
      GatherChangesThread t = gatherChangesThreadFactory.createThreadTo(
          gatherChangesFactory.createGatherChanges(groupsOfCommitPairs.get(counter),
                  gitRepoInteractions, trackProgress,
              groupOfChangedFiles));
      t.startGathering();
      t.waitToFinish();
      allGroups.add(groupOfChangedFiles);
    });

    List<ChangedFile> allChangedFiles = new ArrayList<>();

    allGroups.stream().forEach(
        group -> allChangedFiles.addAll(group));

    return allChangedFiles;
  }

  protected List<ChangedFile> createListToHoldChangedFiles() {
    return new ArrayList<>();
  }

}
