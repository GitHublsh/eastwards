package com.neil.easywords;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;


public class MainActivity extends AppCompatActivity {

    private TextView mTextSearch;
    private TextView mTextDetail;
    private Button mButtonSearch;//查询按钮
    private ImageView mImageClear;
    private ImageView mImageOpen;//open
    private TextView mTextExample;
    private Button mButtonAdd;//添加忘记单词
    private ImageView mImageOpenEyes;
    private TextView mTextHistory;
    private ForgetWordsBeanDao forgetWordsBeanDao;
    WordsBean wordsBean = new WordsBean();
    ExampleBean exampleBean = new ExampleBean();

    final Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    wordsBean = (WordsBean) msg.obj;
                    String en_definitions = "";
                    if (wordsBean.getData().getEn_definitions() == null || wordsBean.getData()
                            .getEn_definition().getDefn() == null) {
                        mTextDetail.setText("我找不到了~");
                    } else {
                        if (wordsBean.getData().getEn_definitions().getN() != null) {
                            for (int i = 0; i < wordsBean.getData().getEn_definitions().getN()
                                    .size()

                                    ; i++) {
                                en_definitions = en_definitions + wordsBean.getData()
                                        .getEn_definitions().getN().get(i) + "\n";
                            }
                            mTextDetail.setText(
                                    wordsBean.getData().getContent() + "\n"
                                            + "/" + wordsBean.getData().getPronunciation() + "/"
                                            + "\n"
                                            + "英文释义：" + "\n" + en_definitions + "\n"
                                            + "中文释义：" + "\n" + wordsBean.getData()
                                            .getCn_definition()
                                            .getDefn() + "\n"
                            );
                            mTextExample.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
                            mTextExample.setVisibility(View.VISIBLE);
                        } else {
                            mTextDetail.setText(
                                    wordsBean.getData().getContent() + "\n"
                                            + "/" + wordsBean.getData().getPronunciation() + "/"
                                            + "\n"
                                            + "英文释义：" + "\n" + wordsBean.getData()
                                            .getEn_definition().getDefn() + "\n"
                                            + "中文释义：" + "\n" + wordsBean.getData()
                                            .getCn_definition()
                                            .getDefn() + "\n"
                            );
                            mTextExample.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
                            mTextExample.setVisibility(View.VISIBLE);
                        }

                    }
                    break;
                case 1:
                    mTextDetail.setText("我找不到了~");
                    break;
                case 2:
                    exampleBean = (ExampleBean) msg.obj;
                    Toast.makeText(MainActivity.this, "Example:" + exampleBean.getData().get(0)
                                    .getTranslation() + exampleBean.getData().get(0)
                                    .getAnnotation(),
                            Toast.LENGTH_SHORT).show();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DaoSession daoSession = ((App) getApplication()).getDaoSession();
        forgetWordsBeanDao = daoSession.getForgetWordsBeanDao();
        mTextSearch = (TextView) findViewById(R.id.text_search);
        mTextDetail = (TextView) findViewById(R.id.text_detail);
        mButtonSearch = (Button) findViewById(R.id.button_search);
        mImageClear = (ImageView) findViewById(R.id.image_clear);
        mTextExample = (TextView) findViewById(R.id.show_example);
        mButtonAdd = (Button) findViewById(R.id.button_add);
        mImageOpenEyes = (ImageView) findViewById(R.id.imgOpen);
        mTextHistory = (TextView) findViewById(R.id.texthistory);
        mImageOpen = (ImageView) findViewById(R.id.imgOpen);

