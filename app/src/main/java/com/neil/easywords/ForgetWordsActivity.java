package com.neil.easywords;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.neil.easywords.base.BaseRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

import static com.neil.easywords.ComConstans.INTENT_FORGET_TO_DETAIL;
import static com.neil.easywords.ComConstans.INTENT_MAIN_TO_HISTORY;

/**
 * Created by neil on 17/4/19.
 */

public class ForgetWordsActivity extends AppCompatActivity implements BaseRecyclerViewAdapter
        .OnItemClickListener {
    RecyclerView mRecyclerHistory;
    ForgetWordsHistoryAdapter mForgetWordsHistoryAdapter;
    List<ForgetWordsBean> forgetWordsBeanList = new ArrayList<>();
    ForgetWordsBeanDao forgetWordsBeanDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgetwords);
        mRecyclerHistory = (RecyclerView) findViewById(R.id.recyclerForgetWords);
        DaoSession daoSession = ((App) getApplication()).getDaoSession();
        forgetWordsBeanDao = daoSession.getForgetWordsBeanDao();
        initData();
        initView();
    }

    private void initData() {
        Intent intent = getIntent();
        if (intent.hasExtra(INTENT_MAIN_TO_HISTORY)) {
            List<ForgetWordsBean> forgetWordsBeanListall = forgetWordsBeanDao.queryBuilder()
                    .orderDesc(ForgetWordsBeanDao.Properties.ForgetCount).list();
            if (!forgetWordsBeanListall.isEmpty()) {
                forgetWordsBeanList.addAll(forgetWordsBeanListall);
            }
        }
    }

    private void initView() {
        mForgetWordsHistoryAdapter = new ForgetWordsHistoryAdapter(this, this);
        mRecyclerHistory.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerHistory.setItemAnimator(new DefaultItemAnimator());
        mForgetWordsHistoryAdapter.setData(forgetWordsBeanList);
        mRecyclerHistory.setAdapter(mForgetWordsHistoryAdapter);
        mForgetWordsHistoryAdapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(View view, int position) {
        Intent intent = new Intent(this, ForgetWordsDetailActivity.class);
        intent.putExtra(INTENT_FORGET_TO_DETAIL, forgetWordsBeanList.get(position).getWords());
        startActivity(intent);
        Log.d("click position", position + ":" + forgetWordsBeanList.get(position).getWords());
    }
}
