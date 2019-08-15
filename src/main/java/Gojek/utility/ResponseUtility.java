package Gojek.utility;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

/**
 * Created by Deepakattri on 15/08/19.
 */
public class ResponseUtility {

    public String getApiResponse(String apiEndPoint) {
            RequestSpecification request = RestAssured.given();
            Response response = request.get(apiEndPoint);
            String apiResponse = response.getBody().prettyPrint();
            return apiResponse;
    }
}
