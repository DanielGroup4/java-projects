import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        CurrencyConverter converter = new CurrencyConverter();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            try {
                converter.runConversionProcess(scanner);
            } catch (IOException | InterruptedException e) {
                System.out.println("An error occurred: " + e.getMessage());
            }

            System.out.println("\nDo you want to perform another conversion? (yes/no): ");
            if (!scanner.nextLine().toLowerCase().startsWith("y")) {
                break;
            }
        }

        scanner.close();
    }
}