        mTextExample.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "就不给你看~", Toast.LENGTH_SHORT).show();
                if (new NetUtil().isNetworkAvailable(MainActivity.this.getApplicationContext())) {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            String wordsID = String.valueOf(wordsBean.getData().getId());
                            String url = APIComConstans.getWordsExampleAPI + wordsID;
                            String wordsJson = null;
                            try {
                                wordsJson = new NetUtil().getRequest(url);
                            } catch (Exception e) {

                            }
                            //解析返回的json
                            Gson gson = new Gson();
                            exampleBean = gson.fromJson(wordsJson, ExampleBean.class);
                            if (exampleBean.getStatus_code() == 0) {
                                Message msg = handler.obtainMessage();
                                msg.what = 2;
                                msg.obj = exampleBean;
                                handler.sendMessage(msg);
                            } else if (exampleBean.getStatus_code() == 1) {
                                Message msg = handler.obtainMessage();
                                msg.what = 1;
                                handler.sendEmptyMessage(msg.what);
                            }
                        }
                    }).start();
                } else {
                    Toast.makeText(MainActivity.this, "网络开小差啦~", Toast.LENGTH_SHORT).show();
                }
            }
        });

        mImageClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTextSearch.setText("");
                mTextDetail.setText("");
                mImageClear.setVisibility(View.GONE);
                mTextExample.setVisibility(View.GONE);
            }
        });

        mTextSearch.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mImageClear.setVisibility(View.VISIBLE);
            }

        });
        mTextSearch.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {

                    mImageClear.setVisibility(View.VISIBLE);
                } else {
                    mImageClear.setVisibility(View.GONE);
                }
            }
        });
        mButtonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**
                 *隐藏键盘
                 */

                InputMethodManager inputMethodManager = (InputMethodManager) MainActivity.this
                        .getApplicationContext().
                                getSystemService(Context.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(mTextDetail.getWindowToken(), 0); //隐藏

                if (new NetUtil().isNetworkAvailable(MainActivity.this.getApplicationContext())) {
                    if (mTextDetail.getText() != null) {

                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                String words = "";
                                if (mTextSearch.getText() != null) {
                                    words = mTextSearch.getText().toString();
                                }
                                String url = APIComConstans.getWordsAPI + words;
                                String wordsJson = null;
                                try {
                                    wordsJson = new NetUtil().getRequest(url);
                                } catch (Exception e) {
                                }
                                //解析返回的json
                                Gson gson = new Gson();
                                wordsBean = gson.fromJson(wordsJson, WordsBean.class);
                                if (wordsBean.getStatus_code() == 0) {
                                    Message msg = handler.obtainMessage();
                                    msg.what = 0;
                                    msg.obj = wordsBean;
                                    handler.sendMessage(msg);
                                } else if (wordsBean.getStatus_code() == 1) {
                                    Message msg = handler.obtainMessage();
                                    msg.what = 1;
                                    handler.sendEmptyMessage(msg.what);
                                }
                            }
                        }).start();
                    } else {
                        Toast.makeText(MainActivity.this, "我找不到了~", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "网络开小差啦~", Toast.LENGTH_SHORT).show();
                }
            }
        });
        mButtonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ((mTextSearch.getText().toString()).isEmpty()) {
                    Toast.makeText(MainActivity.this, "输入内容为空", Toast.LENGTH_SHORT).show();
                } else {
                    ForgetWordsBean forgetWordsBean = new ForgetWordsBean();
                    forgetWordsBean.setWords(mTextSearch.getText().toString());
                    forgetWordsBean.setForgetCount(1);
                    forgetWordsBean.setId(System.currentTimeMillis());
                    forgetWordsBean.setStatus(true);
                    if (forgetWordsBeanDao.queryBuilder().where(ForgetWordsBeanDao.Properties
                            .Words.eq(mTextSearch.getText().toString())).list().isEmpty()) {
                        forgetWordsBeanDao.insert(forgetWordsBean);
                    } else {
                        int count = forgetWordsBeanDao.queryBuilder().where(ForgetWordsBeanDao
                                .Properties.Words.eq(mTextSearch.getText().toString())).list()
                                .get(0).getForgetCount();
                        forgetWordsBean.setId(forgetWordsBeanDao.queryBuilder().where
                                (ForgetWordsBeanDao.Properties.Words.eq(mTextSearch.getText()
                                        .toString())).list().get(0).getId());
                        forgetWordsBean.setForgetCount(++count);
                        forgetWordsBeanDao.insertOrReplace(forgetWordsBean);
                        Log.d("update forgetCount", forgetWordsBean.getForgetCount() + "");
                    }
                    Log.d("DaoExample", "Inserted new words, ID: " + forgetWordsBean.getId());
                }
            }
        });
        mTextHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ForgetWordsActivity.class);
                intent.putExtra(ComConstans.INTENT_MAIN_TO_HISTORY, "userid");// TODO: 17/4/21
                // 添加登录后，根据userID查询
                startActivity(intent);
            }
        });

        mImageOpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://translate.google.cn/"));
                startActivity(intent);
            }
        });
    }
}
