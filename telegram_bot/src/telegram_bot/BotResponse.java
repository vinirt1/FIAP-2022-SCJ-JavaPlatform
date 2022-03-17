package telegram_bot;

import java.util.regex.Pattern;

import telegram_bot.integration.BoredApi;
import telegram_bot.model.BoredApiResponse;

public class BotResponse {

    private static final String START = "/start";
    private static final String END = "/end";

    private static final String DEFAULT_FRIENDLY_UNKNOWN_USER_MESSAGE = "Desculpe... não consegui entender esse comando :(";

    public String getBotResponseMessage(String userMessage) {

        if (BotUtil.isCommandMessage(userMessage)) {
            return getBotCommandResponseMessage(userMessage);
        }

        if (BotUtil.isNumberMessage(userMessage)) {
            return getBotNumberResponseMessage(userMessage);
        }

        return getBotPatternResponseMessage(userMessage);
    }

    private String getBotCommandResponseMessage(String userMessage) {
        String responseMessage = DEFAULT_FRIENDLY_UNKNOWN_USER_MESSAGE;

        switch (userMessage) {
            case START:
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("Olá, seja bem vindo. Olha todas as coisas que consigo fazer pra te ajudar:\n \n");
                stringBuilder.append("1 - Adivinhar a idade da pessoa à partir do seu nome;\n");
                stringBuilder.append("2 - Adivinhar o seu sexo da pessoa à partir do seu nome;\n");
                stringBuilder.append("3 - Sugerir atividades para te tirar do tédio;\n \n");
                stringBuilder.append("Digite a opção ou o assunto desejado para que eu possa te atender.\n \n");
                stringBuilder.append("A qualquer momento digite /end para finalizar a nossa conversa");

                responseMessage = stringBuilder.toString();
                break;
            case END:
                responseMessage = "Tchau! Foi um prazer interagir com você! Até a próxima!";
                break;
            default:
                break;
        }

        return responseMessage;
    }

    private String getBotNumberResponseMessage(String userMessage) {
        String responseMessage = DEFAULT_FRIENDLY_UNKNOWN_USER_MESSAGE;

        final int AGE_COMMAND_NUMBER = 1;
        final int SEX_COMMAND_NUMBER = 2;
        final int ACTIVITY_COMMAND_NUMBER = 3;

        final int userNumberMessage = Integer.parseInt(userMessage);

        switch (userNumberMessage) {
            case AGE_COMMAND_NUMBER:
                responseMessage = "Ok, entendi. Você quer que eu adivinhe a idade de uma pessoa à partir do seu nome. Por favor me informe o nome. ";
                break;
            case SEX_COMMAND_NUMBER:
                responseMessage = "Ok, entendi. Você quer que eu adivinhe o sexo de uma pessoa à partir do seu nome. Por favor me informe o nome. ";
                break;
            case ACTIVITY_COMMAND_NUMBER:
                BoredApiResponse boredApiResponse = new BoredApi().getActivity();
                responseMessage = getFormattedActivityTip(boredApiResponse);
                break;
            default:
                responseMessage = DEFAULT_FRIENDLY_UNKNOWN_USER_MESSAGE;
                break;
        }

        return responseMessage;
    }

    private String getBotPatternResponseMessage(String userMessage) {
        final String AGE_PATTERN_EXPECTED_WORLD = "idade";
        final String SEX_PATTERN_EXPECTED_WORLD = "sexo";
        final String ACTIVITY_PATTERN_EXPECTED_WORLD = "atividade";

        final boolean isAgePattern = Pattern.compile(AGE_PATTERN_EXPECTED_WORLD).matcher(userMessage).find();
        final boolean isSexPattern = Pattern.compile(SEX_PATTERN_EXPECTED_WORLD).matcher(userMessage).find();
        final boolean isActivityPattern = Pattern.compile(ACTIVITY_PATTERN_EXPECTED_WORLD).matcher(userMessage).find();

        if (isActivityPattern) {
            BoredApiResponse boredApiResponse = new BoredApi().getActivity();

            return getFormattedActivityTip(boredApiResponse);
        }
        if (isAgePattern) {
            return "Ok, entendi. Você quer que eu adivinhe a idade de uma pessoa à partir do seu nome. Por favor me informe o nome. ";
        } else if (isSexPattern) {
            return "Ok, entendi. Você quer que eu adivinhe o sexo de uma pessoa à partir do seu nome. Por favor me informe o nome. ";
        } else {
            return DEFAULT_FRIENDLY_UNKNOWN_USER_MESSAGE;
        }
    }

    private String getFormattedActivityTip(final BoredApiResponse boredApiResponse) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Ok, aqui vai uma sugestão de atividade pra você: \n \n");
        stringBuilder.append("Atividade: " + boredApiResponse.getActivity() + " \n");
        stringBuilder.append("Tipo: " + boredApiResponse.getType() + " \n");
        stringBuilder.append("Participantes: " + boredApiResponse.getParticipants() + " \n");
        stringBuilder.append("Preço: " + boredApiResponse.getPrice() + " \n");
        stringBuilder.append("Link: " + boredApiResponse.getLink() + " \n");
        stringBuilder.append("Chave: " + boredApiResponse.getKey() + " \n");
        stringBuilder.append("Acessibilidade: " + boredApiResponse.getAccessibility() + " \n");

        return stringBuilder.toString();
    }
}
