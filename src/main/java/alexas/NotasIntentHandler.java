package alexas;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;
import converter.Converter;
import requests.HttpRequests;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Objects;
import java.util.Optional;

import static com.amazon.ask.request.Predicates.intentName;

public class NotasIntentHandler implements RequestHandler {

    @Override
    public boolean canHandle(HandlerInput input) {

        return input.matches(intentName(""));
    }

    @Override
    public Optional<Response> handle(HandlerInput input)  {

        String speechText = "nenhuma nota encontrada";
        try {
            speechText = getData();
        }catch (Throwable throwable){
            throwable.printStackTrace();
        }
        return input.getResponseBuilder()
                .withSpeech(speechText)
                .build();

    }

    private String getData() throws URISyntaxException, IOException {

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