package Gojek.utility;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Deepakattri on 15/08/19.
 */
public class FileReaderUtility {

    public List<String> fileContentInList(String path) {
        BufferedReader reader = null;
        List<String> listofLines = new ArrayList<>();
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


}

