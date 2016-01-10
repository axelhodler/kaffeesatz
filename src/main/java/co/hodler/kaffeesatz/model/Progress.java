package co.hodler.kaffeesatz.model;

public final class Progress {
  private final int progress;

  public Progress(int progress) {
    this.progress = progress;
  }

  public int intValue() {
    return this.progress;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Progress progress1 = (Progress) o;

    return progress == progress1.progress;
  }

  @Override
  public int hashCode() {
    return progress;
  }

  @Override
  public String toString() {
    return "Progress{" +
            "progress=" + progress +
            '}';
  }
}
