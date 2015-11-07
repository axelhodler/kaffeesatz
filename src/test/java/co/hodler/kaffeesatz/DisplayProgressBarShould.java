package co.hodler.kaffeesatz;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Test;

public class DisplayProgressBarShould {

  @Test
  public void displayProgressbar() {
    final ByteArrayOutputStream sysOutputContent = new ByteArrayOutputStream();
    System.setOut(new PrintStream(sysOutputContent));

    DisplayProgressBar progressBar = new DisplayProgressBar();
    progressBar.display();

    assertThat(sysOutputContent.toString(), is("progress\n"));
  }

  @After
  public void cleanUp() {
    System.setOut(null);
  }
}
