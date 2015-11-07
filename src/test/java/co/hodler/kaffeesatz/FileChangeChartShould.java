package co.hodler.kaffeesatz;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.BDDMockito.given;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import co.hodler.kaffeesatz.actions.FetchChangedFiles;
import co.hodler.kaffeesatz.FileChangeChart;

@RunWith(MockitoJUnitRunner.class)
public class FileChangeChartShould {

  @Mock
  FetchChangedFiles changedFiles;

  private FileChangeChart chart;

  private List<String> filesWithChanges;

  @Before
  public void initialize() {
    chart = new FileChangeChart(changedFiles);
  }

  @Test
  public void provideAllChangedFilesOrderedBy() {
    filesWithChanges = Arrays.asList(".gitignore", "src/main/java/App.java",
        "src/main/java/App.java", ".gitignore", ".gitignore",
        "src/test/java/AppTest.java");
    given(changedFiles.fetchChangedFiles()).willReturn(filesWithChanges);

    Map<String, Integer> orderedChanges = chart.create();

    Iterator<Entry<String, Integer>> iter = orderedChanges.entrySet().iterator();
    assertThat(iter.next().getValue(), is(3));
    assertThat(iter.next().getValue(), is(2));
    assertThat(iter.next().getValue(), is(1));
    assertThat(orderedChanges.get(".gitignore"), is(3));
    assertThat(orderedChanges.get("src/main/java/App.java"), is(2));
    assertThat(orderedChanges.get("src/test/java/AppTest.java"), is(1));
  }

  @Test
  public void beAbleToProvideTheTopTenOfChangedFiles() {
    filesWithChanges = Arrays.asList(".gitignore", ".gitignore",
        "src/main/java/App.java", "src/main/java/App.java", ".gitignore",
        "src/test/java/AppTest.java", "src/test/java/First.java",
        "src/test/java/First.java", "src/test/java/Second.java",
        "src/test/java/Second.java", "src/test/java/Three.java",
        "src/test/java/Three.java", "src/test/java/Four.java",
        "src/test/java/Four.java", "src/test/java/Five.java",
        "src/test/java/Five.java", "src/test/java/Six.java",
        "src/test/java/Six.java", "src/test/java/Seven.java",
        "src/test/java/Seven.java", "src/test/java/Eigth.java",
        "src/test/java/Eigth.java", "src/test/java/Nine.java",
        "src/test/java/Ten.java");
    given(changedFiles.fetchChangedFiles()).willReturn(filesWithChanges);
    FileChangeChart chart = new FileChangeChart(changedFiles);

    Map<String, Integer> orderedChanges = chart.createTop10();

    Iterator<Entry<String, Integer>> iter = orderedChanges.entrySet().iterator();
    assertThat(iter.next().getValue(), is(3));
    assertThat(iter.next().getValue(), is(2));
    assertThat(iter.next().getValue(), is(2));
    assertThat(iter.next().getValue(), is(2));
    assertThat(iter.next().getValue(), is(2));
    assertThat(iter.next().getValue(), is(2));
    assertThat(iter.next().getValue(), is(2));
    assertThat(iter.next().getValue(), is(2));
    assertThat(iter.next().getValue(), is(2));
    assertThat(iter.next().getValue(), is(2));
    assertThat(iter.hasNext(), is(false));
  }
}
