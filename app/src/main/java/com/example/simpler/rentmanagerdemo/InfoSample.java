package com.example.simpler.rentmanagerdemo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Simpler on 2017/3/1.
 */
public class InfoSample {

    List<String> renterList = new ArrayList<>();
    List<String> roomList = new ArrayList<>();
    List<String> oRoomList = new ArrayList<>();

    InfoSample()
    {
        RenterInfo();
        RoomInfo();
        ORoomInfo();
    }

    private void RenterInfo()
    {
        renterList.add("room_number");
        renterList.add("renter_name");
        renterList.add("renter_phone");
        renterList.add("renter_time");
        renterList.add("renter_fee");
    }

    private void RoomInfo()
    {
        roomList.add("room_number");
        roomList.add("date");
        roomList.add("water");
        roomList.add("electric");
        roomList.add("fee");
    }

    private void ORoomInfo()
    {
        oRoomList.add("room_number");
        oRoomList.add("year");
        oRoomList.add("month");
        oRoomList.add("day");
        oRoomList.add("water");
        oRoomList.add("electric");
    }
}
