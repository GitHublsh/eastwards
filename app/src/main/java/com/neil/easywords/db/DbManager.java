package com.neil.easywords.db;

import android.content.Context;

import com.neil.easywords.DaoMaster;


/**
 * Created by neil on 17/5/9.
 */

public class DbManager {
    private final static String dbName = "forgetwords.db";
    private static DbManager mInstance;
    private DaoMaster.DevOpenHelper openHelper;
    private Context context;

    public DbManager(Context context) {
        this.context = context;
        openHelper = new DaoMaster.DevOpenHelper(context, dbName, null);
    }

    /**
     * 获取单例引用
     *
     * @param context
     * @return
     */
    public static DbManager getInstance(Context context) {
        if (mInstance == null)
            synchronized (DbManager.class) {
            if (mInstance == null) {
                mInstance = new DbManager(context);
            }
        }
        return mInstance;
    }
}
