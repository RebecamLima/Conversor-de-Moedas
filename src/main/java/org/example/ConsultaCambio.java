package org.example;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConsultaCambio {

    private static final String API_KEY = "2c0188cc9e23b9daf8c12534";
    private static final String API_BASE_URL = "https://v6.exchangerate-api.com/v6/";

    public String buscaTaxa(String moedaBase, String moedaAlvo) {

        String url = String.format(
                API_BASE_URL + "%s/pair/%s/%s",
                API_KEY, moedaBase, moedaAlvo
        );

        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();

        try {
            HttpResponse<String> response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                return response.body(); // JSON
            } else {
                throw new RuntimeException("Erro ao consultar a API. Status: " + response.statusCode());
            }

        } catch (IOException | InterruptedException e) {
            // Captura erros de rede
            throw new RuntimeException("Falha de comunicação com a API: " + e.getMessage(), e);
        }
    }
}