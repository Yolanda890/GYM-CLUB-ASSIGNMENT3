package com.example.asus.handbook.dataobject;

import cn.bmob.v3.BmobObject;

public class SC extends BmobObject {
    private String username;
    private String coursename;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCoursename() {
        return coursename;
    }

    public void setCoursename(String coursename) {
        this.coursename = coursename;
    }

}
