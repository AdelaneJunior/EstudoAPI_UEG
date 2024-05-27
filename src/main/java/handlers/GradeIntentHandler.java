//package handlers;
//
//import com.amazon.ask.dispatcher.request.handler.HandlerInput;
//import com.amazon.ask.dispatcher.request.handler.RequestHandler;
//import com.amazon.ask.model.IntentRequest;
//import com.amazon.ask.model.Response;
//import com.amazon.ask.model.Slot;
//import converter.Converter;
//import formatters.GradeFormatter;
//import requests.HttpRequests;
//
//import java.net.URI;
//import java.net.http.HttpClient;
//import java.net.http.HttpRequest;
//import java.net.http.HttpResponse;
//import java.util.Objects;
//import java.util.Optional;
//
//import static com.amazon.ask.request.Predicates.intentName;
//
//public class GradeIntentHandler implements RequestHandler {
//
//    @Override
//    public boolean canHandle(HandlerInput input) {
//
//        return input.matches(intentName("GradeIntent"));
//    }
//
//    @Override
//    public Optional<Response> handle(HandlerInput input) {
//
//        IntentRequest request = (IntentRequest) input.getRequest();
//        Slot periodo = request.getIntent().getSlots().get("semestre");
//        int periodoInt = Integer.parseInt(periodo.getValue());
//        String speech = getData(periodoInt);
//        String jwt = input.getRequestEnvelope().getContext().getSystem().getUser().getAccessToken();
//        return input.getResponseBuilder()
//                .withSpeech(speech)
//                .withSimpleCard("Notas VA:", speech)
//                .withReprompt(speech)
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
//                return GradeFormatter.formatterGradeToSpeech(
//                        Converter.jsonToGradeWithPeriod(
//                                (httpRequests.getJsonGrade()), periodo), periodo);
//            }
//        }catch (Throwable throwable){
//            throwable.printStackTrace();
//        }
//
//        return "nenhuma nota encontrada";
//    }
//
//    private String teste(String jwt){
//        try {
//            HttpRequest postRequest = HttpRequest.newBuilder()
//                    .uri(new URI("https://uegenio.app.guiliano.com.br/api/login/teste"))
//                    .header("Authorization","Bearer "+jwt)
//                    .GET()
//                    .build();
//
//            HttpClient httpClient = HttpClient.newHttpClient();
//            HttpResponse<String> httpResponse =
//                    httpClient.send(postRequest, HttpResponse.BodyHandlers.ofString());
//
//            return  httpResponse.body();
//        } catch (Throwable error) {
//            throw new RuntimeException(error);
//        }
//    }
//}