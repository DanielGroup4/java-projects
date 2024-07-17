import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ApiClient {

    private static final String API_KEY = "0744ae5f4febad6a0d596e4e";
    private static final String URL = "https://v6.exchangerate-api.com/v6/" + API_KEY + "/latest/";

    public JsonObject exchangeRates(String baseCurrency) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(URL + baseCurrency))
                .build();
        HttpResponse<String> response = client
                .send(request, HttpResponse.BodyHandlers.ofString());

        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(response.body(), JsonObject.class);

        if ("success".equals(jsonObject.get("result").getAsString())){
            return jsonObject.getAsJsonObject("conversion_rates");
        }else{
            System.out.println("Error: " + jsonObject.get("error-type").getAsString());
            return null;
        }
    }
}
