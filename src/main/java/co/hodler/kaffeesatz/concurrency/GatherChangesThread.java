package co.hodler.kaffeesatz.concurrency;

public class GatherChangesThread {

  private Thread thread;

  public GatherChangesThread(GatherChanges gatherChanges) {
    this.thread = new Thread(gatherChanges);
  }

  public void startGathering() {
    thread.start();
  }

  public void waitToFinish() {
    try {
      thread.join();
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
  }
}
