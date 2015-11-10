package co.hodler.kaffeesatz;

import org.junit.Test;

public class CalculateFirstSplitSizeShould {

  @Test
  public void beTwoWhenTwoPartsOfThreeCommitsIsDesired() {
    CalculateFirstSplitSize calcFirstSplitSize = new CalculateFirstSplitSize();

    calcFirstSplitSize.using(2, 3);
  }
}
