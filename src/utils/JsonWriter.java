package utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import models.Address;

import java.io.FileWriter;
import java.io.IOException;


public class JsonWriter {


    public void writeToFile(Address address) {


        if (address == null || address.cep() == null || address.cep().isBlank() || address.cep().equals("null")) {

            throw new RuntimeException("❌ Erro: Não é possível salvar. Objeto Address inválido ou CEP não encontrado/preenchido.");
        }

        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();

        String fileName = address.cep() + ".json";


        try (FileWriter escrita = new FileWriter(fileName)) {
            escrita.write(gson.toJson(address));


            System.out.println("✅ Dados salvos com sucesso em: " + fileName);

        } catch (IOException e) {

            throw new RuntimeException("Erro ao escrever no arquivo " + fileName + ": " + e.getMessage());
        }
    }
}
