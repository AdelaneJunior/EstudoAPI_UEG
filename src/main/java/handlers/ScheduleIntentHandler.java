package handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.IntentRequest;
import com.amazon.ask.model.Response;
import com.amazon.ask.model.Slot;
import com.google.gson.Gson;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Objects;
import java.util.Optional;

import static com.amazon.ask.request.Predicates.intentName;

public class ScheduleIntentHandler implements RequestHandler {

    @Override
    public boolean canHandle(HandlerInput input) {

        return input.matches(intentName("ScheduleIntent"));
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {

        IntentRequest request = (IntentRequest) input.getRequest();
        Slot diaSlot = request.getIntent().getSlots().get("dia");
        String data = diaSlot.getValue();
        String jwt = input.getRequestEnvelope().getContext().getSystem().getUser().getAccessToken();
        String speech = getData(data,jwt);

        return input.getResponseBuilder()
                .withSimpleCard("Aulas: ", speech)
                .withSpeech(speech)
                .withReprompt(speech)
                .build();

    }

    private String getData(String data, String jwt) {

        try {
            if (Objects.nonNull(jwt)) {
                System.out.println("antezderequest");
                return getClassScheduleFromAPI(jwt,data);
            }
            else return "TOKEN INVALIDO";
        }catch (Throwable throwable){
            throwable.printStackTrace();
            return "Ocorreu um erro ao buscar os dados";
        }
    }

    private String getClassScheduleFromAPI(String jwt, String day){
        try {
            Gson gson = new Gson();
            HttpRequest postRequest = HttpRequest.newBuilder()
                    .uri(new URI(""+""))
                    .headers("Authorization","Bearer "+jwt,
                            "Content-Type","application/json",
                            "Accept", "text/plain")
                    .POST(HttpRequest.BodyPublishers.ofString("[\""+day+"\"]"))
                    .build();
            HttpClient httpClient = HttpClient.newHttpClient();
            HttpResponse<String> httpResponse =
                    httpClient.send(postRequest, HttpResponse.BodyHandlers.ofString());
            return httpResponse.body();
        } catch (Throwable error) {
            throw new RuntimeException(error);
        }
    }
}