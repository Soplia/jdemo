package jrxie.jdemo.base;

import android.support.v4.app.Fragment;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by jrxie on 2019/5/5.
 */

public abstract class BaseFragment extends Fragment {

    protected Context mContext ;
    protected View mView ;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        int resRootViewId =  GetResRootViewId () ;
        mView = inflater.inflate(resRootViewId , container , false) ;
        mContext = this.getContext();
        Init();
        return mView ;
    }

    protected abstract int GetResRootViewId() ;

    protected abstract void Init();

    /*
    protected void startActivityWithLogin(Intent intent , boolean isNeedLogin , int startIntentStype){

        if (isNeedLogin){

            if(MyApplication.getInstance().getUser() == null){

                Intent intent1 = new Intent(getActivity() , LoginActivity.class) ;

                if (MyApplication.START_FOR_RESULT == startIntentStype){
                    startActivityForResult(intent1 , Contents.REQUEST_CODE);
                }else if(MyApplication.START_NO_RESULT == startIntentStype){
                    MyApplication.getInstance().setIntent(intent);
                    startActivity(intent1);
                }
            }else {
                getActivity().startActivity(intent);
            }
        }else {
            getActivity().startActivity(intent);
        }

    }
    */
}

