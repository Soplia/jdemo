package jrxie.jdemo.util;

import com.google.gson.Gson;

import java.lang.reflect.Type;

/**
 * Created by jrxie on 2019/5/5.
 */

public class JsonUtil {

    static Gson gson = new Gson() ;

    public static  <T> T fromJson(String json , Type type)
    {
        return gson.fromJson(json , type) ;
    }

    public static String toJSON( Object carts )
    {
        return gson.toJson(carts) ;
    }


}

