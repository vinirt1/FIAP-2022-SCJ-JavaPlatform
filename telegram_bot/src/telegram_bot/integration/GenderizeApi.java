package telegram_bot.integration;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.google.gson.Gson;

import telegram_bot.exception.TelegramBotException;
import telegram_bot.model.GenderizeApiResponse;

public class GenderizeApi {

    private static final String GENDERIZE_API_URI = "https://api.genderize.io/?name=";

    public GenderizeApiResponse getNameInfo(final String nameTyped) throws TelegramBotException {

        // instancia um http client
        HttpClient client = HttpClient.newHttpClient();

        // cria o request
        HttpRequest request = HttpRequest.newBuilder(URI.create(GENDERIZE_API_URI + nameTyped))
                .header("accept", "application/json")
                .build();
        HttpResponse<String> response;
        GenderizeApiResponse genderizeApiResponse;

        try {
            // usa o cliente para disparar o request
            response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // faz parte do Json para objeto Java
            genderizeApiResponse = new Gson().fromJson((String) response.body(), GenderizeApiResponse.class);

            return genderizeApiResponse;
        } catch (IOException | InterruptedException e) {
           throw new TelegramBotException();
        }
    }
}
