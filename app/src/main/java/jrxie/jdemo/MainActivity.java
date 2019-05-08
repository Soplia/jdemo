package jrxie.jdemo;

import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TextView;

import java.util.ArrayList;

import jrxie.jdemo.ftabhost.CartFragment;
import jrxie.jdemo.ftabhost.DiscoverFragment;
import jrxie.jdemo.ftabhost.HomeFragment;
import jrxie.jdemo.ftabhost.HotFragment;
import jrxie.jdemo.ftabhost.Tab;
import jrxie.jdemo.ftabhost.UserFragment;

public class MainActivity extends AppCompatActivity {

    private static int numBottomNavigator = 5;
    private FragmentTabHost mTabHost;
    private LayoutInflater mInflater;
    private ArrayList<Tab> mTabs= new ArrayList<Tab>(numBottomNavigator);
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        InitToolBar();
        initTab();

    }

    private void InitToolBar()
    {
        toolbar = (Toolbar) findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
    }

    private void initTab()
    {
        mInflater = LayoutInflater.from(this);

        //这十行就是把所有的内容都融合在一起为了下面的循环做准备
        //实例化5个Tab类的对象
        Tab Tab_home = new Tab(R.drawable.selector_home,R.string.tab_home,HomeFragment.class);
        Tab Tab_hot = new Tab(R.drawable.selector_hot,R.string.tab_hot, HotFragment.class);
        Tab Tab_discover = new Tab(R.drawable.selector_discover,R.string.tab_assort, DiscoverFragment.class);
        Tab Tab_cart = new Tab(R.drawable.selector_cart,R.string.tab_cart, CartFragment.class);
        Tab Tab_user = new Tab(R.drawable.selector_user,R.string.tab_mine, UserFragment.class);

        //将这5个对象加到一个List中
        mTabs.add(Tab_home);
        mTabs.add(Tab_hot);
        mTabs.add(Tab_discover);
        mTabs.add(Tab_cart);
        mTabs.add(Tab_user);


        mTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        mTabHost.setup(this, getSupportFragmentManager(), R.id.realcontent);

        //通过循环实例化一个个TabSpec
        //并调用其中setIndicator方法
        //然后将TabSpec加到TabHost中
        for (Tab tab  :mTabs)
        {
            TabHost.TabSpec tabSpec = mTabHost.newTabSpec(String.valueOf(tab.getText()));
            tabSpec.setIndicator(buildView(tab));
            mTabHost.addTab(tabSpec,tab.getFragment(), null);
        }

        //通过这行代码可以去除掉底部菜单5个图表之间的分割线
        mTabHost.getTabWidget().setShowDividers(LinearLayout.SHOW_DIVIDER_NONE);
    }

    //设置Indicator中的View
    private View buildView(Tab tab)
    {
        View view = mInflater.inflate(R.layout.tab_indicator,null);
        ImageView Tab_img = (ImageView) view.findViewById(R.id.tab_image);
        TextView Tab_txt = (TextView) view.findViewById(R.id.tab_text);

        Tab_img.setBackgroundResource(tab.getImage());
        Tab_txt.setText(tab.getText());
        return view;
    }


}
