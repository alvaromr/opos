package com.alvaro.opos.data.entity.mapper;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class MapperUtils {
    @SuppressWarnings("unchecked")
    public static <T> List<T> getListFromString(String stringList) throws JSONException {
        ArrayList<T> list = new ArrayList<>();
        if (stringList != null) {
            JSONArray jsonArray = new JSONArray(stringList);
            int len = jsonArray.length();
            for (int i = 0; i < len; i++) {
                list.add((T) jsonArray.get(i));
            }
        }
        return list;
    }

    public static String getStringFromList(List<?> list) {
        return new JSONArray(list).toString();
    }
}
