package com.neil.easywords;

/**
 * Created by neil on 17/5/9.
 */

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

public class App extends Application {
    /** A flag to show how easily you can switch from standard SQLite to the encrypted SQLCipher. */
    public static final boolean ENCRYPTED = true;

    private DaoSession daoSession;

    @Override
    public void onCreate() {
        super.onCreate();

        //创建数据库db"
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "forgetwords.db", null);
        //获取可写数据库
        SQLiteDatabase db = helper.getWritableDatabase();
        //获取数据库对象
        DaoMaster daoMaster = new DaoMaster(db);
        //获取Dao对象管理者
        daoSession = daoMaster.newSession();
    }

    public DaoSession getDaoSession() {
        return daoSession;
    }
}
