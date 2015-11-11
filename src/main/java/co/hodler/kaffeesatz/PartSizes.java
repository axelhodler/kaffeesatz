package co.hodler.kaffeesatz;

public class PartSizes {

  private int firstPart;
  private int everyOtherPart;

  public PartSizes(int firstPart, int everyOtherPart) {
    this.firstPart = firstPart;
    this.everyOtherPart = everyOtherPart;
  }

  public int sizeOfFirstPart() {
    return firstPart;
  }

  public int sizeOfEveryOtherPart() {
    return everyOtherPart;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + everyOtherPart;
    result = prime * result + firstPart;
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
    PartSizes other = (PartSizes) obj;
    if (everyOtherPart != other.everyOtherPart)
      return false;
    if (firstPart != other.firstPart)
      return false;
    return true;
  }

  @Override
  public String toString() {
    return "PartSizes [firstPart=" + firstPart + ", everyOtherPart="
        + everyOtherPart + "]";
  }

}
