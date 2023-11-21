package org.estudo.tcc;

import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import lombok.Getter;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.cookie.BasicCookieStore;
import org.apache.hc.client5.http.cookie.CookieStore;
import org.apache.hc.client5.http.entity.UrlEncodedFormEntity;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.client5.http.protocol.HttpClientContext;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.NameValuePair;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.message.BasicNameValuePair;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

@Getter
public class HttpRequests {

    private CookieStore httpCookieStore;
    private HttpClientContext localContext;
    private CloseableHttpClient httpClient;
    private String cpf;
    private String senha;

    public HttpRequests(String cpf, String senha) {
        this.cpf = cpf;
        this.senha = senha;
        this.httpCookieStore = new BasicCookieStore();
        this.localContext = HttpClientContext.create();
        this.httpClient =
                HttpClients.custom().setDefaultCookieStore(httpCookieStore).build();
    }

    public void doLogin() throws URISyntaxException {

        HttpPost httpPost = new HttpPost("https://www.app.ueg.br/auth/acesso/valida_login");
        List<NameValuePair> nvps = new ArrayList<>();
        nvps.add(new BasicNameValuePair("cpf", getCpf()));
        nvps.add(new BasicNameValuePair("senha", getSenha()));
        httpPost.setEntity(new UrlEncodedFormEntity(nvps));
        System.out.println("Execute request " + httpPost.getMethod() + " " + httpPost.getUri());
        try {
            CloseableHttpResponse httpResponse = httpClient.execute(httpPost, localContext);
            HttpEntity entity = httpResponse.getEntity();
            System.out.println(httpResponse);
        } catch (Throwable error) {
            throw new RuntimeException(error);
        }

    }

    public void enterPortalEstudante() {
        HttpGet httpGet = new HttpGet("https://www.app.ueg.br/portal_estudante");
        try {
            CloseableHttpResponse httpResponse = httpClient.execute(httpGet, localContext);
        } catch (Throwable error) {
            throw new RuntimeException(error);
        }
    }

    public JsonArray getClassSchedule() throws IOException {

        HttpGet httpGet = new HttpGet("https://www.app.ueg.br/fenix-back-end/horario_aula");
        try {
            CloseableHttpResponse httpResponse = httpClient.execute(httpGet, localContext);
            HttpEntity entity = httpResponse.getEntity();
            JsonParser jsonParser = new JsonParser();
            return (JsonArray) jsonParser.parse(EntityUtils.toString(entity));
        } catch (Throwable error) {
            throw new RuntimeException(error);
        }

    }
}