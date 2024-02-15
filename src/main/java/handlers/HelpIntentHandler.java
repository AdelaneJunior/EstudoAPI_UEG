package handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;

import java.util.Optional;

import static com.amazon.ask.request.Predicates.intentName;

public class HelpIntentHandler implements RequestHandler {

    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(intentName("AMAZON.HelpIntent"));
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {
        String speechText = "Você pode perguntar as suas aulas do dia ou notas de va do semestre!";
        return input.getResponseBuilder()
                .withSpeech(speechText)
                .withSimpleCard("Ajuda", speechText)
                .withReprompt(speechText)
                .build();
    }
}