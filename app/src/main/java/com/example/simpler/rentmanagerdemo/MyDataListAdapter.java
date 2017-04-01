package com.example.simpler.rentmanagerdemo;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Simpler on 2017/2/20.
 */
public class MyDataListAdapter extends BaseAdapter {

    class ViewHolder {

        public ViewHolder() {
        }

        TextView tData_fee, tData_water, tData_electric, tData_month, tData_year;
    }

    private ArrayList<HashMap<String, String>> dataList;
    private Context context;
    private int view;

    public MyDataListAdapter(Context context, ArrayList<HashMap<String, String>> myList, int view) {
        this.context = context;
        dataList = myList;
        this.view = view;
    }

    public void updateMyView(ArrayList<HashMap<String, String>> newData) {
        dataList = newData;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        if (dataList != null) return dataList.size();
        else return 0;
    }

    @Override
    public Object getItem(int i) {
        return dataList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int posit, View mView, ViewGroup parent) {

        ViewHolder viewHolder = new ViewHolder();

        if (mView == null) {
            LayoutInflater mInflate = LayoutInflater.from(context);
            mView = mInflate.inflate(view, null);

            viewHolder.tData_fee = (TextView) mView.findViewById(R.id.rent_fee_str);
            viewHolder.tData_electric = (TextView) mView.findViewById(R.id.electric_number);
            viewHolder.tData_water = (TextView) mView.findViewById(R.id.water_number);
            viewHolder.tData_month = (TextView) mView.findViewById(R.id.data_month);
            viewHolder.tData_year = (TextView) mView.findViewById(R.id.data_year);

            mView.setTag(viewHolder);
        } else viewHolder = (ViewHolder) mView.getTag();

        viewHolder.tData_year.setText(dataList.get(posit).get("year"));
        viewHolder.tData_month.setText(new StringBuilder(dataList.get(posit).get("month")).append("/").append(dataList.get(posit).get("day")));
        viewHolder.tData_water.setText(dataList.get(posit).get("water"));
        viewHolder.tData_electric.setText(dataList.get(posit).get("electric"));
        viewHolder.tData_fee.setText("Null Default");

        return mView;
    }
}
