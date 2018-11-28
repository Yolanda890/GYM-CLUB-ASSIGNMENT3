package com.example.asus.handbook.dataobject;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobFile;


public class MyUser extends BmobUser {
    private char sex;
    private Integer age;
    private Double remain_money;
    private BmobFile figureimage;

    public String getFigureimage(){
        return this.figureimage.getFileUrl();
    }

    public char getSex() {
        return this.sex;
    }

    public void setSex(char sex) {
        this.sex = sex;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Double getMoney() {
        return remain_money;
    }

    public void setMoney(Double money) {
        this.remain_money = money;
    }
}
