package co.hodler.kaffeesatz.model;

public final class LinkedCommitHashPair {

  private CommitHash upperCommitHash;
  private CommitHash lowerCommitHash;

  public LinkedCommitHashPair(CommitHash upperCommitHash, CommitHash lowerCommitHash) {
    this.upperCommitHash = upperCommitHash;
    this.lowerCommitHash = lowerCommitHash;
  }

  public String getUpperCommitHashValue() {
    return upperCommitHash.value();
  }

  public String getLowerCommitHashValue() {
    return lowerCommitHash.value();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    LinkedCommitHashPair that = (LinkedCommitHashPair) o;

    if (upperCommitHash != null ? !upperCommitHash.equals(that.upperCommitHash) : that.upperCommitHash != null)
      return false;
    return !(lowerCommitHash != null ? !lowerCommitHash.equals(that.lowerCommitHash) : that.lowerCommitHash != null);
  }

  @Override
  public int hashCode() {
    int result = upperCommitHash != null ? upperCommitHash.hashCode() : 0;
    result = 31 * result + (lowerCommitHash != null ? lowerCommitHash.hashCode() : 0);
    return result;
  }

  @Override
  public String toString() {
    return "[" + upperCommitHash
        + ", " + lowerCommitHash + "]";
  }

}
