package jrxie.jdemo.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import jrxie.jdemo.MainActivity;
import jrxie.jdemo.base.BaseActivity;
import jrxie.jdemo.R;

public class PayResultActivity extends BaseActivity {

    @BindView(R.id.resultText)
    TextView resultText ;
    @BindView(R.id.resultImg)
    ImageView resultButton ;
    @BindView(R.id.backHome)
    Button button ;

    @Override
    protected int getContentViewId() {
        return R.activity_pay_result;
    }

    private void addButtonListener(){
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent( v.getContext() , MainActivity.class));
                finish();

            }
        });
    }

    protected void initView(){

        Intent intent = getIntent() ;
        if (intent != null){

            int status = intent.getIntExtra("status" , 0) ;

            Log.d("", "initView: status" + status + "---------------------------------------------------");

            if(status == NewOrderActivity.ORDER_SUCCESS){
                resultText.setText("支付成功");
                resultButton.setImageResource(R.drawable.icon_success_128);
            }else if(status == NewOrderActivity.ORDER_FAIL){
                resultText.setText("支付失败");
                resultButton.setImageResource(R.drawable.icon_cancel_128);
            }else if (status == NewOrderActivity.ORDER_GONE){
                resultText.setText("用户已取消支付");
                resultButton.setImageResource(R.drawable.icon_cancel_128);
            }
        }

    }

    @Override
    protected void addListener() {
        addButtonListener() ;
    }

    @Override
    protected void beforLayout() {

    }
}

