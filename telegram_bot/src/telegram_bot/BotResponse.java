package telegram_bot;

import java.util.regex.Pattern;

public class BotResponse {

    private static final String START = "/start";
    private static final String DEFAULT_FRIENDLY_UNKNOWN_USER_MESSAGE = "Desculpe... não consegui te entender :(";

    public String getBotResponseMessage(String userMessage) {

        if (BotUtil.isCommandMessage(userMessage)) {
            return getBotCommandResponseMessage(userMessage);
        }

        return getBotPatternResponseMessage(userMessage);
    }

    public String getBotCommandResponseMessage(String userMessage) {
        String responseMessage = DEFAULT_FRIENDLY_UNKNOWN_USER_MESSAGE;

        switch (userMessage) {
            case START:
                responseMessage = "Olá, seja bem vindo. Olha todas as coisas que consigo fazer pra te ajudar:\n \n 1 - Adivinhar sua idade à partir do seu nome;\n 2 - Adivinhar o seu sexo à partir do seu nome;\n 3 - Sugerir atividades para te tirar do tédio;\n \n Digite a opção ou o assunto desejado para que eu possa te atender.\n";
                break;
            default:
                break;
        }

        return responseMessage;
    }

    public String getBotPatternResponseMessage(String userMessage) {
        final String AGE_PATTERN_EXPECTED_WORLD = "idade";
        final String SEX_PATTERN_EXPECTED_WORLD = "sexo";
        final String BORED_PATTERN_EXPECTED_WORLD = "atividade";

        final boolean isAgePattern = Pattern.compile(AGE_PATTERN_EXPECTED_WORLD).matcher(userMessage).find();
        final boolean isSexPattern = Pattern.compile(SEX_PATTERN_EXPECTED_WORLD).matcher(userMessage).find();
        final boolean isBoredPattern = Pattern.compile(BORED_PATTERN_EXPECTED_WORLD).matcher(userMessage).find();

        if (isBoredPattern) {
            return "Chamarei a API";
        } if (isAgePattern) {
            return "Ok, entendi. Você quer saber sua idade à partir do seu nome. Por favor me informe o seu nome. ";
        } else if (isSexPattern) {
            return "Ok, entendi. Você quer saber seu sexo à partir do seu nome. Por favor me informe o seu nome. ";
        } else {
            return DEFAULT_FRIENDLY_UNKNOWN_USER_MESSAGE;
        }
    }

}
