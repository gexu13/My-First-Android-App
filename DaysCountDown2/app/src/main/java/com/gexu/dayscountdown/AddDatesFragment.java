package com.gexu.dayscountdown;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethod;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddDatesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddDatesFragment extends Fragment {
    private TextView myTextView;
    private DatePickerDialog.OnDateSetListener myDateSetListener;
    private EditText editTextEvent;
    private String date;
    private CountDays countDays = null;
    private static final String TAG = "Calender";


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AddDatesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddDatesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AddDatesFragment newInstance(String param1, String param2) {
        AddDatesFragment fragment = new AddDatesFragment();
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
        return inflater.inflate(R.layout.fragment_add_dates, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
//        Log.d(TAG, "Create?");
        super.onActivityCreated(savedInstanceState);
        editTextEvent = getView().findViewById(R.id.editTextEvent);
        Button buttonSave1 = getView().findViewById(R.id.button2);
        Button buttonSave2 = getView().findViewById(R.id.button3);
        //进入addDateFragment自动弹出键盘
        editTextEvent.requestFocus();
        InputMethodManager inputMethodManager = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.showSoftInput(editTextEvent,0);
        //点击save Button1 获取保存数据名称并切换回主页面
        buttonSave1.setOnClickListener(new View.OnClickListener() {
            //添加数据
            @Override
            public void onClick(View v) {
                String event = editTextEvent.getText().toString();
//                if(TextUtils.isEmpty(event)){
//                    event = " ";
//                }
                //计算天数差
                //获取date里的年月日（用户选择的日期）
                if(date == null){
                    Toast.makeText(getContext().getApplicationContext(),getString(R.string.selectDate),Toast.LENGTH_LONG).show();
                }
                else{
                    String [] YYYY_MM_DD = date.split("/");
                    countDays = new CountDays(Integer.parseInt(YYYY_MM_DD[0]),
                            Integer.parseInt(YYYY_MM_DD[1]),
                            Integer.parseInt(YYYY_MM_DD[2]));
                    countDays.getDayCount(getContext());

//                Log.d(TAG, "DATE"+Integer.parseInt(YYYY_MM_DD[0])+"/"+Integer.parseInt(YYYY_MM_DD[1])+"/"+Integer.parseInt(YYYY_MM_DD[2]));
//                Log.d(TAG, "DATE"+countDays.absDiffValue+"/"+countDays.untilOrSince+"date="+date);

                    DateAndEvent dateAndEvent = new DateAndEvent(event,countDays.absDiffValue,countDays.untilOrSince,date,countDays.startDateOrTargetDate);
                    MainFragment.myViewModel.InsertDateAndEvent(dateAndEvent);

                    //收回键盘
                    InputMethodManager inputMethodManager = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                    inputMethodManager.hideSoftInputFromWindow(v.getWindowToken(),0);

                    //页面切换
                    NavController navController = Navigation.findNavController(v);
                    navController.navigate(R.id.action_addDatesFragment_to_mainFragment);
                }
            }
        });
        ////点击save Button2 获取保存数据名称并切换回主页面
        buttonSave2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String event = editTextEvent.getText().toString();
//                if(TextUtils.isEmpty(event)){
//                    event = " ";
//                }
                //计算天数差
                //获取date里的年月日（用户选择的日期）
                if(date == null){
                    Toast.makeText(getContext().getApplicationContext(),getString(R.string.selectDate),Toast.LENGTH_LONG).show();
                }
                else{
                    String [] YYYY_MM_DD = date.split("/");
                    countDays = new CountDays(Integer.parseInt(YYYY_MM_DD[0]),
                            Integer.parseInt(YYYY_MM_DD[1]),
                            Integer.parseInt(YYYY_MM_DD[2]));
                    countDays.getDayCount(getContext());

//                Log.d(TAG, "DATE"+Integer.parseInt(YYYY_MM_DD[0])+"/"+Integer.parseInt(YYYY_MM_DD[1])+"/"+Integer.parseInt(YYYY_MM_DD[2]));
//                Log.d(TAG, "DATE"+countDays.absDiffValue+"/"+countDays.untilOrSince+"date="+date);

                    DateAndEvent dateAndEvent = new DateAndEvent(event,countDays.absDiffValue,countDays.untilOrSince,date,countDays.startDateOrTargetDate);
                    MainFragment.myViewModel.InsertDateAndEvent(dateAndEvent);

                    //收回键盘
                    InputMethodManager inputMethodManager = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                    inputMethodManager.hideSoftInputFromWindow(v.getWindowToken(),0);

                    //页面切换
                    NavController navController = Navigation.findNavController(v);
                    navController.navigate(R.id.action_addDatesFragment_to_mainFragment);
                }
            }
        });

        //选择时间
        myTextView = getView().findViewById(R.id.textView);
        myTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal  = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(),
                        AlertDialog.THEME_HOLO_LIGHT,myDateSetListener,year,month,day);
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();
            }
        });
        //得到用户设置的时间
        myDateSetListener  = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;
                date  = year + "/" + month + "/" + dayOfMonth;
                myTextView.setText(date);
            }
        };






    }


}