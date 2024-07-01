package org.estudo.tcc;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;

public class Main {
    public static void main(String[] args) throws URISyntaxException, IOException {

        HttpRequests httpRequests = new HttpRequests("", "");
        if(httpRequests.doLogin()) {
            httpRequests.enterPortalEstudante();
            httpRequests.getClassSchedule();
            httpRequests.getGrade();

            if (Objects.nonNull(httpRequests.getJsonSchedule())) {
                List<Discipline> disciplineList =
                        Converter.jsonToDiscipline(httpRequests.getJsonSchedule());

                if (Objects.nonNull(disciplineList)) {
                    for (Discipline discipline : disciplineList) {
                        System.out.println("Disciplina e horarios: " + discipline.toString() + "\n");
                    }
                }
            }

            if(Objects.nonNull(httpRequests.getJsonGrade())){
                System.out.println(Converter.jsonToGrade((httpRequests.getJsonGrade())));
            }
        }
    }
}