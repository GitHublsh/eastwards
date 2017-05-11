package com.neil.easywords;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by neil on 17/3/24.
 */

public class SecondActivity extends AppCompatActivity {
    public final String TAG = "RxJava";
    WordsBean wordsBean = new WordsBean();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
//        hello("s","e","o");
        wordsBean.setMsg("Hello World!");
//        initTask();
        InitRxjavaTask();
        Log.d(TAG, Thread.currentThread().getName());
    }

    private void InitRxjavaTask() {
        Observable.create(new ObservableOnSubscribe<WordsBean>() {
            @Override
            public void subscribe(ObservableEmitter<WordsBean> e) throws Exception {
                Log.d(TAG, Thread.currentThread().getName());
                e.onNext(wordsBean);
                e.onComplete();
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<WordsBean>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, "subscribe");
            }

            @Override
            public void onNext(WordsBean wordsBean) {
                Log.d(TAG, "" + wordsBean.getMsg());
                Log.d(TAG, Thread.currentThread().getName());
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "error");
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "complete");
            }
        });
    }

    private void initTask() {

        //创建Observable
        Observable<WordsBean> observable = Observable.create(new ObservableOnSubscribe<WordsBean>
                () {
            @Override
            public void subscribe(ObservableEmitter<WordsBean> emitter) throws Exception {
                emitter.onNext(wordsBean);
                emitter.onComplete();
            }
        });
        //创建一个下游 Observer
        Observer<WordsBean> observer = new Observer<WordsBean>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, "subscribe");
            }

            @Override
            public void onNext(WordsBean value) {
                Log.d(TAG, "" + value.getMsg());
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "error");
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "complete");
            }
        };
        //建立连接
        observable.subscribe(observer);
    }

//    public static void hello(String... names) {
//        Observable.create(new ObservableOnSubscribe<Integer>() {
//            @Override
//            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
//                emitter.onNext(1);
//            }
//        }).map(new Function<Integer, String>() {
//            @Override
//            public String apply(Integer integer) throws Exception {
//                return "This is result " + integer;
//            }
//        }).subscribe(new Consumer<String>() {
//            @Override
//            public void accept(String s) throws Exception {
//                Log.d("s", s);
//            }
//        });
//    }

}
