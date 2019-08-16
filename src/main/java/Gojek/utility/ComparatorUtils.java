package Gojek.utility;

import Gojek.interfaces.IComparator;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

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
    public String getApiResponse(String apiEndPoint) {
        RequestSpecification request = RestAssured.given();
        Response response = null;
        String apiResponse = null;
        try{
            response = request.get(apiEndPoint);
        }catch (Exception e){
            e.getMessage();
        }
        if(response==null){
            apiResponse = null;
            System.out.println("Request is invalid could not get any response");
        }else {
            apiResponse = response.getBody().prettyPrint();
            System.out.println(apiResponse);
        }
        return apiResponse;
    }


    public void compare(String pathOfFileOne, String pathOfFileTwo) {
        int s;
        ArrayList<String> firstList =  getfileContentInList(pathOfFileOne);
        ArrayList<String> secondList = getfileContentInList(pathOfFileTwo);
        if(firstList.size()==secondList.size()){
            s = secondList.size();
            System.out.println("Comparing all the api requests from both the files");
            System.out.println("No. of requests being compared " + s);
        }else if (firstList.size()>secondList.size()){
            s = secondList.size();
            System.out.println("No. of requests being compared " + s + "since second file has lesser enteries ");
        }else
            s = firstList.size();
            System.out.println("No. of requests being compared " + s + "since first file has lesser enteries ");
        for (int i = 0; i<s; i++){
            String apiFromFirstList = getApiResponse(firstList.get(i));
            String apiFromSecondList = getApiResponse(secondList.get(i));
            if(apiFromFirstList.equalsIgnoreCase(apiFromSecondList)){
                System.out.println(apiFromFirstList + "equals" + apiFromSecondList);
            }else {
                System.out.println(apiFromFirstList + "not equals" + apiFromSecondList);
            }
        }

    }
}
