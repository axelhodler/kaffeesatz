package co.hodler.kaffeesatz.model;

public final class ChangedFile {

  private String file;

  public ChangedFile(String file) {
    this.file = file;
  }

  public String value() {
    return file;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    ChangedFile that = (ChangedFile) o;

    return !(file != null ? !file.equals(that.file) : that.file != null);
  }

  @Override
  public int hashCode() {
    return file != null ? file.hashCode() : 0;
  }
}
