import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class App {
    public static void main(String[] args) {
        String filePath = "data.txt";
        double minSupport = 0.3;
        double minConfidence = 0.7;

        createDataFile(filePath);

        try {
            Apriori apriori = new Apriori(filePath, minSupport, minConfidence);
            apriori.run();
        } catch (IOException e) {
            System.err.println("Error running the Apriori algorithm: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void createDataFile(String filePath) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
            writer.println("Milk,Bread,Diapers");
            writer.println("Bread,Butter,Beer,Lotus");
            writer.println("Milk,Bread,Butter,Coke");
            writer.println("Milk,Bread,Diapers,Coke");
            writer.println("Bread,Milk,Diapers");
            writer.println("Milk,Coke");
            writer.println("Bread,Butter");
            writer.println("Milk,Bread,Diapers,Beer");
            writer.println("Milk,Bread,Butter");
            writer.println("Bread,Coke,Diapers");
            System.out.println("Successfully created " + filePath + " for the demo.");
            System.out.println("================================================");
        } catch (IOException e) {
            System.err.println("An error occurred while writing the data file.");
            e.printStackTrace();
        }
    }
}