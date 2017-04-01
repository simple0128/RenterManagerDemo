package com.example.simpler.rentmanagerdemo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Simpler on 2017/2/20.
 */
public class MySQLiteHelper implements IDataHandle {

    Context context;
    InfoSample info = new InfoSample();
    String path = null;
    String DATA_NAME = "renter";
    String TABLE_NAME = "RenterInfo";
    String TABLE_ROOM = "RoomInfo";
    SQLiteDatabase myDataBase;
    List<String> infoList = new ArrayList<String>();
    List<String> roomInfoList = new ArrayList<String>();


    /**
     * @param room 房间号
     */
    @Override
    public void initData(String room) {

        ContentValues noDataValues = new ContentValues();
        noDataValues.put(infoList.get(0), room);
        noDataValues.put(infoList.get(1), "No Person here now.");
        noDataValues.put(infoList.get(2), "000-0000-0000");
        noDataValues.put(infoList.get(3), "无限制");
        noDataValues.put(infoList.get(4), "1200.0");

        myDataBase.insert(TABLE_NAME, null, noDataValues);

        Log.i(getClass().getName(), ".......Room " + room + " has been initialization!!!");
    }


    @Override
    public void initRoom(String room) {
        ContentValues noDataValues = new ContentValues();
        noDataValues.put(roomInfoList.get(0), room);
        noDataValues.put(roomInfoList.get(1), "20120101");
        noDataValues.put(roomInfoList.get(2), "1");
        noDataValues.put(roomInfoList.get(3), "1200");
        noDataValues.put(roomInfoList.get(4), "200.0");
        noDataValues.put(roomInfoList.get(5), "200.0");

        myDataBase.insert(TABLE_ROOM, null, noDataValues);
        Log.i(getClass().getName(), ".......Room " + room + " Information has been initialization");
    }


    public MySQLiteHelper(Context context) {

        this.context = context;
        path = context.getFilesDir().getPath() + DATA_NAME + ".db";

        infoList = info.renterList;
        roomInfoList = info.roomList;
        OpenOrCreateData();
    }

    @Override
    public ArrayList<HashMap<String, String>> getRoomData(String room) {
        ArrayList<HashMap<String, String>> rtnList = new ArrayList<>();
        Cursor cursor = myDataBase.rawQuery("select * from " + TABLE_ROOM + " where room_number=?", new String[]{room});

        Toast.makeText(context,""+cursor.moveToFirst(),Toast.LENGTH_SHORT).show();
        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> tempMap = new HashMap<>();
                String date = "", water = "", electric = "",fee = "";

                date = cursor.getString(cursor.getColumnIndex(roomInfoList.get(1)));
                water = cursor.getString(cursor.getColumnIndex(roomInfoList.get(2)));
                electric = cursor.getString(cursor.getColumnIndex(roomInfoList.get(3)));
                fee = cursor.getString(cursor.getColumnIndex(roomInfoList.get(4)));

                tempMap.put(roomInfoList.get(0), room);
                tempMap.put(roomInfoList.get(1), date);
                tempMap.put(roomInfoList.get(2), water);
                tempMap.put(roomInfoList.get(3), electric);
                tempMap.put(roomInfoList.get(3), fee);

                rtnList.add(tempMap);
            } while (cursor.moveToNext());
        }

        cursor.close();

        if (!rtnList.isEmpty()) return rtnList;
        else {
            Log.i(getClass().getName(), "........Null RoomMap has Returned");
            return null;
        }
    }

    /**
     * @param room 房间号
     * @return 返回对应房间的映射表，包含房间号，住户名字，住户电话，住户租期，每月费用
     */
    @Override
    public HashMap<String, String> getData(String room) {

        HashMap<String, String> rtnMap = new HashMap<>();
        String renter_name = "", renter_phone = "", renter_time = "", renter_fee = "";
        Cursor cursor = myDataBase.rawQuery("select * from " + TABLE_NAME + " where room_number=?", new String[]{room});

        if (cursor.moveToFirst()) {
            renter_name = cursor.getString(cursor.getColumnIndex(infoList.get(1)));
            renter_phone = cursor.getString(cursor.getColumnIndex(infoList.get(2)));
            renter_time = cursor.getString(cursor.getColumnIndex(infoList.get(3)));
            renter_fee = cursor.getString(cursor.getColumnIndex(infoList.get(4)));
        }

        rtnMap.put(infoList.get(0), room);
        rtnMap.put(infoList.get(1), renter_name);
        rtnMap.put(infoList.get(2), renter_phone);
        rtnMap.put(infoList.get(3), renter_time);
        rtnMap.put(infoList.get(4), renter_fee);

        cursor.close();

        if (!rtnMap.isEmpty()) return rtnMap;
        else {
            Log.w(getClass().getName(), "......Null RenterMap has Returned!!!!");
            return null;
        }

    }

    @Override
    public void SetRoomData(String room_number, HashMap<String, String> myData) {

        ContentValues contentValues = new ContentValues();
        for (String tempStr : roomInfoList
                ) {
            contentValues.put(tempStr, myData.get(tempStr));
        }
//        String whereClause = "room_number=?";
//        String[] whereArgs = {String.valueOf(roomId)};
        //////////////////////////////////////////////////////////////准备实现加入每月费用数据时候的重复验证////////////////////////
        myDataBase.insert(TABLE_ROOM,null ,contentValues);
    }

    /**
     * @param roomId 房间号
     * @param myData 对应房间的映射表
     */
    @Override
    public void SetData(String roomId, HashMap<String, String> myData) {

        ContentValues contentValues = new ContentValues();
        for (String tempStr : infoList
                ) {
            contentValues.put(tempStr, myData.get(tempStr));
        }

        String whereClause = "room_number=?";
        String[] whereArgs = {String.valueOf(roomId)};
        myDataBase.update(TABLE_NAME, contentValues, whereClause, whereArgs);

    }

    private void OpenOrCreateData() {
        myDataBase = SQLiteDatabase.openOrCreateDatabase(path, null);
        String create_table = "create table if not exists RenterInfo(room_number primary key ,renter_name text,renter_phone text,renter_time,renter_fee)";
        String create_room = "create table if not exists RoomInfo(room_number primary key ,date,water,electric,fee)";
        myDataBase.execSQL(create_table);
        myDataBase.execSQL(create_room);
        Log.i(getClass().getName(), "Open Database successful...");
    }

    private void CloseDatabase() {
        myDataBase.close();
    }

    public void DropAllDatabase() {
        String dropRenter = "drop table RenterInfo";
        String dropRoom = "drop table RoomInfo";
        myDataBase.execSQL(dropRenter);
        myDataBase.execSQL(dropRoom);
    }
}
