package vector.common.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

import java.io.IOException;

/**
 * Created by vectorzeng on 2018/2/28.
 */

public class GsonUtil {

    public static Gson gson = new GsonBuilder().create();

    /**
     * Object --> json
     */
    public static String buildJsonStr(Object obj){
        return gson.toJson(obj);
    }

    /**
     * 把一个json反序列化为一个对象
     *
     * @param <T>
     */

    public static <T> T fromJson(String json, Class<T> clazz) {
        return gson.fromJson(json, clazz);
    }

    public static <T> T fromJsonSafe(String json, Class<T> clazz) {

        try {
            if (clazz != null) {
                return gson.fromJson(json, clazz);
            }
        }catch (JsonSyntaxException e){
            e.printStackTrace();
        }
        return null;
    }

}
