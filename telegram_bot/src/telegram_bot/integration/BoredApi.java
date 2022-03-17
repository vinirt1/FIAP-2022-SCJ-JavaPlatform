package telegram_bot.integration;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.google.gson.Gson;

import telegram_bot.model.BoredApiResponse;

public class BoredApi {

    private static final String BORED_API_URI = "https://www.boredapi.com/api/activity";

    //https://api.genderize.io/?name=luc
    //https://api.agify.io/?name=vinicius

    public BoredApiResponse getActivity() {

        // instancia um http client
        HttpClient client = HttpClient.newHttpClient();

        // cria o request
        HttpRequest request = HttpRequest.newBuilder(URI.create(BORED_API_URI))
                .header("accept", "application/json")
                .build();

        // usa o cliente para disparar o
        HttpResponse<String> response;
        BoredApiResponse boredApiResponse;

        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
            boredApiResponse = new Gson().fromJson((String) response.body(), BoredApiResponse.class);

            return boredApiResponse;
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return new BoredApiResponse();
        }
    }
}
