package co.hodler;

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
}
