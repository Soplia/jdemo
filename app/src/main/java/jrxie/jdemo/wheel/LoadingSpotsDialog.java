package jrxie.jdemo.wheel;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

import dmax.dialog.SpotsDialog;

/**
 * Created by jrxie on 2019/5/5.
 */

public abstract class LoadingSpotsDialog<T> extends BaseCallback<T> {

    private SpotsDialog spotsDialog ;
    private Context mContext ;

    public LoadingSpotsDialog(Context context){

        spotsDialog = new SpotsDialog(context) ;
        mContext = context ;
    }

    public void showSpotsDialog(){
        spotsDialog.show();
    }

    public void closeSpotsDialog(){
        spotsDialog.cancel();
    }

    @Override
    public void onRequestBefore() {
        showSpotsDialog() ;
    }

    @Override
    public void onFailure(Request request, IOException e) {
        closeSpotsDialog();
    }

    @Override
    public void onTokenError(Response response, int responseCode) {

        Toast.makeText(mContext , "TokenError-LoadSportDialog.java" , Toast.LENGTH_SHORT).show();

        //注释3行,jrx
        //Intent intent = new Intent(mContext , LoginActivity.class);

        //mContext.startActivity(intent);

        //MyApplication.getInstance().clearUser();
    }
}

