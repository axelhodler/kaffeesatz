package co.hodler.integrationtests;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class ProvideGitLogShould {

  @Test
  public void beAbleToReadRepository() {
    assertNotNull(getClass().getResource("test.txt").getPath());
  }
}
