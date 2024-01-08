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
    private String idPessoa;
    private JsonArray jsonSchedule;
    private JsonArray jsonGrade;

    public HttpRequests(String cpf, String senha) {
        this.cpf = cpf;
        this.senha = senha;
        this.httpCookieStore = new BasicCookieStore();
        this.localContext = HttpClientContext.create();
        this.httpClient =
                HttpClients.custom().setDefaultCookieStore(httpCookieStore).build();
    }


    /**
     * Verifica se o cpf e senha estão preenchidos, se sim
     * Realiza um request para realizar login no sistema
     * @return retorna true caso tenha um response 200 e false caso contrario
     * @throws URISyntaxException
     */
    public boolean doLogin() throws URISyntaxException {

        if(!cpf.isEmpty() || !senha.isEmpty()) {
            HttpPost httpPost = new HttpPost("https://www.app.ueg.br/auth/acesso/valida_login");
            List<NameValuePair> nvps = new ArrayList<>();
            nvps.add(new BasicNameValuePair("cpf", getCpf()));
            nvps.add(new BasicNameValuePair("senha", getSenha()));
            httpPost.setEntity(new UrlEncodedFormEntity(nvps));
            System.out.println("Execute request " + httpPost.getMethod() + " " + httpPost.getUri());
            try {
                CloseableHttpResponse httpResponse = httpClient.execute(httpPost, localContext);
                return responseOK(httpResponse);
            } catch (Throwable error) {
                throw new RuntimeException(error);
            }
        }
        return false;
    }

    /**
     * Executa um request para entrar no portal do Estudante, caso o response seja 200 chama um metodo para obter o
     * acu_id do estudante, utilizado em outras requisições
     */
    public void enterPortalEstudante() {
        HttpGet httpGet = new HttpGet("https://www.app.ueg.br/portal_estudante");
        try {
            CloseableHttpResponse httpResponse = httpClient.execute(httpGet, localContext);
            if(responseOK(httpResponse)) {
                getIdPessoa();
            }
        } catch (Throwable error) {
            throw new RuntimeException(error);
        }
    }

    /**
     * Executa um request responsavel pelo retorno do acu_id do estudante e o armazenando na variavel idPessoa
     */
    public void getIdPessoa() {
        HttpGet httpGet = new HttpGet("https://www.app.ueg.br/fenix-back-end/perfil/");

        try {
            CloseableHttpResponse httpResponse = httpClient.execute(httpGet, localContext);
            HttpEntity entity = httpResponse.getEntity();
            if(responseOK(httpResponse)){
                JsonParser jsonParser = new JsonParser();
                idPessoa = Converter.getAcuId(jsonParser.parse(EntityUtils.toString(entity)));
            }

        } catch (Throwable error) {
            error.printStackTrace();
        }

    }

    /**
     * Executa um request que caso tenha um response de 200, preenche a variavel de jsonAulas
     * com os horarios das aulas em formato Json, caso contrario a variavel permamece nulla
     * @throws IOException
     */
    public void getClassSchedule() throws IOException {

        HttpGet httpGet = new HttpGet("https://www.app.ueg.br/fenix-back-end/horario_aula");
        try {
            CloseableHttpResponse httpResponse = httpClient.execute(httpGet, localContext);
            HttpEntity entity = httpResponse.getEntity();
            JsonParser jsonParser = new JsonParser();
            if(responseOK(httpResponse)){
                jsonSchedule = (JsonArray) jsonParser.parse(EntityUtils.toString(entity));
            }
        } catch (Throwable error) {
            throw new RuntimeException(error);
        }

    }


    /**
     * Executa um request para obter as notas das diciplinas do estudante
     * Caso tenha um response 200, preenche a variavel jsonGrade com as notas e diciplinas em formato Json
     * @throws IOException
     */
    public void getGrade() throws IOException {
        HttpGet httpGet = new HttpGet("https://www.app.ueg.br/fenix-back-end/percurso_academico/lista_matriculas_aluno/?acu_id="+idPessoa);
        try {
            CloseableHttpResponse httpResponse = httpClient.execute(httpGet, localContext);
            HttpEntity entity = httpResponse.getEntity();
            if(responseOK(httpResponse)) {
                JsonParser jsonParser = new JsonParser();
                jsonGrade = (JsonArray) jsonParser.parse(EntityUtils.toString(entity));
            }
        } catch (Throwable error) {
            throw new RuntimeException(error);
        }

    }

    /**
     * Verifica o response obtido
     * @param httpResponse
     * @return true caso 200
     */
    private boolean responseOK(CloseableHttpResponse httpResponse) {
        return httpResponse.getCode() == 200;
    }
}