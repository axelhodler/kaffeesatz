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
    int[] distributedParts = distributeParts.distribute(21, 2);

    assertThat(distributedParts[0], is(11));
    assertThat(distributedParts[1], is(11));
  }

  @Test
  public void beAbleToDistribute10into4Parts() {
    int[] distributedParts = distributeParts.distribute(10, 4);

    assertThat(distributedParts[0], is(4));
    assertThat(distributedParts[1], is(3));
    assertThat(distributedParts[2], is(3));
    assertThat(distributedParts[3], is(3));
  }

  @Test
  public void beAbleToDistribute20into2Parts() {
    int[] distributedParts = distributeParts.distribute(20, 2);

    assertThat(distributedParts[0], is(11));
    assertThat(distributedParts[1], is(10));
  }

  @Test
  public void beAbleToDistribute8into3parts() {
    int[] distributedParts = distributeParts.distribute(8, 3);

    assertThat(distributedParts[0], is(4));
    assertThat(distributedParts[1], is(3));
    assertThat(distributedParts[2], is(3));
  }


  @Test
  public void beAbleToDistribute28into5parts() throws Exception {
    int[] distributedParts = distributeParts.distribute(28, 5);

    assertThat(distributedParts[0], is(8));
    assertThat(distributedParts[1], is(6));
    assertThat(distributedParts[2], is(6));
    assertThat(distributedParts[3], is(6));
    assertThat(distributedParts[4], is(6));
  }
}
