package co.hodler.kaffeesatz.model;

public final class CommitHash {

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

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    CommitHash that = (CommitHash) o;

    return !(hashValue != null ? !hashValue.equals(that.hashValue) : that.hashValue != null);
  }

  @Override
  public int hashCode() {
    return hashValue != null ? hashValue.hashCode() : 0;
  }

  @Override
  public String toString() {
    return "CommitHash [hashValue=" + hashValue + "]";
  }

}
