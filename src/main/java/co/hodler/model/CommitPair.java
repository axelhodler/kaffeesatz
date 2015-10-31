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

}
