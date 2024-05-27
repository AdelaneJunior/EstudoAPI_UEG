//package handlers;
//
//import com.amazon.ask.dispatcher.request.handler.HandlerInput;
//import com.amazon.ask.dispatcher.request.handler.RequestHandler;
//import com.amazon.ask.model.IntentRequest;
//import com.amazon.ask.model.Response;
//import com.amazon.ask.model.Slot;
//import com.amazon.ask.model.services.listManagement.AlexaList;
//import converter.Converter;
//import formatters.GradeFormatter;
//import requests.HttpRequests;
//
//import java.util.Objects;
//import java.util.Optional;
//
//import static com.amazon.ask.request.Predicates.intentName;
//
//public class DetailedGradeIntentHandler implements RequestHandler {
//
//    @Override
//    public boolean canHandle(HandlerInput input) {
//
//        return input.matches(intentName("DetailedGradeIntent"));
//    }
//
//    @Override
//    public Optional<Response> handle(HandlerInput input) {
//
//        IntentRequest request = (IntentRequest) input.getRequest();
//        Slot periodo = request.getIntent().getSlots().get("semestre");
//        int periodoInt = Integer.parseInt(periodo.getValue());
//        String speech = getData(periodoInt);
//        input.getRequestEnvelope().getContext().getSystem().getUser().getAccessToken();
//
//        return input.getResponseBuilder()
//                .withSpeech(speech)
//                .withReprompt(speech)
//                .withSimpleCard("Notas VA detalhadas:", speech)
//                .build();
//
//    }
//
//    private String getData(int periodo) {
//
//        HttpRequests httpRequests = new HttpRequests("02296120164", "@DEJunior0204");
//
//        try {
//            httpRequests.doLogin();
//            httpRequests.enterPortalEstudante();
//            httpRequests.getGrade();
//
//            if (Objects.nonNull(httpRequests.getJsonGrade())) {
//                return GradeFormatter.formatterDetailedGradeToSpeech(
//                        Converter.jsonToGradeWithPeriod(
//                                (httpRequests.getJsonGrade()), periodo), periodo);
//            }
//        }catch (Throwable throwable){
//            throwable.printStackTrace();
//        }
//
//        return "nenhuma nota encontrada";
//    }
//}