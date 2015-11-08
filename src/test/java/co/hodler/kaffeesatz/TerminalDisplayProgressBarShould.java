package co.hodler.kaffeesatz;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
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

  @Test
  public void display20Percent() {
    progressBar.twentyPercentDone();

    assertThat(sysOutputContent.toString(), is("==>       \r"));
  }

  @Test
  public void display30Precent() {
    progressBar.thirtyPercentDone();

    assertThat(sysOutputContent.toString(), is("===>      \r"));
  }

  @Test
  public void display40Precent() {
    progressBar.fourtyPercentDone();

    assertThat(sysOutputContent.toString(), is("====>     \r"));
  }

  @Test
  public void display50Precent() {
    progressBar.fiftyPercentDone();

    assertThat(sysOutputContent.toString(), is("=====>    \r"));
  }

  @Test
  public void display60Precent() {
    progressBar.sixtyPercentDone();

    assertThat(sysOutputContent.toString(), is("======>   \r"));
  }

  @Test
  public void display70Precent() {
    progressBar.seventyPercentDone();

    assertThat(sysOutputContent.toString(), is("=======>  \r"));
  }

  @Test
  public void display80Precent() {
    progressBar.eightyPercentDone();

    assertThat(sysOutputContent.toString(), is("========> \r"));
  }

  @Test
  public void display90Precent() {
    progressBar.ninetyPercentDone();

    assertThat(sysOutputContent.toString(), is("=========>\r"));
  }

  @After
  public void cleanUp() {
    System.setOut(null);
  }
}
