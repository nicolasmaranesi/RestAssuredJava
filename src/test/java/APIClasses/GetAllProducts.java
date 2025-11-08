package APIClasses;

import com.aventstack.extentreports.ExtentTest;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.List;

import static Utils.reportUtils.requestLogger;
import static Utils.reportUtils.responseReporterLogger;

public class GetAllProducts {

    private static final String getProductsEndpoint = "/products";


    private static RequestSpecification buildGetAllProductsRequest(String url, ExtentTest extentTest) {

        String requestEndpoint = url + getProductsEndpoint;
        extentTest.info("Building get all products Request. Endpoint: " + requestEndpoint);


        RequestSpecification requestSpecification = RestAssured.given();

        requestLogger(requestSpecification,extentTest,requestEndpoint);

        return requestSpecification;
    }




    public static Response getAllProducts(String url, ExtentTest extentTest) {

        RequestSpecification request = buildGetAllProductsRequest(url, extentTest);

        extentTest.info("Sending get all products Request...");

        Response response = request.when().get(url + getProductsEndpoint);

        responseReporterLogger(response, extentTest);

        return response;
    }


    public static String getProductTitle(Response response,int number){

        number = number - 1;
        String firstProductTitle = response.jsonPath().getString("[" +  number + "].title");
        return firstProductTitle;

    }

    public static int responseObjectCounter(Response response){

        List<?> list = response.jsonPath().getList("$");
        return list.size();
    }




}
