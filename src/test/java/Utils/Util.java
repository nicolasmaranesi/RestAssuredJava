package Utils;

import com.aventstack.extentreports.ExtentTest;
import org.testng.Assert;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import org.apache.commons.codec.binary.Base64;
public class Util {




    public static void validateRentExpire(String beginingDate, String endingDate, String expectedTime){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        LocalDateTime startDate = LocalDateTime.parse(beginingDate,formatter);
        LocalDateTime endDate = LocalDateTime.parse(endingDate,formatter);
        Long hourDifference = java.time.Duration.between(startDate,endDate).toHours();
        String valueHourDifference =  Long.toString(hourDifference);
        Assert.assertEquals(valueHourDifference,expectedTime);
    }


    public static void validateSubscriptionExpire(String beginingDate, String endingDate, String expectedTime){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        LocalDateTime startDate = LocalDateTime.parse(beginingDate,formatter);
        LocalDateTime endDate = LocalDateTime.parse(endingDate,formatter);
        long monthsDifference = ChronoUnit.MONTHS.between(startDate, endDate);
        String valueMonthDifference =  Long.toString(monthsDifference);
        Assert.assertEquals(valueMonthDifference,expectedTime);
    }

    public static String getCurrentDate(){
        LocalDateTime currentDateTime = LocalDateTime.now();
        String pattern = "dd/MM/yyyy HH:mm:ss";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        String originalTimestamp = currentDateTime.format(formatter);
        String preFormattedTimestamp = originalTimestamp.replace("/","_");
        String formattedTimestamp = preFormattedTimestamp.replace(":","_");
        return formattedTimestamp;
    }



    public static String decodedJWT(String token, ExtentTest extentTest) {
        String[] splitString = token.split("\\.");
        String base64EncoderHeder = splitString[0];
        String base64EncoderBody = splitString[1];
        String base64EncoderSignature = splitString[2];
        Base64 base64Url = new Base64(true);
        //Header
        String tokenHeader = new String(base64Url.decode(base64EncoderHeder));
        extentTest.info("Token header:  " +tokenHeader );
        //Body
        String tokenBody = new String(base64Url.decode(base64EncoderBody));
        extentTest.info("Token body:  " +tokenBody );
        //Signature
        String tokenSignature = new String(base64Url.decode(base64EncoderSignature));
        extentTest.info("Token signature:  " +tokenSignature );
        return tokenBody;
    }






}
