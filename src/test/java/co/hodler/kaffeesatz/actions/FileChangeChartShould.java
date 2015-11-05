package co.hodler.kaffeesatz.actions;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.BDDMockito.given;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import co.hodler.kaffeesatz.actions.FileChangeChart;

@RunWith(MockitoJUnitRunner.class)
public class FileChangeChartShould {

  @Mock
  ChangedFiles changedFiles;

  @Test
  public void provideAllChangedFilesOrderedBy() {
    List<String> filesWithChanges = new ArrayList<>();
    filesWithChanges.add(".gitignore");
    filesWithChanges.add("src/main/java/App.java");
    filesWithChanges.add("src/main/java/App.java");
    filesWithChanges.add(".gitignore");
    filesWithChanges.add(".gitignore");
    filesWithChanges.add("src/test/java/AppTest.java");
    given(changedFiles.fetchChangedFiles()).willReturn(filesWithChanges);
    FileChangeChart chart = new FileChangeChart(changedFiles);

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
    List<String> filesWithChanges = new ArrayList<>();
    filesWithChanges.add(".gitignore");
    filesWithChanges.add("src/main/java/App.java");
    filesWithChanges.add("src/main/java/App.java");
    filesWithChanges.add(".gitignore");
    filesWithChanges.add(".gitignore");
    filesWithChanges.add("src/test/java/AppTest.java");
    filesWithChanges.add("src/test/java/First.java");
    filesWithChanges.add("src/test/java/First.java");
    filesWithChanges.add("src/test/java/Second.java");
    filesWithChanges.add("src/test/java/Second.java");
    filesWithChanges.add("src/test/java/Three.java");
    filesWithChanges.add("src/test/java/Three.java");
    filesWithChanges.add("src/test/java/Four.java");
    filesWithChanges.add("src/test/java/Four.java");
    filesWithChanges.add("src/test/java/Five.java");
    filesWithChanges.add("src/test/java/Five.java");
    filesWithChanges.add("src/test/java/Six.java");
    filesWithChanges.add("src/test/java/Six.java");
    filesWithChanges.add("src/test/java/Seven.java");
    filesWithChanges.add("src/test/java/Seven.java");
    filesWithChanges.add("src/test/java/Eigth.java");
    filesWithChanges.add("src/test/java/Eigth.java");
    filesWithChanges.add("src/test/java/Nine.java");
    filesWithChanges.add("src/test/java/Ten.java");
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
