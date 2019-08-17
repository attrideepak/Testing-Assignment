package Gojek;

import Gojek.utility.ComparatorUtils;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    ComparatorUtils comparatorUtils = new ComparatorUtils();
    @Test
    public void shouldAnswerWithTrue()
    {
        comparatorUtils.compare("/src/main/java/Gojek/textfiles/FirstFile.txt","/src/main/java/Gojek/textfiles/SecondFile.txt");
    }
}
