package Gojek;


import org.testng.annotations.Test;

/**
 * Created by Deepakattri on 15/08/19.
 */
public class TestClass {
    FileReaderUtility fileReaderUtility = new FileReaderUtility();


    @Test
    public void testFile(){
        System.out.println(fileReaderUtility.fileContentInList("/src/main/java/Gojek/textfiles/TestText.txt"));
    }
}
