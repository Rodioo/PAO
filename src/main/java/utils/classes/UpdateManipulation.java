package utils.classes;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class UpdateManipulation {

    public static String getUpdateQuery(@NotNull HashMap<String, String> params) {
        String query = "";
        for(Map.Entry<String, String> entry : params.entrySet()) {
            String column = entry.getKey();
            String value = entry.getValue();
            query += " " + column + "=" + value + ",";
        }
        query = query.substring(0, query.length() - 1);
        return query;
    }
}
