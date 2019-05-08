package jrxie.jdemo.application;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.widget.ImageView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.lzy.ninegrid.NineGridView;
import com.squareup.picasso.Picasso;

import jrxie.jdemo.R;
import jrxie.jdemo.bean.User;
import jrxie.jdemo.bean.UserLocalData;

/**
 * Created by jrxie on 2019/5/5.
 */

public class MyApplication extends MobApplication {

    public static final int START_FOR_RESULT  = 0 ;
    public static final int START_NO_RESULT  = 1 ;

    private User user ;
    private String token ;
    private static Intent intent ;
    private static  int startIntentStype ;

    public static int getStartIntentStype() {
        return startIntentStype;
    }

    public static void setStartIntentStype(int startIntentStype) {
        MyApplication.startIntentStype = startIntentStype;
    }

    public Intent getIntent() {
        return intent;
    }

    public void setIntent(Intent intent) {
        this.intent = intent;
    }

    public static MyApplication myApplication ;

    public static MyApplication getInstance(){
        return myApplication ;
    }

    @Override
    public void onCreate()
    {
        super.onCreate();
        this.myApplication = this ;
        initUser();
        Fresco.initialize(this);
        NineGridView.setImageLoader(new PicassoImageLoader());

    }

    public void initUser()
    {
        this.user = UserLocalData.getUser(this);
        this.token = UserLocalData.getToken(this) ;
    }

    public User getUser(){
        return user ;
    }

    public String getToken(){
        return token ;
    }

    public void putUser(User user , String token){

        this.token = token ;
        this.user = user ;
        UserLocalData.putUser(this, user);
        UserLocalData.putToken(this, token);
    }

    public void clearUser(){
        this.user = null ;
        this.token = null ;
        UserLocalData.clearUser(this);
        UserLocalData.clearToken(this);
    }

    public static void jumpToTargetoActivity(Activity activity){
        activity.startActivity(intent);
        intent = null ;
    }

    /** Picasso 加载 */
    private class PicassoImageLoader implements NineGridView.ImageLoader
    {
        @Override
        public void onDisplayImage(Context context, ImageView imageView, String url)
        {
            Picasso.with(context).load(url)//
                    .placeholder(R.drawable.ic_default_image)//
                    .error(R.drawable.ic_default_image)//
                    .into(imageView);
        }
        @Override
        public Bitmap getCacheImage(String url)
        {
            return null;
        }
    }
}

