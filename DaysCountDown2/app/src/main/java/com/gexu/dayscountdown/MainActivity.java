package com.gexu.dayscountdown;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "Swipe";
    RecyclerView myRecyclerView;
    MyRecyclerViewAdapter myRecyclerViewAdapter;
    MyViewModel myViewModel;
    Button button5;
    NavController navController;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



//        myViewModel = ViewModelProviders.of(this).get(MyViewModel.class);
//        //设置RecyclerView和Adapter
//        myRecyclerView = findViewById(R.id.RecyclerView);
//        myRecyclerViewAdapter = new MyRecyclerViewAdapter();
//        myRecyclerView.setLayoutManager(new LinearLayoutManager(this) );
//        myRecyclerView.setAdapter(myRecyclerViewAdapter);


//        //liveData检测数据改变
//        myViewModel.getDateAndEventList().observe(this, new Observer<List<DateAndEvent>>() {
//            @Override
//            public void onChanged(List<DateAndEvent> dateAndEventList) {
//                //将数据传入给Adapter
//                myRecyclerViewAdapter.setDateAndEvent(dateAndEventList);
//                //通知刷新
//                myRecyclerViewAdapter.notifyDataSetChanged();
//            }
//        });

//        button5 = findViewById(R.id.button5);
//        button5.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                DateAndEvent dateAndEvent1 = new DateAndEvent("Christmas Day", 120);
//                DateAndEvent dateAndEvent2 = new DateAndEvent("New year", 150);
//                myViewModel.InsertDateAndEvent(dateAndEvent1,dateAndEvent2);
//            }
//        });


//        TextView textView;
//        textView = findViewById(R.id.textViewCount);
//        textView.setText("days until");


        //actionBar返回按键
        navController = Navigation.findNavController(this, R.id.fragment);
        NavigationUI.setupActionBarWithNavController(this , navController);
    }
    //actionBar返回按键
    @Override
    public boolean onSupportNavigateUp() {
        //在addDates界面按退出时，弹出一个提醒框
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        if(navController.getCurrentDestination().getId() == R.id.addDatesFragment){
            builder.setTitle(getString(R.string.leave_title));
            builder.setPositiveButton(R.string.yes_button, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    navController.navigateUp();
                }
            });
            builder.setNegativeButton(R.string.no_button, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });

            //创建提示框
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        }
        //在其他界面按返回按键
        else{
            builder.setTitle(getString(R.string.quit_title));
            builder.setPositiveButton(R.string.yes_button, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            });
            builder.setNegativeButton(R.string.no_button, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            //创建提示框
            AlertDialog alertDialog = builder.create();
            alertDialog.show();


        }
//        navController = Navigation.findNavController(this , R.id.fragment);
        return super.onSupportNavigateUp();
    }
    //系统返回键
    @Override
    public void onBackPressed() {
        onSupportNavigateUp();
    }

    @Override
    protected void onResume() {
        Log.d(TAG, "List In MainActivity="+MainFragment.myViewModel.getDateAndEventList().getValue());
//        if (MainFragment.myViewModel.getDateAndEventList().getValue() != null){
//            updateDays(MainFragment.myViewModel.getDateAndEventList().getValue());
//        }

        super.onResume();
    }
    //刷新
//    void updateDays(List<DateAndEvent> dateAndEventList){
//            for(int i = 0; i<dateAndEventList.size();i++){
//                DateAndEvent temp = dateAndEventList.get(i);
//                int tempID = temp.getId();
//                String [] daySetBefore =  temp.getDate().split("/");
//                CountDays countDays = new CountDays(Integer.parseInt(daySetBefore[0]),Integer.parseInt(daySetBefore[1]),Integer.parseInt(daySetBefore[2]));
//                countDays.getDayCount(this);
//                DateAndEvent newDateAndEvent = new DateAndEvent(temp.getEvent(),countDays.absDiffValue,countDays.untilOrSince,temp.getDate(),countDays.startDateOrTargetDate);
//                newDateAndEvent.setId(tempID);
//                MainFragment.myViewModel.UpdateDateAndEvent(newDateAndEvent);
//        }
//
//    }
    //    @Override
//    public boolean onNavigateUp() {
//        //退出提醒框
//        if(navController.getCurrentDestination().getId() == R.id.addDatesFragment){
//            AlertDialog.Builder builder = new AlertDialog.Builder(this);
//            builder.setTitle(getString(R.string.quit_title));
//            builder.setPositiveButton(R.string.yes_button, new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialog, int which) {
//                    navController.navigateUp();
//                }
//            });
//            builder.setNegativeButton(R.string.no_button, new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialog, int which) {
//
//                }
//            });
//            AlertDialog alertDialog = builder.create();
//            alertDialog.show();
//        }
//
//
//    }
}