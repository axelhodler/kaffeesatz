package co.hodler.kaffeesatz;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Before;
import org.junit.Test;

public class CalculateFirstSplitSizeShould {

  private CalculateFirstSplitSize calcFirstSplitSize;

  @Before
  public void setUp() {
    calcFirstSplitSize = new CalculateFirstSplitSize();
  }

  @Test
  public void beTwoWhenTwoPartsOfThreeCommitsIsDesired() {
    assertThat(calcFirstSplitSize.using(2, 3), is(2));
  }

  @Test
  public void beThreeWhenTwoPartsOfFourCommitsIsDesired() {
    assertThat(calcFirstSplitSize.using(2, 4), is(3));
  }

  @Test
  public void beTwoWhenFourPartsOfFiveCommitsAreDesired() {
    assertThat(calcFirstSplitSize.using(4, 5), is(2));
  }
}
