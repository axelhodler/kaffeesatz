package co.hodler.kaffeesatz;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class TerminalDisplayProgressBarShould {

  private ByteArrayOutputStream sysOutputContent;
  private TerminalDisplayProgressBar progressBar;

  @Before
  public void setUp() {
    sysOutputContent = new ByteArrayOutputStream();
    System.setOut(new PrintStream(sysOutputContent));

    progressBar = new TerminalDisplayProgressBar();
  }

  @Test
  public void displayProgressbar() {
    progressBar.display();

    assertThat(sysOutputContent.toString(), is("progress\n"));
  }

  @Test
  public void displayAMovingProgressbar() {
    progressBar.begin();

    assertThat(sysOutputContent.toString(), is(">         \r"));
  }

  @Test
  public void displayFullProgressBar() {
    progressBar.full();

    assertThat(sysOutputContent.toString(), is("==========\r"));
  }

  @Test
  public void display10Percent() {
    progressBar.tenPercentDone();

    assertThat(sysOutputContent.toString(), is("=>        \r"));
  }

  @After
  public void cleanUp() {
    System.setOut(null);
  }
}
