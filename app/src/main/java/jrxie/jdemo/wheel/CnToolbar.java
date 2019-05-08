package jrxie.jdemo.wheel;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.annotation.StringRes;
import android.support.v7.widget.TintTypedArray;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import jrxie.jdemo.R;

/**
 * Created by jrxie on 2019/5/4.
 */

public class CnToolbar extends Toolbar
{

    private LayoutInflater inflater ;
    private View mView ;
    private TextView mTextTitle ;
    private Button mRightButton ;
    private ImageButton mLeftImageButton ;
    private EditText mSearchView ;
    private ImageButton mRightImageButton ;
    private ImageView userPhoto ;
    private TextView userName ;

    private TintTypedArray b ;

    public CnToolbar(Context context)
    {
        this(context , null);
    }

    public CnToolbar(Context context, @Nullable AttributeSet attrs)
    {
        this(context, attrs , 0);
    }

    @SuppressLint("RestrictedApi")
    public CnToolbar(Context context, @Nullable AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);

        b = TintTypedArray.obtainStyledAttributes(getContext(), attrs,
                R.styleable.CnToolbar, defStyleAttr, 0);
        initView() ;
        /*
        if(getIsShowSearchView())
        {
            showSearchView();
            return;
        }
        */
        if(GetShowViewType() == 1)
            showSearchView();
        else if (GetShowViewType() == 2)
            ShowHotAssortView();
        else if (GetShowViewType() == 3)
            ShowHotCartView();

