package handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.IntentRequest;
import com.amazon.ask.model.Response;
import com.amazon.ask.model.Session;
import com.amazon.ask.model.Slot;

import java.util.Optional;

import static com.amazon.ask.request.Predicates.intentName;

public class SingUpIntentHandler implements RequestHandler {

    @Override
    public boolean canHandle(HandlerInput input) {

        return input.matches(intentName("SingUpIntent"));
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {

        Session session = input.getRequestEnvelope().getSession();

        IntentRequest request = (IntentRequest) input.getRequest();
        Slot dados = request.getIntent().getSlots().get("cpfsenha");
        System.out.println(dados.getValue());
//        UserDBClient dbClient = UserDBClient.getInstance();
//        UserData userData = dbClient.saveUser(session, "cpf");
        return input.getResponseBuilder()
                .withSpeech(dados.getValue())
                .build();

    }
}