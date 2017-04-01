package com.example.simpler.rentmanagerdemo;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MainActivity extends Activity{

    Context context;
    MyListAdapter myListAdapter;
    LayoutInflater root_if;
    List<View> viewList = new ArrayList<>();
    List<String> tabTList = new ArrayList<>();
    static int FLOOR_COUNT = 6;
    static int START_FLOOR = 3;
    static int ROOM_PER_FLOOR = 8;
    static int posit = 0;
    boolean INIT_FLAG = false;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = this;

        root_if = getLayoutInflater();
        for (int i = 0; i < FLOOR_COUNT; i++) {
            tabTList.add("Floor " + (i + START_FLOOR));
            View tempView;
            tempView = root_if.inflate(R.layout.viewpager_exp, null);
        viewList.add(tempView);
    }

    initView();
    }

    private void initView() {

        ViewPager mViewPager = (ViewPager) findViewById(R.id.myViewPager);
        mViewPager.setOffscreenPageLimit(FLOOR_COUNT);                                              //防止滑动销毁界面的问题，但是会加大内存消耗
        mViewPager.setAdapter(new MyPagerAdapter(viewList, tabTList));

        myListAdapter = new MyListAdapter(context, getData(posit + START_FLOOR), R.layout.my_list_view);    //初始化第一页
        ((ListView)viewList.get(posit).findViewById(R.id.list_view)).setAdapter(myListAdapter);

        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(final int position) {

                posit = position;
                ListView lv = (ListView) viewList.get(position).findViewById(R.id.list_view);
                myListAdapter = new MyListAdapter(context, getData(position + START_FLOOR), R.layout.my_list_view);
                lv.setAdapter(myListAdapter);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }


    private ArrayList<Map<String, String>> getData(int posit) {
        ArrayList<Map<String, String>> data = new ArrayList<>();
        DataExchanger dataExchanger = new DataExchanger(context);
        for(int j=1;j<=ROOM_PER_FLOOR;j++)
        {
            if(INIT_FLAG)
            {
                dataExchanger.initData(posit+"0"+j);
                dataExchanger.initRoom(posit+"0"+j);
            }
            data.add( dataExchanger.getData(posit+"0"+j));
        }
        return data;
    }

    public  void TestInit(View v)
    {
        INIT_FLAG = !INIT_FLAG;
        Toast.makeText(context,INIT_FLAG+"",Toast.LENGTH_SHORT).show();
        myUpdateView();
    }
    public void TestDrop(View v)
    {
        DataExchanger dataExchanger = new DataExchanger(context);
        dataExchanger.DropAll();
        Toast.makeText(context,"数据库已清除",Toast.LENGTH_SHORT).show();
        myUpdateView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        myUpdateView();
    }

    //更新界面中的ListView用
    public void myUpdateView() {
        if(myListAdapter!=null) myListAdapter.UpdateView(getData(posit + START_FLOOR));
    }
}
