package co.hodler.kaffeesatz.ui;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.io.ByteArrayOutputStream;
import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import co.hodler.kaffeesatz.ui.TerminalDisplayProgressBar;

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
    progressBar.withPercentageDone(0);

    assertThat(sysOutputContent.toString(), is("|>         |\r"));
  }

  @Test
  public void displayFullProgressBar() {
    progressBar.full();

    assertThat(sysOutputContent.toString(), is("|==========|\r"));
  }

  @Test
  public void display10Percent() {
    progressBar.withPercentageDone(0.1);

    assertThat(sysOutputContent.toString(), is("|=>        |\r"));
  }

  @Test
  public void display20Percent() {
    progressBar.withPercentageDone(0.2);

    assertThat(sysOutputContent.toString(), is("|==>       |\r"));
  }

  @Test
  public void display30Precent() {
    progressBar.withPercentageDone(0.3);

    assertThat(sysOutputContent.toString(), is("|===>      |\r"));
  }

  @Test
  public void display40Precent() {
    progressBar.withPercentageDone(0.4);

    assertThat(sysOutputContent.toString(), is("|====>     |\r"));
  }

  @Test
  public void display50Precent() {
    progressBar.withPercentageDone(0.5);

    assertThat(sysOutputContent.toString(), is("|=====>    |\r"));
  }

  @Test
  public void display60Precent() {
    progressBar.withPercentageDone(0.6);

    assertThat(sysOutputContent.toString(), is("|======>   |\r"));
  }

  @Test
  public void display70Precent() {
    progressBar.withPercentageDone(0.7);

    assertThat(sysOutputContent.toString(), is("|=======>  |\r"));
  }

  @Test
  public void display80Precent() {
    progressBar.withPercentageDone(0.8);

    assertThat(sysOutputContent.toString(), is("|========> |\r"));
  }

  @Test
  public void display90Precent() {
    progressBar.withPercentageDone(0.9);

    assertThat(sysOutputContent.toString(), is("|=========>|\r"));
  }

  @After
  public void cleanUp() {
    System.setOut(null);
  }

  @AfterClass
  public static void tearDown() { System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));}
}
