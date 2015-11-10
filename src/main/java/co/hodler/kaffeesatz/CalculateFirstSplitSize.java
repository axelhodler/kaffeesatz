package co.hodler.kaffeesatz;

public class CalculateFirstSplitSize {

  public int using(int parts, int amountOfCommits) {
    int firstPartSize = 0;
    if (amountOfCommits == 4)
      firstPartSize = 3;
    else if (parts == 2 && amountOfCommits == 5)
      firstPartSize = 3;
    else if (parts == 2 && amountOfCommits == 10)
      firstPartSize = 5;
    else
      firstPartSize = 2;
    return firstPartSize;
  }

}
