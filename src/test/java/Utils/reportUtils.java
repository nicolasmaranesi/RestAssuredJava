package Utils;


import com.aventstack.extentreports.ExtentTest;
import io.restassured.response.Response;
import io.restassured.specification.QueryableRequestSpecification;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.SpecificationQuerier;

import java.net.URI;


public class reportUtils {


    public static void requestLogger(RequestSpecification requestSpecification, ExtentTest extentTest, String url) {
        QueryableRequestSpecification queryableRequestSpecification = SpecificationQuerier.query(requestSpecification);
        String URIString = queryableRequestSpecification.getURI();
        String newEndpoint = URIString.replace("http://localhost:8080",url);

        extentTest.info("URL:  " +newEndpoint );
        extentTest.info("Request Method: " + queryableRequestSpecification.getMethod());
        extentTest.info("Request Headers: " + queryableRequestSpecification.getHeaders());
        extentTest.info("Request Params: " + queryableRequestSpecification.getPathParams());
        extentTest.info("Request Body: " + queryableRequestSpecification.getBody());
    }

    public static void responseReporterLogger(Response response, ExtentTest extentTest){

        extentTest.info("Received Register Response");
        extentTest.info("Response Headers: " + response.getHeaders());
        extentTest.info("<pre>" + "Response Body: " + response.getBody().prettyPrint().replace("\t", "&nbsp;").replace("    ", "&nbsp;") + "</pre>");
    }


}
