package com.example.simpler.rentmanagerdemo;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;

/**
 * Created by Simpler on 2017/2/20.
 */
public class MyAddPersonDialog extends Dialog {

    Activity context;
    Button btn_save;
    Button btn_cancel;
    EditText edit_dateTime;
    View.OnClickListener mClickListener;
    Calendar calendar;
    int tLayout;

    MyAddPersonDialog(Activity context) {
        super(context);
        this.context = context;
    }

    MyAddPersonDialog(Activity context, int layout_dialog, View.OnClickListener clickListener) {
        super(context);
        this.context = context;
        mClickListener = clickListener;
        this.tLayout = layout_dialog;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(tLayout);

        btn_save = (Button) findViewById(R.id.btn_edit_save);
        btn_cancel = (Button) findViewById(R.id.btn_edit_cancel);

        Window tWin = this.getWindow();
        WindowManager winManger = context.getWindowManager();
        Display dWin = winManger.getDefaultDisplay();
        WindowManager.LayoutParams wlp = tWin.getAttributes();
        wlp.height = (int) (dWin.getHeight() * 0.6);
        wlp.width = (int) (dWin.getWidth() * 0.6);
        tWin.setAttributes(wlp);

        btn_save.setOnClickListener(mClickListener);
        btn_cancel.setOnClickListener(mClickListener);
        this.setCancelable(true);



        if (tLayout == R.layout.data_add2_dialog) {
            calendar = Calendar.getInstance();

            ((EditText) findViewById(R.id.add2_day)).setText((new StringBuilder()
                            .append(calendar.get(Calendar.YEAR))
                            .append((calendar.get(Calendar.MONTH) - 1) < 10 ? "0"
                                    + (calendar.get(Calendar.MONTH) + 1) : (calendar.get(Calendar.MONTH) + 1))
                            .append((calendar.get(Calendar.DAY_OF_MONTH) < 10) ? "0"
                                    + calendar.get(Calendar.DAY_OF_MONTH) : calendar.get(Calendar.DAY_OF_MONTH))
                    )
            );



            edit_dateTime = (EditText) findViewById(R.id.add2_day);
            edit_dateTime.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                            calendar.set(year, month, day);

                            ((EditText) findViewById(R.id.add2_day)).setText((new StringBuilder()
                                            .append(calendar.get(Calendar.YEAR))
                                            .append((calendar.get(Calendar.MONTH) - 1) < 10 ? "0"
                                                    + (calendar.get(Calendar.MONTH) + 1) : (calendar.get(Calendar.MONTH) + 1))
                                            .append((calendar.get(Calendar.DAY_OF_MONTH) < 10) ? "0"
                                                    + calendar.get(Calendar.DAY_OF_MONTH) : calendar.get(Calendar.DAY_OF_MONTH))
                                    )
                            );

                        }
                    }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
                }
            });


        }
    }

}
