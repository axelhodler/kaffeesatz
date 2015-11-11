package co.hodler.kaffeesatz;

import java.util.stream.IntStream;

public class DistributeParts {

  public int[] distribute(int amountOfCommits, int parts) {
    int[] distributedParts = new int[parts];
    distributedParts[0] = firstAmount(amountOfCommits, parts);
    IntStream.range(1, parts).forEach(counter -> {
      distributedParts[counter] = 
          divisionHasNoRemainder(amountOfCommits, parts) ? 
              amountOfCommits / parts : amountOfCommits / parts + 1;
    });
    return distributedParts;
  }

  private boolean divisionHasNoRemainder(int amountOfCommits, int parts) {
    return amountOfCommits % parts == 0;
  }

  private int firstAmount(int amountOfCommits, int parts) {
    return amountOfCommits/parts + firstAmountExtra(amountOfCommits, parts);
  }

  private int firstAmountExtra(int amountOfCommits, int parts) {
    int extraPart = amountOfCommits % parts;
    if (extraPart == 0)
      extraPart = extraPart + 1;
    return extraPart;
  }

}
