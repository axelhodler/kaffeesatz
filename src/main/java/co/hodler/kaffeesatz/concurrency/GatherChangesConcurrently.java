package co.hodler.kaffeesatz.concurrency;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;

import co.hodler.kaffeesatz.actions.ProvideChangesBetweenTwoCommits;
import co.hodler.kaffeesatz.model.LinkedCommitHashPair;
import co.hodler.kaffeesatz.ui.TrackProgress;

public class GatherChangesConcurrently {

  private GatherChangesThreadFactory gatherChangesThreadFactory;
  private GatherChangesFactory gatherChangesFactory;
  private TrackProgress trackProgress;
  private ProvideChangesBetweenTwoCommits provideChangesBetweenTwoCommits;

  public GatherChangesConcurrently(
      ProvideChangesBetweenTwoCommits provideChangesBetweenTwoCommits,
      GatherChangesThreadFactory gatherChangesThreadFactory,
      GatherChangesFactory gatherChangesFactory, TrackProgress trackProgress) {
    this.provideChangesBetweenTwoCommits = provideChangesBetweenTwoCommits;
    this.gatherChangesThreadFactory = gatherChangesThreadFactory;
    this.gatherChangesFactory = gatherChangesFactory;
    this.trackProgress = trackProgress;
  }

  public void gather(List<Set<LinkedCommitHashPair>> groupsOfCommitPairs) {
    IntStream.range(0, groupsOfCommitPairs.size())
        .forEach(counter -> gatherChangesThreadFactory.createThreadTo(
            gatherChangesFactory.createGatherChanges(new HashSet<>(),
                provideChangesBetweenTwoCommits, trackProgress,
                new ArrayList<>())));
  }

}
