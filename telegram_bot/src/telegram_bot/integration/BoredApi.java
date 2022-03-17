package telegram_bot.integration;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.google.gson.Gson;

import telegram_bot.exception.TelegramBotException;
import telegram_bot.model.BoredApiResponse;

public class BoredApi {

    private static final String BORED_API_URI = "https://www.boredapi.com/api/activity";

    public BoredApiResponse getActivity() throws TelegramBotException {

        // instancia um http client
        HttpClient client = HttpClient.newHttpClient();

        // cria o request
        HttpRequest request = HttpRequest.newBuilder(URI.create(BORED_API_URI))
                .header("accept", "application/json")
                .build();
        HttpResponse<String> response;
        BoredApiResponse boredApiResponse;

        try {
            // usa o cliente para disparar o request
            response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // faz parte do Json para objeto Java
            boredApiResponse = new Gson().fromJson((String) response.body(), BoredApiResponse.class);

            return boredApiResponse;
        } catch (IOException | InterruptedException e) {
           throw new TelegramBotException();
        }
    }
}
