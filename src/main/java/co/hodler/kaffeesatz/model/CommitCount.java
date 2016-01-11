package co.hodler.kaffeesatz.model;

public final class CommitCount {
  private final int amount;

  public CommitCount(int amount) {
    this.amount = amount;
  }

  public int intValue() {
    return this.amount;
  }
}
