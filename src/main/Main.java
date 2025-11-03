package main;

import models.Address;
import utils.CepQuery;
import utils.JsonConverter;
import utils.JsonWriter;

import java.util.Scanner;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {

        CepQuery cepQuery = new CepQuery();
        JsonConverter jsonConverter = new JsonConverter();
        JsonWriter jsonWriter = new JsonWriter();

        Scanner scanner = new Scanner(System.in);
        String cep ;
        boolean sucesso = false;
        while (!sucesso) {
            System.out.println("Digite o CEP para consulta (somente 8 dígitos):");
            cep = scanner.nextLine().trim();

            try {
                String jsonRetornado = cepQuery.queryCep(cep);
                Address objetoEndereco = jsonConverter.toObject(jsonRetornado, Address.class);
                System.out.println("\n✅ Endereço Encontrado:");
                System.out.println(objetoEndereco);
                jsonWriter.writeToFile(objetoEndereco);
                sucesso = true;
            } catch (IllegalArgumentException e) {
                System.err.println("\n❌ Erro de Validação: " + e.getMessage());

            } catch (RuntimeException e) {
                System.err.println("\n❌ Erro no Serviço de Consulta: " + e.getMessage());

            } catch (IOException e) {
                System.err.println("\n❌ Erro de I/O (Rede ou Arquivo): " + e.getMessage());

            } catch (InterruptedException e) {
                System.err.println("\n❌ Operação de rede interrompida.");

            }
        }
        scanner.close();
        System.out.println("\nPrograma Finalizado.");
    }
}
