package co.hodler.model;

import org.junit.Test;

public class CommitHashShould {

  @Test
  public void haveALengthOf40() {
    new CommitHash("ef3cc2dc4c04be7c87af619ad1d7cc1d6f11bb26");
  }

  @Test(expected = IllegalArgumentException.class)
  public void notBeShorterThanALengthOf40() {
    new CommitHash("asdfasd");
  }

  @Test(expected = IllegalArgumentException.class)
  public void notBeLongerThanALengthOf40() {
    new CommitHash("0ef3cc2dc4c04be7c87af619ad1d7cc1d6f11bb26");
  }
}
