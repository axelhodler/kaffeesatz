package co.hodler.model;

public class CommitPair {

  private String upperCommitHash;
  private String lowerCommitHash;

  public CommitPair(String upperCommitHash, String lowerCommitHash) {
    this.upperCommitHash = upperCommitHash;
    this.lowerCommitHash = lowerCommitHash;
  }

  public String getUpperCommitHash() {
    return upperCommitHash;
  }

  public String getLowerCommitHash() {
    return lowerCommitHash;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result
        + ((lowerCommitHash == null) ? 0 : lowerCommitHash.hashCode());
    result = prime * result
        + ((upperCommitHash == null) ? 0 : upperCommitHash.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    CommitPair other = (CommitPair) obj;
    if (lowerCommitHash == null) {
      if (other.lowerCommitHash != null)
        return false;
    } else if (!lowerCommitHash.equals(other.lowerCommitHash))
      return false;
    if (upperCommitHash == null) {
      if (other.upperCommitHash != null)
        return false;
    } else if (!upperCommitHash.equals(other.upperCommitHash))
      return false;
    return true;
  }

}
