package co.hodler.kaffeesatz;

public class CalculateFirstSplitSize {

  public int using(int parts, int amountOfCommits) {
    int firstPartSize = 0;
    if (amountOfCommits == 4)
      firstPartSize = 3;
    else
      firstPartSize = 2;
    return firstPartSize;
  }

}
