import com.google.gson.JsonObject;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class CurrencyConverter {
    // attributes
    private static final List<String> SUPPORTED_CURRENCIES = Arrays.asList("ARS", "MXN", "CLP", "COP", "USD");
    private final ApiClient apiClient;

    // constructor
    public CurrencyConverter() {
        this.apiClient = new ApiClient();
    }

    public void runConversionProcess(Scanner scanner) throws IOException, InterruptedException {
        String baseCurrency = getBaseCurrency(scanner);
        JsonObject rates = apiClient.exchangeRates(baseCurrency);

        if (rates == null) {
            System.out.println("Failed to fetch exchange rates. Please try again.");
            return;
        }

        displayExchangeRates(rates);
        performConversion(scanner, baseCurrency, rates);
    }

    private String getBaseCurrency(Scanner scanner) {
        System.out.println("Enter the base currency code (e.g., USD): ");
        return scanner.nextLine().toUpperCase();
    }

    private void displayExchangeRates(JsonObject rates) {
        System.out.println("\nExchange Rates:");
        for (String currency : SUPPORTED_CURRENCIES) {
            if (rates.has(currency)) {
                System.out.printf("%s: %.4f%n", currency, rates.get(currency).getAsDouble());
            }
        }
    }

    private void performConversion(Scanner scanner, String baseCurrency, JsonObject rates) {
        System.out.println("\nEnter the amount to convert: ");
        double amount = scanner.nextDouble();
        scanner.nextLine(); // Consume newline

        System.out.println("Enter the target currency code: ");
        String targetCurrency = scanner.nextLine().toUpperCase();

        if (rates.has(targetCurrency)) {
            double rate = rates.get(targetCurrency).getAsDouble();
            double convertedAmount = amount * rate;
            System.out.printf("%.2f %s = %.2f %s%n", amount, baseCurrency, convertedAmount, targetCurrency);
        } else {
            System.out.println("Target currency not supported.");
        }
    }
}
