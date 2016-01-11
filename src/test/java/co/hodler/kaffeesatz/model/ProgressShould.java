package co.hodler.kaffeesatz.model;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class ProgressShould {

  @Test
  public void beAbleToBeIncreasedByTen() {
    Progress progress = new Progress(0);

    progress.increaseByTen();

    assertThat(progress.intValue(), is(10));
  }
}
