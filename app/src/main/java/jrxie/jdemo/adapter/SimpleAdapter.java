package jrxie.jdemo.adapter;

import android.content.Context;

import java.util.List;

import jrxie.jdemo.base.BaseAdapter;
import jrxie.jdemo.base.BaseViewHolder;

/**
 * Created by jrxie on 2019/5/5.
 */

public abstract class SimpleAdapter<T> extends BaseAdapter< T , BaseViewHolder> {

    public SimpleAdapter(Context context, List<T> mDatas, int resId) {
        super(context, mDatas, resId);
    }

}
