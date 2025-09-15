import java.text.DecimalFormat;
import java.util.Set;

public class AssociationRule {
  private final Set<String> antecedent;
  private final Set<String> consequent;
  private final double confidence;
  private final double support;

  public AssociationRule(Set<String> antecedent, Set<String> consequent, double confidence, double support) {
    this.antecedent = antecedent;
    this.consequent = consequent;
    this.confidence = confidence;
    this.support = support;
  }

  public Set<String> getAntecedent() {
    return antecedent;
  }

  public Set<String> getConsequent() {
    return consequent;
  }

  public double getConfidence() {
    return confidence;
  }

  public double getSupport() {
    return support;
  }

  @Override
  public String toString() {
    DecimalFormat df = new DecimalFormat("0.##");
    return antecedent + " -> " + consequent +
        " (Support: " + df.format(support) +
        ", Confidence: " + df.format(confidence * 100) + "%)";
  }
}