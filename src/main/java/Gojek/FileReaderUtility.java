package Gojek;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Deepakattri on 15/08/19.
 */
public class FileReaderUtility {

    public List<String> fileContentInList(String path){
        BufferedReader reader;
        List<String> listofLines = new ArrayList<>();
        String line = null;
        try {
            reader = new BufferedReader(new FileReader(new File(System.getProperty("user.dir"))+path));
            line = reader.readLine();
            while(line != null){
                listofLines.add(line);
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return listofLines;
    }

    }

