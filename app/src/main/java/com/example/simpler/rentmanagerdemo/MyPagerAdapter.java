package com.example.simpler.rentmanagerdemo;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import java.util.List;

/**
 * Created by Simpler on 2017/2/15.
 */
public class MyPagerAdapter extends PagerAdapter {

    List<View> mViewList;
    List<String> mTitleList;

    public MyPagerAdapter(List<View> mViewList,List<String> mTitleList) {
        this.mViewList = mViewList;
        this.mTitleList = mTitleList;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

        container.removeView(mViewList.get(position));
    }

    //实例化页面
    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        if(container.getParent()!=null) container.removeView(mViewList.get(position));
        container.addView(mViewList.get(position),0);
        notifyDataSetChanged();
        return mViewList.get(position);
    }

    @Override
    public int getCount() {
        return mTitleList.size();
    }


    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitleList.get(position);
    }
}
