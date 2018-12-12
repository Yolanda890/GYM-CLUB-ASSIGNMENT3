package com.example.asus.handbook.dataobject;

import java.io.File;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;
import cn.bmob.v3.listener.UploadFileListener;


public class MyUser extends BmobUser {
    private char sex;
    private Integer age;
    private Double remain_money;
    private BmobFile figureimage;

    public String getFigureimage(){
        return this.figureimage.getFileUrl();
    }

    public void setFigureimage(final MyUser newUser,String str){
        System.out.println(str);
        this.figureimage=new BmobFile(new File(str));

        this.figureimage.uploadblock(new UploadFileListener() {
            @Override
            public void done(BmobException e) {
                if(e==null){

                    BmobUser bmobUser = BmobUser.getCurrentUser();
                    newUser.update(bmobUser.getObjectId(),new UpdateListener() {
                        @Override
                        public void done(BmobException e) {
                            if(e==null){
                              System.out.println("设置成功");
                            }else{

                                System.out.println("设置失败");
                            }
                        }
                    });
                }else{

                    System.out.println("失败");
                }
            }
            @Override
            public void onProgress(Integer value) {
                // 返回的上传进度（百分比）
            }
        });

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
