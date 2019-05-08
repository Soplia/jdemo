package jrxie.jdemo.ftabhost;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.cjj.MaterialRefreshLayout;

import java.util.List;

import jrxie.jdemo.Activity.WareDetialActivity;
import jrxie.jdemo.R;
import jrxie.jdemo.adapter.HotAdapter;
import jrxie.jdemo.base.BaseAdapter;
import jrxie.jdemo.base.BaseFragment;
import jrxie.jdemo.bean.Page;
import jrxie.jdemo.bean.Ware;
import jrxie.jdemo.content.Contents;
import jrxie.jdemo.util.CartProvider;
import jrxie.jdemo.util.Pager;
import jrxie.jdemo.wheel.MyDivider;

public class HotFragment extends BaseFragment {

    private RecyclerView recyclerView ;
    private MaterialRefreshLayout materialRefreshLayout ;
    private HotAdapter mHotAdapter ;
    private Pager pager ;

    @Override
    protected int GetResRootViewId() {
        return R.layout.fragment_hot;
    }

    @Override
    protected void Init()
    {

        recyclerView = (RecyclerView) mView.findViewById(R.id.resyclerView);
        materialRefreshLayout = (MaterialRefreshLayout) mView.findViewById(R.id.materialRefreshLayout) ;

        try
        {
            initMaterialRefreshLayout();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void initMaterialRefreshLayout() throws Exception {

        String uri = Contents.API.HOT ;

        Pager.Builder builder = Pager.getBuilder()
                .setMaterialRefreshLayout(materialRefreshLayout)
                .putParams("curPage" , 1)
                .putParams("pageSize" , 10)
                .setUri(uri)
                .setOnPageListener(new Pager.OnPageListener<Ware>() {
                    @Override
                    public void loadNormal(List<Ware> mData, int totalPage, int pageSize) {
                        mHotAdapter =new HotAdapter(mContext , mData ) ;
                        setItemlistenler() ;
                        recyclerView.setAdapter(mHotAdapter);
                        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
                        recyclerView.addItemDecoration(new MyDivider());
                    }

                    @Override
                    public void loadMoreData(List<Ware> mData, int totalPage, int pageSize) {
                        mHotAdapter.addData(mData);
                    }

                    @Override
                    public void refData(List<Ware> mData, int totalPage, int pageSize) {
                        mHotAdapter.cleanData();
                        mHotAdapter.addData(mData);
                        recyclerView.scrollToPosition(0);
                    }
                }) ;

        pager = builder.build(mContext , Page.class) ;

    }

    public void setItemlistenler (){

        mHotAdapter.setOnItemClickListener(new BaseAdapter.onItemClickListener() {
            @Override
            public void onClick(View view, int position) {

                if(view.getId() == R.id.buyButton){
                    addToCart(position) ;
                }else {

                    Ware ware = mHotAdapter.getData(position);
                    Intent intent = new Intent(getActivity() , WareDetialActivity.class);
                    intent.putExtra(Contents.WARE , ware);

                    startActivity(intent);
                }

            }
        });

    }

    public void addToCart( int position ){

        Ware hotGoodsMsgPart = mHotAdapter.getData(position) ;

        CartProvider.getCartProvider(getContext()) .put( hotGoodsMsgPart );
        Toast.makeText( getContext() , "已添加到购物车" , Toast.LENGTH_SHORT ).show();
    }


}

