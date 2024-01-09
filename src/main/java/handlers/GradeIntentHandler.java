package handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;
import converter.Converter;
import requests.HttpRequests;

import java.util.Objects;
import java.util.Optional;

import static com.amazon.ask.request.Predicates.intentName;

public class GradeIntentHandler implements RequestHandler {

    @Override
    public boolean canHandle(HandlerInput input) {

        return input.matches(intentName("GradeIntent"));
    }

    @Override
    public Optional<Response> handle(HandlerInput input)  {

        return input.getResponseBuilder()
                .withSpeech(getData())
                .build();

    }

    private String getData() {

        HttpRequests httpRequests = new HttpRequests("02296120164", "@DEJunior06");

        try {
           if (httpRequests.doLogin()) {
               httpRequests.enterPortalEstudante();
               httpRequests.getGrade();
           }
           if (Objects.nonNull(httpRequests.getJsonGrade())) {
               return Converter.jsonToGrade((httpRequests.getJsonGrade())).get(0).toString();
           }
       }catch (Throwable throwable){
           throwable.printStackTrace();
       }
        return "nenhuma nota encontrada";
    }
}