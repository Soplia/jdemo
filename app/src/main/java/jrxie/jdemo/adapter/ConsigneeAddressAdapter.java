package jrxie.jdemo.adapter;

import android.content.Context;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.List;

import jrxie.jdemo.base.BaseViewHolder;
import jrxie.jdemo.bean.ConsigneeMsg;

/**
 * Created by jrxie on 2019/5/6.
 */

public class ConsigneeAddressAdapter extends SimpleAdapter<ConsigneeMsg> {

    private TextView consigneeName ;
    private TextView consigneePhone ;
    private TextView consigneeAddress ;
    private TextView checkText ;
    private RadioButton isCheck ;
    private TextView edit ;
    private TextView delete ;

    public ConsigneeAddressAdapter(Context context, List<ConsigneeMsg> mDatas) {
        super(context, mDatas, R.layout.show_adressitem_adapter);
    }

    @Override
    public void bindData(BaseViewHolder holder, ConsigneeMsg consigneeMsg, int position) {

        consigneeName = holder.findTextView(R.id.consignee_name) ;
        consigneePhone = holder.findTextView(R.id.consignee_phone) ;
        consigneeAddress = holder.findTextView(R.id.consignee_address) ;
        checkText = holder.findTextView(R.id.radio_selectedText) ;
        isCheck = holder.findRadioButton(R.id.radio_selected) ;
        edit = holder.findTextView(R.id.edite_address) ;
        delete = holder.findTextView(R.id.delete_address) ;

        isCheck.setChecked(consigneeMsg.isDefault());
        consigneeName.setText(consigneeMsg.getConsignee());
        consigneePhone.setText(showPhone(consigneeMsg.getPhone()));
        consigneeAddress.setText(consigneeMsg.getAddr());
        checkText.setText(consigneeMsg.isDefault() ? "默认地址" : "设为默认");

    }

    private String showPhone(String phone){
        return phone.substring(0 ,3) + "*****" + phone.substring(phone.length()-3) ;
    }

}

