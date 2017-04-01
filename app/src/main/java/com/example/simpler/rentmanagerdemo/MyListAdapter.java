package com.example.simpler.rentmanagerdemo;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Map;
import java.util.zip.Inflater;

/**
 * Created by Simpler on 2017/2/19.
 */
public class MyListAdapter extends BaseAdapter {

    class ViewHolder {
        public ViewHolder() {
        }

        TextView room_number, renter_name, renter_phone;
        LinearLayout mainLayout;
    }

    private ArrayList<Map<String, String>> dataList;
    private Context context;
    private int view;

    public void UpdateView(ArrayList<Map<String, String>> newDate)
    {
        dataList = newDate;
        notifyDataSetChanged();
    }


    public MyListAdapter(Context context, ArrayList<Map<String,String>> myList,int view) {
        this.context = context;
        dataList = myList;
        this.view = view;
    }

    @Override
    public int getCount() {
        return dataList.size();
    }

    @Override
    public Object getItem(int i) {
        return dataList.get(i);
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int posit, View mView, ViewGroup parent) {

        ViewHolder viewHolder = new ViewHolder();
        MyListClickListener myListClickListener = new MyListClickListener(context);

        if (mView == null) {
            LayoutInflater mInflate = LayoutInflater.from(context);
            mView = mInflate.inflate(view, null);

            viewHolder.room_number = (TextView) mView.findViewById(R.id.room_number);
            viewHolder.renter_name = (TextView) mView.findViewById(R.id.renter_name);
            viewHolder.renter_phone = (TextView) mView.findViewById(R.id.renter_phone);
            viewHolder.mainLayout = (LinearLayout) mView.findViewById(R.id.click_list);

            viewHolder.mainLayout.setTag(posit);
            mView.setTag(viewHolder);
        } else viewHolder = (ViewHolder) mView.getTag();

        viewHolder.room_number.setText( (dataList.get(posit)).get("room_number") );
        viewHolder.renter_name.setText( (dataList.get(posit)).get("renter_name") );
        viewHolder.renter_phone.setText( (dataList.get(posit)).get("renter_phone") );

        viewHolder.mainLayout.setOnClickListener(myListClickListener);

        return mView;
    }
}

