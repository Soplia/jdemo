package jrxie.jdemo.Activity;

import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import jrxie.jdemo.R;
import jrxie.jdemo.adapter.MyOrderAdapter;
import jrxie.jdemo.application.MyApplication;
import jrxie.jdemo.base.BaseActivity;
import jrxie.jdemo.bean.OrderMsg;
import jrxie.jdemo.content.Contents;
import jrxie.jdemo.wheel.CnToolbar;
import jrxie.jdemo.wheel.LoadingSpotsDialog;
import jrxie.jdemo.wheel.MyDivider;
import jrxie.jdemo.wheel.OkhttpHelper;

public class MyOrderActivity extends BaseActivity {

    @BindView(R.id.toolBar)
    CnToolbar pbToolbar ;
    @BindView(R.id.recycleView)
    RecyclerView recyclerView ;
    @BindView(R.id.show_order_tab)
    TabLayout show_order_tab ;
    private TabLayout.Tab showAllTab ;
    private TabLayout.Tab showSuccessTab ;
    private TabLayout.Tab showFailTab ;
    private TabLayout.Tab showWaitPayTab ;

    private MyOrderAdapter myAdapter ;
    private OkhttpHelper okhttp = OkhttpHelper.getOkhttpHelper() ;

    private static final int SHOWALL = 2 ;
    private static final int SHOWSUCC = 1 ;
    private static final int SHOWWAIT = 0 ;
    private static final int SHOWFAIL = -1 ;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_my_order;
    }

    @Override
    protected void initView() {
        initTabLayout();
    }

    protected void addListener() {

        pbToolbar.setLeftButtonIcOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void beforLayout() {

    }

    private void initTabLayout() {

        showAllTab = show_order_tab.newTab().setText("全部订单").setTag(SHOWALL) ;
        showSuccessTab = show_order_tab.newTab().setText("支付成功").setTag(SHOWSUCC) ;
        showWaitPayTab = show_order_tab.newTab().setText("等待支付").setTag(SHOWWAIT) ;
        showFailTab = show_order_tab.newTab().setText("支付失败").setTag(SHOWFAIL) ;

        show_order_tab.addTab(showAllTab);
        show_order_tab.addTab(showSuccessTab);
        show_order_tab.addTab(showWaitPayTab);
        show_order_tab.addTab(showFailTab);

        getData(SHOWALL) ;

        show_order_tab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                getData((int)tab.getTag()) ;
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });

    }

    private void getData(int status) {

        Map<String , String> params = new HashMap<String , String>() ;

        params.put("user_id" , MyApplication.getInstance().getUser().getId()+"") ;
        params.put("status" , status + "" ) ;

        okhttp.DoGet(Contents.API.GET_ORDER_LIST, new LoadingSpotsDialog<List<OrderMsg>>(this) {
            @Override
            public void onErroe(Response response, int responseCode, Exception e) throws IOException {
                this.closeSpotsDialog();
            }

            @Override
            public void callBackSucces(Response response, List<OrderMsg> orderMsgs) throws IOException {
                this.closeSpotsDialog();

                showOrderData(orderMsgs) ;


            }

        }, params);

    }

    private void showOrderData(List<OrderMsg> orderMsgs) {

        if (orderMsgs != null && orderMsgs.size() > 0 ){

            if (myAdapter == null ){

                myAdapter = new MyOrderAdapter(this , orderMsgs ) ;
                recyclerView.setAdapter(myAdapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(this));
                recyclerView.addItemDecoration(new MyDivider());

            }else{
                myAdapter.cleanData();
                myAdapter.addData(orderMsgs);
            }
        }else if (myAdapter != null){
            myAdapter.cleanData();
        }

    }


}

