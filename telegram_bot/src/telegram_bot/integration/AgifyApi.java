package telegram_bot.integration;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.google.gson.Gson;

import telegram_bot.exception.TelegramBotException;
import telegram_bot.model.AgifyApiResponse;

public class AgifyApi {

    private static final String AGIFY_API_URI = "https://api.agify.io/?name=";

    public AgifyApiResponse getNameInfo(final String nameTyped) throws TelegramBotException {

        // instancia um http client
        HttpClient client = HttpClient.newHttpClient();

        // cria o request
        HttpRequest request = HttpRequest.newBuilder(URI.create(AGIFY_API_URI + nameTyped))
                .header("accept", "application/json")
                .build();
        HttpResponse<String> response;
        AgifyApiResponse agifyApiResponse;

        try {
            // usa o cliente para disparar o request
            response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // faz parte do Json para objeto Java
            agifyApiResponse = new Gson().fromJson((String) response.body(), AgifyApiResponse.class);

            return agifyApiResponse;
        } catch (IOException | InterruptedException e) {
           throw new TelegramBotException();
        }
    }
}
