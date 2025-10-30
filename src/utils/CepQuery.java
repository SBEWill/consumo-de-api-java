package utils;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;


public class CepQuery {


    public String queryCep(String cep) throws IOException, InterruptedException {


        if (!cep.matches("[0-9]{8}")) {
            throw new IllegalArgumentException("O CEP " + cep + " é inválido (deve ter 8 dígitos numéricos).");
        }

        String cepAPesquisar = "https://viacep.com.br/ws/" + cep + "/json/";

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(cepAPesquisar))
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        String json = response.body();

        if (json.contains("\"erro\":true")) {
            throw new RuntimeException("❌ CEP " + cep + " não encontrado pela ViaCEP.");
        }

        if (response.statusCode() != 200) {
            throw new RuntimeException("❌ Erro HTTP: Código " + response.statusCode() + " na consulta do CEP " + cep);
        }

        return json;
    }
}
