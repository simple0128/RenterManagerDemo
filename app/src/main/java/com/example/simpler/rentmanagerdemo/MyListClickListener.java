package com.example.simpler.rentmanagerdemo;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;


/**
 * Created by Simpler on 2017/2/20.
 */
public class MyListClickListener implements View.OnClickListener {


    Context mContext;
    MyListClickListener(Context context){
        mContext = context;
    }
    @Override
    public void onClick(View view) {
        Log.i(getClass().getName(), view.getTag().toString());
        MyListAdapter.ViewHolder viewHolder = (MyListAdapter.ViewHolder)view.getTag();

        Intent i = new Intent(mContext,DataActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("room_number", ( ((MyListAdapter.ViewHolder)view.getTag()).room_number).getText().toString());
        i.putExtras(bundle);

        mContext.startActivity(i);
    }
}
