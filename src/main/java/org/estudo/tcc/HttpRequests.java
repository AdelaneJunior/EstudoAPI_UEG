package org.estudo.tcc;

import org.apache.hc.client5.http.fluent.Request;
import org.apache.hc.client5.http.fluent.Response;

import java.io.IOException;

/**
 * @author https://www.wdbyte.com
 */
public class HttpClient5Get {

    public static void main(String[] args) {
        String result = get("https://www.app.ueg.br/auth/usuarios/sistemas");
        System.out.println(result);
    }

    public static String get(String url) {
        String result = null;
        try {
            Response response = Request.get(url).execute();
            result = response.returnContent().asString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

}