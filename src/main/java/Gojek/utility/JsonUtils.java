package Gojek.utility;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.*;

/**
 * Created by Aeepakattri on 16/08/19.
 */
public class JsonUtils {

    public static Map<String, Object> jsonToMap(JSONObject object) throws JSONException {
        Map<String, Object> map = new HashMap<>();
        Iterator<String>jsonKeys = object.keys();
        while(jsonKeys.hasNext()) {
            String key = jsonKeys.next();
            Object value = object.get(key);

            if(value instanceof JSONArray) {
                value = jsonToList((JSONArray) value);
            }

             if(value instanceof JSONObject) {
                value = jsonToMap((JSONObject) value);
            }
            map.put(key, value);
        }
        return map;
    }

    public static List<Object> jsonToList(JSONArray array) {
        List<Object> list = new ArrayList<>();
        for(int i = 0; i < array.length(); i++) {
            Object value = array.get(i);
            if(value instanceof JSONArray) {
                value = jsonToList((JSONArray) value);
            }
            else if(value instanceof JSONObject) {
                value = jsonToMap((JSONObject) value);
            }
            list.add(value);
        }
        return list;
    }

}
