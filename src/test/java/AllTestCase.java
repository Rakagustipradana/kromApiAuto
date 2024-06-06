import io.restassured.RestAssured;
import io.restassured.http.Headers;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.Test;

public class AllTestCase {

    @Test
    public void userWantGetStatusAvailablePet() { //positive
        RestAssured.baseURI = "https://petstore.swagger.io";

        RequestSpecification httpRequest = RestAssured.given();

        Response response = httpRequest.request(Method.GET, "v2/pet/5");

        int statusCode = response.getStatusCode();
        System.out.println("Response status code is " + statusCode);

        Headers header = response.getHeaders();
        System.out.println("Headers of the response body are " + header);

        ResponseBody body = response.getBody();
        body.prettyPrint();
        System.out.println("Response Body is " + body.asString());

    }

    @Test
    public void userWantGetPetFindByStatusIsSold() { //positive
        RestAssured.baseURI = "https://petstore.swagger.io";

        RequestSpecification httpRequest = RestAssured.given();

        Response response = httpRequest.request(Method.GET, "v2/pet/findByStatus?status=sold");

        int statusCode = response.getStatusCode();
        System.out.println("Response status code is " + statusCode);

        Headers header = response.getHeaders();
        System.out.println("Headers of the response body are " + header);

        ResponseBody body = response.getBody();
        body.prettyPrint();
        System.out.println("Response Body is " + body.asString());

    }

    @Test
    public void userWantToDoPostNewUser() { //positive
        RestAssured.baseURI = "https://petstore.swagger.io";

        String request = "{\n" +
                "  \"id\": 0,\n" +
                "  \"category\": {\n" +
                "    \"id\": 0,\n" +
                "    \"name\": \"new user\"\n" +
                "  },\n" +
                "  \"name\": \"Budiman\",\n" +
                "  \"photoUrls\": [\n" +
                "    \"string\"\n" +
                "  ],\n" +
                "  \"tags\": [\n" +
                "    {\n" +
                "      \"id\": 0,\n" +
                "      \"name\": \"testing\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"status\": \"available\"\n" +
                "}";

        RequestSpecification httpRequest = RestAssured.given();

        httpRequest.header("Content-Type", "application/json");

        Response response = httpRequest.body(request).post("/v2/user");

        int statusCode = response.getStatusCode();
        System.out.println("Response status code is " + statusCode);

    }

    @Test
    public void userWantToPutPetName() { //positive
        RestAssured.baseURI = "https://petstore.swagger.io";

        String request = "\n" +
                "  \"id\": 1,\n" +
                "  \"category\": {\n" +
                "    \"id\": 0,\n" +
                "    \"name\": \"string\"\n" +
                "  },\n" +
                "  \"name\": \"blackky\",\n" +
                "  \"photoUrls\": [\n" +
                "    \"string\"\n" +
                "  ],\n" +
                "  \"tags\": [\n" +
                "    {\n" +
                "      \"id\": 0,\n" +
                "      \"name\": \"string\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"status\": \"available\"\n" +
                "}";

        RequestSpecification httpRequest = RestAssured.given();

        httpRequest.header("Content-Type", "application/json").header("accept", "application/json");

        Response response = httpRequest.body(request).put("/v2/pet");

        System.out.println(response);
        System.out.println("Response status code is " + response.getStatusCode());

    }

    @Test
    public void userWantToDeletePetById() { //positive

        RestAssured.baseURI = "https://petstore.swagger.io";

        RequestSpecification httpRequest = RestAssured.given();

        httpRequest.header("Content-Type", "application/json").header("accept", "application/json");

        Response response = httpRequest.delete("/v2/pet/1");

        System.out.println(response);
        System.out.println("Response status code is " + response.getStatusCode());

    }

//NEGATIVE\\
    @Test
    public void userWantGetNotFoundPet() { //negative
        RestAssured.baseURI = "https://petstore.swagger.io";

        RequestSpecification httpRequest = RestAssured.given();

        Response response = httpRequest.request(Method.GET, "v2/pet/600"); //input id pet not available on list

        int statusCode = response.getStatusCode();
        System.out.println("Response status code is " + statusCode);

        Headers header = response.getHeaders();
        System.out.println("Headers of the response body are " + header);

        ResponseBody body = response.getBody();
        body.prettyPrint();
        System.out.println("Response Body is " + body.asString());

    }

    @Test
    public void userWantToPutPetNameWithInvalidInput() { //negative
        RestAssured.baseURI = "https://petstore.swagger.io";

        String request = "\n" +
                "  \"id\": 1,\n" +
                "  \"category\": {\n" +
                "    \"id\": 0,\n" +
                "    \"name\": \"string\"\n" +
                "  },\n" +
                "  \"name\": 00001222,\n" +  //name input with integer
                "  \"photoUrls\": [\n" +
                "    \"string\"\n" +
                "  ],\n" +
                "  \"tags\": [\n" +
                "    {\n" +
                "      \"id\": 0,\n" +
                "      \"name\": \"string\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"status\": 1234567\n" + //status input with integer
                "}";

        RequestSpecification httpRequest = RestAssured.given();

        httpRequest.header("Content-Type", "application/json").header("accept", "application/json");

        Response response = httpRequest.body(request).put("/v2/pet");

        System.out.println(response);
        System.out.println("Response status code is " + response.getStatusCode());

    }

    @Test
    public void userWantToDeletePetByInvalidId() { //negative

        RestAssured.baseURI = "https://petstore.swagger.io";

        RequestSpecification httpRequest = RestAssured.given();

        httpRequest.header("Content-Type", "application/json").header("accept", "application/json");

        Response response = httpRequest.delete("/v2/pet/900"); //pet id not available on list

        System.out.println(response);
        System.out.println("Response status code is " + response.getStatusCode());

    }

    @Test
    public void userWantGetPetWithInvalidInput() { //negative
        RestAssured.baseURI = "https://petstore.swagger.io";

        RequestSpecification httpRequest = RestAssured.given();

        Response response = httpRequest.request(Method.GET, "v2/pet/abc"); //try use incorrect id pet

        int statusCode = response.getStatusCode();
        System.out.println("Response status code is " + statusCode);

        Headers header = response.getHeaders();
        System.out.println("Headers of the response body are " + header);

        ResponseBody body = response.getBody();
        body.prettyPrint();
        System.out.println("Response Body is " + body.asString());

    }

    @Test
    public void userWantGetUserNotFound() { //negative
        RestAssured.baseURI = "https://petstore.swagger.io";

        RequestSpecification httpRequest = RestAssured.given();

        Response response = httpRequest.request(Method.GET, "v2/user/users1");

        int statusCode = response.getStatusCode();
        System.out.println("Response status code is " + statusCode);

        Headers header = response.getHeaders();
        System.out.println("Headers of the response body are " + header);

        ResponseBody body = response.getBody();
        body.prettyPrint();
        System.out.println("Response Body is " + body.asString());

    }
}
