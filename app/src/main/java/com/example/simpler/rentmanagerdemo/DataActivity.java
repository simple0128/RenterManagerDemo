package com.example.simpler.rentmanagerdemo;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Simpler on 2017/2/20.
 */
public class DataActivity extends Activity{

    //Nothing here
    Context context;
    Button btn_back, btn_more;
    TextView room_number, data_name, data_phone, data_time, data_fee;
    DataExchanger dataExchanger;
    String mRoom;
    InfoSample info = new InfoSample();
    MyDataListAdapter myDataListAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.data_main);

        try {
            Bundle bundle = this.getIntent().getExtras();
            mRoom = bundle.getString("room_number");
        } catch (NullPointerException e) {
            e.printStackTrace();
            finish();
        }

        context = this;
        dataExchanger = new DataExchanger(context);
        initW();
    }

    private void initW() {

        myDataListAdapter = new MyDataListAdapter(context,getData(),R.layout.data_list_view);
        btn_back = (Button) findViewById(R.id.back_button);
        btn_more = (Button) findViewById(R.id.more_button);
        room_number = (TextView) findViewById(R.id.data_title);
        data_name = (TextView) findViewById(R.id.data_name);
        data_phone = (TextView) findViewById(R.id.data_phone);
        data_time = (TextView) findViewById(R.id.data_time);
        data_fee = (TextView) findViewById(R.id.data_fee);



        ListView listView = (ListView) findViewById(R.id.list_view_data);
        listView.setAdapter(myDataListAdapter);

        room_number.setText(mRoom);
        btn_more.setOnClickListener(new MyDataBtnListener(context){
            @Override
            public void updateView() {
                myUpdateView();
            }
        });

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private ArrayList<HashMap<String, String>> getData() {

        DataExchanger dataExchanger = new DataExchanger(context);
        return dataExchanger.getRoomData(mRoom);
    }

    public void myUpdateView()
    {
        Log.i(getClass().getName(), "Data Has Been Updated");
        myDataListAdapter.updateMyView(getData());

        HashMap<String, String> dataMap = dataExchanger.getData(mRoom);
        room_number.setText(dataMap.get(info.renterList.get(0)));
        data_name.setText(dataMap.get(info.renterList.get(1)));
        data_phone.setText(dataMap.get(info.renterList.get(2)));
        data_time.setText(dataMap.get(info.renterList.get(3)));
        data_fee.setText(dataMap.get(info.renterList.get(4)));
    }

}
