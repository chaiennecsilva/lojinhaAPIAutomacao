## Lojinha API Automação
Esse é um repositório que contém a automação de alguns testes API REST de um software denominado Lojinha. Os sub-tópicos abaixo descrevem algumas decisões tomadas na estruturação do projeto.

## Tecnologias Utilizadas

- Java
  https://www.oracle.com/br/java/technologies/javase/jdk11-archive-downloads.html
- JUnit
  https://mvnrepository.com/artifact/org.junit.jupiter/junit-jupiter-engine/5.7.1
- Maven
  https://maven.apache.org/

## Testes Automatizados
Testes para validar as partições de equivalência relacionadas ao valor do produto na Lojinha, que estão vinculados diretamente a RN que diz que o valor do produto deve estar entre R$0,01 e R$7.000,00.

## Notas Gerais
- Sempre utilizamos a anotação Before Each para capturar o token que será utilizado posteriormente nos métodos de testes.
- Armazenamos os dados que são enviados para a API através do uso de classes pojo.
- Criamos dados iniciais através do uso de classe Data Factory, para facilitar a criação e controle dos mesmos.
- Nesse projeto fazemos uso do JUnit5, o que nós da a possibilidade de usar a anotação DisplayName para dar descrições em português para nossos testes. 
- Refatoração do código segundo para melhorias.
