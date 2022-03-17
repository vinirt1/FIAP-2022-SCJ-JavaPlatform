package telegram_bot;

import java.util.List;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.ChatAction;
import com.pengrad.telegrambot.request.GetUpdates;
import com.pengrad.telegrambot.request.SendChatAction;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.BaseResponse;
import com.pengrad.telegrambot.response.GetUpdatesResponse;
import com.pengrad.telegrambot.response.SendResponse;

import telegram_bot.exception.TelegramBotException;
import telegram_bot.model.ChatFlow;

public class Main {

	public static void main(String[] args) {
		// Criacao do objeto bot com as informacoes de acesso.
		TelegramBot bot = new TelegramBot("5264731114:AAEgnaETiA4Co5IBzquVmG2qeSWqa-9kyrs");

		// Objeto responsavel por receber as mensagens.
		GetUpdatesResponse updatesResponse;

		// Objeto responsavel por gerenciar o envio de respostas.
		SendResponse sendResponse;

		// Objeto responsavel por gerenciar o envio de acoes do chat.
		BaseResponse baseResponse;

		// Controle de off-set, isto e, a partir deste ID sera lido as mensagens
		// pendentes na fila.
		int m = 0;

		// Instancia fluxo do chat, objeto que controla estado, mensagem atual, anterior, quantidade de iterações, etc;
		ChatFlow chatFlow = new ChatFlow();

		// Loop infinito pode ser alterado por algum timer de intervalo curto.
		while (true) {
			// Executa comando no Telegram para obter as mensagens pendentes a partir de um
			// off-set (limite inicial).
			updatesResponse = bot.execute(new GetUpdates().limit(100).offset(m));

			// Lista de mensagens.
			List<Update> updates = updatesResponse.updates();

			// Analise de cada acao da mensagem.
			for (Update update : updates) {

				// Atualizacao do off-set.
				m = update.updateId() + 1;

				// Atualiza numero de iteracoes
				final int interationCount = chatFlow.getInterationCount();
				chatFlow.setInterationCount(interationCount + 1);
				System.out.println("Número da iteração: " + interationCount);

				// A cada iteração seta a mensagem anterior como a mensagem atual da última iteração
				chatFlow.setPreviousMessage(chatFlow.getCurrentMessage());

				// Recupera mensagem do usuário
				final String userMessage = update.message().text();
				System.out.println("Recebendo mensagem: " + userMessage);

				// Se a mensagem digitada for /start reinicia iterações
				if ("/start".equals(userMessage)) {
					chatFlow.setInterationCount(1);
				}

				// Envio de "Escrevendo" antes de enviar a resposta.
				baseResponse = bot.execute(new SendChatAction(update.message().chat().id(), ChatAction.typing.name()));

				// Verificacao de acao de chat foi enviada com sucesso.
				System.out.println("Resposta de Chat Action Enviada? " + baseResponse.isOk());

				String responseMessage;
				try {
					// recupera mensagem a ser respondida ao usuário
					responseMessage = new BotResponse().getBotResponseMessage(userMessage, chatFlow);

					// Seta mensagem atual no controle de fluxo
					chatFlow.setCurrentMessage(responseMessage);
				} catch (TelegramBotException e) {
					e.printStackTrace();
					responseMessage = "Ops... aconteceu um erro ao atender a sua mensagem :( Tente novamente...";
				}

				// Envio da mensagem de resposta.
				sendResponse = bot.execute(new SendMessage(update.message().chat().id(), responseMessage));

				// Verificacao de mensagem enviada com sucesso.
				System.out.println("Mensagem Enviada? " + sendResponse.isOk());
			}
		}
	}
}
