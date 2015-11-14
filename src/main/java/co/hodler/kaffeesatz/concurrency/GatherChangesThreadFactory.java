package co.hodler.kaffeesatz.concurrency;

public class GatherChangesThreadFactory {

  public Thread createThreadTo(GatherChanges gatherChanges) {
    return new Thread(gatherChanges);
  }

}