        setRightButton(attrs, defStyleAttr) ;
        setLeftButton(attrs , defStyleAttr) ;
        setRightImageButton(attrs ,defStyleAttr );
        setUserName(attrs , defStyleAttr);
        setUserPhoto(attrs , defStyleAttr);
        b.recycle();
    }

    //将mview布局加入toolBar中
    private void initView()
    {
        if (mView == null) {
            inflater = LayoutInflater.from(this.getContext());
            mView = inflater.inflate(R.layout.cntoolbar_view, null);

            mSearchView = (EditText) mView.findViewById(R.id.tb_Search_view);
            mTextTitle = (TextView) mView.findViewById(R.id.tb_title);
            mRightButton = (Button) mView.findViewById(R.id.tb_right_button);
            mLeftImageButton = (ImageButton) mView.findViewById(R.id.tb_left_ImageButton) ;
            mRightImageButton = (ImageButton) mView.findViewById(R.id.tb_right_imagebutton) ;
            userPhoto = (ImageView) mView.findViewById(R.id.tb_user_photo) ;
            userName = (TextView) mView.findViewById(R.id.tb_user_name) ;

            mSearchView.setSelected(false);
            mSearchView.setClickable(false);

            LayoutParams lp = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, Gravity.CENTER_HORIZONTAL);
            this.addView(mView, lp);
        }

    }

    @Override
    public void setTitle(@StringRes int resId)
    {
        setTitle(getContext().getText(resId));
    }

    @Override
    public void setTitle(CharSequence title)
    {
        initView();
        if(title != null)
        {
            if(mTextTitle == null)
            {
                mTextTitle = (TextView) mView.findViewById(R.id.tb_title);
            }
            mTextTitle.setText(title);
            mTextTitle.setVisibility(VISIBLE);
            mTextTitle.setSelected(false);
            mTextTitle.setFocusable(false);
        }
    }

    @SuppressLint("RestrictedApi")
    private void setUserName(@Nullable AttributeSet attrs, int defStyleAttr)
    {
        if(attrs != null)
        {
            final String rightIcon = b.getString( R.styleable.CnToolbar_userName );
            if (rightIcon != null && rightIcon.length()>0) {
                setUserNameText(rightIcon);
            }
        }
    }

    public void setUserNameText(String s)
    {
        userName.setText(s);
        userName.setVisibility(VISIBLE);
        userName.setEnabled(true);
    }

    @SuppressLint("RestrictedApi")
    private void setUserPhoto(@Nullable AttributeSet attrs, int defStyleAttr)
    {
        if(attrs != null)
        {
            final Drawable leftIcon = b.getDrawable(R.styleable.CnToolbar_userPhotoIcon);
            if (leftIcon != null) {
                setUserPhotoIcon(leftIcon);
            }
        }
    }

    public void setUserClickable(boolean enable)
    {
        userPhoto.setClickable(enable);
    }

    private void setUserPhotoIcon(Drawable drawable)
    {
        if( drawable != null )
        {
            userPhoto.setImageDrawable(drawable);

            userPhoto.setVisibility(VISIBLE);
            userPhoto.setEnabled(true);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void setUserPhotoIcon(Context context , String resId , int defResId)
    {
        if( resId != null && resId.length() > 0)
        {
            Picasso.with(context)
                    .load(resId)
                    .into(this.userPhoto);
            /*
            Glide.with(context)
                    .load(resId)
                    //这个地方不知道为什么可能能够通过毕加索进行替换
                    //.transform(new GlideCircleTransform(context))
                    .into(this.userPhoto);
                    */
        }
        else
        {
            setUserPhotoIcon(context , defResId) ;
        }
        userPhoto.setVisibility(VISIBLE);
        userPhoto.setEnabled(true);
    }

    public void setUserPhotoIcon(Context context , int resId)
    {
        Picasso.with(context)
                .load(resId)
                .into(this.userPhoto);
        /*
        Glide.with(context)
                .load(resId)
                //.transform(new GlideCircleTransform(context))
                .into(this.userPhoto);
                */
        userPhoto.setVisibility(VISIBLE);
        userPhoto.setEnabled(true);
    }

    public void setUserPhotoClickListener (OnClickListener listener)
    {
        userPhoto.setOnClickListener(listener);
    }

    @SuppressLint("RestrictedApi")
    public void setRightButton(@Nullable AttributeSet attrs, int defStyleAttr)
    {
        if(attrs != null)
        {
            final String rightIcon = b.getString( R.styleable.CnToolbar_rightButtonText );
            if (rightIcon != null && rightIcon.length()>0)
            {
                setRightButtonText(rightIcon);
            }
        }
    }

    public void setRightButtonText (String s)
    {
        Log.d("----" , "----------------s---------------" + s) ;
        mRightButton.setText(s);
        mRightButton.setVisibility(VISIBLE);
        mRightButton.setEnabled(true);
    }

    public void setRightButtonIcOnClickListener (OnClickListener listener)
    {
        mRightButton.setOnClickListener(listener);
    }

    @SuppressLint("RestrictedApi")
    public void setLeftButton(@Nullable AttributeSet attrs, int defStyleAttr){
        if(attrs != null)
        {
            final Drawable leftIcon = b.getDrawable(R.styleable.CnToolbar_leftButtonIcon);
            if (leftIcon != null)
            {
                setLeftButtonIcon(leftIcon);
            }
        }
    }

    public void setLeftButtonIcon (Drawable drawable)
    {
        if( drawable != null )
        {
            mLeftImageButton.setImageDrawable(drawable);

            mLeftImageButton.setVisibility(VISIBLE);
            userPhoto.setVisibility(GONE);
            userName.setVisibility(GONE);
            mLeftImageButton.setEnabled(true);
        }
    }

    public void setLeftButtonIcOnClickListener (OnClickListener listener)
    {
        mLeftImageButton.setOnClickListener(listener);
    }

    @SuppressLint("RestrictedApi")
    public void setRightImageButton(@Nullable AttributeSet attrs, int defStyleAttr)
    {
        if(attrs != null)
        {
            final Drawable rightImage = b.getDrawable(R.styleable.CnToolbar_rightImageButtonIcon);
            if (rightImage != null)
            {
                setRightButtonIcon(rightImage);
            }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void setRightButtonIcon(int resId)
    {
        this.setRightButtonIcon(getResources().getDrawable(resId , null));
    }

    public void setRightButtonIcon (Drawable drawable)
    {
        if( drawable != null )
        {
            mRightImageButton.setImageDrawable(drawable);

            mRightImageButton.setVisibility(VISIBLE);
            mRightImageButton.setEnabled(true);
        }
    }

    public void setRightImgeButtonIcOnClickListener (OnClickListener listener)
    {
        mRightImageButton.setOnClickListener(listener);
    }

    @SuppressLint("RestrictedApi")
    public boolean getIsShowSearchView()
    {
        final boolean isShowSearchView = b.getBoolean(R.styleable.CnToolbar_isShowSearchView , false) ;
        return  isShowSearchView ;
    }

    public void showSearchView()
    {
        mRightButton.setVisibility(GONE);
        mLeftImageButton.setVisibility(GONE);
        mTextTitle.setVisibility(GONE);
        mSearchView.setVisibility(VISIBLE);
    }

    public void notShowSearchView()
    {
        mRightButton.setVisibility(VISIBLE);
        mLeftImageButton.setVisibility(VISIBLE);
        mTextTitle.setVisibility(VISIBLE);
        mSearchView.setVisibility(GONE);
    }

    public void ShowHotAssortView()
    {
        mRightButton.setVisibility(GONE);
        mLeftImageButton.setVisibility(GONE);
        mTextTitle.setVisibility(VISIBLE);
        mSearchView.setVisibility(GONE);
    }

    public void ShowHotCartView()
    {
        mRightButton.setVisibility(VISIBLE);
        mLeftImageButton.setVisibility(GONE);
        mTextTitle.setVisibility(VISIBLE);
        mSearchView.setVisibility(GONE);
    }

    @SuppressLint("RestrictedApi")
    public int GetShowViewType()
    {
        final int viewType = b.getInteger(R.styleable.CnToolbar_viewType , 2);
        return  viewType ;
    }


    public void setSearchViewOnClicklistener( OnClickListener listener )
    {
        mSearchView.setOnClickListener(listener);
    }

}
