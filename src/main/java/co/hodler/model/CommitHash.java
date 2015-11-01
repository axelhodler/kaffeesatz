package co.hodler.model;

public class CommitHash {

  private String hashValue;

  public CommitHash(String hashValue) {
    if (hashValue.length() != 40) {
      throw new IllegalArgumentException("the hash value should contain 40 chars");
    }
    this.hashValue = hashValue;
  }

  public String value() {
    return this.hashValue;
  }
}
