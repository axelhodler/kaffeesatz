package co.hodler.kaffeesatz.ui;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.PrintStream;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class ConsolePrinterIT {

  @Test
  public void printToConsole() {
    ByteArrayOutputStream sysOutputContent = new ByteArrayOutputStream();
    System.setOut(new PrintStream(sysOutputContent));

    Printer p = new ConsolePrinter();

    p.display("hello");

    assertThat(sysOutputContent.toString(), is("hello\r"));
    System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
  }

}