package com.neil.easywords;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;

/**
 * Created by neil on 17/4/19.
 */
@Entity
public class ForgetWordsBean {
    @Id(autoincrement = true)
    private long id;
    @NotNull
    private String words;
    private int forgetCount;
    private boolean status;
    @Generated(hash = 1367394333)
    public ForgetWordsBean(long id, @NotNull String words, int forgetCount,
            boolean status) {
        this.id = id;
        this.words = words;
        this.forgetCount = forgetCount;
        this.status = status;
    }
    @Generated(hash = 1497668121)
    public ForgetWordsBean() {
    }
    public String getWords() {
        return this.words;
    }
    public void setWords(String words) {
        this.words = words;
    }
    public int getForgetCount() {
        return this.forgetCount;
    }
    public void setForgetCount(int forgetCount) {
        this.forgetCount = forgetCount;
    }
    public long getId() {
        return this.id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public boolean getStatus() {
        return this.status;
    }
    public void setStatus(boolean status) {
        this.status = status;
    }

}
