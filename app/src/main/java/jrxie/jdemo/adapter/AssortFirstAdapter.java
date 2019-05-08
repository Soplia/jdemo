package jrxie.jdemo.adapter;

import android.content.Context;
import android.widget.TextView;

import java.util.List;

import jrxie.jdemo.R;
import jrxie.jdemo.base.BaseViewHolder;
import jrxie.jdemo.bean.AssortFirstData;

/**
 * Created by jrxie on 2019/5/5.
 */

public class AssortFirstAdapter extends SimpleAdapter<AssortFirstData> {

    private TextView name ;

    public AssortFirstAdapter(Context context, List<AssortFirstData> mDatas) {
        super(context, mDatas, R.layout.assort_first_adapter);
    }

    @Override
    public void bindData(BaseViewHolder holder, AssortFirstData firstClassifyData , int position) {

        name = holder.findTextView(R.id.text) ;

        name.setText(firstClassifyData.getName());

    }

}

