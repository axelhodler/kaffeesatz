package co.hodler.kaffeesatz;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Test;

public class CalculateFirstSplitSizeShould {

  @Test
  public void beTwoWhenTwoPartsOfThreeCommitsIsDesired() {
    CalculateFirstSplitSize calcFirstSplitSize = new CalculateFirstSplitSize();

    assertThat(calcFirstSplitSize.using(2, 3), is(2));
  }

  @Test
  public void beThreeWhenTwoPartsOfFourCommitsIsDesired() {
    CalculateFirstSplitSize calcFirstSplitSize = new CalculateFirstSplitSize();

    assertThat(calcFirstSplitSize.using(2, 4), is(3));
  }
}
