package com.neil.easywords;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.neil.easywords.base.BaseRecyclerViewAdapter;

/**
 * Created by neil on 17/4/21.
 */

public class ForgetWordsHistoryAdapter extends BaseRecyclerViewAdapter<ForgetWordsBean>{
    public ForgetWordsHistoryAdapter(Context mContext, OnItemClickListener onItemClickListener) {
        super(mContext);
        setItemClickListener(onItemClickListener);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_forget_words,parent,false);
        return new ForgetWordsHistoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ForgetWordsHistoryViewHolder mViewHolder= (ForgetWordsHistoryViewHolder) holder;
        mViewHolder.mTextWords.setText(getList().get(position).getWords()+"");
        mViewHolder.mTextForgetCount.setText(getList().get(position).getForgetCount()+"");
        onItemClickListener(mViewHolder,position);
    }

    class ForgetWordsHistoryViewHolder extends RecyclerView.ViewHolder{
        TextView mTextWords;
        TextView mTextForgetCount;
        public ForgetWordsHistoryViewHolder(View itemView) {
            super(itemView);
            mTextWords = (TextView)itemView.findViewById(R.id.words);
            mTextForgetCount = (TextView)itemView.findViewById(R.id.forgetCounts);
        }
    }
}
