package Gojek.utility;

import Gojek.interfaces.IComparator;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Deepakattri on 15/08/19.
 */
public class ComparatorUtils implements IComparator {

    @Override
    public ArrayList<String> getfileContentInList(String path) {
        BufferedReader reader = null;
        ArrayList<String> listofLines = new ArrayList<>();
        String line;
        try {
            reader = new BufferedReader(new FileReader(new File(System.getProperty("user.dir")) + path));
            line = reader.readLine();
            while (line != null) {
                listofLines.add(line);
                line = reader.readLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return listofLines;
    }

    @Override
    public JSONObject getApiResponse(String apiEndPoint) {
        RequestSpecification request = RestAssured.given();
        Response response = null;
        JSONObject responseJson;
        try{
            response = request.get(apiEndPoint);
        }catch (Exception e){
            e.getMessage();
        }
        if(response==null){
            responseJson = null;
            System.out.println("Request \"" + apiEndPoint + "\" is invalid could not get any response");
        }else {
            System.out.println("Response for "+apiEndPoint+ " is :");
            responseJson = new JSONObject(response.getBody().prettyPrint());
        }
        return responseJson;
    }
}
