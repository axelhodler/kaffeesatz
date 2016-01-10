package co.hodler.kaffeesatz.ui;

import co.hodler.kaffeesatz.model.Progress;

public interface DisplayProgressBar {

  void full();

  void withPercentageDone(Progress progress);

}
