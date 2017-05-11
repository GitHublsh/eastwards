package com.neil.easywords;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by neil on 17/5/9.
 */

public class ForgetWordsDetailActivity extends AppCompatActivity implements View.OnClickListener {

    TextView mTextWords;
    TextView mTextCn;
    TextView mTextEn;
    Button mButtonNotKnow;
    Button mButtonKnow;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgetwords_detail);
        initView();
        initIntentData();
        initListener();
    }

    private void initView() {
        mTextWords = (TextView) findViewById(R.id.forgetWords);
        mTextCn = (TextView) findViewById(R.id.cnDetail);
        mTextEn = (TextView) findViewById(R.id.enDetail);
        mButtonNotKnow = (Button) findViewById(R.id.notknow);
        mButtonKnow = (Button) findViewById(R.id.know);
    }

    private void initIntentData() {
        Intent intent = getIntent();
        if (!intent.getStringExtra(ComConstans.INTENT_FORGET_TO_DETAIL).isEmpty()) {
            mTextWords.setText(intent.getStringExtra(ComConstans.INTENT_FORGET_TO_DETAIL));
        }

    }

    private void initListener() {
        mButtonKnow.setOnClickListener(this);
        mButtonNotKnow.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.notknow:
                // TODO: 17/5/9 点击不认识，进行查询展示
                break;
            case R.id.know:
                //从忘记列表删除

                break;
            default:
                break;
        }

    }
}
