package handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.IntentRequest;
import com.amazon.ask.model.Response;
import com.amazon.ask.model.Slot;
import converter.Converter;
import formatters.GradeFormatter;
import requests.HttpRequests;

import java.util.Objects;
import java.util.Optional;

import static com.amazon.ask.request.Predicates.intentName;

public class DetailedGradeIntentHandler implements RequestHandler {

    @Override
    public boolean canHandle(HandlerInput input) {

        return input.matches(intentName("DetailedGradeIntent"));
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {

        IntentRequest request = (IntentRequest) input.getRequest();
        Slot periodo = request.getIntent().getSlots().get("semestre");
        int periodoInt = Integer.parseInt(periodo.getValue());

        return input.getResponseBuilder()
                .withSpeech(getData(periodoInt))
                .build();

    }

    private String getData(int periodo) {

        HttpRequests httpRequests = new HttpRequests("02296120164", "@DEJunior06");

        try {
            httpRequests.doLogin();
            httpRequests.enterPortalEstudante();
            httpRequests.getGrade();

            if (Objects.nonNull(httpRequests.getJsonGrade())) {
                return GradeFormatter.formatterDetailedGradeToSpeech(
                        Converter.jsonToGradeWithPeriod(
                                (httpRequests.getJsonGrade()), periodo), periodo);
            }
        }catch (Throwable throwable){
            throwable.printStackTrace();
        }

        return "nenhuma nota encontrada";
    }
}