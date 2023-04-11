package modulos.produto;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

@DisplayName("Testes de API Rest do modulo de Produto")
public class ProdutoTest {
    private String token;

    @BeforeEach //antes de cada teste execute isso aqui
    public void beforeEach(){
        //CONFIGURANDO OS DADOS DA API REST DA LOJINHA (Já sabe onde buscar os dados)
        baseURI = "http://165.227.93.41";
        // port = 8080; quando for necessario, varia de projeto em projeto
        basePath = "lojinha";


        //OBTER O TOKEN DO USUARIO ADMIN
        this.token = given()
                .contentType(ContentType.JSON)
                .body("{\n" +
                        "  \"usuarioLogin\": \"admin\",\n" +
                        "  \"usuarioSenha\": \"admin\"\n" +
                        "}")
                //qual o metodo que vc quer usar
                .when()
                    .post("/v2/login") //caminho da requisição

                .then() // o que a gente quer que aconteca depois de enviar a requisão: pegar o token
                    .extract()
                        .path("data.token");
    }

    @Test
    @DisplayName("Validar que o valor do produto igual a 0.00 não é permitido")
    public void testValidarLimitesZeradoProibidoValorProduto(){
        //TENTAR INSERIR UM PRODUTO COM VALOR DE 0.00 E VALIDAR QUE A MGS DE ERRO FOI EXIBIDA
        //E O STATUS CODE RETORNADO FOI 422

        given()
                .contentType(ContentType.JSON)
                .header("token", this.token)
                .body("{\n" +
                        "  \"produtoNome\": \"PLAY 5\",\n" +
                        "  \"produtoValor\": 0.00,\n" +
                        "  \"produtoCores\": [\n" +
                        "    \"red\"\n" +
                        "  ],\n" +
                        "  \"produtoUrlMock\": \"\",\n" +
                        "  \"componentes\": [\n" +
                        "    {\n" +
                        "      \"componenteNome\": \"Controle\",\n" +
                        "      \"componenteQuantidade\": 1\n" +
                        "    }\n" +
                        "  ]\n" +
                        "}")
                .when()
                    .post("/v2/produtos")
                .then()
                    .assertThat()
                .body("error", equalTo("O valor do produto deve estar entre R$ 0,01 e R$ 7.000,00"))
                .statusCode(422);


    }

    @Test
    @DisplayName("Validar que o valor do produto igual a 7000.01 não é permitido")
    public void testValidarLimiteMaiorQueSeteMilProibidoValorProduto(){
        //TENTAR INSERIR UM PRODUTO COM VALOR DE 7000.01 E VALIDAR QUE A MGS DE ERRO FOI EXIBIDA
        //E O STATUS CODE RETORNADO FOI 422

        given()
                .contentType(ContentType.JSON)
                .header("token", this.token)
                .body("{\n" +
                        "  \"produtoNome\": \"PLAY 4\",\n" +
                        "  \"produtoValor\": 7000.01,\n" +
                        "  \"produtoCores\": [\n" +
                        "    \"red\"\n" +
                        "  ],\n" +
                        "  \"produtoUrlMock\": \"\",\n" +
                        "  \"componentes\": [\n" +
                        "    {\n" +
                        "      \"componenteNome\": \"Controle\",\n" +
                        "      \"componenteQuantidade\": 1\n" +
                        "    }\n" +
                        "  ]\n" +
                        "}")
                .when()
                    .post("/v2/produtos")
                .then()
                    .assertThat()
                .body("error", equalTo("O valor do produto deve estar entre R$ 0,01 e R$ 7.000,00"))
                .statusCode(422);


    }
}
