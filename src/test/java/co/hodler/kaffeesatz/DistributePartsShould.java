package co.hodler.kaffeesatz;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Before;
import org.junit.Test;

public class DistributePartsShould {

  private DistributeParts distributeParts;

  @Before
  public void setUp() {
    distributeParts = new DistributeParts();
  }

  @Test
  public void beAbleToDistribute21into2Parts() {
    PartSizes distributedParts = distributeParts.distributeSizes(21, 2);

    assertThat(distributedParts.sizeOfFirstPart(), is(11));
    assertThat(distributedParts.sizeOfEveryOtherPart(), is(11));
  }

  @Test
  public void beAbleToDistribute10into4Parts() {
    PartSizes distributedParts = distributeParts.distributeSizes(10, 4);

    assertThat(distributedParts.sizeOfFirstPart(), is(4));
    assertThat(distributedParts.sizeOfEveryOtherPart(), is(3));
  }

  @Test
  public void beAbleToDistribute20into2Parts() {
    PartSizes distributedParts = distributeParts.distributeSizes(20, 2);

    assertThat(distributedParts.sizeOfFirstPart(), is(11));
    assertThat(distributedParts.sizeOfEveryOtherPart(), is(10));
  }

  @Test
  public void beAbleToDistribute8into3parts() {
    PartSizes distributedParts = distributeParts.distributeSizes(8, 3);

    assertThat(distributedParts.sizeOfFirstPart(), is(4));
    assertThat(distributedParts.sizeOfEveryOtherPart(), is(3));
  }


  @Test
  public void beAbleToDistribute28into5parts() throws Exception {
    PartSizes distributedParts = distributeParts.distributeSizes(28, 5);

    assertThat(distributedParts.sizeOfFirstPart(), is(8));
    assertThat(distributedParts.sizeOfEveryOtherPart(), is(6));
  }
}
