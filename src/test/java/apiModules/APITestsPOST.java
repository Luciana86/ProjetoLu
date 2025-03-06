package apiModules;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;

public class APITestsPOST {

    @Test
    public void validarPOST(){
        Map<String, Object> map = new HashMap<String,Object>();

        map.put("name","João");
        map.put("job","Teacher");

        System.out.println(map);

        JSONObject request = new JSONObject(map);

        System.out.println(request.toJSONString());

    }

    @Test
    public void validarPOSTRefactor(){
        Map<String, Object> map = new HashMap<String,Object>();

        JSONObject request = new JSONObject(map);

        request.put("name","João");
        request.put("job","Software Engineer");

        baseURI = "https://reqres.in/api";
        given().
                header("Content-Type", "application/json").
                contentType(ContentType.JSON).
                body(request.toJSONString()).
                when().
                post("users").
                then().
                statusCode(201).
                log().all();
    }
}

