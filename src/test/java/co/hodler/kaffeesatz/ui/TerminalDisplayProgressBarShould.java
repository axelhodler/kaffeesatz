package co.hodler.kaffeesatz.ui;

import co.hodler.kaffeesatz.model.Progress;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.PrintStream;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

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
    progressBar.withPercentageDone(new Progress(0));

    assertThat(sysOutputContent.toString(), is("|>         |\r"));
  }

  @Test
  public void displayFullProgressBar() {
    progressBar.full();

    assertThat(sysOutputContent.toString(), is("|==========|\r"));
  }

  @Test
  public void display10Percent() {
    progressBar.withPercentageDone(new Progress(10));

    assertThat(sysOutputContent.toString(), is("|=>        |\r"));
  }

  @Test
  public void display20Percent() {
    progressBar.withPercentageDone(new Progress(20));

    assertThat(sysOutputContent.toString(), is("|==>       |\r"));
  }

  @Test
  public void display30Precent() {
    progressBar.withPercentageDone(new Progress(30));

    assertThat(sysOutputContent.toString(), is("|===>      |\r"));
  }

  @Test
  public void display40Precent() {
    progressBar.withPercentageDone(new Progress(40));

    assertThat(sysOutputContent.toString(), is("|====>     |\r"));
  }

  @Test
  public void display50Precent() {
    progressBar.withPercentageDone(new Progress(50));

    assertThat(sysOutputContent.toString(), is("|=====>    |\r"));
  }

  @Test
  public void display60Precent() {
    progressBar.withPercentageDone(new Progress(60));

    assertThat(sysOutputContent.toString(), is("|======>   |\r"));
  }

  @Test
  public void display70Precent() {
    progressBar.withPercentageDone(new Progress(70));

    assertThat(sysOutputContent.toString(), is("|=======>  |\r"));
  }

  @Test
  public void display80Precent() {
    progressBar.withPercentageDone(new Progress(80));

    assertThat(sysOutputContent.toString(), is("|========> |\r"));
  }

  @Test
  public void display90Precent() {
    progressBar.withPercentageDone(new Progress(90));

    assertThat(sysOutputContent.toString(), is("|=========>|\r"));
  }

  @After
  public void cleanUp() {
    System.setOut(null);
  }

  @AfterClass
  public static void tearDown() { System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));}
}
