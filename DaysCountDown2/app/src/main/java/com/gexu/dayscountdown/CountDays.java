package com.gexu.dayscountdown;

import android.content.Context;
import android.util.Log;

import java.time.Year;
import java.util.Calendar;

public class CountDays{
    int year;
    int month;
    int day;
    //天数差，正为until，负为since
    int diffValue;
    int absDiffValue;
    String untilOrSince;
    String startDateOrTargetDate;
    int daysInMonthFront, daysInMonthRear,daysInBetween;
    Integer [] daysOfMonthLeapYear = {31,29,31,30,31,30,31,31,30,31,30,31};
    Integer [] daysOfMonthCommonYear = {31,28,31,30,31,30,31,31,30,31,30,31};
    int daysInYearFront, daysInYearRear;
    int daysBetweenYears;
    int daysOfLeapYear = 366;
    int daysOfCommonYear = 365;
    private static final String TAG = "diff";
    public CountDays(int year,int month, int day){
        this.year = year;
        this.month = month;
        this.day = day;

    }

    public void getDayCount(Context context){
        Calendar cal  = Calendar.getInstance();
        int calYear = cal.get(Calendar.YEAR);
        int calMonth = cal.get(Calendar.MONTH)+1;
        int calDay = cal.get(Calendar.DAY_OF_MONTH);
        Log.d(TAG, "Month"+(calYear));
        absDiffValue = 50;
        untilOrSince = context.getString(R.string.Until);

//        //TEST
//        untilOrSince = context.getString(R.string.Since);
//        //设置的年的月还剩多少天
//        daysInMonthFront = daysOfMonthLeapYear[this.month - 1] - this.day;
//        //日历的年的月过了多少天
//        daysInMonthRear = calDay;
//        //设置的年还剩多少月
//        for(int i = 0 ; i<12 - this.month; i++){
//            daysInYearFront += daysOfMonthLeapYear[this.month+i];
//        }
//        //日历的年过了多少月
//        for(int i = 0 ; i<calMonth-1 ;i++){
//            daysInYearRear += daysOfMonthCommonYear[i];
//        }
//        //设置年与日历年中间年的天数
//        int temp1 = 0;
//        int temp2 = 0;
//        for(int i = 1 ; i<  calYear - this.year ;i++){
//            //判断中间年是平年还是闰年
//            //如果中间年为闰年
//            if((((this.year+i) % 4 == 0)&&((this.year+i) % 100 != 0))||((this.year+i) %400 == 0)){
//                temp1 =temp1 + 366;
//            }
//            //中间年为平年
//            else{
//                temp2= temp2+ 365;
//            }
//        }
//        daysBetweenYears = temp1 + temp2;
//        //最后把所有天数加起来
//        absDiffValue = daysInMonthFront + daysInMonthRear + daysInYearFront +daysInYearRear+
//                daysBetweenYears;
//        Log.d(TAG, "Month"+(absDiffValue +" "+ daysInMonthFront +" "+ daysInMonthRear +" "+ daysInYearFront +" "+daysInYearRear+" "+
//                daysBetweenYears));

        //判断设置年是否闰年
        //设置年是闰年
        if(((this.year % 4 == 0)&&(this.year % 100 != 0))||(this.year%400 == 0)){
            //如果设置的年与日历年相等
            if(this.year == calYear){
                //如果设置的月与日历月相等
                if(this.month == calMonth){
                    //如果设置的日与日历日相等
                    if (this.day == calDay){
//                        Log.d(TAG, "untilOrSince"+(R.string.Until));
                        //天数差为零
                        diffValue = this.day - calDay;
                        absDiffValue = Math.abs(diffValue);
//                        untilOrSince = "Days test";
                        untilOrSince = context.getString(R.string.Until);
                        startDateOrTargetDate = context.getString(R.string.Target_Date);
                    }
                    //如果设置的日与日历日不相等
                    else{
                        diffValue = this.day - calDay;

                        absDiffValue = Math.abs(diffValue);
                        //差值大于零：Until
                        if(diffValue > 0) {
                            untilOrSince = context.getString(R.string.Until);
                            startDateOrTargetDate = context.getString(R.string.Target_Date);
                        }
                        //差值小于零：Since
                        else{
                            untilOrSince = context.getString(R.string.Since);
                            startDateOrTargetDate = context.getString(R.string.Start_Date);

                        }
                    }

                }
                //如果设置的月与日历月不相等
                else{
                    diffValue = this.month - calMonth;
                    //如果设置的月大于日历月
                    if(this.month - calMonth > 0){
                        untilOrSince = context.getString(R.string.Until);
                        startDateOrTargetDate = context.getString(R.string.Target_Date);
                        //计算最前月剩余多少天
                        daysInMonthFront = daysOfMonthLeapYear[calMonth-1] - calDay;
                        //计算最后月过了多少天
                        daysInMonthRear = this.day;
                        //计算中间有多少天
                        for(int i = 0; i<this.month-calMonth-1;i++){
                            daysInBetween += daysOfMonthLeapYear[i+calMonth];
                        }
                        absDiffValue = daysInMonthFront + daysInMonthRear + daysInBetween;
                    }
                    //如果设置的月小于日历月
                    else{
                        untilOrSince = context.getString(R.string.Since);
                        startDateOrTargetDate = context.getString(R.string.Start_Date);
                        //计算最前月剩余多少天
                        daysInMonthFront = daysOfMonthLeapYear[this.month-1] - this.day;
                        //计算最后月过了多少天
                        daysInMonthRear = calDay;
                        //计算中间有多少天
                        for(int i = 0; i<calMonth - this.month - 1;i++){
                            daysInBetween += daysOfMonthLeapYear[i+this.month];
                        }
                        absDiffValue = daysInMonthFront + daysInMonthRear + daysInBetween;
                    }
                }
            }
            //设置的年与日历年不相等
            else{
                //如果日历年是闰年
                if(((calYear % 4 == 0)&&(calYear % 100 != 0))||(calYear%400 == 0)) {
                    //如果设置的年大于日历年
                    if (this.year > calYear) {
                        untilOrSince = context.getString(R.string.Until);
                        startDateOrTargetDate = context.getString(R.string.Target_Date);
                        //设置的年的月过了多少天
                        daysInMonthRear = this.day;
                        //日历的年的月还剩多少天
                        daysInMonthFront = daysOfMonthLeapYear[calMonth - 1] - calDay;
                        //设置年过了多少月
                        for(int i = 0 ; i<this.month-1 ;i++){
                            daysInYearRear += daysOfMonthLeapYear[i];
                        }
                        //日历的年还剩多少月
                        for(int i = 0 ; i<12 - calMonth; i++){
                            daysInYearFront += daysOfMonthLeapYear[calMonth+i];
                        }
                        //设置年与日历年中间年的天数
                        for(int i = 1 ; i< this.year - calYear;i++){
                            //判断中间年是平年还是闰年
                            //如果中间年为闰年
                            if((((calYear+i) % 4 == 0)&&((calYear+i) % 100 != 0))||((calYear+i)%400 == 0)){
                                daysBetweenYears+= 366;
                            }
                            //中间年为平年
                            else{
                                daysBetweenYears+= 365;
                            }
                        }
                        //最后把所有天数加起来
                        absDiffValue = daysInMonthFront + daysInMonthRear + daysInYearFront +daysInYearRear+
                                daysBetweenYears;

                    }
                    //如果设置的年小于日历年
                    else{
                        untilOrSince = context.getString(R.string.Since);
                        startDateOrTargetDate = context.getString(R.string.Start_Date);
                        //设置的年的月还剩多少天
                        daysInMonthFront = daysOfMonthLeapYear[this.month - 1] - this.day;
                        //日历的年的月过了多少天
                        daysInMonthRear = calDay;
                        //设置的年还剩多少月
                        for(int i = 0 ; i<12 - this.month; i++){
                            daysInYearFront += daysOfMonthLeapYear[this.month+i];
                        }
                        //日历的年过了多少月
                        for(int i = 0 ; i<calMonth-1 ;i++){
                            daysInYearRear += daysOfMonthLeapYear[i];
                        }
                        //设置年与日历年中间年的天数
                        for(int i = 1 ; i<  calYear - this.year ;i++){
                            //判断中间年是平年还是闰年
                            //如果中间年为闰年
                            if((((this.year+i) % 4 == 0)&&((this.year+i) % 100 != 0))||((this.year)+i%400 == 0)){
                                daysBetweenYears+= 366;
                            }
                            //中间年为平年
                            else{
                                daysBetweenYears+= 365;
                            }
                        }
                        //最后把所有天数加起来
                        absDiffValue = daysInMonthFront + daysInMonthRear + daysInYearFront +daysInYearRear+
                                daysBetweenYears;


                    }
                }
                //如果日历年不是闰年
                else{
                    //如果设置的年大于日历年
                    if (this.year > calYear){
                        untilOrSince = context.getString(R.string.Until);
                        startDateOrTargetDate = context.getString(R.string.Target_Date);
                        //设置的年的月过了多少天
                        daysInMonthRear = this.day;
                        //日历的年的月还剩多少天
                        daysInMonthFront = daysOfMonthCommonYear[calMonth - 1] - calDay;
                        //设置年过了多少月
                        for(int i = 0 ; i<this.month-1 ;i++){
                            daysInYearRear += daysOfMonthLeapYear[i];
                        }
                        //日历的年还剩多少月
                        for(int i = 0 ; i<12 - calMonth; i++){
                            daysInYearFront += daysOfMonthCommonYear[calMonth+i];
                        }
                        //设置年与日历年中间年的天数
                        for(int i = 1 ; i< this.year - calYear;i++){
                            //判断中间年是平年还是闰年
                            //如果中间年为闰年
                            if((((calYear+i) % 4 == 0)&&((calYear+i) % 100 != 0))||((calYear+i)%400 == 0)){
                                daysBetweenYears+= 366;
                            }
                            //中间年为平年
                            else{
                                daysBetweenYears+= 365;
                            }
                        }
                        //最后把所有天数加起来
                        absDiffValue = daysInMonthFront + daysInMonthRear + daysInYearFront +daysInYearRear+
                                daysBetweenYears;
                    }
                    //如果设置的年小于日历年
                    else{
                        untilOrSince = context.getString(R.string.Since);
                        startDateOrTargetDate = context.getString(R.string.Start_Date);
                        //设置的年的月还剩多少天
                        daysInMonthFront = daysOfMonthLeapYear[this.month - 1] - this.day;
                        //日历的年的月过了多少天
                        daysInMonthRear = calDay;
                        //设置的年还剩多少月
                        for(int i = 0 ; i<12 - this.month; i++){
                            daysInYearFront += daysOfMonthLeapYear[this.month+i];
                        }
                        //日历的年过了多少月
                        for(int i = 0 ; i<calMonth-1 ;i++){
                            daysInYearRear += daysOfMonthCommonYear[i];
                        }
                        //设置年与日历年中间年的天数
                        for(int i = 1 ; i<  calYear - this.year ;i++){
                            //判断中间年是平年还是闰年
                            //如果中间年为闰年
                            if((((this.year+i) % 4 == 0)&&((this.year+i) % 100 != 0))||((this.year+i)%400 == 0)){
                                daysBetweenYears+= 366;
                            }
                            //中间年为平年
                            else{
                                daysBetweenYears+= 365;
                            }
                        }
                        //最后把所有天数加起来
                        absDiffValue = daysInMonthFront + daysInMonthRear + daysInYearFront +daysInYearRear+
                                daysBetweenYears;
//                        Log.d(TAG, "Month"+(absDiffValue = daysInMonthFront + daysInMonthRear + daysInYearFront +daysInYearRear+
//                                daysBetweenYears));

                    }

                }
            }

         }
        //设置年不是闰年
        else{
            //如果设置的年与日历年相等
            if(this.year == calYear){
                //如果设置的月与日历月相等
                if(this.month == calMonth){
                    //如果设置的日与日历日相等
                    if(this.day ==calDay){
                        diffValue = this.day - calDay;
                        absDiffValue = Math.abs(diffValue);
                        untilOrSince = context.getString(R.string.Until);
                        startDateOrTargetDate = context.getString(R.string.Target_Date);

                    }
                    //如果设置的日与日历日不相等
                    else{
                        diffValue = this.day - calDay;

                        absDiffValue = Math.abs(diffValue);
                        //差值大于零：Until
                        if(diffValue > 0) {
                            untilOrSince = context.getString(R.string.Until);
                            startDateOrTargetDate = context.getString(R.string.Target_Date);
                        }
                        //差值小于零：Since
                        else{
                            untilOrSince = context.getString(R.string.Since);
                            startDateOrTargetDate = context.getString(R.string.Start_Date);

                        }
                    }

                }
                //如果设置的月与日历月不相等
                else{
                    diffValue = this.month - calMonth;
                    //如果设置的月大于日历月
                    if(this.month - calMonth > 0){
                        untilOrSince = context.getString(R.string.Until);
                        startDateOrTargetDate = context.getString(R.string.Target_Date);
                        //计算最前月剩余多少天
                        daysInMonthFront = daysOfMonthCommonYear[calMonth-1] - calDay;
                        //计算最后月过了多少天
                        daysInMonthRear = this.day;
                        //计算中间有多少天
                        for(int i = 0; i<this.month-calMonth-1;i++){
                            daysInBetween += daysOfMonthCommonYear[i+calMonth];
                        }
                        absDiffValue = daysInMonthFront + daysInMonthRear + daysInBetween;
                    }
                    //如果设置的月小于日历月
                    else{
                        untilOrSince = context.getString(R.string.Since);
                        startDateOrTargetDate = context.getString(R.string.Start_Date);
                        //计算最前月剩余多少天
                        daysInMonthFront = daysOfMonthCommonYear[this.month-1] - this.day;
                        //计算最后月过了多少天
                        daysInMonthRear = calDay;
                        //计算中间有多少天
                        for(int i = 0; i<calMonth - this.month - 1;i++){
                            daysInBetween += daysOfMonthCommonYear[i+this.month];
                        }
                        absDiffValue = daysInMonthFront + daysInMonthRear + daysInBetween;
                    }

                }
            }
            //如果设置的年与日历年不相等
            else{
                //如果日历年是闰年
                if(((calYear % 4 == 0)&&(calYear % 100 != 0))||(calYear%400 == 0)){
                    //如果设置的年大于日历年
                    if(this.year > calYear){
                        untilOrSince = context.getString(R.string.Until);
                        startDateOrTargetDate = context.getString(R.string.Target_Date);
                        //设置的年的月过了多少天
                        daysInMonthRear = this.day;
                        //日历的年的月还剩多少天
                        daysInMonthFront = daysOfMonthLeapYear[calMonth - 1] - calDay;
                        //设置年过了多少月
                        for(int i = 0 ; i<this.month-1 ;i++){
                            daysInYearRear += daysOfMonthCommonYear[i];
                        }
                        //日历的年还剩多少月
                        for(int i = 0 ; i<12 - calMonth; i++){
                            daysInYearFront += daysOfMonthLeapYear[calMonth+i];
                        }
                        //设置年与日历年中间年的天数
                        for(int i = 1 ; i< this.year - calYear;i++){
                            //判断中间年是平年还是闰年
                            //如果中间年为闰年
                            if((((calYear+i) % 4 == 0)&&((calYear+i) % 100 != 0))||((calYear+i)%400 == 0)){
                                daysBetweenYears+= 366;
                            }
                            //中间年为平年
                            else{
                                daysBetweenYears+= 365;
                            }
                        }
                        //最后把所有天数加起来
                        absDiffValue = daysInMonthFront + daysInMonthRear + daysInYearFront +daysInYearRear+
                                daysBetweenYears;

                    }
                    //如果设置的年小于日历年
                    else{
                        untilOrSince = context.getString(R.string.Since);
                        startDateOrTargetDate = context.getString(R.string.Start_Date);
                        //设置的年的月还剩多少天
                        daysInMonthFront = daysOfMonthCommonYear[this.month - 1] - this.day;
                        //日历的年的月过了多少天
                        daysInMonthRear = calDay;
                        //设置的年还剩多少月
                        for(int i = 0 ; i<12 - this.month; i++){
                            daysInYearFront += daysOfMonthCommonYear[this.month+i];
                        }
                        //日历的年过了多少月
                        for(int i = 0 ; i<calMonth-1 ;i++){
                            daysInYearRear += daysOfMonthLeapYear[i];
                        }
                        //设置年与日历年中间年的天数
                        for(int i = 1 ; i<  calYear - this.year ;i++){
                            //判断中间年是平年还是闰年
                            //如果中间年为闰年
                            if((((this.year+i) % 4 == 0)&&((this.year+i) % 100 != 0))||((this.year)+i%400 == 0)){
                                daysBetweenYears+= 366;
                            }
                            //中间年为平年
                            else{
                                daysBetweenYears+= 365;
                            }
                        }
                        //最后把所有天数加起来
                        absDiffValue = daysInMonthFront + daysInMonthRear + daysInYearFront +daysInYearRear+
                                daysBetweenYears;
                    }
                }
                //如果日历年不是闰年
                else{
                    //如果设置年大于日历年
                    if(this.year > calYear){
                        untilOrSince = context.getString(R.string.Until);
                        startDateOrTargetDate = context.getString(R.string.Target_Date);
                        //设置的年的月过了多少天
                        daysInMonthRear = this.day;
                        //日历的年的月还剩多少天
                        daysInMonthFront = daysOfMonthCommonYear[calMonth - 1] - calDay;
                        //设置年过了多少月
                        for(int i = 0 ; i<this.month-1 ;i++){
                            daysInYearRear += daysOfMonthCommonYear[i];
                        }
                        //日历的年还剩多少月
                        for(int i = 0 ; i<12 - calMonth; i++){
                            daysInYearFront += daysOfMonthCommonYear[calMonth+i];
                        }
                        //设置年与日历年中间年的天数
                        for(int i = 1 ; i< this.year - calYear;i++){
                            //判断中间年是平年还是闰年
                            //如果中间年为闰年
                            if((((calYear+i) % 4 == 0)&&((calYear+i) % 100 != 0))||((calYear+i)%400 == 0)){
                                daysBetweenYears+= 366;
                            }
                            //中间年为平年
                            else{
                                daysBetweenYears+= 365;
                            }
                        }
                        //最后把所有天数加起来
                        absDiffValue = daysInMonthFront + daysInMonthRear + daysInYearFront +daysInYearRear+
                                daysBetweenYears;


                    }
                    //如果设置年小于日历年
                    else{
                        untilOrSince = context.getString(R.string.Since);
                        startDateOrTargetDate = context.getString(R.string.Start_Date);
                        //设置的年的月还剩多少天
                        daysInMonthFront = daysOfMonthCommonYear[this.month - 1] - this.day;
                        //日历的年的月过了多少天
                        daysInMonthRear = calDay;
                        //设置的年还剩多少月
                        for(int i = 0 ; i<12 - this.month; i++){
                            daysInYearFront += daysOfMonthCommonYear[this.month+i];
                        }
                        //日历的年过了多少月
                        for(int i = 0 ; i<calMonth-1 ;i++){
                            daysInYearRear += daysOfMonthCommonYear[i];
                        }
                        //设置年与日历年中间年的天数
                        for(int i = 1 ; i<  calYear - this.year ;i++){
                            //判断中间年是平年还是闰年
                            //如果中间年为闰年
                            if((((this.year+i) % 4 == 0)&&((this.year+i) % 100 != 0))||((this.year+i)%400 == 0)){
                                daysBetweenYears+= 366;
                            }
                            //中间年为平年
                            else{
                                daysBetweenYears+= 365;
                            }
                        }
                        //最后把所有天数加起来
                        absDiffValue = daysInMonthFront + daysInMonthRear + daysInYearFront +daysInYearRear+
                                daysBetweenYears;

                    }

                }


            }

        }

    }

}


