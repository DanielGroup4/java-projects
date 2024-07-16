import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        Scanner lecture = new Scanner(System.in);
        System.out.println("type the currency denomination to be converted: ");
        String search = lecture.nextLine();

        String address = "https://v6.exchangerate-api.com/v6/" + "0744ae5f4febad6a0d596e4e/latest/" + search ;

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(address))
                .build();
        HttpResponse<String> response = client
                .send(request, HttpResponse.BodyHandlers.ofString());

        String json = response.body();
        System.out.println(json);

        Gson gson = new Gson();
        gson.fromJson(json, Ti)

        System.out.println(response.body());
    }
}
