public class WordDefinition {
  private String word;

  public WordDefinition(String word) {
    this.word = word;
  }

  public String getWord() {
    return word;
  }

  @Override
  public String toString() {
    return "Word: " + word;
  }
}