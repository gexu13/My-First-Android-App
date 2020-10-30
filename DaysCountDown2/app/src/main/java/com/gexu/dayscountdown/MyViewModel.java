package com.gexu.dayscountdown;

import android.app.Application;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class MyViewModel extends AndroidViewModel {
    private MyDao myDao;
    private LiveData<List<DateAndEvent>> dateAndEventList;


    public MyViewModel(@NonNull Application application) {
        super(application);
        MyDatabase myDatabase = MyDatabase.getMyDatabase(application);
        myDao = myDatabase.getDao();
        dateAndEventList = myDao.getAllDateAndEvent();
    }
    //对数据库进行操作
    //添加
    void InsertDateAndEvent (DateAndEvent...dateAndEvents){
        new InsertAsyncTask(myDao).execute(dateAndEvents);
    }
    //更新
    void UpdateDateAndEvent (DateAndEvent...dateAndEvents){
        new UpdateAsyncTask(myDao).execute(dateAndEvents);
    }
    //删除
    void DeleteDateAndEvent (DateAndEvent...dateAndEvents) {
        new DeleteAsyncTask(myDao).execute(dateAndEvents);
    }
    //删除全部
    void DeleteAllDateAndEvent (){
        new DeleteAllAsyncTask(myDao).execute();
    }
    //后台操作数据库插入
    static class InsertAsyncTask extends AsyncTask<DateAndEvent,Void,Void> {
        private MyDao myDao;
        public InsertAsyncTask(MyDao myDao){
            this.myDao = myDao;
        }
        @Override
        protected Void doInBackground(DateAndEvent... dateAndEvents) {
            myDao.InsertDateAndEvent(dateAndEvents);
            return null;
        }
    }
    //后台操作数据库更新
    static class UpdateAsyncTask extends AsyncTask<DateAndEvent,Void,Void>{
        private MyDao myDao;
        public UpdateAsyncTask(MyDao myDao){
            this.myDao = myDao;
        }
        @Override
        protected Void doInBackground(DateAndEvent... dateAndEvents) {
            myDao.UpdateDateAndEvent(dateAndEvents);
            return null;
        }
    }
    //后台操作数据库删除
    static class DeleteAsyncTask extends AsyncTask<DateAndEvent,Void,Void>{
        private MyDao myDao;
        public DeleteAsyncTask(MyDao myDao){
            this.myDao = myDao;
        }
        @Override
        protected Void doInBackground(DateAndEvent... dateAndEvents) {
            myDao.DeleteDateAndEvent(dateAndEvents);
            return null;
        }
    }
    //后台操作数据库清空
    static class DeleteAllAsyncTask extends AsyncTask<Void,Void,Void>{
        private MyDao myDao;
        public DeleteAllAsyncTask(MyDao myDao){
            this.myDao = myDao;
        }
        @Override
        protected Void doInBackground(Void...voids) {
            myDao.DeleteAllDateAndEvent();
            return null;
        }
    }

    public LiveData<List<DateAndEvent>> getDateAndEventList() {
        return dateAndEventList;
    }

    public void setDateAndEventList(LiveData<List<DateAndEvent>> dateAndEventList) {
        this.dateAndEventList = dateAndEventList;
    }

}

