import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Apriori {

  private List<Set<String>> transactions;
  private final int minSupportCount;
  private final double minConfidence;
  private final Map<Set<String>, Integer> frequentItemsetsWithSupport;

  public Apriori(String filePath, double minSupport, double minConfidence) throws IOException {
    this.transactions = null;
    this.minConfidence = minConfidence > 1 ? minConfidence / 100 : minConfidence;

    loadTransactions(filePath);
    this.minSupportCount = (int) Math.ceil(minSupport * transactions.size());
    this.frequentItemsetsWithSupport = new HashMap<>();
  }

  public void run() {
    System.out.println("Starting Apriori Algorithm...");
    System.out.println("Total transactions: " + transactions.size());
    System.out.println("Minimum support count: " + minSupportCount);
    System.out.println("Minimum confidence: " + (minConfidence * 100) + "%");
    System.out.println("--------------------------------------------------");

    Set<Set<String>> l1 = findFrequent1Itemsets();
    System.out.println("Frequent 1-Itemsets (L1) found: " + l1.size());
    printItemsets("L1", l1);

    Set<Set<String>> lk_1 = l1; // L(k-1)
    int k = 2;
    while (!lk_1.isEmpty()) {
      Set<Set<String>> ck = generateCandidates(lk_1, k);

      Map<Set<String>, Integer> candidateSupport = countSupport(ck);

      Set<Set<String>> lk = new HashSet<>();
      for (Map.Entry<Set<String>, Integer> entry : candidateSupport.entrySet()) {
        if (entry.getValue() >= minSupportCount) {
          lk.add(entry.getKey());
          frequentItemsetsWithSupport.put(entry.getKey(), entry.getValue());
        }
      }

      if (lk.isEmpty()) {
        break;
      }

      System.out.println("\nFrequent " + k + "-Itemsets (L" + k + ") found: " + lk.size());
      printItemsets("L" + k, lk);

      lk_1 = lk;
      k++;
    }

    System.out.println("\n--- Generating Association Rules ---");
    List<AssociationRule> rules = generateAssociationRules();
    System.out.println("Found " + rules.size() + " strong association rules.");
    rules.sort(Comparator.comparing(AssociationRule::getConfidence).reversed());
    for (AssociationRule rule : rules) {
      System.out.println(rule);
    }
    System.out.println("--------------------------------------------------");
  }

  private void loadTransactions(String filePath) throws IOException {
    transactions = new ArrayList<>();
    try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
      String line;
      while ((line = reader.readLine()) != null) {
        if (!line.trim().isEmpty()) {
          Set<String> transaction = new HashSet<>(Arrays.asList(line.split(",")));
          transactions.add(transaction);
        }
      }
    }
  }

  private Set<Set<String>> findFrequent1Itemsets() {
    Map<String, Integer> itemCounts = new HashMap<>();
    for (Set<String> transaction : transactions) {
      for (String item : transaction) {
        itemCounts.put(item, itemCounts.getOrDefault(item, 0) + 1);
      }
    }

    Set<Set<String>> l1 = new HashSet<>();
    for (Map.Entry<String, Integer> entry : itemCounts.entrySet()) {
      if (entry.getValue() >= minSupportCount) {
        Set<String> itemset = new HashSet<>();
        itemset.add(entry.getKey());
        l1.add(itemset);
        frequentItemsetsWithSupport.put(itemset, entry.getValue());
      }
    }
    return l1;
  }

  private Set<Set<String>> generateCandidates(Set<Set<String>> lk_1, int k) {
    Set<Set<String>> candidates = new HashSet<>();
    List<Set<String>> lk_1List = new ArrayList<>(lk_1);

    for (int i = 0; i < lk_1List.size(); i++) {
      for (int j = i + 1; j < lk_1List.size(); j++) {
        Set<String> set1 = lk_1List.get(i);
        Set<String> set2 = lk_1List.get(j);

        Set<String> union = new HashSet<>(set1);
        union.addAll(set2);

        if (union.size() == k) {
          if (hasInfrequentSubsets(union, lk_1)) {
            continue; // Prune this candidate
          }
          candidates.add(union);
        }
      }
    }
    return candidates;
  }

  private boolean hasInfrequentSubsets(Set<String> candidate, Set<Set<String>> lk_1) {
    List<String> itemList = new ArrayList<>(candidate);
    for (String itemToRemove : itemList) {
      Set<String> subset = new HashSet<>(candidate);
      subset.remove(itemToRemove);
      if (!lk_1.contains(subset)) {
        return true;
      }
    }
    return false;
  }

  private Map<Set<String>, Integer> countSupport(Set<Set<String>> candidates) {
    Map<Set<String>, Integer> supportCounts = new HashMap<>();
    for (Set<String> candidate : candidates) {
      for (Set<String> transaction : transactions) {
        if (transaction.containsAll(candidate)) {
          supportCounts.put(candidate, supportCounts.getOrDefault(candidate, 0) + 1);
        }
      }
    }
    return supportCounts;
  }

  private List<AssociationRule> generateAssociationRules() {
    List<AssociationRule> rules = new ArrayList<>();
    for (Set<String> itemset : frequentItemsetsWithSupport.keySet()) {
      if (itemset.size() > 1) {
        for (Set<String> antecedent : getNonEmptySubsets(itemset)) {
          Set<String> consequent = new HashSet<>(itemset);
          consequent.removeAll(antecedent);

          if (consequent.isEmpty())
            continue;

          int supportAB = frequentItemsetsWithSupport.get(itemset);
          int supportA = frequentItemsetsWithSupport.get(antecedent);
          double confidence = (double) supportAB / supportA;

          if (confidence >= minConfidence) {
            double support = (double) supportAB / transactions.size();
            rules.add(new AssociationRule(antecedent, consequent, confidence, support));
          }
        }
      }
    }
    return rules;
  }

  private List<Set<String>> getNonEmptySubsets(Set<String> set) {
    List<Set<String>> subsets = new ArrayList<>();
    String[] elements = set.toArray(new String[0]);
    int n = elements.length;

    for (int i = 1; i < (1 << n) - 1; i++) {
      Set<String> subset = new HashSet<>();
      for (int j = 0; j < n; j++) {
        if ((i & (1 << j)) > 0) {
          subset.add(elements[j]);
        }
      }
      subsets.add(subset);
    }
    return subsets;
  }

  private void printItemsets(String title, Set<Set<String>> itemsets) {
    System.out.println(title + ":");
    List<Set<String>> sortedList = new ArrayList<>(itemsets);
    sortedList.sort(Comparator.comparing(Object::toString));
    for (Set<String> itemset : sortedList) {
      System.out.println("  " + itemset + " (Support: " + frequentItemsetsWithSupport.get(itemset) + ")");
    }
  }
}