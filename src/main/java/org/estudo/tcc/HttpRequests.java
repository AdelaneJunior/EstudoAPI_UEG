package org.estudo.tcc;

import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.cookie.BasicCookieStore;
import org.apache.hc.client5.http.cookie.Cookie;
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

public class HttpRequests {

    public static void main(String[] args) throws Exception {

        CookieStore httpCookieStore = new BasicCookieStore();
        HttpClientContext localContext = HttpClientContext.create();
        CloseableHttpClient httpClient = HttpClients.custom().setDefaultCookieStore(httpCookieStore).build();

        doLogin(httpClient, localContext);

        final List<Cookie> cookies = httpCookieStore.getCookies();

        for (Cookie cookie : cookies) {
            System.out.println("Local cookie: " + cookie.getName() + " " + cookie.getValue());
        }

        enterPortalEstudante(httpClient, localContext);
        getHorarioAulas(httpClient, localContext);

    }

    public static void doLogin(CloseableHttpClient client, HttpClientContext localContext) throws URISyntaxException {

        HttpPost httpPost = new HttpPost("https://www.app.ueg.br/auth/acesso/valida_login");
        List<NameValuePair> nvps = new ArrayList<>();
        nvps.add(new BasicNameValuePair("cpf", "Login"));
        nvps.add(new BasicNameValuePair("senha", "Senha"));
        httpPost.setEntity(new UrlEncodedFormEntity(nvps));
        System.out.println("Execute request " + httpPost.getMethod() + " " + httpPost.getUri());
        try {
            CloseableHttpResponse httpResponse = client.execute(httpPost, localContext);
            HttpEntity entity = httpResponse.getEntity();
            System.out.println(httpResponse);
        } catch (Throwable error) {
            throw new RuntimeException(error);
        }

    }

    public static void enterPortalEstudante(CloseableHttpClient client, HttpClientContext localContext) {
        HttpGet httpGet = new HttpGet("https://www.app.ueg.br/portal_estudante");
        try {
            CloseableHttpResponse httpResponse = client.execute(httpGet, localContext);
        } catch (Throwable error) {
            throw new RuntimeException(error);
        }
    }

    public static void getHorarioAulas(CloseableHttpClient client, HttpClientContext localContext) throws IOException {

        HttpGet httpGet = new HttpGet("https://www.app.ueg.br/fenix-back-end/horario_aula");
        try {
            CloseableHttpResponse httpResponse = client.execute(httpGet, localContext);
            HttpEntity entity = httpResponse.getEntity();
            System.out.println(EntityUtils.toString(entity));
        }catch (Throwable error) {
            throw new RuntimeException(error);
        }

    }
}