package co.hodler;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class ProvideGitLogIT {

  @Test
  public void beAbleToReadRepository() {
    assertNotNull(getClass().getResource("test.txt").getPath());
  }
}
