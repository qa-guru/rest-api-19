package tests;

import models.lombok.LoginBodyLombokModel;
import models.lombok.LoginResponseLombokModel;
import models.pojo.LoginBodyPojoModel;
import models.pojo.LoginResponsePojoModel;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReqresInExtendedTests {

    @Test
    void successfulLoginBadPracticeTest() {
        String requestBody = "{ \"email\": \"eve.holt@reqres.in\", \"password\": \"cityslicka\" }"; // BAD PRACTICE

        given()
                .log().uri()
                .log().body()
                .contentType(JSON)
                .body(requestBody)
                .when()
                .post("https://reqres.in/api/login")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("token", is("QpwL5tke4Pnpja7X4"));
    }

    @Test
    void successfulLoginWithPojoModelsTest() {
        LoginBodyPojoModel requestBody = new LoginBodyPojoModel();
        requestBody.setEmail("eve.holt@reqres.in");
        requestBody.setPassword("cityslicka");

        LoginResponsePojoModel loginResponse = given()
                .log().uri()
                .log().body()
                .contentType(JSON)
                .body(requestBody)
                .when()
                .post("https://reqres.in/api/login")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .extract().as(LoginResponsePojoModel.class);

        assertEquals("QpwL5tke4Pnpja7X4", loginResponse.getToken());
    }

    @Test
    void successfulLoginWithLombokModelsTest() {
        LoginBodyLombokModel requestBody = new LoginBodyLombokModel();
        requestBody.setEmail("eve.holt@reqres.in");
        requestBody.setPassword("cityslicka");

        LoginResponseLombokModel loginResponse = given()
                .log().uri()
                .log().body()
                .contentType(JSON)
                .body(requestBody)
                .when()
                .post("https://reqres.in/api/login")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .extract().as(LoginResponseLombokModel.class);

        assertEquals("QpwL5tke4Pnpja7X4", loginResponse.getToken());
    }

}
