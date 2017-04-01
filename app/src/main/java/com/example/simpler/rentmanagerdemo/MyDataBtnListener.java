package com.example.simpler.rentmanagerdemo;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;
import java.util.HashMap;

/**
 * Created by Simpler on 2017/2/20.
 */
public abstract class MyDataBtnListener implements View.OnClickListener {

    Context context;
    InfoSample info = new InfoSample();
    String room_number;
    MyAddPersonDialog myAddPersonDialog,myEditPersonDialog,myfindPersonDialog;

    HashMap<String, String> myDataMap = new HashMap<>();
    HashMap<String,String> myRoomDataMap = new HashMap<>();

    MyDataBtnListener(Context mContext) {
        context = mContext;
        room_number = ((TextView) ((Activity) context).findViewById(R.id.data_title)).getText().toString();
    }

    public abstract void updateView();

    @Override
    public void onClick(View view) {

        final PopupMenu popupMenu = new PopupMenu(context, view);
        popupMenu.getMenuInflater().inflate(R.menu.data_popupmenu, popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.menu_add:
                        MenuAdd();
                        break;
                    case R.id.menu_edit:
                        MenuEdit();
                        break;
                    case R.id.menu_del:
                        MenuDel();
                    default:
                        return false;
                }
                return true;
            }
        });
        popupMenu.show();
    }

    private void MenuAdd() {
        myAddPersonDialog = new MyAddPersonDialog((Activity) context,R.layout.data_add_dialog,new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(view.getId() == R.id.btn_edit_save) {
                    EditText edit_name = (EditText) myAddPersonDialog.findViewById(R.id.edit_name);
                    EditText edit_phone = (EditText) myAddPersonDialog.findViewById(R.id.edit_phone);
                    EditText edit_time = (EditText) myAddPersonDialog.findViewById(R.id.edit_time);
                    EditText edit_fee = (EditText) myAddPersonDialog.findViewById(R.id.edit_fee);


                    myDataMap.clear();
                    myDataMap.put(info.renterList.get(0), room_number);
                    myDataMap.put(info.renterList.get(1), edit_name.getText().toString());
                    myDataMap.put(info.renterList.get(2), edit_phone.getText().toString());
                    myDataMap.put(info.renterList.get(3), edit_time.getText().toString());
                    myDataMap.put(info.renterList.get(4), edit_fee.getText().toString());


                    if (setData()) {
                        Toast.makeText(context,"插入数据成功",Toast.LENGTH_SHORT).show();
                        updateView();
                        myAddPersonDialog.dismiss();
                    }
                    else {
                        Toast.makeText(context, "插入数据失败,请重试", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
        myAddPersonDialog.show();
    }

    private void MenuDel() {
        Log.i(getClass().getName(), "Menu Del....");
    }

    private void MenuEdit() {
        Log.i(getClass().getName(), "Menu Edit....");
        myEditPersonDialog = new MyAddPersonDialog((Activity)context,R.layout.data_add2_dialog, new View.OnClickListener() {
            @Override
                    public void onClick(View view) {
                    if(view.getId() == R.id.btn_edit_save){
                        EditText water = (EditText) myEditPersonDialog.findViewById(R.id.add2_water);
                        EditText electric = (EditText) myEditPersonDialog.findViewById(R.id.add2_electric);
                        EditText dateTime = (EditText) myEditPersonDialog.findViewById(R.id.add2_day);

                        myRoomDataMap.clear();
                        myRoomDataMap.put(info.roomList.get(0), room_number);
                        myRoomDataMap.put(info.roomList.get(1), dateTime.getText().toString());
                        myRoomDataMap.put(info.roomList.get(2), water.getText().toString());
                        myRoomDataMap.put(info.roomList.get(3), electric.getText().toString());

                        if(setRoomData()){
                            Toast.makeText(context,"插入数据成功",Toast.LENGTH_SHORT).show();
                            updateView();
                            myEditPersonDialog.dismiss();
                        }else {
                            Toast.makeText(context,"插入数据失败，请重试",Toast.LENGTH_SHORT).show();
                        }
                    }
            }
        });
        myEditPersonDialog.show();
    }

    public boolean setData() {
        if (myDataMap.isEmpty()) {
            Log.e(getClass().getName(), "RenterMap is empty!!!!");
            return false;
        }
        DataExchanger dataExchanger = new DataExchanger(context);
        dataExchanger.SetData(room_number, myDataMap);
        return true;
    }

    public boolean setRoomData()
    {

        if(myRoomDataMap.isEmpty()){
            Log.e(getClass().getName(),"RoomMap is empty!!!!");
            return false;
        }

        DataExchanger dataExchanger = new DataExchanger(context);
        dataExchanger.SetRoomData(room_number,myRoomDataMap);
        return true;
    }
}

