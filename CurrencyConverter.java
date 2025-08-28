import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import org.json.JSONObject;

public class CurrencyConverter {

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {

            System.out.println("💱 Currency Converter");

            System.out.print("Enter base currency (e.g., USD, INR): ");
            String baseCurrency = scanner.nextLine().trim().toUpperCase();

            System.out.print("Enter target currency (e.g., EUR, JPY): ");
            String targetCurrency = scanner.nextLine().trim().toUpperCase();

            System.out.print("Enter amount to convert: ");
            double amount = scanner.nextDouble();

            // Updated API URL
            String apiUrl = "https://open.er-api.com/v6/latest/" + baseCurrency;

            URL url = new URL(apiUrl);
            HttpURLConnection request = (HttpURLConnection) url.openConnection();
            request.setRequestMethod("GET");
            request.connect();

            BufferedReader in = new BufferedReader(new InputStreamReader(request.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;

            while ((line = in.readLine()) != null) {
                response.append(line);
            }
            in.close();

            JSONObject jsonResponse = new JSONObject(response.toString());

            if (jsonResponse.getString("result").equals("success")) {
                if (jsonResponse.has("rates")) {
                    JSONObject rates = jsonResponse.getJSONObject("rates");

                    if (rates.has(targetCurrency)) {
                        double rate = rates.getDouble(targetCurrency);
                        double convertedAmount = rate * amount;

                        System.out.println("\n✅ Exchange Rate: 1 " + baseCurrency + " = " + rate + " " + targetCurrency);
                        System.out.println("💰 Converted Amount: " + convertedAmount + " " + targetCurrency);
                    } else {
                        System.out.println("❌ Target currency not found.");
                    }
                } else {
                    System.out.println("❌ 'rates' not found in response.");
                }
            } else {
                System.out.println("❌ Failed to fetch exchange rates. Check base currency.");
            }

        } catch (Exception e) {
            System.out.println("⚠️ Error: " + e.getMessage());
        }
    }
}
