package Gojek.interfaces;

import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Deepakattri on 15/08/19.
 */
public interface IComparator {
    public void compare(String one, String two);

    public ArrayList<String> getfileContentInList(String path);

    public JSONObject getApiResponse(String apiEndPoint);
}
