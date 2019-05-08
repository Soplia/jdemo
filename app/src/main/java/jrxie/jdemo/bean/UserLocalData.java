package jrxie.jdemo.bean;

import android.content.Context;
import android.text.TextUtils;

import jrxie.jdemo.content.Contents;
import jrxie.jdemo.util.JsonUtil;
import jrxie.jdemo.util.PreferenceUtil;

/**
 * Created by jrxie on 2019/5/5.
 */

public class UserLocalData {

    public static void putUser(Context context , User user){

        String user_json = JsonUtil.toJSON(user) ;
        PreferenceUtil.putString(context , Contents.USER_JSON , user_json);
    }

    public static void putToken(Context context , String token){

        PreferenceUtil.putString(context , Contents.TOKEN , token);
    }

    public static User getUser(Context context){
        String user_json = PreferenceUtil.getString(context , Contents.USER_JSON , null);
        if (!TextUtils.isEmpty(user_json)){

            return JsonUtil.fromJson(user_json , User.class) ;
        }
        return null ;
    }

    public static String getToken(Context context){
        String token = PreferenceUtil.getString(context , Contents.TOKEN , null);
        if (!TextUtils.isEmpty(token)){

            return JsonUtil.fromJson(token , String.class) ;
        }
        return null ;
    }

    public static void clearUser(Context context){
        PreferenceUtil.putString(context , Contents.USER_JSON , "");
    }

    public static void clearToken(Context context){
        PreferenceUtil.putString(context , Contents.TOKEN , "");
    }

}

