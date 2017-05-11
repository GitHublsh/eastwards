package com.neil.easywords;

import java.util.List;

import static com.neil.easywords.BaseApplication.getDaoInstant;

/**
 * Created by neil on 17/4/19.
 */

public class ForgetWordsDao {
    //添加数据(单个)
    public static void insertForgetWords(ForgetWordsBean forgetWordsBean) {
        getDaoInstant().getForgetWordsBeanDao().insert(forgetWordsBean);
    }

    //添加数据（单个）
    public static void insertOrReplaceForgetWords(ForgetWordsBean forgetWordsBean) {
        getDaoInstant().getForgetWordsBeanDao().insertOrReplace(forgetWordsBean);
    }

    //添加数据（多个）
    public static void insertListForgetWords(List<ForgetWordsBean> listforgetWordsBean) {
        getDaoInstant().getForgetWordsBeanDao().insertInTx(listforgetWordsBean);
    }

    //添加数据（多个）
    public static void insertOrReplaceForgetWords(List<ForgetWordsBean> listforgetWordsBean) {
        getDaoInstant().getForgetWordsBeanDao().insertOrReplaceInTx
                (listforgetWordsBean);
    }

    //更新数据(单个)
    public static void updateForgetWords(ForgetWordsBean forgetWordsBean) {
        getDaoInstant().getForgetWordsBeanDao().insertOrReplace(forgetWordsBean);
    }
    //更新数据（多个）

    //删除数据（单个）
    public static void deleteForgetWords(ForgetWordsBean forgetWordsBean) {
        getDaoInstant().getForgetWordsBeanDao().delete(forgetWordsBean);
    }

    //删除数据（多个）
    public static void deleteListForgetWords(List<ForgetWordsBean> listforgetWordsBean) {
        getDaoInstant().getForgetWordsBeanDao().deleteInTx(listforgetWordsBean);
    }

    //查询数据（全部）
    public static List<ForgetWordsBean> queryAll() {
        return getDaoInstant().getForgetWordsBeanDao().loadAll();
    }

    //查询数据（全部）
    public static List<ForgetWordsBean> queryAllData() {
        return getDaoInstant().getForgetWordsBeanDao().queryBuilder().list();
    }

    //根据忘记次数查询
    public static List<ForgetWordsBean> queryAllByCondition(String count) {
        return getDaoInstant().getForgetWordsBeanDao().queryBuilder().where
                (ForgetWordsBeanDao.Properties.ForgetCount.eq(count)).list();
    }

    //根据单词名查询
    public static List<ForgetWordsBean> queryAllByWords(String words) {
        return getDaoInstant().getForgetWordsBeanDao().queryBuilder().where
                (ForgetWordsBeanDao.Properties.Words.eq(words)).list();
    }

    //查询（时间逆序）
    public static List<ForgetWordsBean> queryAllByTime() {
        return getDaoInstant().getForgetWordsBeanDao().queryBuilder().orderAsc
                (ForgetWordsBeanDao.Properties.Words).list();
    }
}
