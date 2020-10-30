package com.gexu.dayscountdown;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

@Database(entities = {DateAndEvent.class} , version = 2 , exportSchema = false)
public abstract class MyDatabase extends RoomDatabase {
    //set Singleton/ 只允许生成一个myDataBase对象
    private static MyDatabase myDatabase;
    static synchronized MyDatabase getMyDatabase(Context context){
        if (myDatabase == null){
            myDatabase = Room.databaseBuilder(context.getApplicationContext(),MyDatabase.class,"my_database")
                    .allowMainThreadQueries()
                    .build();
        }
        return myDatabase;
    }
    //关联myDao
    public abstract MyDao getDao();

//    static final Migration MIGRATION_1_2 = new Migration(1,2) {
//        @Override
//        public void migrate(@NonNull SupportSQLiteDatabase database) {
//            database.execSQL("ALTER TABLE dateAndEvent ADD COLUMN date String NOT NULL DEFAULT 20201028");
//        }
//    };

}
