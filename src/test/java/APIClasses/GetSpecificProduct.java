package APIClasses;

import com.aventstack.extentreports.ExtentTest;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static Utils.reportUtils.requestLogger;
import static Utils.reportUtils.responseReporterLogger;

public class GetSpecificProduct {

    private static final String getProductEndpoint = "/products/";


    private static RequestSpecification buildGetSpecificProductRequest(String url, String productID, ExtentTest extentTest) {

        String requestEndpoint = url + getProductEndpoint + productID;
        extentTest.info("Building Get Specific Product Request for ID: " + requestEndpoint);
        RequestSpecification requestSpecification = RestAssured.given();

        requestLogger(requestSpecification,extentTest,requestEndpoint);

        return requestSpecification;
    }

    public static Response getSpecificProductById(String url, String productId, ExtentTest extentTest) {

        RequestSpecification request = buildGetSpecificProductRequest(url, productId, extentTest);

        extentTest.info("Sending Get Specific Product Request...");
        Response response = request.when().get(url + getProductEndpoint + productId);

        responseReporterLogger(response, extentTest);

        return response;
    }

    public static String getSpecificProductTitle(Response response){

        String productTitle =  response.jsonPath().getString("title");
        return productTitle;
    }


}
