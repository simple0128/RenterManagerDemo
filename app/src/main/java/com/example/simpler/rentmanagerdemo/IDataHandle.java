package com.example.simpler.rentmanagerdemo;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Simpler on 2017/2/20.
 */
public interface IDataHandle {


    /**
     * @param room 房间号
     */
    HashMap<String,String> getData(String room);

    /**
     * @param room 房间号
     */
    ArrayList<HashMap<String,String>> getRoomData(String room);

    /**
     * @param room_number 房间号
     * @param myData 对应房间的映射表
     */
    void SetData(String room_number,HashMap<String,String> myData);

    /**
     * @param room_number 房间号
     * @param myData 对应房间的映射表
     */
    void SetRoomData(String room_number,HashMap<String,String> myData);
    /**
     * @param room 房间号
     */
    void initData(String room);

    /**
     * @param room 房间号
     */
    void initRoom(String room);



}
