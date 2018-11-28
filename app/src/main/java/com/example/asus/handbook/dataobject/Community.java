package com.example.asus.handbook.dataobject;

import android.os.Handler;
import android.os.Message;

import java.util.List;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

public class Community extends BmobObject {
    private String statecontent;
    private String username;
    private BmobFile stateimage;

    public String getStatecontent(){
        return statecontent;
    }

    public void setStatecontent(String statecontent){
        this.statecontent = statecontent;
    }

    public String getUsername(){
        return username;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public String getStateimage(){
        return stateimage.getFileUrl();
    }

}
