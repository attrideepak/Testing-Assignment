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
    JsonUtils jsonUtils = new JsonUtils();
    @Override
    public ArrayList<String> getfileContentInList(String path) {
        BufferedReader reader = null;
        ArrayList<String> listofLines = new ArrayList<>();
        String line = null;
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


    public void compare(String pathOfFileOne, String pathOfFileTwo) {
        int s=0;
        ArrayList<String> firstList =  getfileContentInList(pathOfFileOne);
        ArrayList<String> secondList = getfileContentInList(pathOfFileTwo);
        if(firstList.size()==secondList.size()){
            s = secondList.size();
            System.out.println("Comparing all the api requests from both the files. No. of requests being compared is "+s);
        }else if (firstList.size()>secondList.size()){
            s = secondList.size();
            System.out.println("No. of requests being compared " + s + " since second file has lesser enteries ");
        }else if(secondList.size()>firstList.size()) {
            s = firstList.size();
            System.out.println("No. of requests being compared " + s + " since first file has lesser enteries ");
        }
        for (int i = 0; i<s; i++){
            JSONObject apiResponseFromFirstList = getApiResponse(firstList.get(i));
            JSONObject apiResponseFromSecondList = getApiResponse(secondList.get(i));
            if(JsonUtils.jsonToMap(apiResponseFromFirstList) == JsonUtils.jsonToMap(apiResponseFromSecondList)){
                System.out.println(firstList.get(i) + " equals " +secondList.get(i));
            }else {
                System.out.println(firstList.get(i) + " not equals " +secondList.get(i));
            }


        }

    }
}
