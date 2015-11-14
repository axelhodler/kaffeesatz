package co.hodler.kaffeesatz.concurrency;

public class GatherChangesThreadFactory {

  public GatherChangesThread createThreadTo(GatherChanges gatherChanges) {
    return new GatherChangesThread(gatherChanges);
  }

}
