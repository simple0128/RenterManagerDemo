package com.example.simpler.rentmanagerdemo;

import android.content.Context;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Simpler on 2017/2/20.
 */
public class DataExchanger implements IDataHandle {
    MySQLiteHelper mySQLiteHelper;
    Context context;
    InfoSample info = new InfoSample();

    /**
     * @param context 设备上下文
     */
    DataExchanger(Context context) {
        this.context = context;
        mySQLiteHelper = new MySQLiteHelper(context);
    }

    /**
     * @param room 房间号
     * @return 返回对应数据映射表
     */
    @Override
    public HashMap<String, String> getData(String room) {
        return mySQLiteHelper.getData(room);
    }

    /**
     *
     * @param room 房间号
     * @return
     */
    @Override
    public ArrayList<HashMap<String, String>> getRoomData(String room) {

        ArrayList<HashMap<String, String>> rtnList=new ArrayList<>();
        ArrayList<HashMap<String, String>> myDataList = mySQLiteHelper.getRoomData(room);

        if(myDataList == null) return null;
        for (HashMap<String,String> tMap:myDataList
             ) {
            HashMap<String,String> tempMap = new HashMap<>();
            tempMap.clear();
            tempMap.put(info.oRoomList.get(0), tMap.get(info.roomList.get(0)));
            tempMap.put(info.oRoomList.get(1),tMap.get(info.roomList.get(1)).substring(0, 4));
            tempMap.put(info.oRoomList.get(2), tMap.get(info.roomList.get(1)).substring(4, 6));
            tempMap.put(info.oRoomList.get(3),tMap.get(info.roomList.get(1)).substring(6,8));
            tempMap.put(info.oRoomList.get(4),tMap.get(info.roomList.get(2)));
            tempMap.put(info.oRoomList.get(5),tMap.get(info.roomList.get(3)));
            rtnList.add(tempMap);
        }

        return rtnList;
    }

    /**
     * @param room 房间号
     */
    @Override
    public void initData(String room) {
        mySQLiteHelper.initData(room);
    }

    @Override
    public void initRoom(String room) {
        mySQLiteHelper.initRoom(room);
    }


    /**
     * @param room   房间号
     * @param myData 对应房间的映射表
     */
    @Override
    public void SetData(String room, HashMap<String, String> myData) {

        mySQLiteHelper.SetData(room, myData);

    }

    @Override
    public void SetRoomData(String room_number, HashMap<String, String> myData) {

        mySQLiteHelper.SetRoomData(room_number, myData);
    }

    public void DropAll()
    {
        mySQLiteHelper.DropAllDatabase();
    }
}
