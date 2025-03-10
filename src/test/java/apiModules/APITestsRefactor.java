package apiModules;

import org.testng.Assert;
import org.testng.annotations.Test;
import  io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;



public class APITestsRefactor {

    @Test
    public void validarRetornoStatusCode(){

        Response response = get("https://reqres.in/api/users?page=2");
        int statusCode = response.getStatusCode();

        Assert.assertEquals(statusCode,200);
    }

    @Test
    public void validarRetornoStatusCodeRefactor(){
        baseURI = "https://reqres.in/api";
        given().
                get("/users?page=2").
                then().
                statusCode(200).
                body("data[1].id",equalTo(8));

    }

    @Test
    public void validarPrimeiroNome(){
        baseURI = "https://reqres.in/api";
        given().
                get("/users?page=2").
                then().
                statusCode(200).
                body("data[2].first_name",equalTo("Tobias")).
                body("data.first_name",hasItems("Byron","Tobias","Rachel"));

    }
    @Test
    public void validarResource(){
        baseURI = "https://reqres.in/api";
        given().
                get("/unknown").
                then().
                statusCode(200).
                body("data[1].name",equalTo("fuchsia rose")).
                body("data[2].name",equalTo("true red")).
                body("data[3].name",equalTo("aqua sky"));

    }

    @Test
    public void validarNomesResource(){
        baseURI = "https://reqres.in/api";
        Response response = given()
               // .log().all() // Loga a requisição
                .when()
                .get("/unknown");

        // Imprime apenas uma parte específica da resposta
        String nomeSegundo = response.jsonPath().getString("data[1].name");
        String nomeTerceiro = response.jsonPath().getString("data[2].name");
        String nomeQuarto = response.jsonPath().getString("data[3].name");

        System.out.println("Nome do segundo item: " + nomeSegundo);
        System.out.println("Nome do terceiro item: " + nomeTerceiro);
        System.out.println("Nome do quarto item: " + nomeQuarto);

        // Valida o status e os nomes
        response.then()
                .statusCode(200)
                .body("data[1].name", equalTo("fuchsia rose"))
                .body("data[2].name", equalTo("true red"))
                .body("data[3].name", equalTo("aqua sky"));

    }
}

