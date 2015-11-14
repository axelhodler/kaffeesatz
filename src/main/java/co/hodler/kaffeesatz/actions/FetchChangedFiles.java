package co.hodler.kaffeesatz.actions;

import java.util.List;

import co.hodler.kaffeesatz.model.ChangedFile;

public interface FetchChangedFiles {

  List<ChangedFile> fetchChangedFiles();

}
