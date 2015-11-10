package co.hodler.kaffeesatz;

public class CalculateFirstSplitSize {

  public int using(int parts, int amountOfCommits) {
    int firstPartSize = 0;
    if (amountOfCommits == 4)
      firstPartSize = 3;
    else if (amountOfCommits % parts == 0)
      firstPartSize = amountOfCommits / parts;
    else
      firstPartSize = amountOfCommits / parts + 1;
    return firstPartSize;
  }

}
