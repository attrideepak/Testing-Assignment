package Gojek;

import Gojek.utility.ComparatorUtils;
import Gojek.utility.JsonUtils;
import org.json.JSONObject;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.ArrayList;

/**
 * Created by Deepakattri on 17/08/19.
 */
public class CompareApiTest extends ComparatorUtils {

    private SoftAssert softAssert = new SoftAssert();

    @DataProvider(name = "testdata")
    public Object[][] testdata(){
        return new Object[][]{
                //{"/src/main/java/Gojek/textfiles/FirstFile.txt","/src/main/java/Gojek/textfiles/SecondFile.txt"},
                {"/src/main/java/Gojek/textfiles/FirstFileWithInvalidRequest","/src/main/java/Gojek/textfiles/SecondFileWithInvalidRequests"}
        };
    }

    @Test(description = "This test read api endpoints from two textfiles and comapre their responses",dataProvider = "testdata")
    public void compare(String pathOfFileOne, String pathOfFileTwo) {
        int s=0;
        ArrayList<String> firstList =  getfileContentInList(pathOfFileOne);
        ArrayList<String> secondList = getfileContentInList(pathOfFileTwo);
        softAssert.assertTrue(firstList.size()==secondList.size(),"No. of API end points in both files are different");
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
            softAssert.assertTrue(firstList.get(i).toLowerCase().startsWith("http"),firstList.get(i) +" is not a valid http/https request");
            softAssert.assertTrue(secondList.get(i).toLowerCase().startsWith("http"),secondList.get(i) +" is not a valid http/https request");
            if(apiResponseFromFirstList==null&&apiResponseFromSecondList==null){
                if(firstList.get(i).equalsIgnoreCase(secondList.get(i))){
                    System.out.println(firstList.get(i) + " equals " +secondList.get(i));
                }else {
                    System.out.println(firstList.get(i) + " not equals " +secondList.get(i));
                }
            }
            else if(JsonUtils.jsonToMap(apiResponseFromFirstList).equals(JsonUtils.jsonToMap(apiResponseFromSecondList))){
                System.out.println(firstList.get(i) + " equals " +secondList.get(i));
            }else {
                System.out.println(firstList.get(i) + " not equals " +secondList.get(i));
            }
        }
        softAssert.assertAll();
    }
}
