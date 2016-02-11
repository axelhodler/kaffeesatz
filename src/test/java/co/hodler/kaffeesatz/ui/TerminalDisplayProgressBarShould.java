package co.hodler.kaffeesatz.ui;

import co.hodler.kaffeesatz.model.Progress;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class TerminalDisplayProgressBarShould {

  @Mock
  Printer printer;

  private TerminalDisplayProgressBar progressBar;

  @Before
  public void setUp() {
    progressBar = new TerminalDisplayProgressBar(printer);
  }

  @Test
  public void displayAMovingProgressbar() {
    progressBar.withPercentageDone(new Progress(0));

    verify(printer).display("|>         |");
  }

  @Test
  public void displayFullProgressBar() {
    progressBar.full();

    verify(printer).display("|==========|");
  }

  @Test
  public void display10Percent() {
    progressBar.withPercentageDone(new Progress(10));

    verify(printer).display("|=>        |");
  }

  @Test
  public void display20Percent() {
    progressBar.withPercentageDone(new Progress(20));

    verify(printer).display("|==>       |");
  }

  @Test
  public void display30Precent() {
    progressBar.withPercentageDone(new Progress(30));

    verify(printer).display("|===>      |");
  }

  @Test
  public void display40Precent() {
    progressBar.withPercentageDone(new Progress(40));

    verify(printer).display("|====>     |");
  }

  @Test
  public void display50Precent() {
    progressBar.withPercentageDone(new Progress(50));

    verify(printer).display("|=====>    |");
  }

  @Test
  public void display60Precent() {
    progressBar.withPercentageDone(new Progress(60));

    verify(printer).display("|======>   |");
  }

  @Test
  public void display70Precent() {
    progressBar.withPercentageDone(new Progress(70));

    verify(printer).display("|=======>  |");
  }

  @Test
  public void display80Precent() {
    progressBar.withPercentageDone(new Progress(80));

    verify(printer).display("|========> |");
  }

  @Test
  public void display90Precent() {
    progressBar.withPercentageDone(new Progress(90));

    verify(printer).display("|=========>|");
  }
}
