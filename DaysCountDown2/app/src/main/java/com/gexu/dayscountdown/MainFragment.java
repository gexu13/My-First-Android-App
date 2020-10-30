package com.gexu.dayscountdown;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.TextView;

import java.util.Calendar;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MainFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MainFragment extends Fragment {
    private static final String TAG = "Swipe";
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    static MyViewModel myViewModel;
    Button button5, buttonDeleteAll;
    RecyclerView myRecyclerView;
    MyRecyclerViewAdapter myRecyclerViewAdapter;
    private List<DateAndEvent> myDateAndEventList;
    private List<DateAndEvent> myNewDateAndEventList;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public MainFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MainFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MainFragment newInstance(String param1, String param2) {
        MainFragment fragment = new MainFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }






    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false);


    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        myViewModel = ViewModelProviders.of(this).get(MyViewModel.class);
        //设置RecyclerView和Adapter
        myRecyclerView = getView().findViewById(R.id.RecyclerView);
        myRecyclerViewAdapter = new MyRecyclerViewAdapter();
        myRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        myRecyclerView.setAdapter(myRecyclerViewAdapter);



        //liveData检测数据改变
        myViewModel.getDateAndEventList().observe(getViewLifecycleOwner(), new Observer<List<DateAndEvent>>() {
            @Override
            public void onChanged(List<DateAndEvent> dateAndEventList) {
                myDateAndEventList = dateAndEventList;

                //将数据传入给Adapter
                myRecyclerViewAdapter.setDateAndEvent(dateAndEventList);
                //通知刷新
                myRecyclerViewAdapter.notifyDataSetChanged();

            }
        });
        //Add按钮
        Button buttonAdd;
        buttonAdd = getView().findViewById(R.id.button);
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            //点击切换到add页面
            @Override
            public void onClick(View v) {
                NavController navController = Navigation.findNavController(v);
                navController.navigate(R.id.action_mainFragment_to_addDatesFragment);
            }
        });
//        //test add Button
//        button5 = getView().findViewById(R.id.button5);
//        button5.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                DateAndEvent dateAndEvent1 = new DateAndEvent("Christmas Day", 120,"Days Until","1994/06/13","Start Date");
//                DateAndEvent dateAndEvent2 = new DateAndEvent("New year", 150,"Days Until","1994/06/13","Start Date");
//                myViewModel.InsertDateAndEvent(dateAndEvent1,dateAndEvent2);
//            }
//        });

//
//        //test delete all
//        buttonDeleteAll = getView().findViewById(R.id.button6);
//        buttonDeleteAll.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                myViewModel.DeleteAllDateAndEvent();
//            }
//        });

        //滑动删除
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                final DateAndEvent dateAndEventToBeDeleted = myDateAndEventList.get(viewHolder.getAdapterPosition());
                //删除时弹出提醒框
                AlertDialog.Builder builder = new AlertDialog.Builder(requireActivity());
                builder.setTitle(getString(R.string.delete_title));
                builder.setPositiveButton(R.string.yes_button, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        myViewModel.DeleteDateAndEvent(dateAndEventToBeDeleted);
                    }
                });
                builder.setNegativeButton(R.string.no_button, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //刷新recyclerView
                        myRecyclerViewAdapter.notifyDataSetChanged();

                    }
                });

                //创建提示框
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
//                myViewModel.DeleteDateAndEvent(dateAndEventToBeDeleted);

            }
        }).attachToRecyclerView(myRecyclerView);

    }
    //切换回MainFragment时，收回键盘
    @Override
    public void onResume() {
        //收回键盘
        InputMethodManager inputMethodManager = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(getView().getWindowToken(),0);
        //刷新days
        if (myViewModel.getDateAndEventList().getValue() != null){
            updateDays(myViewModel.getDateAndEventList().getValue());
        }

        super.onResume();
//        Log.d(TAG,"List"+myNewDateAndEventList );

    }

    void updateDays(List<DateAndEvent> dateAndEventList){
        for(int i = 0; i<dateAndEventList.size();i++){
            DateAndEvent temp = dateAndEventList.get(i);
            int tempID = temp.getId();
            String [] daySetBefore =  temp.getDate().split("/");
            CountDays countDays = new CountDays(Integer.parseInt(daySetBefore[0]),Integer.parseInt(daySetBefore[1]),Integer.parseInt(daySetBefore[2]));
            countDays.getDayCount(getContext());
            DateAndEvent newDateAndEvent = new DateAndEvent(temp.getEvent(),countDays.absDiffValue,countDays.untilOrSince,temp.getDate(),countDays.startDateOrTargetDate);
            newDateAndEvent.setId(tempID);
            myViewModel.UpdateDateAndEvent(newDateAndEvent);
        }

    }


}