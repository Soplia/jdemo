package jrxie.jdemo.adapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import java.util.List;

import jrxie.jdemo.R;
import jrxie.jdemo.base.BaseViewHolder;
import jrxie.jdemo.base.ResyslerViewIndicator;

/**
 * Created by jrxie on 2019/5/5.
 */

public class HomeAdapter extends SimpleAdapter<ResyslerViewIndicator>
{

    private static int viewTypeNum = 0 ;
    private ImageView imageViewBig ;
    private ImageView imageViewSmallBottom ;
    private ImageView imageViewSmallTop ;
    private TextView title ;

    public HomeAdapter(Context context, List<ResyslerViewIndicator> mDatas)
    {

        super(context, mDatas, (viewTypeNum ++ % 2 == 0) ? R.layout.home_cardview_left : R.layout.home_cardview_right );
    }

    @Override
    public void bindData(BaseViewHolder holder, ResyslerViewIndicator resyslerViewIndicator , int position) {

        imageViewBig = holder.findImageView(R.id.imageview_big) ;
        imageViewSmallTop = holder.findImageView(R.id.imageview_small_top);
        imageViewSmallBottom = holder.findImageView(R.id.imageview_small_bottom) ;
        title = holder.findTextView(R.id.cardView_title) ;

        this.title.setText(resyslerViewIndicator.getTitle());
//
        //将图片加载到指定view
        //Glide.with(context).load(resyslerViewIndicator.getCpTwo().getImgUrl()).asBitmap().into(this.imageViewSmallBottom);
        //Glide.with(context).load(resyslerViewIndicator.getCpOne().getImgUrl()).asBitmap().into(this.imageViewBig);
        //Glide.with(context).load(resyslerViewIndicator.getCpThree().getImgUrl()).asBitmap().into(this.imageViewSmallTop);

        Picasso.with(context).load(resyslerViewIndicator.getCpTwo().getImgUrl()).into(this.imageViewSmallBottom);
        Picasso.with(context).load(resyslerViewIndicator.getCpOne().getImgUrl()).into(this.imageViewBig);
        Picasso.with(context).load(resyslerViewIndicator.getCpThree().getImgUrl()).into(this.imageViewSmallTop);
    }
}

