package co.hodler.kaffesatz.model;

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

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((hashValue == null) ? 0 : hashValue.hashCode());
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
    CommitHash other = (CommitHash) obj;
    if (hashValue == null) {
      if (other.hashValue != null)
        return false;
    } else if (!hashValue.equals(other.hashValue))
      return false;
    return true;
  }

  @Override
  public String toString() {
    return "CommitHash [hashValue=" + hashValue + "]";
  }

}
