package handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.IntentRequest;
import com.amazon.ask.model.Response;
import com.amazon.ask.model.Slot;
import converter.Converter;
import formatters.DisciplineFormatter;
import jsonObjects.Discipline;
import requests.HttpRequests;

import java.util.List;
import java.util.Map;
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
        String speech = getData(data);
        return input.getResponseBuilder()
                .withSimpleCard("Aulas: ", speech)
                .withSpeech(speech)
                .withReprompt(speech)
                .build();

    }

    private String getData(String data) {

        HttpRequests httpRequests = new HttpRequests("02296120164", "@DEJunior06");

        try {
            httpRequests.doLogin();
            httpRequests.enterPortalEstudante();
            httpRequests.getClassSchedule();

            if (Objects.nonNull(httpRequests.getJsonSchedule())) {
               Map<String, List<Discipline>> diasAulas = Converter.montaHorarioSemana(
                               Objects.requireNonNull(Converter.jsonToDiscipline(httpRequests.getJsonSchedule())));

                return DisciplineFormatter.checkDayAndFormatterToSpeech(data, diasAulas);
            }
        }catch (Throwable throwable){
            throwable.printStackTrace();
        }

        return "nenhuma aula encontrada";
    }
}