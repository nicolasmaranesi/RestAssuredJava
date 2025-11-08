package APIClasses;

import com.aventstack.extentreports.ExtentTest;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static Utils.reportUtils.requestLogger;
import static Utils.reportUtils.responseReporterLogger;

public class PostNewProduct {

    private static final String createProductEndpoint = "/products";

    private static RequestSpecification buildCreateProductRequest(String url, String requestBody, ExtentTest extentTest) {

        String requestEndpoint = url + createProductEndpoint;
        extentTest.info("Building POST Create Product Request. Endpoint: " + requestEndpoint);


        RequestSpecification requestSpecification = RestAssured.given().contentType("application/json").body(requestBody);

        requestLogger(requestSpecification,extentTest,requestEndpoint);

        return requestSpecification;
    }




    public static Response createNewProduct(String url, String title, double  price, String description, String image, String category,ExtentTest extentTest) {

        String requestBody = "{"
                + "\"title\": \"" + title + "\","
                + "\"price\": " + price + ","
                + "\"description\": \"" + description + "\","
                + "\"image\": \"" + image + "\","
                + "\"category\": \"" + category + "\""
                + "}";

        RequestSpecification request = buildCreateProductRequest(url, requestBody, extentTest);

        extentTest.info("Request Body: " + requestBody);

        extentTest.info("Sending POST Create Product Request...");

        Response response = request.when().post(url + createProductEndpoint);

        responseReporterLogger(response, extentTest);

        return response;
    }



}
