🔎 Sistema de Consulta de CEP (ViaCEP - Java)

Este projeto é uma aplicação de console (CLI) desenvolvida em Java para demonstrar a arquitetura em camadas, o consumo de APIs REST (ViaCEP) e a persistência de dados em formato JSON.

O foco principal foi na separação de responsabilidades (POO) e na robustez do tratamento de erros em cada etapa do fluxo.

🎯 Objetivos do Projeto

Validação Rígida de Input: Garantir que o CEP consultado tenha exatamente 8 dígitos numéricos.

Consumo Eficiente: Utilizar o java.net.http.HttpClient para requisições de rede.

Mapeamento de Dados: Utilizar o Gson para mapear a resposta JSON da API para objetos Java (Deserialização) e serializar o objeto final para um novo arquivo JSON (Serialização).

Persistência: Salvar o endereço consultado em um arquivo JSON nomeado pelo próprio CEP.

Arquitetura em Camadas: Isolar a lógica de negócio, persistência e rede em classes específicas.

⚙️ Tecnologias Utilizadas

Linguagem: Java (JDK 17+)

HTTP Client: java.net.http.HttpClient

JSON: Gson (Biblioteca do Google)

Padrão: Programação Orientada a Objetos (POO)

🏗️ Arquitetura e Fluxo de Dados

O projeto segue o princípio de separação de responsabilidades, onde a classe Main atua como o controlador de fluxo e cada utilitário tem uma única função:

Classe

Responsabilidade Principal

Função no Fluxo

Main

Controle e Interface

Gerencia o loop de repetição, a validação inicial do usuário e o tratamento de todas as exceções.

CepQuery

Serviço de Rede

Responsável por validar a entrada de 8 dígitos e lançar exceções para erros HTTP ou erros de API ("erro":true).

JsonConverter

Utilidade de Mapeamento

Deserializa a string JSON bruta para o objeto utils.models.Address (e vice-versa).

JsonWriter

Persistência

Responsável por serializar o objeto utils.models.Address e escrevê-lo com segurança no arquivo, barrando objetos null.

utils.models.Address

Modelo de Dados

O record que representa o objeto final (Endereço) com os dados limpos.

Fluxo de Execução

Input: Main inicia um loop while e solicita o CEP.

Validação Inicial: O CEP é validado na Main (8 dígitos).

Consulta: Main chama CepQuery.queryCep(cep).

Resposta: CepQuery retorna a String JSON (caminho de sucesso).

Conversão: Main chama JsonConverter.fromJson(json, utils.models.Address.class), obtendo o objeto address.

Persistência Segura: Main chama JsonWriter.writeToFile(address). Se o address for nulo ou inválido (ex: cep=null), o JsonWriter lança uma exceção para o Main e impede o salvamento.

🚨 Tratamento de Erros e Robustez

O projeto garante a robustez através do uso do try-catch em camadas:

Validação de Input: O loop while impede que a requisição de rede seja feita com menos de 8 dígitos (erro tratado na Main).

Erros do Serviço (ViaCEP): A classe CepQuery lança RuntimeException se o código HTTP falhar ou se a API retornar "erro":true". Essa exceção é capturada na Main, que informa o usuário e repete o loop.

Persistência Segura: O JsonWriter utiliza try-with-resources para garantir que o arquivo seja fechado corretamente e inclui uma validação que impede o salvamento de objetos incompletos/nulos.

👨‍💻 Autor

Item

Contato

Nome

Willian Diniz Menezes

LinkedIn

https://www.linkedin.com/in/willian-diniz-2360b74b/

GitHub

https://github.com/SBEWill
