package APIClasses;

import com.aventstack.extentreports.ExtentTest;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static Utils.reportUtils.requestLogger;
import static Utils.reportUtils.responseReporterLogger;

public class DeleteProduct {
    private static final String deleteEndpoint = "/products/";

    private static RequestSpecification buildDeleteProductRequest(String url, String productID, ExtentTest extentTest) {

        String requestEndpoint = url + deleteEndpoint + productID;
        extentTest.info("Building Delete Product Request for ID: " + requestEndpoint);
        RequestSpecification requestSpecification = RestAssured.given();

        requestLogger(requestSpecification,extentTest,requestEndpoint);

        return requestSpecification;
    }

    public static Response deleteProductById(String url, String productId, ExtentTest extentTest) {

        RequestSpecification request = buildDeleteProductRequest(url, productId, extentTest);

        extentTest.info("Sending DELETE Product Request...");
        Response response = request.when().delete(url + deleteEndpoint + productId);

        responseReporterLogger(response, extentTest);

        return response;
    }


}
