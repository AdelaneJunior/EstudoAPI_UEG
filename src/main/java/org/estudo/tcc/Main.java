package org.estudo.tcc;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws URISyntaxException, IOException {

        HttpRequests httpRequests = new HttpRequests("cpf", "senha");
        httpRequests.doLogin();
        httpRequests.enterPortalEstudante();

        List<Discipline> disciplineList =
                Converter.JsonToDiscipline(httpRequests.getClassSchedule());

        for (Discipline discipline : disciplineList) {
            System.out.println("Disciplina e horarios: " + discipline.toString() + "\n");
        }

    }
}