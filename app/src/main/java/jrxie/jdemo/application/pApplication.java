package jrxie.jdemo.application;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * Created by jrxie on 2019/5/5.
 */

/**
 * 这个类直接添加在布局文件中后,能够自动首先被执行
 */
public class pApplication extends Application
{
    @Override
    public void onCreate()
    {
        super.onCreate();
        Fresco.initialize(getApplicationContext());
    }
}
